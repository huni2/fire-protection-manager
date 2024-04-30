package net.skhu.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import net.skhu.dto.User;
import net.skhu.mapper.UserMapper;
import net.skhu.model.UserSignUp;

@Service
public class UserService {

	@Autowired UserMapper userMapper;
    @Autowired PasswordEncoder passwordEncoder;
    ModelMapper modelMapper = new ModelMapper();

	public List<User> findAll() {
	        return userMapper.findAll();
	}

	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public void insert(UserSignUp userSignUp, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new Exception("사용자를 등록할 수 없습니다.");
        if (userSignUp.getPasswd1().equals(userSignUp.getPasswd2()) == false) {
            bindingResult.rejectValue("passwd2", null, "비밀번호가 일치하지 않습니다.");
            throw new Exception("사용자를 등록할 수 없습니다.");
        }

        User newUser = modelMapper.map(userSignUp, User.class);	// 입력값을 db 넣는 객체로 변환
        newUser.setId(userSignUp.getLoginName());
        newUser.setPassword(passwordEncoder.encode(userSignUp.getPasswd1()));	// 비밀번호 암호화
        newUser.setEnabled(true);	// 사용가능

        int successFlag = userMapper.insert(newUser);

        User users = userMapper.findByLoginName(userSignUp.getLoginName());
//        if (users != null) {
//            bindingResult.rejectValue("loginName", null, "사용자 아이디가 중복됩니다.");
//            throw new Exception("사용자를 등록할 수 없습니다.");
//        }

//        User newUser = modelMapper.map(userSignUp, User.class);
//        newUser.setPassword(passwordEncoder.encode(userSignUp.getPasswd1()));
//        newUser.setEnabled(true);
//        userMapper.insert(newUser);
    }

}
