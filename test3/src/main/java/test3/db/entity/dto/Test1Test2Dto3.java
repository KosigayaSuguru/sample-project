package test3.db.entity.dto;

import java.time.LocalDateTime;
import java.util.List;

import test3.db.entity.Test1;
import test3.db.entity.Test2;
import test3.db.entity.Test3EntityEnum;

public class Test1Test2Dto3 {

	protected Integer aid;
	protected Integer test1Col1;
	protected Integer test1Col2;
	protected Integer bid;
	protected Integer test1Id;
	protected Integer test2Col1;
	protected Integer test2Col2;
	protected Integer cid;
	protected Integer test2Id;
	protected Test3EntityEnum enumTest;
	protected LocalDateTime updateDate;

	public Integer getAid() {
		return aid;
	}

	public Integer getTest1Col1() {
		return test1Col1;
	}

	public Integer getTest1Col2() {
		return test1Col2;
	}

	public Integer getBid() {
		return bid;
	}

	public Integer getTest1Id() {
		return test1Id;
	}

	public Integer getTest2Col1() {
		return test2Col1;
	}

	public Integer getTest2Col2() {
		return test2Col2;
	}

	public Integer getCid() {
		return cid;
	}

	public Integer getTest2Id() {
		return test2Id;
	}

	public Test3EntityEnum getEnumTest() {
		return enumTest;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public void setTest1Col1(Integer test1Col1) {
		this.test1Col1 = test1Col1;
	}

	public void setTest1Col2(Integer test1Col2) {
		this.test1Col2 = test1Col2;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public void setTest1Id(Integer test1Id) {
		this.test1Id = test1Id;
	}

	public void setTest2Col1(Integer test2Col1) {
		this.test2Col1 = test2Col1;
	}

	public void setTest2Col2(Integer test2Col2) {
		this.test2Col2 = test2Col2;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setTest2Id(Integer test2Id) {
		this.test2Id = test2Id;
	}

	public void setEnumTest(Test3EntityEnum enumTest) {
		this.enumTest = enumTest;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Test1Test2Dto3 [aid=" + aid + ", test1Col1=" + test1Col1 + ", test1Col2=" + test1Col2 + ", bid=" + bid + ", test1Id=" + test1Id
				+ ", test2Col1=" + test2Col1 + ", test2Col2=" + test2Col2 + ", cid=" + cid + ", test2Id=" + test2Id + ", enumTest=" + enumTest
				+ ", updateDate=" + updateDate + "]";
	}

}