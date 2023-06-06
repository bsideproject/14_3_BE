package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.QuestionAndAnswerDto;
import com.bside.BSIDE.contents.domain.QuestionDto;

public interface QuestionService {
	void insertQuestion(QuestionDto questionDto);
	QuestionDto getQuestionByPNO(int pNo);
	List<QuestionDto> getQuestionByCategory(String category);
	
	int countUnansweredQuestions(String writer);
	int countAnsweredQuestionsThisMonth(String writer);
	int countAnsweredQuestionsToday(String writer);
	int countAnsweredQuestionsByMonth(int year, int month, String writer);
	
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByMonthAndEmail(String email, String year, String month);
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByDayAndEmail(String email, String date);
}
