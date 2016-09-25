package test3.app.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	MessageSource messageSource;

	// BeanValidatorのメッセージソースをmessageSourceにする
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}

	// Thymeleaf用の設定
	@Bean
	ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setMessageSource(messageSource);
		templateEngine.setTemplateEngineMessageSource(messageSource);
		return templateEngine;
	}

	@Bean
	ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(2);
		return viewResolver;
	}

	// Velocity用の設定
	@Bean
	VelocityConfigurer velocityConfig(){
		VelocityConfigurer velocityConfig = new VelocityConfigurer();
		velocityConfig.setResourceLoaderPath("/WEB-INF/velocity/");
		Map<String, Object> propertiesMap = new HashMap<>();
		propertiesMap.put("input.encoding", "UTF-8");
		propertiesMap.put("output.encoding", "UTF-8");
		velocityConfig.setVelocityPropertiesMap(propertiesMap);
		return velocityConfig;
	}

	@Bean
	VelocityViewResolver velocityViewResolver(){
		VelocityViewResolver resolver = new VelocityViewResolver();
		resolver.setCache(false);
		resolver.setPrefix("");
		resolver.setSuffix(".html");
		resolver.setContentType("text/html;charset=UTF-8");
		resolver.setExposeSpringMacroHelpers(true);
		resolver.setOrder(1);
		return resolver;
	}

}