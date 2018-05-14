package com.example.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest {

	@Async
	public Future<String> asyncMethod(int a) throws Exception {

		System.out.println(String.format("ThreadName:%s, async1: %d", Thread.currentThread().getName(), a));
		Thread.sleep(5000);
		System.out.println(String.format("ThreadName:%s, async2: %d", Thread.currentThread().getName(), a * a));

		return CompletableFuture.completedFuture(a + "");

	}
}
