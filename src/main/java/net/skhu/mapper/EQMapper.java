package net.skhu.mapper;

import java.util.List;
//import org.springframework.web.bind.annotation.Mapping;

import net.skhu.dto.EQ;
import org.apache.ibatis.annotations.Mapper;

//@Mapping
//public interface EQMapper {
//
//    @Select("""
//    			SELECT *
//    			FROM eq_master; """)
//    List<EQ> findAll();
//
//}

@Mapper
public interface EQMapper {
	List<EQ> selectEQList() throws Exception;
}


