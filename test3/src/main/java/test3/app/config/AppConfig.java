package test3.app.config;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Properties;

import javax.jms.Session;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.hornetq.jms.client.HornetQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import test3.TestEnumStatus;
import test3.bean.TestBean;

@Configuration
@EnableJms
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
		PropertyPlaceholderConfigurer holder = new PropertyPlaceholderConfigurer();
		holder.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/*.properties"));
		holder.setFileEncoding("UTF-8");
		return holder;
	}

	@Bean
	HornetQJMSConnectionFactory hornetConnectionFactory() {

		String factoryClass = "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory";
		HashMap<String, Object> map = new HashMap<>();
		map.put("host", "localhost");
		map.put("port", "5445");
		TransportConfiguration config = new TransportConfiguration(factoryClass, map);

		HornetQJMSConnectionFactory hoge = new HornetQJMSConnectionFactory(false, config);
		return hoge;
	}

	@Bean
	CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory ret = new CachingConnectionFactory(hornetConnectionFactory());
		return ret;
	}

	@Bean
	JmsTemplate jmsTemplate() {
		JmsTemplate tmp = new JmsTemplate();
		tmp.setConnectionFactory(cachingConnectionFactory());
		tmp.setDefaultDestination(defaultDestination());
		tmp.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
		return tmp;
	}

	@Bean
	JmsMessagingTemplate jmsMessagingTemplate() {
		JmsMessagingTemplate tmp = new JmsMessagingTemplate();
		tmp.setJmsTemplate(jmsTemplate());
		return tmp;
	}

	@Bean
	HornetQQueue defaultDestination(){
		HornetQQueue tmp = new HornetQQueue("ExpiryQueue");
		return tmp;
	}

	// http://docs.spring.io/spring/docs/4.2.7.RELEASE/spring-framework-reference/html/jms.html#jms-annotated-support
	@Bean
	DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory tmp = new DefaultJmsListenerContainerFactory();
		tmp.setConnectionFactory(cachingConnectionFactory());
		tmp.setDestinationResolver(jmsTemplate().getDestinationResolver());
		tmp.setRecoveryInterval((long) 10000);
		tmp.setConcurrency("1-10");
		return tmp;
	}
}