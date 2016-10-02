package test3.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.app.dto.bs.Test1EntityDto;
import test3.db.mapper.TestMapper;

@RestController
public class TestController6 extends WebApplicationObjectSupport {

	@Autowired
	TestMapper testMapper;

	@RequestMapping("/MybatisTest1")
	public List<Map<String,Object>> myBatisTest1() {

		List<Map<String, Object>> ret = testMapper.query("select * from test1");
		return ret;
	}

	@RequestMapping("/MybatisTest2")
	public List<Test1EntityDto> myBatisTest2() {

		List<Test1EntityDto> ret = testMapper.selectTest();
		return ret;
	}

}
