package test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
public class TestController extends WebApplicationObjectSupport {

	private TestService testService;

	private TestBean testBean;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value(value = "${hoge.test}")
	private String hoge;

	@Resource
	private MessageSource hogeMessages;

	@Value(value = "${catalina.home}")
	private String moge;

	@Value(value = "${test.hogehoge}")
	private String mage;

	@RequestMapping(value = "/Test")
	public String test(Locale locale, Model model) {
		System.out.println("TEST1");
		System.out.println("TEST2");

		// Test spring DI getBean <-
		ApplicationContext context = getApplicationContext();
		InternalResourceViewResolver rsl = (InternalResourceViewResolver) context
				.getBean("org.springframework.web.servlet.view.InternalResourceViewResolver");
				// Test spring DI getBean ->

		// Test spring jdbcTemplete <-
		RowMapper<Test> mapper = new BeanPropertyRowMapper<Test>(Test.class);
		List<Test> dtoList = jdbcTemplate.query("select * from test", mapper);
		// Test spring jdbcTemplete ->

		transactional();

		// Test spring message <-
		String message = hogeMessages.getMessage("hoge.message1", null, locale);
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

		model.addAttribute("listTest",
				Arrays.asList(new String[] { "list_test1", "list_test2", "list_test3", "list_test4" }));

		testService = new TestService();
		testService.service();
		return "hoge";
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
		RowMapper<Test> mapper = new BeanPropertyRowMapper<Test>(Test.class);
		List<Test> dtoList = jdbcTemplate.query("select * from test", mapper);
	}
}
