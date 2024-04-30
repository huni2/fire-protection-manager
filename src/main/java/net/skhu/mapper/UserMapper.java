package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import net.skhu.dto.User;

@Mapper
//@Repository
public interface UserMapper {



  @Select("SELECT * FROM users")
  List<User> findAll();


  @Select("SELECT * FROM users WHERE loginName = #{loginName}")
  User findByLoginName(String loginName);

  @Insert("""
		    INSERT users (id, loginName, password, username, email, enabled, userType)
		    VALUES (#{id}, #{loginName}, #{password}, #{username}, #{email}, #{enabled}, #{userType})
		  """)
		  @Options(useGeneratedKeys=true, keyProperty="id")
		  int insert(User users);

}