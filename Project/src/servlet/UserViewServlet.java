package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserViewServlet
 */
@WebServlet("/UserViewServlet")
public class UserViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//// TODO 未実装：ログインセッションがない場合、ログイン画面にリダイレクトさせる

		Common common = new Common();
		if (common.getSessionUserInfoBool(request)) {
			response.sendRedirect("LoginServlet");
			return;
		}

		//		if (session == null) {
		//			response.sendRedirect("LoginServlet");
		//			return;
		//		}else {

		// ユーザ一覧情報を取得
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		// ユーザ一覧のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO  未実装：検索処理全般
		request.setCharacterEncoding("UTF-8");

		String loginId2 = request.getParameter("login-id");
		String name2 = request.getParameter("user-name");
		String dateStart = request.getParameter("date-start");
		String dateEnd = request.getParameter("date-end");

		UserDao userDao = new UserDao();
		List<User> userList = userDao.search(loginId2, name2, dateStart, dateEnd);

		request.setAttribute("userList", userList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserView.jsp");
		dispatcher.forward(request, response);

	}

}
