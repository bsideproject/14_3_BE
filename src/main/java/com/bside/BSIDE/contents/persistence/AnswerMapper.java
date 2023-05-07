package com.bside.BSIDE.contents.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.contents.domain.AnswerDto;

@Mapper
public interface AnswerMapper {
	void saveAnswer(AnswerDto answerDto);
	List<AnswerDto> getUnansweredAnswers();
	List<AnswerDto> selectAnsweredAnswersByToday();
}
