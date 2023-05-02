package com.bside.BSIDE.contents.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.AnswerDto;
import com.bside.BSIDE.service.AnswerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
	private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
    
    /* 답변 저장 */
    @PostMapping
    @Operation(summary = "답변 저장")
    public ResponseEntity<Void> saveAnswer(@RequestBody AnswerDto answerDto) {
        answerService.saveAnswer(answerDto);
        return ResponseEntity.ok().build();
    }
}
