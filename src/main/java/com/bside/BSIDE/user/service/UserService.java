package com.bside.BSIDE.user.service;

import java.util.List;
import java.util.Optional;

import com.bside.BSIDE.user.domain.UserDto;

public interface UserService {	
	int deleteUser(String email);
	void updateUser(UserDto userDto);
	
	UserDto getUserByEmail(String email);
	void saveTemporaryPassword(String email, String password);
}
