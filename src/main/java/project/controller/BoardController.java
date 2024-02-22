package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Board;
import project.service.BoardService;
import project.service.BoardServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/bbs/board/list", "/bbs/board/insert", "/bbs/board/update",
			"/bbs/board/delete", "/bbs/board/detail"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService bSvc = new BoardServiceImpl();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		switch(action) {
		case "list":					// /jw/bbs/board/list?p=1&f=title&q=검색
			String page_ = request.getParameter("p");
			String field = request.getParameter("f");
			String query = request.getParameter("q");
			int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			session.setAttribute("currentBoardPage", page);
			field = (field == null || field.equals("")) ? "title" : field;
			query = (query == null || query.equals("")) ? "": query;
			List<Board> boardList = bSvc.getBoardList(page, field, query);
			request.setAttribute("boardList", boardList);
			
			int totalItems = bSvc.getBoardCount();
			int totalPages = (int) Math.ceil(totalItems * 1.0 / bSvc.COUNT_PER_PAGE);
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalPages; i++)
				pageList.add(String.valueOf(i));
			request.setAttribute("pageList", pageList);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
			rd.forward(request, response);
			break;
		
		}
	}

}
