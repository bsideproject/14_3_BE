package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.QuestionDto;

public interface QuestionService {
	void insertQuestion(QuestionDto questionDto);
	List<QuestionDto> getQuestion();
	int countUnansweredQuestions();
	int countAnsweredQuestionsThisMonth();
	int countAnsweredQuestionsToday();
	int countAnsweredQuestionsByMonth(int year, int month);
	QuestionDto getQuestionByPNO(int pNo);
}
