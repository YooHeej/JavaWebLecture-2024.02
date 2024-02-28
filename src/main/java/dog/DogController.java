package dog;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dog.entity.Dog;
import dog.service.DogService;
import dog.service.DogServiceImpl;

@WebServlet({"/dog/dog/list", "/dog/dog/insert", "/dog/dog/update", "dog/dog/detail"})

public class DogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DogService dSvc = new DogServiceImpl();
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		String bDate = "", breed = "", gender = "", dname = "", field = "", 
				query = "", page_ = "", uId = "";
		Dog dog = null;
		int dogId = 0, page = 0;
		
		String sessUId = (String) session.getAttribute("sessUId");
		
		switch(action) {
		case "list":
			page_ = request.getParameter("p");
			field = request.getParameter("f");
			query = request.getParameter("q");
			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			session.setAttribute("currentDogPage", page);
			field = (field == null || field.equals("")) ? "breed" : field;
			query = (query == null || query.equals("")) ? "": query;
			session.setAttribute("field", field);
			session.setAttribute("query", query);
			List<Dog> dogList = dSvc.getDogList(page, field, query);
			request.setAttribute("dogList",  dogList);
			
			// Pagina
			int totalItems = dSvc.getDogCount(field, query);
			int totalPages = (int) Math.ceil(totalItems * 1.0 / dSvc.COUNT_PER_PAGE);
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalPages; i++)
				pageList.add(String.valueOf(i));
			request.setAttribute("pageList", pageList);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/dog/list.jsp");
			rd.forward(request, response);
			break;
			
		case "insert":
			if (sessUId == null || sessUId.equals("")) {
				response.sendRedirect("/jw/dog/user/login");
				break;
			}	
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/dog/insert.jsp");
				rd.forward(request, response);
			} else {
				bDate = request.getParameter("bDate");
				breed = request.getParameter("breed");
				gender = request.getParameter("gender");
				dname = request.getParameter("dname");
				dog = new Dog(null, breed, gender, dname, sessUId);
				dSvc.insertDog(dog);
				response.sendRedirect("/jw/dog/dog/list?p=1");
			}
			break;
			
		case "update":
			if (method.equals("GET")) {
				dogId = Integer.parseInt(request.getParameter("dogId"));
				dog = dSvc.getDog(dogId);
				request.setAttribute("dog", dog);
				rd = request.getRequestDispatcher("/WEB-INF/view/dog/update.jsp");
				rd.forward(request, response);
			} else {
				dogId = Integer.parseInt(request.getParameter("dogId"));
				uId = request.getParameter("uId");
				breed = request.getParameter("breed");
				gender = request.getParameter("gender");
				dname = request.getParameter("dname");
				
				dSvc.updateBoard(dog);
				response.sendRedirect("/jw/dog/dog/detail?dogId=" + dogId + "&uId=" + uId);
			}
			break;
			
		case "detail":
			dog = dSvc.getDog(dogId);
			request.setAttribute("dog", dog);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/dog/detail.jsp");
			rd.forward(request, response);
			break;
		}
	}

}
