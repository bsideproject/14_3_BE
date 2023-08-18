package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionListInDto {
    @Schema(description = "페이지 번호", example = "1")
    private int pageNo;

    @Schema(description = "페이지당 들어갈 컬럼 수", example = "20")
    private int pageSize;
}
