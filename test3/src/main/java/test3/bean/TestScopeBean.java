package test3.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class TestScopeBean {

	public String hoge = "hogehoge";
	public String moge = "mogemoge";

	@Override
	public String toString() {
		return "TestScopeBean [hoge=" + hoge + ", moge=" + moge + "]";
	}

	public String getHoge() {
		return hoge;
	}

	public String getMoge() {
		return moge;
	}

	public void setHoge(String hoge) {
		this.hoge = hoge;
	}

	public void setMoge(String moge) {
		this.moge = moge;
	}
}
