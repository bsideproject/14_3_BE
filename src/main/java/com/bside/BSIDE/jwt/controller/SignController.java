package com.bside.BSIDE.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/api/")
public class SignController {
	  @PostMapping(value = "/login")
	  public ResponseEntity<String> login() throws Exception {
	      return ResponseEntity.ok().body(SignService.test_login("",""));
	  }

//    private final MemberRepository memberRepository;
//    private final SignService memberService;
//
//    @PostMapping(value = "/login")
//    public ResponseEntity<SignResponse> signin(@RequestBody SignRequest request) throws Exception {
//        return new ResponseEntity<>(memberService.login(request), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/register")
//    public ResponseEntity<Boolean> signup(@RequestBody SignRequest request) throws Exception {
//        return new ResponseEntity<>(memberService.register(request), HttpStatus.OK);
//    }
//
//    @GetMapping("/user/get")
//    public ResponseEntity<SignResponse> getUser(@RequestParam String account) throws Exception {
//        return new ResponseEntity<>( memberService.getMember(account), HttpStatus.OK);
//    }
//
//    @GetMapping("/admin/get")
//    public ResponseEntity<SignResponse> getUserForAdmin(@RequestParam String account) throws Exception {
//        return new ResponseEntity<>( memberService.getMember(account), HttpStatus.OK);
//    }
}
