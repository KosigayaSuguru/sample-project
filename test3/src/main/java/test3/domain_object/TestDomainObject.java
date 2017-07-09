package test3.domain_object;

import java.util.HashMap;

@SuppressWarnings("serial")
public class TestDomainObject extends HashMap<String, String> {

	public String hoge = "domainObject";

	public TestDomainObjectView1 forView1 = new TestDomainObjectView1();
	public TestDomainObjectView2 forview2 = new TestDomainObjectView2();

	public TestDomainObject() {

	}

	public TestDomainObject(TestDomainObject a) {
		super(a);
	}

	public class TestDomainObjectView1 {

		public String displayHoge() {
			return hoge;
		}
	}

	public class TestDomainObjectView2 {

		public String displayHoge() {
			return hoge + hoge;
		}
	}

}
