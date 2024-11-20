package com.ecommerce.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;


@RestController
//@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;


	//@RequestMapping(value = "/public/categories" ,method = RequestMethod.GET)
	@GetMapping("/api/public/categories")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
		//return categoryService.getAllCategories();
	}

	//@RequestMapping(value = "/public/categories" ,method = RequestMethod.POST)
	@PostMapping("/api/public/categories")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>("Category added", HttpStatus.OK);
		//return "Category added";
	}
	
	@DeleteMapping("/api/admin/categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		try {
			String status = categoryService.deleteCategory(categoryId);
			//return ResponseEntity.ok(status);
			return ResponseEntity.status(HttpStatus.OK).body(status);
			//return new ResponseEntity<String>(status, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
		}
	}
	
	@PutMapping("/api/public/categories/{categoryId}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
		Category categories = categoryService.updateCategory(categoryId, category);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
