package test3;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

//今、使ってない（TestAspect.javaに移行）
@Component
public class TestAdvice  implements MethodBeforeAdvice, AfterReturningAdvice {

	// メソッド呼び出し前にメッセージを出力する
	public void before(Method method, Object[] args, Object target) throws Throwable {

		System.out.println("Beforeアドバイスからのログ出力です");
	}

	// メソッド呼び出し後にメッセージを出力する
	public void afterReturning(Object ret, Method method, Object[] args, Object target) throws Throwable {

		System.out.println("Afterアドバイスからのログ出力です");
	}
}
