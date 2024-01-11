package com.bside.BSIDE.user.web;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.jwt.JwtProvider;
import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @UserController
 * @작성자 DongHun
 * @일자 2023.05.10.
 **/

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	/* 회원 조회 */
	@GetMapping("/select/{email}")
	@Operation(summary = "회원 조회", description = "String eml")
	public UserDto getUserByEmail(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}

	/* 회원 탈퇴 */
	@DeleteMapping("/delete/{email}")
	@Operation(summary = "회원 탈퇴", description = "String eml")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		try {
			int deletedRows = userService.deleteUser(email);
			if (deletedRows == 0) {
				return ResponseEntity.ok("해당 이메일을 가진 사용자가 존재하지 않습니다.");
			} else {
				return ResponseEntity.ok("사용자 삭제 성공");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/* 회원 수정 */
	@PutMapping("/update/{email}")
	@Operation(summary = "회원 정보 수정", description = "String eml")
	public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
		// 이메일 유효성 검사
		if (!isValidEmail(email)) {
			return ResponseEntity.ok().body("유효하지 않은 이메일입니다.");
		}

		// UserDto 필수 필드 유효성 검사
		if (!isValidUserDto(userDto)) {
			return ResponseEntity.ok().body("필수 필드가 누락되었습니다.");
		}

		userDto.setEml(email);
		userService.updateUser(userDto);
		return ResponseEntity.ok().body("회원 수정이 성공하였습니다.");
	}

	/* 로그인 */
	@ResponseBody
	@PostMapping("/login")
	@Operation(summary = "로그인")
	public ResponseEntity<?> login(@RequestBody Map<String, String> obj) {
		System.out.println(obj.get("email").toString());
		UserDto user = userService.getUserByEmailPw(obj.get("email").toString(), passwordEncoder.encode(obj.get("password").toString()));
		System.out.println(passwordEncoder.encode(obj.get("password").toString()));
		
		user = userService.getUserByEmail(obj.get("email").toString());
		
		if(user == null) {
			return ResponseEntity.ok().body(obj.get("email") + "는(은) 회원이 아닙니다.");
		}
		
		if(!passwordEncoder.matches(obj.get("password").toString(), user.getPassword())) {
			return ResponseEntity.ok().body("비밀번호가 다릅니다.");
		}
		
		String accessToken = JwtProvider.createToken(obj.get("email").toString());
		return ResponseEntity.ok().body(accessToken);
	}

    @PostMapping("/passwordConfirm")
    @Operation(summary = "비밀번호 확인")
    public boolean passwordConfirm(@RequestBody Map<String, Object> obj) {


		if(userService.getPasswordConfirm(obj.get("eml").toString(), obj.get("password").toString())){
			return true;

		}else{
			return false;

		}

//        if (user != null) {
//            return ResponseEntity.ok().body(user);
//        } else {
//            String msg = "아이디 혹은 비밀번호가 일치하지 않습니다.";
//            return ResponseEntity.ok(msg);
//        }
    }

	/* 이메일 주소의 유효성 검사 */
	private boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(regex);
	}
	
	/* UserDto 필수 필드 유효성 검사 */
	private boolean isValidUserDto(UserDto userDto) {
		if (userDto.getPassword() == null || userDto.getGndrClsCd() == null || userDto.getAgreement() == null) {
	        return false;
	    }
		return true;
	}
}
