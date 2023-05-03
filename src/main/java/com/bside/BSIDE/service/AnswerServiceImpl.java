package com.bside.BSIDE.service;

import org.springframework.stereotype.Service;

import com.bside.BSIDE.contents.domain.AnswerDto;
import com.bside.BSIDE.contents.persistence.AnswerMapper;

@Service
public class AnswerServiceImpl implements AnswerService {
	private final AnswerMapper answerMapper;

    public AnswerServiceImpl(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    
    @Override
    public void saveAnswer(AnswerDto answerDto) {
    	answerMapper.saveAnswer(answerDto);
    }
}
