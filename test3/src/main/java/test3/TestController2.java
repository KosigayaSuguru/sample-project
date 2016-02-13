package test3;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationObjectSupport;

@Controller
@SessionAttributes("testForm")
// @SessionAttributes(types = { TestSession.class, TestForm.class })
public class TestController2 extends WebApplicationObjectSupport {

	// セッションの引き継がれ方の確認用（testFormがsessionに居るか、modelに居るか）
	@RequestMapping(value = "/Test2")
	public String velocityTestConfirm(HttpSession session, Model model) {

		System.out.println("testForm=" + model.asMap().get("testForm"));

		return TestEnumView.TEST_VIEW_VELOCITY_SESSION_TEST.getViewName();
	}

	// セッション管理対象であるtestFormが、modelにないと例外が発生するので、初回アクセス用にModelAttribute
	@ModelAttribute("testForm")
	TestForm testForm(){
		TestForm test = new TestForm();
		test.setName1("test1");
		test.setName2("test2");
		test.setName3("test3");
		test.setName4("test4");

		return test;
	}

	@RequestMapping("/Test3")
	String velocitySpringBind(@Valid @ModelAttribute("testForm") TestForm testForm, BindingResult rs, Model model) {

		if(rs.hasErrors()){
			rs.getAllErrors().stream().forEach(e-> System.out.println(e.getDefaultMessage()));
		}

		return TestEnumView.TEST_VIEW_VELOCITY_SPRING_BIND_TEST.getViewName();

	}
}
