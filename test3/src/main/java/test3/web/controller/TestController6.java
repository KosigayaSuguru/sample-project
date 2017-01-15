package test3.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.bean.TestScopeBean;
import test3.db.entity.Test1;
import test3.db.entity.Test2;
import test3.db.entity.Test3;
import test3.db.entity.dto.ForD2Test1Dto;
import test3.db.entity.dto.Test1Test2Dto;
import test3.db.entity.dto.Test1Test2Dto2;
import test3.db.entity.dto.Test1Test2Dto3;
import test3.db.mapper.Test1Mapper;
import test3.db.mapper.Test3Mapper;

@RestController
public class TestController6 extends WebApplicationObjectSupport {

	int a = 0;
	@Autowired
	Test1Mapper testMapper;

	@Autowired
	Test3Mapper test3Mapper;

	@Autowired
	TestScopeBean test;

	@RequestMapping("/MybatisTest")
	public List<Map<String, Object>> myBatisTest() {

		System.out.println(test.toString());
		test.setHoge("hogehogehoge" + a++);
		test.setMoge("mogemogemoge" + a++);
		System.out.println(test.toString());
		List<Map<String, Object>> ret = testMapper.query("select * from test1");
		return ret;
	}

	@RequestMapping("/MybatisTest1")
	public List<Test1> myBatisTest1() {

		List<Test1> ret = testMapper.selectTest1();
		return ret;
	}

	@RequestMapping("/MybatisTest2")
	public List<Test1Test2Dto> myBatisTest2() {

		List<Test1Test2Dto> ret = testMapper.selectTest1Test2();
		return ret;
	}

	@RequestMapping("/MybatisTest2b")
	public List<Test1Test2Dto2> myBatisTest2b() {

		List<Test1Test2Dto2> ret = testMapper.selectTest1Test2b();
		return ret;
	}

	@RequestMapping("/MybatisTest2c")
	public List<Test1> myBatisTest2c() {

		List<Test1> ret = testMapper.selectTest1Test2c();
		return ret;
	}

	@RequestMapping("/MybatisTest2d")
	public List<Test1> myBatisTest2d() {

		List<Test1> ret = testMapper.selectTest1Test2d();
		return ret;
	}

	@RequestMapping("/MybatisTest2d2")
	public List<ForD2Test1Dto> myBatisTest2d2() {

		List<ForD2Test1Dto> ret = testMapper.selectTest1Test2d2();
		return ret;
	}

	@RequestMapping("/MybatisTest2e")
	public List<Test1Test2Dto3> myBatisTest2e() {

		List<Test1Test2Dto3> ret = testMapper.selectTest1Test2e();
		return ret;
	}

	@RequestMapping("/MybatisTest3")
	public Test3 myBatisTest3() {

		Test3 ret = test3Mapper.selectByPrimaryKey(0);
		return ret;
	}
}
