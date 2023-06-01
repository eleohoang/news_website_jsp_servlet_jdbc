package vnnews.service;

import vnnews.model.UserModel;

public interface IUserService {
	UserModel findByUserName_Password_Status(String userName, String password, Integer status);
}
