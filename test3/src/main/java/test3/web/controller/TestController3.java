package test3.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.web.form.TestFormDynamic;
import test3.web.url.Urls;
import test3.web.view.EnumViewName;

@Controller
public class TestController3 extends WebApplicationObjectSupport {

	@RequestMapping(value = Urls.DYNAMIC_FORM_TEST)
	public String dynamicFormTest(Model model) {

		return EnumViewName.VELOCITY_DYNAMIC_FORM_TEST.getViewName();
	}

	@RequestMapping(value = Urls.DYNAMIC_FORM_CONFIRM_TEST)
	public String dynamicFormTestConfirm(@Valid @ModelAttribute("testForm") TestFormDynamic testForm, BindingResult rs, Model model) {

		return EnumViewName.VELOCITY_DYNAMIC_FORM_TEST.getViewName();
	}
}
