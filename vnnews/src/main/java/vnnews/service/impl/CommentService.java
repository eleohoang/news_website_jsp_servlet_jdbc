package vnnews.service.impl;

import java.util.List;

import javax.inject.Inject;

import vnnews.dao.ICommentDAO;
import vnnews.model.CommentModel;
import vnnews.service.ICommentService;

public class CommentService implements ICommentService {

	@Inject
	private ICommentDAO commentDAO;

	@Override
	public void delete(long[] ids) {
		for (long id:ids) {
			commentDAO.delete(id);
		}
	}

	@Override
	public List<CommentModel> findByNewId(Long id) {
		return commentDAO.findByNewId(id);
	}

	

}
