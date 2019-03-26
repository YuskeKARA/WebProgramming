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
<title>userView</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="userCreate.html">ユーザ管理システム</a>
				</div>

				<ul class="nav navbar-nav navbar-right">
					<li class="navbar-text">${userInfo.name}さん</li>
					<li class="dropdown"><a href="LoginServlet"
						class="navbar-link logout-link">ログアウト</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- /header -->

	<!-- body -->
	<div class="container">

		<div class="text-right">
			<a href="UserRecordServlet">新規登録</a>
		</div>


		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">検索条件</div>
				</div>
				<div class="panel-body">
					<form action="UserViewServlet" method="post">
						<div class="form-group">
							<label for="code" class="control-label col-sm-2">ログインID</label>
							<div class="col-sm-6">
								<input type="text" name="login-id" id="login-id"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="control-label col-sm-2">ユーザ名</label>
							<div class="col-sm-6">
								<input type="text" name="user-name" id="user-name"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="continent" class="control-label col-sm-2">生年月日</label>
							<div class="row">
								<div class="col-sm-2">
									<input type="date" name="date-start" id="date-start"
										class="form-control" size="30" />
								</div>
								<div class="col-xs-1 text-center">~</div>
								<div class="col-sm-2">
									<input type="date" name="date-end" id="date-end"
										class="form-control" />
								</div>
							</div>
						</div>
						<div class="text-right">
							<button type="submit" value="検索"
								class="btn btn-primary form-submit">検索</button>
						</div>
					</form>

				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ログインID</th>
							<th>ユーザ名</th>
							<th>生年月日</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList}">
							<tr>
								<td>${user.loginId}</td>
								<td>${user.name}</td>
								<td>${user.birthDate}</td>
								<!-- TODO 未実装；ログインボタンの表示制御を行う -->
								<td><a class="btn btn-primary"
									href="UserDetailsServlet?id=${user.id}">詳細</a>
									<c:if test="${userInfo.loginId == 'admin' || userInfo.loginId == user.loginId}">
										<a class="btn btn-success" href="UserUpdateServlet?id=${user.id}">更新</a>
									</c:if>
									<c:if test="${userInfo.loginId == 'admin'}">
										<a class="btn btn-danger"
											href="UserDeleteServlet?id=${user.id}">削除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>