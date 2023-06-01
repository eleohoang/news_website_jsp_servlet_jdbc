package vnnews.dao;


import vnnews.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
	UserModel findByUserName_Password_Status(String userName, String password, Integer status);
}
