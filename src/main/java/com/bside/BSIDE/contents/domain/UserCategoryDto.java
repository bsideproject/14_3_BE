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
public class UserCategoryDto {
	@Schema(description = "유저 이메일", example = "programmer_h@naver.com")
    private String email;
	
	@Schema(description = "카테고리 아이디", example = "2")
    private int categoryId;
}
