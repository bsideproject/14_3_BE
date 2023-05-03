package com.bside.BSIDE.user.service;


import com.bside.BSIDE.user.domain.MailDto;
import com.bside.BSIDE.user.domain.UserDto;

import java.util.Map;

public interface SignUpService {

    // 이메일 중복체크
    int duplicateCheck(String eml) throws Exception;
    // 회원가입
    void createMember(UserDto userDto) throws Exception;

    // 회원 조회
    Map<String, Object> selectMember(String eml) throws Exception;

    // 이메일발송한 비번 변경
    void updatePassword(MailDto mailDto) throws Exception;
}
