package vnnews.service;

import java.util.List;

import vnnews.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	CategoryModel findOne(Long id);
}
