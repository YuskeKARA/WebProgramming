<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>UserDelete</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li class="navbar-text">${userInfo.name}さん</li>
				<li class="dropdown"><a href="LoginServlet"
					class="navbar-link logout-link">ログアウト</a></li>
			</ul>
		</div>
	</nav>
	<form action="UserDeleteServlet" method="post">
		<input type="hidden" name="id" value="${details.id }" />
		<h1 class="hello">ユーザ削除確認</h1>
		<div class="login-area">
			<div class="hello">
				<div class="row">
					<div class="col-md-6">
						<h4>
							<label for="loginId">ログインID:</label>
						</h4>
					</div>
					<div class="col-md-6">${details.loginId }</div>
				</div>
				さんを本当に削除してよろしいでしょうか。
				<div class="row">
					<div class="col-md-6">
						<button onclick="location.href=UserViewServlet">キャンセル</button>
					</div>
					<div class="col-md-6">
						<button type="submit" class="btn ">削除</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>