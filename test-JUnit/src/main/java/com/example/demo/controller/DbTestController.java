package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.TestTable;
import com.example.demo.models.TestTableRepository;

@RestController
public class DbTestController {

	@Autowired
	TestTableRepository hogeRepository;

	@GetMapping("/dbtest")
	public TestTable dbtest() {
		Optional<TestTable> a = hogeRepository.findById(1);
		return a.get();
	}
}
