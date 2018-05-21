package com.example.demo;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@Import({AsyncConfig.class, DbConfig.class})
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholder() throws Exception {

		String aabb = System.getProperty("aabb");
		System.err.println(aabb);
		String profile = System.getProperty("spring.profiles.active");

		PropertySourcesPlaceholderConfigurer holder = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources1 = new PathMatchingResourcePatternResolver().getResources("classpath:/config/common/*.properties");
		Resource[] resources2 = new PathMatchingResourcePatternResolver().getResources("classpath:/config/" + profile + "/*.properties");
		Resource[] addAll = (Resource[]) ArrayUtils.addAll(resources1, resources2);
		holder.setLocations(addAll);
		holder.setFileEncoding("UTF-8");
		return holder;
	}
}