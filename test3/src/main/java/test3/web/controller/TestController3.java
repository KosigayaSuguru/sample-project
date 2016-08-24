package test3.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.web.form.TestFormDynamic;

@Controller
public class TestController3 extends WebApplicationObjectSupport {

	@RequestMapping(value = "/dynamicFormTest")
	public String dynamicFormTest(Model model) {
		return TestEnumView.TEST_VIEW_VELOCITY_DYNAMIC_FORM_TEST.getViewName();
	}

	@RequestMapping(value = "/dynamicFormTestConfirm")
	public String dynamicFormTestConfirm(@Valid @ModelAttribute("testForm") TestFormDynamic testForm, BindingResult rs, Model model) {
		return TestEnumView.TEST_VIEW_VELOCITY_DYNAMIC_FORM_TEST.getViewName();
	}
}
