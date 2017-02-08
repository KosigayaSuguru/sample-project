package test3.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import test3.db.entity.Test1;
import test3.db.entity.dto.ForD2Test1Dto;
import test3.db.entity.dto.Test1Test2Dto;
import test3.db.entity.dto.Test1Test2Dto2;
import test3.db.entity.dto.Test1Test2Dto3;

public interface Test1Mapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table test1
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table test1
	 * @mbg.generated
	 */
	int insert(Test1 record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table test1
	 * @mbg.generated
	 */
	int insertSelective(Test1 record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table test1
	 * @mbg.generated
	 */
	Test1 selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table test1
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(Test1 record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table test1
	 * @mbg.generated
	 */
	int updateByPrimaryKey(Test1 record);

	@Select("${sql}")
	List<Map<String, Object>> query(@Param("sql") String sql);

	List<Test1> selectTest1();

	List<Test1Test2Dto> selectTest1Test2();

	List<Test1Test2Dto2> selectTest1Test2b();

	List<Test1> selectTest1Test2c();

	List<Test1> selectTest1Test2d();

	List<ForD2Test1Dto> selectTest1Test2d2();

	List<Test1Test2Dto3> selectTest1Test2e();

}