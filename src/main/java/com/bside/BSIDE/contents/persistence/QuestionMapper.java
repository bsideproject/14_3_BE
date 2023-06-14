package com.bside.BSIDE.contents.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.contents.domain.QuestionAndAnswerDto;
import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.contents.domain.CountAnsweredQuestionsByMonthDto;



@Mapper
public interface QuestionMapper {
	void insertQuestion(QuestionDto questionDto);
	QuestionDto getQuestionByPNO(int pNo);
	List<QuestionDto> getQuestionByCategory(String category);
	
	int countUnansweredQuestions(String writer);
	int countAnsweredQuestionsThisMonth(String writer);
	int countAnsweredQuestionsToday(String writer);
	CountAnsweredQuestionsByMonthDto countAnsweredQuestionsByMonth(String email, int year, int month);
	int countAnsweredQuestionsByDay(String email, String date);
	
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByMonthAndEmail(String email, String date);
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByDayAndEmail(String email, String date);
}
