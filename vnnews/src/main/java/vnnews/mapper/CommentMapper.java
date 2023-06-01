package vnnews.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import vnnews.model.CommentModel;
import vnnews.model.UserModel;


public class CommentMapper implements RowMapper<CommentModel> {

	@Override
	public CommentModel mapRow(ResultSet resultSet) {
		try {
			CommentModel commentModel = new CommentModel();
			commentModel.setId(resultSet.getLong("id"));
			commentModel.setContent(resultSet.getString("content"));
			commentModel.setUserId(resultSet.getLong("user_id"));
			commentModel.setNewId(resultSet.getLong("new_id"));
			commentModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			commentModel.setCreatedBy(resultSet.getString("createdby"));
			if (resultSet.getTimestamp("modifieddate") != null) {
				commentModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if (resultSet.getString("modifiedby") != null) {
				commentModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			try {
				commentModel.setUserName(resultSet.getString("username"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return commentModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
