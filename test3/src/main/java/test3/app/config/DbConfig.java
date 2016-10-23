package test3.app.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("test3.db.mapper")
public class DbConfig {

	// tomcatのシステムプロパティで環境を指定する(-Dspring.profiles.active="release"見たいな感じ)
	@Value("${spring.profiles.active}")
	public String profile;

	@Value("${database.h2.url}")
	public String url;
	@Value("${database.h2.username}")
	public String username;
	@Value("${database.h2.password}")
	public String password;

	@Bean
	DriverManagerDataSource dataSourceH2() {
		return createDataSource();
	}

	DriverManagerDataSource createDataSource() {

		DriverManagerDataSource tmp = new DriverManagerDataSource();
		tmp.setDriverClassName("org.h2.Driver");

		tmp.setUrl(url);
		tmp.setUsername(username);
		tmp.setPassword(password);

		return tmp;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager tran = new DataSourceTransactionManager();
		tran.setDataSource(dataSourceH2());
		return tran;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSourceH2());
	}

	@Bean
	DriverManagerDataSource dataSourceTest2() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://host:3306/test");
		ds.setUsername("user");
		ds.setPassword("password");
		return ds;
	}

	@Bean
	SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSourceTest2());
		sessionFactoryBean.setConfigLocation(new ClassPathResource("/config/" + profile + "/mybatis-config.xml"));
		return sessionFactoryBean;
	}
}
