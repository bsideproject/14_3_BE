package com.bside.BSIDE.contents.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.contents.domain.QuestionDto;



@Mapper
public interface QuestionMapper {
	void insertQuestion(QuestionDto questionDto);
	List<QuestionDto> getQuestion();
	int countUnansweredQuestions();
	int countAnsweredQuestionsThisMonth();
	int countAnsweredQuestionsToday();
	int countAnsweredQuestionsByMonth(int year, int month);
	QuestionDto getQuestionByPNO(int pNo);
}
