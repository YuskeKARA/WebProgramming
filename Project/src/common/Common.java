package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Common {
	public boolean getSessionUserInfoBool(HttpServletRequest request) {
		// セッション情報取得
		HttpSession s = request.getSession();
		// セッションに"userInfo"が存在しない場合true
		//		return s.getAttribute("userInfo") == null
		//				? true : false;
		boolean returnBool = false;
		if (s.getAttribute("userInfo") == null) {
			returnBool = true;
		}
		return returnBool;
	}
}
