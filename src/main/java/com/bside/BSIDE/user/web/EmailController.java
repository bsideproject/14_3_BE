package com.bside.BSIDE.user.web;

import com.bside.BSIDE.user.domain.EmailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.bside.BSIDE.user.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @EmailController
 * @작성자 DongHun
 * @일자 2023.05.10.
 **/


@CrossOrigin
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
	public String emailConfirm(@RequestParam EmailDto param) throws Exception {
	  return emailService.sendCodeMessage(param.getEmail());
	}
	
	/* 월간고밍 페이지에서 ‘이메일로 보내기’ 버튼을 눌렀을 때 */
	@PostMapping("/sendByMonth")
	@Operation(summary = "월간 고밍 이메일로 전송")
	public void sendByMonth(@RequestBody EmailDto param) throws Exception {
		System.out.println(param.getEmail()+"+ @#@#@##@#@#@#!@$@$!@$email");
		System.out.println(param.getSendEmail()+"+ @#@#@##@#@#@#!@$@$!@sendEmail");
		System.out.println(param.getDate()+ "+ @#@#@##@#@#@#!@$@$!@date");

		emailService.sendByMonth(param.getEmail(),param.getSendEmail(),param.getDate());
	}
	
	/* 월간 고밍 & 리마인드 메일 */
	@Scheduled(cron = "0 0 0 1 * *")	//매월 1일 전송
	@GetMapping("/scheduleMonthlyEmail")
	@Operation(summary = "매일 1일 월간 고밍 자동 전송")
	public void scheduleMonthlyEmail() throws Exception {
		emailService.scheduleMonthlyEmail();
	}
		
}
