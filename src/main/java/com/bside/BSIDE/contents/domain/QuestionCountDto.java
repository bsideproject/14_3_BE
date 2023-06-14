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
public class QuestionCountDto {
	@Schema(description = "질문 개수", example = "4")
	private int count;
	
	@Schema(description = "날짜", example = "1993-08-04")
	private String date;
}
