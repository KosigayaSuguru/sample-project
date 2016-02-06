package test3;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationObjectSupport;

@Controller
@SessionAttributes(value = "testForm")
public class TestController2 extends WebApplicationObjectSupport {

	@RequestMapping(value = "/Test2")
	public String velocityTestConfirm(HttpSession session, Model model) {

		System.out.println("testForm=" + model.asMap().get("testForm"));

		return "";
	}
}
