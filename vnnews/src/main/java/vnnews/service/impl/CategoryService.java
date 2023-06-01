package vnnews.service.impl;

import java.util.List;

import javax.inject.Inject;

import vnnews.dao.ICategoryDAO;
import vnnews.model.CategoryModel;
import vnnews.service.ICategoryService;

public class CategoryService implements ICategoryService{
	//Manual 
	//Nhúng DAO vào service
//	private ICategoryDAO categoryDao;
//	
//	public CategoryService() {
//		categoryDao = new CategoryDAO();
//	}
//	
	@Inject
	private ICategoryDAO categoryDao;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDao.findOne(id);
	}


}
