package com.bside.BSIDE.contents.domain;

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
	private String aNo;
		
	@Schema(description = "질문 고유 식별 번호", example = "1")
	private String qNo;
		
	@Schema(description = "질문에 대한 답변", example = "겨울")
	private String aAnswerContent;
	
	@Schema(description = "답변 작성자", example = "donghun@gmail.com")
	private String aWriter;

	
}
