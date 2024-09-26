package domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
	@Insert("insert into order_table values(#{})")
	public void order();
}
