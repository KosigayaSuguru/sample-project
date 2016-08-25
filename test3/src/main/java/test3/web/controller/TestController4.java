package test3.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.TestBean;

@RestController
public class TestController4 extends WebApplicationObjectSupport {

	@RequestMapping("/RestTest")
	public TestBean dynamicFormTest() {

		TestBean bean = new TestBean();
		bean.setCatalinaHome("catalinaHome");
		bean.setHoge("hoge");
		bean.setMoge("moge");
		return bean;
	}
}
