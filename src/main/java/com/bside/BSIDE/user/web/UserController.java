package com.bside.BSIDE.user.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    //회원 가입
    @PostMapping("/signup")
    @Operation(summary = "회원 가입")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    //유저 전체 조회
    @GetMapping("/selectAll")
    @Operation(summary = "유저 전체 조회")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    
    //회원 삭제    
    @DeleteMapping("/delete/{email}")
    @Operation(summary = "회원 탈퇴", description = "String eml")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        try {
        	userService.deleteUser(email);
        	return ResponseEntity.ok("success");
        } catch (Exception e) {
        	throw e;
        }
    }
    
    //회원 수정
    @PutMapping("/update/{email}")
    @Operation(summary = "회원 정보 수정", description = "String eml")
    public ResponseEntity<String> updateUser(@PathVariable String eml, @RequestBody UserDto userDto) {
        userDto.setEml(eml);
        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }
}
