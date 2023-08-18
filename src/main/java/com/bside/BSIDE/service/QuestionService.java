package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.*;

public interface QuestionService {
	void insertQuestion(QuestionDto questionDto);
	QuestionDto getQuestionByPNO(int pNo);
	List<QuestionDto> getQuestionByCategory(String category);
	
	int countUnansweredQuestions(String writer);
	int countPassQuestions(String writer);
	int countAnsweredQuestionsThisMonth(String writer);
	int countAnsweredQuestionsToday(String writer);
	CountAnsweredQuestionsByMonthDto countAnsweredQuestionsByMonth(String email, int year, int month);
	int countAnsweredQuestionsByDay(String email, String date);
	List<QuestionCountDto> countAnsweredDatesInMonth(String email, String date); 
	
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByMonthAndEmail(String email, String date);
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByDayAndEmail(String email, String date);

    List<QuestionDto> selectListQuestion(QuestionListInDto input);
}
