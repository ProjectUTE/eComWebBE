package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.ute.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/category/get-all-category-still-in-business", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllCategoryStillInBusiness() {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategoryStillInBusiness());
	}
}
