package test3.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;
import org.springframework.web.util.WebUtils;

import test3.web.form.TestForm;
import test3.web.view.EnumViewName;

@Controller
@SessionAttributes("testForm")
// @SessionAttributes(types = { TestSession.class, TestForm.class })
public class TestController2 extends WebApplicationObjectSupport {

	// �Z�b�V�����̈����p������̊m�F�p�itestForm��session�ɋ��邩�Amodel�ɋ��邩�j
	@RequestMapping(value = "/Test2")
	public String velocityTestConfirm(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {

		System.out.println("testForm=" + model.asMap().get("testForm"));

		return EnumViewName.VELOCITY_SESSION_TEST.getViewName();
	}

	// �Z�b�V�����Ǘ��Ώۂł���testForm���Amodel�ɂȂ��Ɨ�O����������̂ŁA����A�N�Z�X�p��ModelAttribute
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
	String velocitySpringBind(@Valid @ModelAttribute("testForm") TestForm testForm, BindingResult rs, Model model, HttpSession session) {

		if(rs.hasErrors()){
			rs.getAllErrors().stream().forEach(e-> System.out.println(e.getDefaultMessage()));
		}

		return EnumViewName.VELOCITY_SPRING_BIND_TEST.getViewName();

	}

	// FlashMap���g���i��ʑJ�ڌ�ɁA��ʑJ�ڌ��model��flashMap������Asession����flashMap�ɂ�������j
	@RequestMapping(value = "/TestFlash")
	public String testFlashMap(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {

		FlashMap flashMap = new FlashMap();
		flashMap.put("key", "value");

		FlashMapManager flashMapManager = new SessionFlashMapManager();
		WebUtils.setSessionAttribute(request, "flashMapManager", flashMapManager);
		flashMapManager.saveOutputFlashMap(flashMap, request, response);

		return EnumViewName.VELOCITY_SESSION_TEST.getViewName();
	}

	// model��flashMap������Asession����flashMap��������B�����[�h�����model�ɓ����Ă���flashMap��������j
	@RequestMapping(value = "/TestFlashConfirm")
	public String testFlashMapConfirm(HttpSession session, Model model) {

		return EnumViewName.VELOCITY_SESSION_TEST.getViewName();
	}
}
