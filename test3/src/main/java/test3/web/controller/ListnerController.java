package test3.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.web.listener.TestQueue;

@RestController
public class ListnerController extends WebApplicationObjectSupport {

	@Autowired
	TestQueue testQueue;

	@GetMapping("/ListenerTest")
	String listner() throws InterruptedException {

		testQueue.put("hello!");

		return "queued message";
	}
}
