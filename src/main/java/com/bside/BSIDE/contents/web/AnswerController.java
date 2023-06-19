package com.bside.BSIDE.contents.web;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.AnswerDto;
import com.bside.BSIDE.contents.domain.CategoryDto;
import com.bside.BSIDE.contents.domain.UserCategoryDto;
import com.bside.BSIDE.service.AnswerService;
import com.bside.BSIDE.service.CategoryService;
import com.bside.BSIDE.service.UserCategoryService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @AnswerController
 * @작성자 DongHun
 * @일자 2023.04.27.
 **/

@CrossOrigin
@RestController
@RequestMapping("/answers")
public class AnswerController {
	private final AnswerService answerService;
	private final CategoryService categoryService;
	private final UserCategoryService userCategoryService;

	public AnswerController(AnswerService answerService, CategoryService categoryService,
			UserCategoryService userCategoryService) {
		this.answerService = answerService;
		this.categoryService = categoryService;
		this.userCategoryService = userCategoryService;
	}

	/* 선택된 질문을 보관하기 */
	@PostMapping("/selectedQuestion")
	@Operation(summary = "선택된 질문 보관")
	public void selectedQuestion(@RequestBody Map<String, Object> obj) {
		int qNo = (int) obj.get("qNo");
		String aWriter = (String) obj.get("aWriter");
		answerService.selectedQuestion(qNo, aWriter);
	}

	/* 질문에 대한 답변 저장하기 */
	@PostMapping("/saveAnswer")
	@Operation(summary = "답변 저장")
	public ResponseEntity<Boolean> saveAnswer(@RequestBody Map<String, Object> obj) {
		AnswerDto adto = new AnswerDto();
		adto.setQNo((int) obj.get("qNo"));
		adto.setAAnswerContent(obj.get("aAnswerContent").toString());
		adto.setAWriter(obj.get("aWriter").toString());
		adto.setCategory(obj.get("category").toString());

		System.out.println(obj.get("category").toString());

		CategoryDto dto = categoryService.getCategory(adto.getCategory());

		UserCategoryDto userCategoryDto = new UserCategoryDto();
		userCategoryDto.setEmail(adto.getAWriter());
		userCategoryDto.setCategoryId(dto.getCategoryId());

		insertUserCategory(userCategoryDto);

		return ResponseEntity.ok(answerService.saveAnswer(adto));
	}

	/* 카테고리 저장 */
	public void insertUserCategory(@RequestBody UserCategoryDto userCategoryDto) {
		userCategoryService.insertUserCategory(userCategoryDto);
	}
}
