package vnnews.dao.impl;
//package vnnews.dao.impl;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import vnnews.dao.INewDAO;
//import vnnews.model.NewsModel;
//import vnnews.model.NewsModel;
//
//public class NewDAO_old extends AbstractDAO implements INewDAO {
//
//	public Connection getConnection() {
//		try {
//			// load driver bang Class.forName file lang
//			Class.forName("com.mysql.jdbc.Driver");
//
//			// login to MySQL by user, password
//			String url = "jdbc:mysql://localhost:3306/vnnews";
//			String user = "root";
//			String password = "1234";
//			return DriverManager.getConnection(url, user, password);
//		} catch (ClassNotFoundException | SQLException e) {
//			return null;
//		}
//	}
//
//	@Override
//	public List<NewsModel> findByCategoryId(Long categoryID) {
//		List<NewsModel> results = new ArrayList<>();
//		String sql = "SELECT * FROM news WHERE categoryid = ?";
//
//		// open connection
//		Connection connection = getConnection();
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//
//		if (connection != null) {
//			try {
//				// excute sql
//				statement = connection.prepareStatement(sql);
//				statement.setLong(1, categoryID);
//				
//				resultSet = statement.executeQuery();
//				while (resultSet.next()) {
//					NewsModel news = new NewsModel();
//					news.setId(resultSet.getLong("id"));
//					news.setTitle(resultSet.getString("title"));
//					news.setThumbnail(resultSet.getString("thumbnail"));
//					news.setShortDescription(resultSet.getString("shortdescription"));
//					news.setContent(resultSet.getString("content"));
//					news.setCategoryId(resultSet.getLong("categoryid"));
//					
//					results.add(news);
//				}
//				return results;
//			} catch (SQLException e) {
//				return null;
//			} finally {
//				try {
//					if (connection != null) {
//						connection.close();
//					}
//					if (statement != null) {
//						statement.close();
//					}
//					if (resultSet != null) {
//						resultSet.close();
//					}
//				} catch (SQLException e) {
//					return null;
//				}
//			}
//		}
//		return null;
//	}
//
//}
