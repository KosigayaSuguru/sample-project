package test3.app.config;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import test3.TestEnumStatus;
import test3.bean.TestBean;

@Configuration
public class AppConfig {

	@Value(value = "${catalina.home}")
	String catalinaHome;

	@Bean
	TestBean testBean() {
		TestBean tmp = new TestBean();
		tmp.setHoge("DI_HOGE");
		tmp.setMoge("DI_MOGE");
		tmp.setCatalinaHome(catalinaHome);
		tmp.setTestStatus(TestEnumStatus.TEST_STATUS1);
		return tmp;
	}

	@Bean
	Properties test2Properties() {
		Properties p = new Properties();
		try (Reader r = new InputStreamReader(AppConfig.class.getResourceAsStream("/test2.properties"), "UTF-8");) {
			p.load(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Bean
	MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String[] basenames = { "classpath:message/hoge", "classpath:message/error_message" };
		messageSource.setBasenames(basenames);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	PropertyPlaceholderConfigurer propertyPlaceholder() throws Exception {

		String profile = System.getProperty("spring.profiles.active");

		// profileが指定されているかどうか（-Dspring.profiles.active="local"）
		if (StringUtils.isBlank(profile)) {
			System.err.println("set profile ex:-Dspring.profiles.active=local");
			System.exit(1);
		}

		// resourcesにprofile用のパスがあるかどうか
		ClassPathResource config = new ClassPathResource("classpath:/config/" + profile);
		if (config.exists()) {
			System.err.println("not exists config path : " + config.getPath());
			System.exit(1);
		}

		PropertyPlaceholderConfigurer holder = new PropertyPlaceholderConfigurer();
		holder.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/config/" + profile + "/*.properties"));
		holder.setFileEncoding("UTF-8");
		return holder;
	}
}