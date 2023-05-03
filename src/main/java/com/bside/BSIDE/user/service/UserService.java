package com.bside.BSIDE.user.service;

import java.util.List;
import java.util.Optional;

import com.bside.BSIDE.user.domain.UserDto;

public interface UserService {
	void addUser(UserDto userDto);
	List<UserDto> getAllUsers();
	int deleteUser(String email);
	void updateUser(UserDto userDto);
}
