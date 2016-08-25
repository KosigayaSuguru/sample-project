package test3.app.config;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import test3.TestBean;
import test3.TestEnumStatus;

@Component
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
		String[] basenames = { "classpath:hoge", "classpath:error_message" };
		messageSource.setBasenames(basenames);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}