package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Test3Mapper {

	@Select("select * from test3")
	public List<Test3Vo> get();

}
