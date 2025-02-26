package com.ecommerce.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	//private List<Category> categories = new ArrayList<>();
	private Long id = 1L;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public void createCategory(Category category) {
		category.setCategoryId(id++);
		categoryRepository.save(category);		
	}

	@Override
	public String deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
		
//		List<Category> categories = categoryRepository.findAll();
//		Category category = categories.stream()
//				.filter(c -> c.getCategoryId().equals(categoryId))
//				.findFirst()
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
//		if(category == null)
//			return "Category not found";
		categoryRepository.delete(category);
		return "Category with ID:" + categoryId + " DELETED";
	}

	@Override
	public Category updateCategory(Long categoryId, Category category) {
		Optional<Category> saveCategoryOptional =  categoryRepository.findById(categoryId);
		
		Category saveCategory = saveCategoryOptional
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
		
		category.setCategoryId(categoryId);
		saveCategory = categoryRepository.save(category);
		return saveCategory;
		
//		List<Category> categories = categoryRepository.findAll();
//		Optional<Category> optionalCategory = categories.stream()
//				.filter(c -> c.getCategoryId().equals(categoryId))
//				.findFirst();
//		if(optionalCategory.isPresent()) {
//			Category existingCategory = optionalCategory.get();
//			existingCategory.setCategoryName(category.getCategoryName());
//			Category saveCategory = categoryRepository.save(existingCategory);
//			return saveCategory;
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
//		}
	}
	
}
