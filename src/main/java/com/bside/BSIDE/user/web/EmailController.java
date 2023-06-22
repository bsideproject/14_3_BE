package com.bside.BSIDE.user.web;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.user.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @EmailController
 * @작성자 DongHun
 * @일자 2023.05.10.
 **/


@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RestController
@RequestMapping("/email")
public class EmailController {
	
	private final EmailService emailService;
	
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	/* 이메일 인증 번호 전송 */
	@PostMapping("/emailConfirm")
	@Operation(summary = "이메일 인증 번호 전송")
	public String emailConfirm(@RequestParam String email) throws Exception {
	  return emailService.sendCodeMessage(email);
	}
	
	/* 월간고밍 페이지에서 ‘이메일로 보내기’ 버튼을 눌렀을 때 */
	@GetMapping("/sendByMonth")
	@Operation(summary = "월간 고밍 이메일로 전송")
	public void sendByMonth(@RequestParam String email, @RequestParam String date) throws Exception {
		emailService.sendByMonth(email,date);
	}
	
	/* 월간 고밍 & 리마인드 메일 */
	@Scheduled(cron = "0 0 0 1 * *")	//매월 1일 전송
	@GetMapping("/scheduleMonthlyEmail")
	@Operation(summary = "월간 고밍 전송")
	public void scheduleMonthlyEmail() throws Exception {
		emailService.scheduleMonthlyEmail();
	}
		
}
