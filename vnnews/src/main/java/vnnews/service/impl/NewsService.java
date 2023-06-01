package vnnews.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vnnews.dao.ICategoryDAO;
import vnnews.dao.ICommentDAO;
import vnnews.dao.INewDAO;
import vnnews.model.CategoryModel;
import vnnews.model.NewsModel;
import vnnews.paging.Pageble;
import vnnews.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	private INewDAO newDao;
	
	@Inject
	private ICommentDAO commentDao;
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}

	@Override
	public NewsModel create(NewsModel newsModel) {
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findOneByCode(newsModel.getCategoryCode());
		newsModel.setCategoryId(categoryModel.getId());
		Long newId = newDao.create(newsModel);
		return newDao.findOne(newId);
	}

	@Override
	public NewsModel update(NewsModel newsModel) {
		NewsModel oldnews = newDao.findOne(newsModel.getId());
		newsModel.setCreatedDate(oldnews.getCreatedDate());
		newsModel.setCreatedBy(oldnews.getCreatedBy());
		newsModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findOneByCode(newsModel.getCategoryCode());
		newsModel.setCategoryId(categoryModel.getId());
		newDao.update(newsModel);
		return newDao.findOne(newsModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id:ids) {
			// delete table "comment" first
			commentDao.delete(id);
			// delete table "news" 
			newDao.delete(id);
		}
	}
	
	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDao.getTotalItem();
	}
	
	@Override
	public NewsModel findOne(Long id) {
		NewsModel newsmodel = newDao.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newsmodel.getCategoryId());
		newsmodel.setCategoryCode(categoryModel.getCode());
		return newsmodel;
	}

	@Override
	public List<NewsModel> findByAllCategoryId(List<Long> categoriesId) {
		List<NewsModel> list = new ArrayList<NewsModel>();
		NewsModel newsmodel = new NewsModel();
		for (Long categoryId : categoriesId) {
			list.add(newDao.findNewClosetDateByCategoryId(categoryId));
		}
		newsmodel.setListResult(list);
		return newsmodel.getListResult();
	}

	@Override
	public List<NewsModel> findTop() {
		return newDao.findTop();
	}
}
