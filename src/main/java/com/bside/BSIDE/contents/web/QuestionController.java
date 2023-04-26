package com.bside.BSIDE.contents.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @QuestionController
 * @작성자 DongHun
 * @일자 2023.04.23.
 */

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }	
	
	
	//질문 조회
    @GetMapping("/selectQuestion")
    @Operation(summary = "질문 조회")
    public List<QuestionDto> getQuestion() {
        return questionService.getQuestion();
    }
    
    // 질문 저장    
    @PostMapping("/insertQuestion")
    @Operation(summary = "질문 저장")
    public ResponseEntity<Void> createQuestion(@RequestBody QuestionDto questionDto) {
        questionService.insertQuestion(questionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
