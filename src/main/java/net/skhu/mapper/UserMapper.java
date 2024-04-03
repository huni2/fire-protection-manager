package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import net.skhu.dto.User;

@Mapper
//@Repository
public interface UserMapper {

  @Select("""
          SELECT id, username, email, password
			FROM users """)
  List<User> findAll();
}