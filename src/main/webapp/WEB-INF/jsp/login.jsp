<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${APP_PATH}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${APP_PATH}/js/jquery-1.11.0.min.js" ></script>
		<script type="text/javascript" src="${APP_PATH}/js/bootstrap.min.js" ></script>
<title>用户登录</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<div class="navbar-brand">
					<div><a style="color: gray;text-decoration:none; " href="/BookAppointment/students/login">图书预约系统</a></div>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="center-block" style="width: 300px;margin-top: 100px;">
				<div class="h2 text-center">用户登录</div>
				<form id="form" action="judgelogin" method="post">
					<div class="form-group">
						<label>账号</label>
						<input type="text" id="username" class="form-control" name="username" value="${tasksize }"/>
					</div>
					<div class="form-group">
						<label>密码</label>
						<input type="password" id="password" class="form-control" name="password" />
					</div>
				</form>
				<button id="submit" class="btn-success btn btn-block" style="margin-top: 25px;">登录</button>
			</div>
		</div>
	</div>
	<script>
		$("#submit").click(function(){
			var username= $("#username").val();
			var password=$("#password").val();
			if(username.length==0||username==""){
				alert("用户名不能为空");
				return;
			}
			if(password.length==0||password==""){
				alert("密码不能为空");
				return;
			}
			if(username.length!=10||isNaN(username)||isNaN(password)){
				alert("格式不正确");
				return;
			}
			/* $("#form").submit(); */
			$.ajax({
				url:"judgelogin",
				data:{
					"username":$("#username").val(),
					"password":$("#password").val()
				},
				success:function(data){
					if(data.success){
						/* window.location.href="${APP_PATH}/books/list"; */
						window.location.href="${APP_PATH}/students/index";
					}else{
						alert("账号或密码不正确");
					}
				},
				dataType:"json"
			});
		})
	</script>
</body>
</html>