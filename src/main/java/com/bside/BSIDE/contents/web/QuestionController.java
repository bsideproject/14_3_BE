package com.bside.BSIDE.contents.web;

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

import com.bside.BSIDE.contents.domain.QuestionAndAnswerDto;
import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.contents.domain.CountAnsweredQuestionsByMonthDto;
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
    public ResponseEntity<CountAnsweredQuestionsByMonthDto> countAnsweredQuestionsByMonth(@PathVariable int year, @PathVariable int month, @PathVariable String writer) {
        CountAnsweredQuestionsByMonthDto dto = questionService.countAnsweredQuestionsByMonth(year, month, writer);
        return ResponseEntity.ok(dto);
    }
    
    /* 선택한 년도, 월에 답변한 질문 */
    @GetMapping("/answered/{year}/{month}")
    @Operation(summary = "선택한 월에 답변한 질문 조회")
    public ResponseEntity<?> getQuestionsAndAnswersByMonthAndEmail(@RequestParam String email, @RequestParam String date) {
 
    	System.out.println(email+", "+date);
    	
    	List<QuestionAndAnswerDto> questionsAndAnswers;
    	String[] dateArr = date.split("-");
    	
    	/* YYYY 입력했을 경우 */
    	if(dateArr.length == 1) {
    		return ResponseEntity.ok("YYYY-MM 의 형식으로 정확한 MM을 입력해주세요.");
    	}
    	/* YYYY-MM 입력했을 경우 */
    	else if(dateArr.length == 2) {
    		questionsAndAnswers = questionService.getQuestionsAndAnswersByMonthAndEmail(email, dateArr[0], dateArr[1]);
    	}
    	/* YYYY-MM-DD 입력했을 경우 */
    	else {
    		questionsAndAnswers = questionService.getQuestionsAndAnswersByDayAndEmail(email, date);    		
    	}
    	
    	if(questionsAndAnswers.isEmpty()) {
    		ResponseEntity.ok("선택한 날짜의 값이 존재하지 않습니다.");
    	}
    	
    	
    	return ResponseEntity.ok(questionsAndAnswers);
    }
}
