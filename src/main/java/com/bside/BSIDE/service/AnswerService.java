package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.AnswerDto;

public interface AnswerService {
	void saveAnswer(AnswerDto answerDto);
	List<AnswerDto> getUnansweredAnswers();
}
