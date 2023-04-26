package com.bside.BSIDE.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.contents.persistence.QuestionMapper;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }
    
    @Override
    public List<QuestionDto> getQuestion() {
        return questionMapper.getQuestion();
    }
    
    @Override
    public void insertQuestion(QuestionDto questionDto) {
    	questionMapper.insertQuestion(questionDto);
    }
}
