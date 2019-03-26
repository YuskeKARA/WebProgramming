package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Common common = new Common();
		if (common.getSessionUserInfoBool(request)) {
			response.sendRedirect("LoginServlet");
			return;
		}


		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		System.out.println(id);

		// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する
		UserDao userDao = new UserDao();
		User user = userDao.findDetails(id);

		// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
		request.setAttribute("details", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserUpdateDetails.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String userName = request.getParameter("userName");
		String birthDate = request.getParameter("birthDate");

		//登録に失敗したとき
		if (!password.equals(password2)) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された情報は正しくありません。");

			// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する
			UserDao userDao = new UserDao();
			User user = userDao.findDetails(id);

			// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
			request.setAttribute("details", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserUpdateDetails.jsp");
			dispatcher.forward(request, response);
			return;
		} else if (userName.equals("") || birthDate.equals("")) {
			request.setAttribute("errMsg", "入力された情報は正しくありません。");

			// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する
			UserDao userDao = new UserDao();
			User user = userDao.findDetails(id);

			// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
			request.setAttribute("details", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserUpdateDetails.jsp");
			dispatcher.forward(request, response);
			return;
		}else if (password.equals("") || password2.equals("")) {

			UserDao userDao = new UserDao();
			userDao.updateUser2(userName, birthDate, id);

			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("userInfo");
			user.setName(userName);
			session.setAttribute("userInfo", user);

			response.sendRedirect("UserViewServlet");
			return;
		}
		UserDao userDao = new UserDao();
		userDao.updateUser(userName, birthDate, password, id);

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		user.setName(userName);
		session.setAttribute("userInfo", user);

		response.sendRedirect("UserViewServlet");
	}

}
