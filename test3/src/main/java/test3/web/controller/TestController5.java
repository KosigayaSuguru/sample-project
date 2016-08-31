package test3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.Exception.BaseException;
import test3.Exception.BaseRuntimeException;
import test3.Exception.EnumExceptionLevel;
import test3.Exception.TestException;

@Controller
public class TestController5 extends WebApplicationObjectSupport {

	@SuppressWarnings("unused")
	@RequestMapping("/ExceptionTest1")
	public String test1() throws BaseException {

		if (true) {
			throw new BaseException("ExceptionTest", EnumExceptionLevel.INFO);
		}
		return "";
	}

	@SuppressWarnings("unused")
	@RequestMapping("/ExceptionTest2")
	public String test2() {

		if (true) {
			throw new BaseRuntimeException("ExceptionTest");
		}
		return "";
	}

	@SuppressWarnings("unused")
	@RequestMapping("/ExceptionTest3")
	public String test3() throws TestException {

		if (true) {
			throw new TestException(EnumExceptionLevel.DEBUG);
		}
		return "";
	}
}