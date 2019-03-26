package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import dao.UserDao;

/**
 * Servlet implementation class MyUserRecord
 */
@WebServlet("/UserRecordServlet")
public class UserRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRecordServlet() {
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserRecord.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータの文字コードを指定

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String userName = request.getParameter("userName");
		String birthDate = request.getParameter("birthDate");



		//登録に失敗したとき
		if (!password.equals(password2)) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "登録に失敗しました。");

			 request.setAttribute("loginId", loginId);
			 request.setAttribute("userName", userName);
			 request.setAttribute("birthDate", birthDate);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserRecord.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(loginId.equals("")|| password.equals("") || password2.equals("") || userName.equals("") || birthDate.equals("")) {
			request.setAttribute("errMsg", "登録に失敗しました。");

			 request.setAttribute("loginId", loginId);
			 request.setAttribute("userName", userName);
			 request.setAttribute("birthDate", birthDate);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MyUserRecord.jsp");
			dispatcher.forward(request, response);
			return;
		}

		UserDao userDao = new UserDao();
		userDao.newUser(loginId, password, userName, birthDate);
		request.setAttribute("msg", "登録しました。");

		response.sendRedirect("UserViewServlet");

	}

}
