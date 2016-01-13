package conf.test4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bean.test4.BeanB;

@Configuration
public class AppConfig2 {

	@Bean
	public BeanB beanb(){
		BeanB tmp = new BeanB();
		tmp.hoge1 = "hoge1tmpb2";
		tmp.hoge2 = "hoge2tmpb2";
		return tmp;

	}
}
