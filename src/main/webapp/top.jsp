<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
		<link rel="stylesheet" href="${APP_PATH}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${APP_PATH}/js/jquery-1.11.0.min.js" ></script>
		<script type="text/javascript" src="${APP_PATH}/js/bootstrap.min.js" ></script>
</head>
	<body>
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<div class="navbar-brand">
						<div style="color: gray;">图书预约系统</div>
					</div>
				</div>
				<div class="pull-right navbar-btn">
					<button class="btn btn-warning">
						<label class="glyphicon glyphicon-user"></label>
						${sessionScope.student.nickname}
					</button>
					<button class="btn btn-danger" id="exit">
						<label class="glyphicon glyphicon-log-out"></label>
						退出
					</button>
				</div>
			</div>
		</nav>
		<script type="text/javascript">
		$("#exit").click(function(){
			//退出整个页面（外部退出）
			window.top.location.href="${APP_PATH}/books/exit";
		});
		</script>
	</body>
</html>