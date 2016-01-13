package test3;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

//���A�g���ĂȂ��iTestAspect.java�Ɉڍs�j
@Component
public class TestAdvice  implements MethodBeforeAdvice, AfterReturningAdvice {

	// ���\�b�h�Ăяo���O�Ƀ��b�Z�[�W���o�͂���
	public void before(Method method, Object[] args, Object target) throws Throwable {

		System.out.println("Before�A�h�o�C�X����̃��O�o�͂ł�");
	}

	// ���\�b�h�Ăяo����Ƀ��b�Z�[�W���o�͂���
	public void afterReturning(Object ret, Method method, Object[] args, Object target) throws Throwable {

		System.out.println("After�A�h�o�C�X����̃��O�o�͂ł�");
	}
}
