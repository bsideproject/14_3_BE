package com.bside.BSIDE.contents.domain;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {
	@Schema(hidden = true)
	private int aNo;
		
	@Schema(description = "질문 고유 식별 번호", example = "1")
	private int qNo;
		
	@Schema(description = "질문에 대한 답변", example = "겨울")
	private String aAnswerContent;
	
	@Schema(description = "답변 작성자", example = "donghun@gmail.com")
	private String aWriter;
	
	@Schema(description = "질문에 대한 답변 유무", example = "false")
	private boolean aCheck;
	
	@Schema(description = "질문에 대한 답변 유무", example = "false")
	private String category; 
	
	public int getQNo() {
		return qNo;
	}
	
}
