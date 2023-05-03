package com.bside.BSIDE.user.persistence;

import com.bside.BSIDE.user.domain.MailDto;
import com.bside.BSIDE.user.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SignUpMapper {
    int duplicateCheck(String eml);
    void createMember(UserDto userDto);
    Map<String, Object> selectMember(String eml);
    void updatePassword(MailDto mailDto);
}
