package com.bside.BSIDE.user.service;

import com.bside.BSIDE.user.domain.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
@AllArgsConstructor
public class EmailService {
    private final SignUpService signUpService;
    private JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "이메일에 보낼 주소";

    public void mailSend(MailDto mailDto) throws Exception {
        String password = createCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getEml());
        message.setSubject("goming test");
        message.setText("임시 비밀번호: "+password);
        javaMailSender.send(message);
        mailDto.setTempPassword(password);
        signUpService.updatePassword(mailDto);
    }

    // 인증번호 및 임시 비밀번호 생성 메서드
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }
}
