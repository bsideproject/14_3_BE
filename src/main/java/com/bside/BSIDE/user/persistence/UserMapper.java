package com.bside.BSIDE.user.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.user.domain.UserDto;


@Mapper
public interface UserMapper {
    void insertUser(UserDto userDto);
    String deleteUser(String eml);
    List<UserDto> getAllUsers();
    void updateUser(UserDto userDto);
}
