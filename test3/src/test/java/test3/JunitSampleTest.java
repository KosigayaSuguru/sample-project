package test3;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test3.bean.TestBean;
import test3.db.entity.TestEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
// @ContextConfiguration("test.xml")
public class JunitSampleTest {

	//インジェクションテスト
	@Autowired
	TestBean test;

	//インジェクションテスト
	@Autowired
	DataSource dataSourceTest;

	//インジェクションテスト
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//インジェクションテスト
	@Value("${url.release:orz}")
	String value;

	@Test
	public void testSample() {

		System.out.println(test);
		RowMapper<TestEntity> mapper = new BeanPropertyRowMapper<TestEntity>(TestEntity.class);
		List<TestEntity> dtoList = jdbcTemplate.query("select * from test", mapper);
		dtoList.forEach(aaa -> System.out.println(aaa));
	}
}
