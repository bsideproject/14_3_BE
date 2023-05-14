package com.bside.BSIDE.user.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.user.domain.UserDto;


@Mapper
public interface UserMapper {
    void insertUser(UserDto userDto);
    int deleteUser(String email);
    void updateUser(UserDto userDto);

    UserDto getUserByEmail(String email);
    void saveTemporaryPassword(String email, String password);
}
