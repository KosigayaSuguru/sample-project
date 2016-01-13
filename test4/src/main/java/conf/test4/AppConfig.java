package conf.test4;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import bean.test4.BeanA;

@Configuration
@ComponentScan("*.test4")
public class AppConfig {

	@Bean
	public BeanA beana(){
		BeanA tmp = new BeanA();
		tmp.hoge1 = "hoge1tmpa1";
		tmp.hoge2 = "hoge2tmpa1";
		return tmp;
	}

	@Bean
	public Properties properties(){
        Properties p = new Properties();
        try{
            Reader r = new InputStreamReader(AppConfig.class.getResourceAsStream("hoge_ja_JP.properties"),"UTF-8");
            p.load(r);
            r.close();
        } catch(Exception e){
        	e.printStackTrace();

        }

		return p;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messages(){
		ReloadableResourceBundleMessageSource m = new ReloadableResourceBundleMessageSource();
		m.setBasename("hoge");
		m.setDefaultEncoding("UTF-8");
		return m;
	}
}
