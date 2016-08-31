package test3.Exception.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

import test3.Exception.BaseException;
import test3.Exception.BaseRuntimeException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handler1(Exception ex, HandlerMethod method) {

		BaseException bex = (BaseException) ex;
		logging(bex);

		return "";
	}

	@ExceptionHandler(BaseRuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handler2(Exception ex, HandlerMethod method) {

		BaseRuntimeException bex = (BaseRuntimeException) ex;
		logging(bex);

		return "";
	}

	private void logging(BaseException bex){
		Logger log = LoggerFactory.getLogger(bex.getClass());

		switch (bex.getLevel()) {

		case DEBUG:
			log.debug(bex.getMessage(), bex);
			break;
		case ERROR:
			log.error(bex.getMessage(), bex);
			break;
		case INFO:
			log.info(bex.getMessage(), bex);
			break;
		case WARN:
			log.warn(bex.getMessage(), bex);
			break;
		case TRACE:
			log.trace(bex.getMessage(), bex);
			break;
		default:
			log.info(bex.getMessage(), bex);
			break;
		}
	}

	private void logging(BaseRuntimeException bex){
		Logger log = LoggerFactory.getLogger(bex.getClass());

		switch (bex.getLevel()) {

		case DEBUG:
			log.debug(bex.getMessage(), bex);
			break;
		case ERROR:
			log.error(bex.getMessage(), bex);
			break;
		case INFO:
			log.info(bex.getMessage(), bex);
			break;
		case WARN:
			log.warn(bex.getMessage(), bex);
			break;
		case TRACE:
			log.trace(bex.getMessage(), bex);
			break;
		default:
			log.info(bex.getMessage(), bex);
			break;
		}
	}
}
