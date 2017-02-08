package test3.web.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.bean.TestBean;

@RestController
public class ApiController extends WebApplicationObjectSupport {

	@RequestMapping("/RestTest1")
	public TestBean restTest1_1() {

		TestBean bean = new TestBean();
		bean.setCatalinaHome("catalinaHome");
		bean.setHoge("hoge");
		bean.setMoge("moge");
		return bean;
	}

	@RequestMapping(value = "/RestTest1", params = "ret=csv")
	public String restTest1_2() {

		return "hoge,hoge,hoge";
	}

	@RequestMapping(value = "/RestTest1", params = "ret=tsv")
	public String restTest1_3() {

		return "moge\tmoge\tmoge";
	}

	@RequestMapping("/RestTest2")
	public HashMap<String, Object> restTest2() {

		HashMap<String, Object> moge = new HashMap<>();
		moge.put("moge1","mogee1");
		String[] array = {"array1","array2","array3","array4"};


		HashMap<String, Object> hoge = new HashMap<>();
		hoge.put("aaaa","aaaa1");
		hoge.put("bbbb",moge);
		hoge.put("cccc","aaaa3");
		hoge.put("dddd","aaaa4");
		hoge.put("eeee","aaaa5");
		hoge.put("ffff",array);
		return hoge;
	}
}
