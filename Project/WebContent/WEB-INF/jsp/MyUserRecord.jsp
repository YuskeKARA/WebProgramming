<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>UserRecord</title>
</head>
<body>

	<div class="alert alert-dark" role="alert">
		<div class="d-flex flex-row-reverse bd-highlight">
			<ul class="nav navbar-nav navbar-right">
					<li class="navbar-text">${userInfo.name}さん</li>
					<li class="dropdown"><a href="LoginServlet"
						class="navbar-link logout-link">ログアウト</a></li>
				</ul>
		</div>
	</div>
	<form action="UserRecordServlet" method="post">
		<h1 class="hello">ユーザ新規登録</h1>
		<div class="hello">
			<div class="row">
				<div class="col-md-6">
					<h4>
						<label for="loginId">ログインID</label>
					</h4>
				</div>
				<div class="col-md-6">
					<input type="text" id="loginId" name="loginId" value="${loginId }" />
				</div>
				<div class="col-md-6">
					<h4>
						<label for="password">パスワード</label>
					</h4>
				</div>
				<div class="col-md-6">
					<input type="text" id="password" name="password" />
				</div>
				<div class="col-md-6">
					<h4>
						<label for="password2">パスワード(確認)</label>
					</h4>
				</div>
				<div class="col-md-6">
					<input type="text" id="password2" name="password2" />
				</div>
				<div class="col-md-6">
					<h4>
						<label for="userName">ユーザ名</label>
					</h4>
				</div>
				<div class="col-md-6">
					<input type="text" id="userName" name="userName"
						value="${userName }" />
				</div>
				<div class="col-md-6">
					<h4>
						<label for="birthDate">生年月日</label>
					</h4>
				</div>
				<div class="col-md-6">
					<input type="text" id="birthDate" name="birthDate"
						value="${birthDate }" />
				</div>
				<div class="col align-self-start">
					<button type="submit" class="btn ">登録</button>
					<div class="row">
						<div class="col-md-6">
							<a href="#" onclick="history.back(); return false;">戻る</a>
						</div>
					</div>
				</div>
				<c:if test="${errMsg != null}">
					<div class="alert alert-danger" role="alert">${errMsg}</div>
				</c:if>
			</div>
		</div>
	</form>
</body>
</html>