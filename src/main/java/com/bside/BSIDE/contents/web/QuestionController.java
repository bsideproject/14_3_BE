package com.bside.BSIDE.contents.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	/* 질문 리스트 조회 */
    @GetMapping("/selectQuestion")
    @Operation(summary = "질문 리스트 조회")
    public List<QuestionDto> getQuestion() {
        return questionService.getQuestion();
    }
    
    /* 질문 저장 */
    @PostMapping("/insertQuestion")
    @Operation(summary = "질문 저장")
    public ResponseEntity<Void> createQuestion(@RequestBody QuestionDto questionDto) {
        questionService.insertQuestion(questionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    /* 금일 잔여 답변 개수 조회 */
    @GetMapping("/unanswered")
    @Operation(summary = "금일 잔여 답변 개수 조회")
    public ResponseEntity<String> countUnansweredQuestions() {
        Integer count = questionService.countUnansweredQuestions();
        String message = String.format("사용자가 답변할 수 있는 질문은 "+ count +"개입니다.");
        return ResponseEntity.ok(message);
    }
    
    /* 이번달에 답변한 질문 개수 조회 */    
    @GetMapping("/answered/month")
    @Operation(summary = "이번달에 답변한 질문 개수 조회")
    public ResponseEntity<String> countAnsweredQuestionsThisMonth() {
        Integer count = questionService.countAnsweredQuestionsThisMonth();
        return ResponseEntity.ok("이번 달에 답변한 질문 개수는 "+count+"개 입니다.");
    }
    
    /* 오늘 답변한 질문 개수 조회 */    
    @GetMapping("/answered/day")
    @Operation(summary = "오늘 답변한 질문 개수 조회")
    public ResponseEntity<String> countAnsweredQuestionsToday() {
        Integer count = questionService.countAnsweredQuestionsToday();
        return ResponseEntity.ok("이번 달에 답변한 질문 개수는 "+count+"개 입니다.");
    }
    
    /* 선택한 월에 답변한 질문 개수 조회 */
    @GetMapping("/answered/{year}/{month}")
    @Operation(summary = "선택한 월에 답변한 질문 개수 조회")
    public ResponseEntity<String> countAnsweredQuestionsByMonth(@PathVariable int year, @PathVariable int month) {
        int count = questionService.countAnsweredQuestionsByMonth(year, month);
        return ResponseEntity.ok(year+"년도 "+month+"월에 답변한 질문 개수는 " + count + "개 입니다.");
    }
}
