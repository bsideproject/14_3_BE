package com.bside.BSIDE.contents.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.AnswerDto;
import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.service.AnswerService;
import com.bside.BSIDE.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @QuestionController
 * @작성자 DongHun
 * @일자 2023.04.23.
 **/

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }
        
	/* 질문 리스트 조회 */
    @GetMapping("/selectByCategory")
    @Operation(summary = "선택한 카테고리의 질문 리스트 조회")
    public List<QuestionDto> getQuestionByCategory(@RequestParam(value = "category", required = false) String category) {
    	// 오늘 생성된 답변되지 않은 질문 조회
        List<AnswerDto> unansweredAnswers = answerService.getUnansweredAnswers();
        
        // 만약 오늘 생성된 답변되지 않은 질문이 있다면 해당 질문 출력
        if (!unansweredAnswers.isEmpty()) {
            List<QuestionDto> unansweredQuestions = new ArrayList<>();
            for (AnswerDto answer : unansweredAnswers) {
                QuestionDto question = questionService.getQuestionByPNO(answer.getQNo());
                unansweredQuestions.add(question);
            }
            return unansweredQuestions;
        }
        
        // 답변되지 않은 질문이 없다면 전체 질문 조회        
        return questionService.getQuestionByCategory(category);
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
    public ResponseEntity<Integer> countUnansweredQuestions(@RequestParam("writer") String writer) {
        Integer count = questionService.countUnansweredQuestions(writer);
        String message = String.format("사용자가 답변할 수 있는 질문은 "+ count +"개입니다.");
        System.out.println(message);
        return ResponseEntity.ok(count);
    }
    
    /* 이번달에 답변한 질문 개수 조회 */    
    @GetMapping("/answered/month")
    @Operation(summary = "이번달에 답변한 질문 개수 조회")
    public ResponseEntity<Integer> countAnsweredQuestionsThisMonth(@RequestParam("writer") String writer) {
        Integer count = questionService.countAnsweredQuestionsThisMonth(writer);
        String message = String.format("이번 달에 답변한 질문 개수는 "+count+"개 입니다.");
        System.out.println(message);
        return ResponseEntity.ok(count);
    }
    
    /* 오늘 답변한 질문 개수 조회 */    
    @GetMapping("/answered/day")
    @Operation(summary = "오늘 답변한 질문 개수 조회")
    public ResponseEntity<Integer> countAnsweredQuestionsToday(@RequestParam("writer") String writer) {
        Integer count = questionService.countAnsweredQuestionsToday(writer);
        String message = String.format("오늘 답변한 질문 개수는 "+count+"개 입니다.");
        System.out.println(message);
        return ResponseEntity.ok(count);
    }
    
    /* 선택한 월에 답변한 질문 개수 조회 */
    @GetMapping("/answered/{year}/{month}/{writer}")
    @Operation(summary = "선택한 월에 답변한 질문 개수 조회")
    public ResponseEntity<Integer> countAnsweredQuestionsByMonth(@PathVariable int year, @PathVariable int month, @PathVariable String writer) {
        int count = questionService.countAnsweredQuestionsByMonth(year, month, writer);
        String message = String.format(year+"년도 "+month+"월에 답변한 질문 개수는 " + count + "개 입니다.");
        System.out.println(message);
        return ResponseEntity.ok(count);
    }
}
