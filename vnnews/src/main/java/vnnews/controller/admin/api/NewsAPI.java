package vnnews.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import vnnews.model.NewsModel;
import vnnews.model.UserModel;
import vnnews.service.INewsService;
import vnnews.utils.HttpUtil;
import vnnews.utils.SessionUtil;

// declare web-servlet to receive request 
@WebServlet(urlPatterns = { "/api-admin-news" })
public class NewsAPI extends HttpServlet {
	private static final long serialVersionUID = 6149966327413867968L;

	@Inject
	private INewsService newService;

	// add news
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// devoid font error when receive data from client
		request.setCharacterEncoding("UTF-8");

		// declare data type respond for client
		response.setContentType("application/json");

		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newsModel.setCreatedBy( ((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		newsModel = newService.create(newsModel);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), newsModel);
	}

	// update news
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// devoid font error when receive data from client
		request.setCharacterEncoding("UTF-8");

		// declare data type respond for client
		response.setContentType("application/json");

		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newsModel.setModifiedBy( ((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		newsModel = newService.update(newsModel);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), newsModel);
	}

	// delete news
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// devoid font error when receive data from client
		request.setCharacterEncoding("UTF-8");

		// declare data type respond for client
		response.setContentType("application/json");

		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		
		newService.delete(newsModel.getIds());
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), "{}");

	}

}
