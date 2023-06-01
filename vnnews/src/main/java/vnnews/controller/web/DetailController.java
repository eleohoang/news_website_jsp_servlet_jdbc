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
import vnnews.model.CommentModel;
import vnnews.model.NewsModel;
import vnnews.model.UserModel;
import vnnews.paging.PageRequest;
import vnnews.paging.Pageble;
import vnnews.service.ICategoryService;
import vnnews.service.ICommentService;
import vnnews.service.INewsService;
import vnnews.service.IUserService;
import vnnews.utils.FormUtil;
import vnnews.utils.SessionUtil;

@WebServlet(urlPatterns ={"/new-detail"})
public class DetailController extends HttpServlet {
	
	@Inject 
	private INewsService newsService;
	
	@Inject
	private ICommentService commentService;
	
	private static final long serialVersionUID = 4351488574355757708L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		NewsModel model = new NewsModel();
		model = newsService.findOne(Long.parseLong(id));
		request.setAttribute(SystemConstant.MODEL, model);
		
		CommentModel commentModel = new CommentModel();
		commentModel.setListResult(commentService.findByNewId(model.getId()));
		request.setAttribute(SystemConstant.COMMENTMODEL, commentModel);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/detail.jsp");
		rd.forward(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
