package test3.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import test3.app.dto.bs.Test1EntityDto;

public interface TestMapper {

    @Select("${sql}")
    List<Map<String, Object>> query(@Param("sql") String sql);

    List<Test1EntityDto> selectTest();
}


