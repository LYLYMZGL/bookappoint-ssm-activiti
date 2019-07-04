<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书预约系统</title>
		<link rel="stylesheet" href="${APP_PATH}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${APP_PATH}/js/jquery-1.11.0.min.js" ></script>
		<script type="text/javascript" src="${APP_PATH}/js/bootstrap.min.js" ></script>
</head>
	<frameset rows="10%,*" frameborder="0">
		<frameset>
			<frame src="${APP_PATH}/top.jsp" />
		</frameset>
		<frameset cols="10%,*">
			<frame src="${APP_PATH}/menu.jsp" />
			<frame src="${APP_PATH}/welcome.jsp" name="right"/>
		</frameset>
	</frameset>
</html>