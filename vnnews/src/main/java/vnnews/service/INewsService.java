package vnnews.service;

import java.util.List;

import vnnews.model.NewsModel;
import vnnews.paging.Pageble;

public interface INewsService {
	List<NewsModel> findByCategoryId(Long categoryId);
	List<NewsModel> findByAllCategoryId(List<Long> categoriesId);
	NewsModel create(NewsModel newsModel);
	NewsModel update(NewsModel newsModel);
	void delete(long[] ids);
//	List<NewsModel> findAll(Integer offset, Integer limit, String sortName, String sortBy);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
	NewsModel findOne(Long id);
	List<NewsModel> findTop();
}
