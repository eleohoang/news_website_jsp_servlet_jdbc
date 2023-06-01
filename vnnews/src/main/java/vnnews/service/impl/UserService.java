package vnnews.service.impl;

import javax.inject.Inject;

import vnnews.dao.IUserDAO;
import vnnews.model.UserModel;
import vnnews.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public UserModel findByUserName_Password_Status(String userName, String password, Integer status) {
		return userDAO.findByUserName_Password_Status(userName, password, status);
	}

}
