package test3.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import test3.TestEnumStatus;
import test3.app.service.TestService;
import test3.bean.TestBean;
import test3.db.entity.TestEntity;
import test3.web.form.TestForm;
import test3.web.sesstion.TestSession;
import test3.web.view.EnumViewName;

// Sessionの確認はTestController2で出来る

// イメージ的には@ModelAttribute()で指定してる文字列(HTMLのformのname)をキー値にして、
// request(@SessionAttributes()で指定されてればセッションにも)にaddAttributeしてるような感じなのかな。。

// ModelAttritubteで指定されたオブジェクトは new で生成されているので、シングルトンにはならない

@Controller
@SessionAttributes(types = { TestSession.class, TestForm.class })
public class TestController1 extends WebApplicationObjectSupport {

	private TestService testService;

	@Autowired
	private TestBean testBean;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value(value = "${hoge.test}")
	private String hoge;

	@Value(value ="${spring.profiles.active}")
	private String spring_profiles_active;

	@Resource
	private MessageSource messageSource;

	@Value(value = "${catalina.home}")
	private String moge;

	@Value(value = "${test.hogehoge}")
	private String testHogehoge;

	@Value(value = "${test2.hogehoge}")
	private String test2Hogehoge;

	// @ModelAttributeのvalueに指定した値が属性名、returnが実体になって、modelにaddされる
	@ModelAttribute("modelAttributeTest1")
	public String modelAttributeTest1() {

		System.out.println("modelAttributeTest1");
		return "modelAttributeTest1";
	}

	// @ModelAttributeのvalueを指定しない場合、属性名がstringになる
	@ModelAttribute
	public String modelAttributeTest2() {

		System.out.println("modelAttributeTest2");
		return "modelAttributeTest2";
	}

	@ModelAttribute("testForm")
	public TestForm testForm() {

		System.out.println("testForm");
		return new TestForm();
	}

	@RequestMapping(value = "/Test")
	public String test(Locale locale, Model model) {

		System.out.println("TEST1");
		System.out.println("TEST2");

		// Get model attribute -<
		String modelAttributeTest1 = (String) model.asMap().get("modelAttributeTest1");
		// Get model attribute ->

		// Test spring DI getBean <-
		ApplicationContext context = getApplicationContext();
		InternalResourceViewResolver rsl = (InternalResourceViewResolver) context
				.getBean("org.springframework.web.servlet.view.InternalResourceViewResolver");
				// Test spring DI getBean ->

		// Test spring jdbcTemplete <-
		RowMapper<TestEntity> mapper = new BeanPropertyRowMapper<TestEntity>(TestEntity.class);
		List<TestEntity> dtoList = jdbcTemplate.query("select * from test", mapper);
		// Test spring jdbcTemplete ->

		transactional();

		// Test spring message <-
		String message = messageSource.getMessage("hoge.message1", null, locale);
		// Test spring message ->

		// Test spring model <-
		model.addAttribute("modelMessage1", "model.addAttribute()");
		// Test spring model ->

		// Test StreamAPI <-
		List<String> hoge = new ArrayList<String>();
		hoge.add("test01");
		hoge.add("test02");
		hoge.add("test03");

		hoge.forEach(aaa -> System.out.println(aaa));
		hoge.stream().filter(x -> x.equals("test01")).forEach(System.out::println);

		Arrays.stream(new String[] { "test11", "test12", "test13" }).forEach(System.out::println);
		// Test StreamAPI ->

		// Test ramda <-
		testBean.ramdaTest(x -> {
			System.out.println(x);
		});

		testBean.ramdaTestMyfunc(x -> {
			System.out.println(x);
		});
		// Test ramda ->

		// modelにThymeleafのforeachテスト用のデータ追加 -<
		model.addAttribute("listTest",
				Arrays.asList(new String[] { "list_test1", "list_test2", "list_test3", "list_test4" }));
		// modelにThymeleafのforeachテスト用のデータ追加 ->

		testService = new TestService();
		testService.service();

		// タイプセーフEnum（たまたま型が同じだった全然関係ない値との比較、代入等を防ぐ） -<
		String nextView = "";
		switch (testBean.getTestStatus()) {
		case TEST_STATUS1:
			// 列挙に対応したコード値が欲しい場合は↓みたいな感じ
			System.out.println(TestEnumStatus.TEST_STATUS1.getCode());

			// 処理結果に応じた次画面用のView名を取得する
			nextView = EnumViewName.TYMELEAF_SAMLE.getViewName();

			break;
		case TEST_STATUS2:
			// 取りあえず省略
			break;

		case TEST_STATUS3:
			// 取りあえず省略
			break;

		default:
			// 取りあえず省略
			break;
		}
		// タイプセーフEnum ->

		if (!model.containsAttribute("sessionTest")) {
			System.out.println("addSession");
			TestSession sess = new TestSession();
			sess.str1 = "TestSession.str1";
			model.addAttribute("sessionTest", sess);
		}
		return nextView;
	}

	@RequestMapping(value = "/TestVelocity")
	public String velocityTest(@ModelAttribute("testForm") TestForm form, Model model) {

		Logger hoge = LoggerFactory.getLogger(this.getClass());
		hoge.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		// VelocityViewの中で（#createVelocityContext）modelの値をVelocityContextにputAllしているので、
		// modelにaddAttirbuteすると、テンプレート上で使える
		model.addAttribute("listTest",
				Arrays.asList(new String[] { "list_test1", "list_test2", "list_test3", "list_test4" }));

		return EnumViewName.VELOCITY_SAMPLE.getViewName();
	}

	@RequestMapping(value = "/TestVelocityConfirm")
	public String velocityTestConfirm(@Validated @ModelAttribute("testForm") TestForm form, BindingResult result,
			Model model) {

		System.out.println("testForm=" + form);;

		// error_message.propertiesからコード値を元にメッセージを引っ張ってくる
		result.getAllErrors().stream().forEach(System.out::println);
		FieldError fieldErr = null;

		//name1はコード値を直接指定
		String errorName1 = messageSource.getMessage("NotEmpty.testForm.name1", null, Locale.getDefault());
		model.addAttribute("errorName1", errorName1);

		// name2はエラーオブジェクトから。エラーメッセージのコード値はspringが自動で生成したもの。
		// アノテーションで指定した値が{1},{2}で置換される（エラーオブジェクトのargmentsに格納される）
		if (((fieldErr = result.getFieldError("name2")) != null)) {
			String errorName2 = messageSource.getMessage(fieldErr, Locale.getDefault());
			model.addAttribute("errorName2", errorName2);
		}

		// name3はエラーオブジェクトから。エラーメッセージのコード値をアノテーションのmessageで指定。
		// ValidationMessages.propertiesから読み込む
		if (((fieldErr = result.getFieldError("name3")) != null)) {
			String errorName3 = messageSource.getMessage(fieldErr, Locale.getDefault());
			model.addAttribute("errorName3", errorName3);
		}

		// name4はエラーオブジェクトから。独自アノテーション。fieldで指定した値をプレースホルダーで表示。
		if (((fieldErr = result.getFieldError("name4")) != null)) {
			String errorName4 = messageSource.getMessage(result.getFieldError("name4"), Locale.getDefault());
			model.addAttribute("errorName4", errorName4);
		}

		return EnumViewName.VELOCITY_SAMPLE.getViewName();
	}

	public TestBean getTest() {
		return testBean;
	}

	@Autowired
	public void setTest(TestBean test) {
		this.testBean = test;
	}

	@Transactional
	public void transactional() {
		System.out.println("transactional()");
		RowMapper<TestEntity> mapper = new BeanPropertyRowMapper<TestEntity>(TestEntity.class);
		List<TestEntity> dtoList = jdbcTemplate.query("select * from test", mapper);
	}
}
