package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

	/**
	 * ログインIDとパスワードに紐づくユーザ情報を返す
	 * @param loginId
	 * @param password
	 * @return
	 */
	public User findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			// 必要なデータのみインスタンスのフィールドに追加
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			return new User(loginIdData, nameData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	/**
	 * 全てのユーザ情報を取得する
	 * @return
	 */
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			// TODO: 未実装：管理者以外を取得するようSQLを変更する
			String sql = "SELECT * FROM user WHERE login_id not in ('admin')";

			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	public void newUser(String loginId, String password, String userName, String birthDate) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			// INSERT文を準備
			String sql = "INSERT INTO user (login_id,name,birth_date,password,create_date,update_date) VALUES  (?,?,?,?,now(),now())";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, loginId);
			pStmt.setString(2, userName);
			pStmt.setString(3, birthDate);
			pStmt.setString(4, password);

			int result = pStmt.executeUpdate();
			System.out.println(result);

			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public User findDetails(String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user  WHERE id = ?";
			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			ResultSet rs = pStmt.executeQuery();

			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			// 必要なデータのみインスタンスのフィールドに追加
			int id1 = rs.getInt("id");
			String loginId = rs.getString("login_id");
			String name = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String password = rs.getString("password");
			String recordTime = rs.getString("create_date");
			String updateTime = rs.getString("update_date");
			User user = new User(id1, loginId, name, birthDate, password, recordTime, updateTime);
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	public void updateUser(String userName, String birthDate, String password, String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			// INSERT文を準備
			String sql = "UPDATE  user SET name = ?,birth_date = ?,password = ?,update_date = now() WHERE id =?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, userName);
			pStmt.setString(2, birthDate);
			pStmt.setString(3, password);
			pStmt.setString(4, id);

			int result = pStmt.executeUpdate();
			System.out.println(result);

			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updateUser2(String userName, String birthDate, String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			// INSERT文を準備
			String sql = "UPDATE  user SET name = ?,birth_date = ?,update_date = now() WHERE id =?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, userName);
			pStmt.setString(2, birthDate);
			pStmt.setString(3, id);

			int result = pStmt.executeUpdate();
			System.out.println(result);

			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteUser(String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			// INSERT文を準備
			String sql = "DELETE FROM user  WHERE id =?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, id);

			int result = pStmt.executeUpdate();
			System.out.println(result);

			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<User> search(String loginId2, String name2, String dateStart, String dateEnd) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			// TODO: 未実装：管理者以外を取得するようSQLを変更する
			String sql = "SELECT * FROM user WHERE login_id not in ('admin')";

			if(!loginId2.equals("")) {
				//ANDの前に半角スペース忘れずに
				//完全一致
				sql += " AND login_id = '" + loginId2 + "'";
			}

			if(!name2.equals("")) {
				//部分一致
				sql += " AND name  LIKE + '" + "%" + name2 + "%" + "'";
			}

			if(!dateStart.equals("")) {
				//大なり小なり
				sql += " AND birth_date >= '" + dateStart + "'";
			}
			if(!dateEnd.equals("")) {
				sql += " AND birth_date <= '" + dateEnd + "'";
			}


			System.out.println(sql);

			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}
}
