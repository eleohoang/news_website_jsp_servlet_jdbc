package vnnews.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import vnnews.dao.INewDAO;
import vnnews.mapper.NewMapper;
import vnnews.mapper.UserMapper;
import vnnews.model.NewsModel;
import vnnews.model.UserModel;
import vnnews.paging.Pageble;

public class NewDAO extends AbstractDAO<NewsModel> implements INewDAO {
	@Override
	public List<NewsModel> findByCategoryId(Long categoryID) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryID);
	}

	@Override
	public Long create(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, thumbnail, shortdescription,");
		sql.append("content, categoryid, createddate, createdby)");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
		
		return insert(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(),newsModel.getShortDescription(),
		newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(),newsModel.getCreatedBy());
	}

	@Override
	public void update(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,"); 
		sql.append("shortdescription = ?, content = ?, categoryid = ?,");
		sql.append("createddate = ?, createdby= ?, modifieddate = ?, modifiedby = ? WHERE id = ? ");
		update(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(),newsModel.getShortDescription(),
		newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(), 
		newsModel.getCreatedBy(), newsModel.getModifiedDate(),newsModel.getModifiedBy(), newsModel.getId());
	}
	
	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
			&& StringUtils.isNotBlank(pageble.getSorter().getSortBy()) ) {
			sql.append(" ORDER BY " +pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}

	@Override
	public NewsModel findNewClosetDateByCategoryId(Long categoryID) {
		String sql = "select * from news where categoryid =? and createddate <= current_timestamp() order by createddate desc limit 1";
		List<NewsModel> news = query(sql, new NewMapper(), categoryID);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public List<NewsModel> findTop() {
		String sql = "select * from news where createddate <= current_timestamp() order by createddate desc limit 3";
		return query(sql.toString(), new NewMapper());
	}
}
