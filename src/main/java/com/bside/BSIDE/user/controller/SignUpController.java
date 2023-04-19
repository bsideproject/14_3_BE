package com.bside.BSIDE.user.controller;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.service.SignUpService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
*
* SignUpController
*
* @author Kang juhee
* @version 1.0.0
* 작성일: 2023-04-13
**/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/duplicate-check")
    @Operation(summary = "이메일 중복체크", description = "String eml")
    public ResponseEntity<String> duplicateCheck(@RequestBody Map<String, String> emlMap) throws Exception {
        String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        int dupli = signUpService.duplicateCheck(emlMap.get("eml"));
        if (dupli == 0) {
            result = "사용가능한 이메일입니다.";
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        }else {
            result = "이미 사용중인 이메일입니다.";
            return new ResponseEntity<>(result, headers, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "UserDto userDto")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        signUpService.createMember(userDto);
        String result = "회원가입이 완료되었습니다.";
        return new ResponseEntity<>(result, headers, HttpStatus.OK);

    }

}
