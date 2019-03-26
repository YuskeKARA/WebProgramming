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
<title>UserDetails</title>
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
		<h1 class="hello">ユーザ情報詳細参照</h1>
		<div class="hello">
			<div class="row">
				<div class="col-md-6">
					<h4>
						<label for="loginId">ログインID</label>
					</h4>
				</div>
				<div class="col-md-6">
					${details.loginId }
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<h4>
						<label for="userName">ユーザ名</label>
					</h4>
				</div>
				<div class="col-md-6">
					${details.name }
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<h4>
						<label for="birthDate">生年月日</label>
					</h4>
				</div>
				<div class="col-md-6">
					${details.birthDate}
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<h4>
						<label for="recordTime">登録日時</label>
					</h4>
				</div>
				<div class="col-md-6">
					${details.createDate}
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<h4>
						<label for="updateTime">更新日時</label>
					</h4>
				</div>
				<div class="col-md-6">
					${details.updateDate}
				</div>
				<div class="col-md-6">
					<a href="#" onclick="history.back(); return false;">戻る</a>
				</div>
			</div>
		</div>

</body>
</html>