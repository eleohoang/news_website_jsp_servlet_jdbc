package vnnews.service;

import java.util.List;

import vnnews.model.CommentModel;

public interface ICommentService {
	void delete(long[] ids);
	List<CommentModel> findByNewId(Long id);
}
