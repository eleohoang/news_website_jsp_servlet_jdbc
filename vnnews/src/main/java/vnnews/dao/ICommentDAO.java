package vnnews.dao;

import java.util.List;

import vnnews.model.CommentModel;

public interface ICommentDAO extends GenericDAO<CommentModel> {
	void delete(long ids); // delete
	List<CommentModel> findByNewId(Long id);
}
