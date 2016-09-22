package test3.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {

	// tomcatのシステムプロパティで環境を指定する(-Drunning.mode="release"見たいな感じ)
	@Value("${running.mode:local}")
	public String runningMode;

	@Value("${url.release}")
	public String urlRelease;
	@Value("${username.release}")
	public String usernameRelease;
	@Value("${password.release}")
	public String passwordRelease;

	@Value("${url.test}")
	public String urlTest;
	@Value("${username.test}")
	public String usernameTest;
	@Value("${password.test}")
	public String passwordTest;

	@Value("${url.local}")
	public String urlLocal;
	@Value("${username.local}")
	public String usernameLocal;
	@Value("${password.local}")
	public String passwordLocal;

	@Bean
	DriverManagerDataSource dataSourceTest() {
		return createDataSource();
	}

	DriverManagerDataSource createDataSource() {

		DriverManagerDataSource tmp = new DriverManagerDataSource();
		tmp.setDriverClassName("org.h2.Driver");

		switch (runningMode) {
		case "release":
			tmp.setUrl(urlRelease);
			tmp.setUsername(usernameRelease);
			tmp.setPassword(passwordRelease);
			break;

		case "test":
			tmp.setUrl(urlTest);
			tmp.setUsername(usernameTest);
			tmp.setPassword(passwordTest);
			break;

		case "local":
			tmp.setUrl(urlLocal);
			tmp.setUsername(usernameLocal);
			tmp.setPassword(passwordLocal);
			break;

		default:
			tmp.setUrl(urlLocal);
			tmp.setUsername(usernameLocal);
			tmp.setPassword(passwordLocal);
			break;
		}

		return tmp;
	}
}
