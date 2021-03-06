package test3.web.controller;

import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.bean.TestBean;
import test3.web.form.JsonRequestForm;
import test3.web.form.JsonRequestForm.ErrorMessageObj;

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
		moge.put("moge1", "mogee1");
		String[] array = { "array1", "array2", "array3", "array4" };

		HashMap<String, Object> hoge = new HashMap<>();
		hoge.put("aaaa", "aaaa1");
		hoge.put("bbbb", moge);
		hoge.put("cccc", "aaaa3");
		hoge.put("dddd", "aaaa4");
		hoge.put("eeee", "aaaa5");
		hoge.put("ffff", array);
		return hoge;
	}

	// producesにjsonを指定すると、json形式で返却するようになる
	//
	// http://localhost:8080/hoge/RestJson
	@GetMapping(value = "/RestJson", produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> restJson() {

		HashMap<String, Object> moge = new HashMap<>();
		moge.put("moge1", "mogee1");
		String[] array = { "array1", "array2", "array3", "array4" };

		HashMap<String, Object> hoge = new HashMap<>();
		hoge.put("aaaa", "aaaa1");
		hoge.put("bbbb", moge);
		hoge.put("cccc", "aaaa3");
		hoge.put("dddd", "aaaa4");
		hoge.put("eeee", "aaaa5");
		hoge.put("ffff", array);
		return hoge;
	}

	// producesにjsonを指定すると、json形式で返却するようになる
	// @ModelAttributeを使うとGetのパラメータがformに格納される
	// BindingResultを指定すると、form.numに文字列が指定されても例外で落ちず、バインドエラー結果がBindingResultに格納される
	// ※BindingResultがないと例外を出力し、クライアントには400エラーを返す
	//
	// http://localhost:8080/hoge/RestJson2?str=test&num=77
	// http://localhost:8080/hoge/RestJson2?str=test&num=hoge
	@GetMapping(value = "/RestJson2", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonRequestForm restJson2(@Validated @ModelAttribute JsonRequestForm form, BindingResult bindingResult) {

		for (FieldError err : bindingResult.getFieldErrors()) {
			ErrorMessageObj aaa = form.new ErrorMessageObj();
			aaa.field = err.getField();
			aaa.message = err.getDefaultMessage();

			form.getErrors().add(aaa);
		}

		return form;
	}
}
