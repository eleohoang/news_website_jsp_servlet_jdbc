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
//import vnnews.dao.ICategoryDAO;
//import vnnews.model.CategoryModel;
//
//public class CategoryDAO_old extends AbstractDAO<CategoryModel> implements ICategoryDAO {
//	
//	public Connection getConnection() {
//		try {
//			//load driver bang Class.forName file lang
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			//login to MySQL by user, password
//			String url = "jdbc:mysql://localhost:3306/vnnews";
//			String user ="root";
//			String password ="1234";
//			return DriverManager.getConnection(url, user, password);
//		} catch (ClassNotFoundException | SQLException e) {
//			return null;
//		}
//	}
//	
//	
//	@Override
//	public List<CategoryModel> findAll() {
//		List<CategoryModel> results = new ArrayList<>();
//		String sql = "SELECT * FROM category";
//		
//		//open connection
//		Connection connection = getConnection();
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		
//		if (connection != null) {
//			try {
//				//excute sql
//				statement = connection.prepareStatement(sql);
//				resultSet = statement.executeQuery();
//				while (resultSet.next()) {
//					CategoryModel category = new CategoryModel();
//					category.setId(resultSet.getLong("id"));
//					category.setCode(resultSet.getString("code"));
//					category.setName(resultSet.getString("name"));
//					results.add(category);
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
//}
