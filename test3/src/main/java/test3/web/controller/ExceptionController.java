package test3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.Exception.BaseException;
import test3.Exception.BaseRuntimeException;
import test3.Exception.EnumExceptionLevel;
import test3.Exception.TestException;
import test3.Exception.TestRuntimeException;

@Controller
public class ExceptionController extends WebApplicationObjectSupport {

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

	@RequestMapping("/ExceptionTest4")
	public String test4() {

		try {
			throw new TestException();

		} catch (TestException e) {
			throw new TestRuntimeException(e);
		}
	}
}