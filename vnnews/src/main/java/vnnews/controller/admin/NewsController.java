package vnnews.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnnews.constant.SystemConstant;
import vnnews.model.NewsModel;
import vnnews.paging.PageRequest;
import vnnews.paging.Pageble;
import vnnews.service.ICategoryService;
import vnnews.service.INewsService;
import vnnews.sort.Sorter;
import vnnews.utils.FormUtil;

@WebServlet(urlPatterns ={"/admin-news"})
public class NewsController extends HttpServlet{
	private static final long serialVersionUID = 4351488574355757708L;
	
	@Inject
	INewsService newsService;
	
	@Inject
	ICategoryService categoryService;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsModel model = FormUtil.toModel(NewsModel.class, request);
		String view = "";
		if (model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newsService.findAll(pageble));
			model.setTotalItem(newsService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/news/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = newsService.findOne(model.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/news/edit.jsp";
		}
		if (request.getParameter("message") != null) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			request.setAttribute("message", resourceBundle.getString(message));
			request.setAttribute("alert", alert);
		}
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
}
