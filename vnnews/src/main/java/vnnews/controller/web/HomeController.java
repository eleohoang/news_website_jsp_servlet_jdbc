package vnnews.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnnews.constant.SystemConstant;
import vnnews.model.CategoryModel;
import vnnews.model.NewsModel;
import vnnews.model.UserModel;
import vnnews.service.ICategoryService;
import vnnews.service.INewsService;
import vnnews.service.IUserService;
import vnnews.utils.FormUtil;
import vnnews.utils.SessionUtil;

@WebServlet(urlPatterns ={"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject 
	private INewsService newsService;
	
	@Inject 
	private IUserService userService;
	
	private static final long serialVersionUID = 4351488574355757708L;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action != null && action.equals("login")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		} else {
			CategoryModel model = new CategoryModel();
			model.setListResult(categoryService.findAll());
			
			List<Long> categoriesIds = new ArrayList<Long>();
			for (CategoryModel i : model.getListResult()) {
				categoriesIds.add(i.getId());
			}
			if (categoriesIds != null) {
				NewsModel newsmodel = new NewsModel();
				newsmodel.setListResult(newsService.findByAllCategoryId(categoriesIds));
				request.setAttribute(SystemConstant.NEWSMODEL, newsmodel);
				
				NewsModel top3Carousel = new NewsModel();
				top3Carousel.setListResult(newsService.findTop());
				request.setAttribute(SystemConstant.CAROUSEL, top3Carousel);
				
				
			}
			request.setAttribute(SystemConstant.MODEL, model);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);	
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.findByUserName_Password_Status(model.getUserName(), model.getPassword(), 1);
			if (model != null) {
				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
				if (model.getRole().getCode().equals("USER")) {
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				} else if (model.getRole().getCode().equals("ADMIN")) {
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
	
}
