package vnnews.dao;

import java.util.List;

import vnnews.model.NewsModel;
import vnnews.paging.Pageble;

public interface INewDAO extends GenericDAO<NewsModel> {
	List<NewsModel> findByCategoryId(Long categoryID); // get
	NewsModel findOne(Long id);
	Long create(NewsModel newsModel); // create
	void update(NewsModel newsModel); // update
	void delete(long ids); // delete
	//List<NewsModel> findAll(Integer offset, Integer limit, String sortName, String sortBy); 
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
	NewsModel findNewClosetDateByCategoryId(Long categoryID); // get
	List<NewsModel> findTop();
}
