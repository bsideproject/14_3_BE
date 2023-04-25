package com.bside.BSIDE.user.service;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.persistence.SignUpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service("SignUpServiceImpl")
public class SignUpServiceImpl implements SignUpService{
    private final SignUpMapper signUpMapper;
    private JavaMailSender javaMailSender;
    @Override
    public int duplicateCheck(String eml) throws Exception {
        int result = 1;
        result = signUpMapper.duplicateCheck(eml);
        return result;
    }

    @Override
    public void createMember(UserDto userDto) throws Exception {
         /*
        Todo: 추후 sns 로그인 추가 필요
         */
        // password 암호화
//        String password = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(userDto.getPassword());
        // 회원가입
        signUpMapper.createMember(userDto);
    }

    @Override
    public Map<String, Object> selectMember(String eml) throws Exception {
        Map<String, Object> result = signUpMapper.selectMember(eml);
        return result;
    }

    @Override
    public Map<String, Object> createMailSendPassword(String eml) {
        String str = getTempPassword();
        Map<String, Object> result = new HashMap<>();
        result.put("userEmail", eml);
        result.put("tempPassword", str);
        signUpMapper.updatePassword(eml, str);
        return result;
    }

    @Override
    public void sendMail(Map<String, Object> result) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("userEmail: "+result.get("userEmail").toString());
        message.setTo(result.get("userEmail").toString());
        message.setFrom("goming.team@gmail.com"); // 상수선언필요
        message.setSubject("goming 비밀번호 재설정");  // 제목
        message.setText("메일 내용"); // 내용

        try{
            javaMailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        String str = "";
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
