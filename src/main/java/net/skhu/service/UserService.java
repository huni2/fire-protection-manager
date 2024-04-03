package net.skhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.User;
import net.skhu.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	public UserMapper userMapper;

	public List<User> findAll() {
	        return userMapper.findAll();
	}
}
