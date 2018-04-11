package tryrest.hoges;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InjectTest {

	String hoge;

	@PostConstruct
	void init() {
		this.hoge = "aaaaaaaaaaaa";
		System.err.println("sssssssssssssssssss");
	}

	@Override
	public String toString() {
		return "InjectTest [hoge=" + hoge + "]";
	}

}
