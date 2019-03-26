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
<title>login</title>
</head>
<body>
	<h1 class="hello">ログイン画面</h1>
	<form action="LoginServlet" method="post">
		<div class="login-area">

			<div class="hello">
				<div class="row">
					<div class="col-md-6">
						<h4>
							<label for="loginId">ログインID</label>
						</h4>
					</div>
					<div class="col-md-6">
						<input type="text" id="loginId" name="loginId" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>
							<label for="password">パスワード</label>
						</h4>
					</div>
					<div class="col-md-6">
						<input type="text" id="password" name="password" />
					</div>
				</div>
				<button type="submit" class="btn ">ログイン</button>
			</div>
			<c:if test="${errMsg != null}">
				<div class="alert alert-danger" role="alert">${errMsg}</div>
			</c:if>
		</div>
	</form>
</body>
</html>