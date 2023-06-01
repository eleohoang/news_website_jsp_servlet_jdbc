package vnnews.dao.impl;

import java.util.List;

import vnnews.dao.ICommentDAO;
import vnnews.mapper.CommentMapper;
import vnnews.model.CommentModel;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO {
	@Override
	public void delete(long id) {
		String sql = "DELETE FROM comment WHERE id = ?";
		update(sql, id);
	}
	
	@Override
	public List<CommentModel> findByNewId(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM comment AS c");
		sql.append(" INNER JOIN user AS u ON c.user_id = u.id");
		sql.append(" WHERE c.new_id = ?"); 
		
		List<CommentModel> commentModel = query(sql.toString(), new CommentMapper(), id);
		
		return commentModel;
	}
}
