package com.bside.BSIDE.contents.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.contents.domain.QuestionDto;



@Mapper
public interface QuestionMapper {
	void insertQuestion(QuestionDto questionDto);
	QuestionDto getQuestionByPNO(int pNo);
	List<QuestionDto> getQuestionByCategory(String category);
	
	int countUnansweredQuestions(String writer);
	int countAnsweredQuestionsThisMonth(String writer);
	int countAnsweredQuestionsToday(String writer);
	int countAnsweredQuestionsByMonth(int year, int month, String writer);
}
