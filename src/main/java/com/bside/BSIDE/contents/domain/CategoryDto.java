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
public class CategoryDto {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private int categoryId;
	
	@Schema(description = "카테고리 종류", example = "기억")
	private String categoryName;
}
