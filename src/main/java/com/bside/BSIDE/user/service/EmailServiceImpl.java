package com.bside.BSIDE.user.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bside.BSIDE.user.domain.UserDto;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	private final UserService userService;
	
	public EmailServiceImpl(UserService userService) {
		this.userService = userService;
	}

	private final String senderEmail = "goming.team@gmail.com"; // 발신자 이메일 주소
	private final String senderName = "gomingSupport"; // 발신자 이름

	/* 인증코드 생성 */
	private String generateVerificationCode() {
		StringBuilder code = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = random.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
			case 0:
				code.append((char) (random.nextInt(26) + 97)); // a~z
				break;
			case 1:
				code.append((char) (random.nextInt(26) + 65)); // A~Z
				break;
			case 2:
				code.append(random.nextInt(10)); // 0~9
				break;
			}
		}
		return code.toString();
	}

	/* 회원가입 인증코드 발송 */
	@Override
	public String sendCodeMessage(String to) throws Exception {
		String verificationCode = generateVerificationCode();

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

		helper.setTo(to); // 수신자 이메일 주소
		helper.setSubject("Goming 이메일 인증"); // 제목

		String emailContent = "<div style='margin:20px;'>" + "<h1>안녕하세요 이동훈입니다.</h1>" + "<br>"
				+ "<p>아래 코드를 복사해 입력해주세요.</p>" + "<br>" + "<p>감사합니다.</p>" + "<br>"
				+ "<div align='center' style='border:1px solid black; font-family:verdana;'>"
				+ "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>" + "<div style='font-size:130%'>" + "CODE : <strong>"
				+ verificationCode + "</strong><div><br/> " + "</div>";

		helper.setText(emailContent, true); // 내용
		helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("verificationCode", verificationCode);

		try {
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return verificationCode;
	}

	/* 임시 비밀번호 발송 */
	@Override
	public String sendTemporaryPassword(String to, String temporaryPassword) throws Exception {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true, "utf-8");

		helper.setTo(to); // 수신자 이메일 주소
		helper.setSubject("Goming 비밀번호 찾기"); // 제목

		String emailContent = "<div style='margin:20px;'>" + "<h1>안녕하세요 이동훈입니다.</h1>" + "<br>"
				+ "<p>아래 코드를 복사해 입력해주세요.</p>" + "<br>" + "<p>감사합니다.</p>" + "<br>"
				+ "<div align='center' style='border:1px solid black; font-family:verdana;'>"
				+ "<h3 style='color:blue;'>임시 비밀번호 입니다.</h3>" + "<div style='font-size:130%'>" + "임시 비밀번호 : <strong>"
				+ temporaryPassword + "</strong><div><br/> " + "</div>";

		// 이미지 파일 경로
		String imagePath = "/Goming.JPG";

		// 이미지 파일을 첨부
		ClassPathResource resource = new ClassPathResource(imagePath);
		helper.addAttachment("Goming.JPG", resource);

		helper.setText(emailContent, true); // 내용
		helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

		try {
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return temporaryPassword;
	}
	
	@Override
	public void sendUserEmail() throws Exception {
	    List<UserDto> user = userService.getUser();

	 // 첨부 파일 생성
        StringBuilder attachmentContent = new StringBuilder();
        for (UserDto dto : user) {
            attachmentContent.append(dto.getEml()).append(", ");
        }
        byte[] attachmentData = attachmentContent.toString().getBytes();

        // MimeMessage 생성 및 설정
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        String to = "ehdgns48@naver.com";
        helper.setTo(to); // 수신자 이메일 주소
        helper.setSubject("Goming 비밀번호 찾기"); // 제목

        StringBuilder emailContent = new StringBuilder();
        for (UserDto dto : user) {
            emailContent.append(dto.getEml()).append(", ");
        }
        helper.setText(emailContent.toString(), true); // 내용
        helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

        // 첨부 파일 추가
        String attachmentFilename = "Goming.txt";
        helper.addAttachment(attachmentFilename, new ByteArrayResource(attachmentData));

	    try {
	        emailSender.send(message);
	    } catch (MailException e) {
	        e.printStackTrace();
	        throw new IllegalArgumentException();
	    }
	}
}