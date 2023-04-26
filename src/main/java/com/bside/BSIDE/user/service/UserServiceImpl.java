package com.bside.BSIDE.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.persistence.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	

	private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(UserDto userDto) {
        userMapper.insertUser(userDto);
    }
    
    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.getAllUsers();
    }
    
    @Override
    public void deleteUser(String eml) {
        userMapper.deleteUser(eml);
    }
    
    @Override
    public void updateUser(UserDto userDto) {
    	userMapper.updateUser(userDto);
    }
	
}
