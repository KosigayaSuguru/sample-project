package test3.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.bean.TestScopeBean;
import test3.db.mapper.Test1Mapper;
import test3.db.mapper.Test3Mapper;

@RestController
public class TransactionController extends WebApplicationObjectSupport {

	int a = 0;
	@Autowired
	Test1Mapper testMapper;

	@Autowired
	Test3Mapper test3Mapper;

	@Autowired
	TestScopeBean test;

	@RequestMapping("/TransactionTest")
	// トランザクション開始(MySQL用のTransactionManager使って)
	@Transactional(transactionManager = "mySqlTransactionManager", rollbackFor = Exception.class, /** propagation = Propagation.NOT_SUPPORTED, */
			readOnly = false)
	// @Transactional(transactionManager = "mySqlTransactionManager", rollbackFor = Exception.class, readOnly = true)
	// @Transactional(transactionManager = "mySqlTransactionManager", noRollbackFor = Exception.class)
	public List<Map<String, Object>> transactionTest() {

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		// このタイミング（sleep()してる間に）でDB落とすと、

		List<Map<String, Object>> ret = testMapper.query("select * from test1");

		// ↑のSQLが実行できないので例外が発生して落ちるところまではいいけど、
		// DBが落ちるタイミングで、TCPの接続が終了する（FINが来る。killで強制終了させるとRSTが来る）のに、
		// rollback()の処理が走るため二重で例外が発生する。
		// noRollbackForでロールバックの対象から外すと、commit()しようとして、結局落ちる
		return ret;
	}
}
