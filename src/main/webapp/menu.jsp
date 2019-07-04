<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单</title>
		<link rel="stylesheet" href="${APP_PATH}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${APP_PATH}/js/jquery-1.11.0.min.js" ></script>
		<script type="text/javascript" src="${APP_PATH}/js/bootstrap.min.js" ></script>
</head>
	<body >
		<div class="container a">
			<a href="${APP_PATH}/books/list" target="right" style="text-decoration: none;"><button class="btn btn-info center-block">图书列表</button></a><br /><br />
			<a href="${APP_PATH}/bpmn/BookAppoint3.png" target="right" style="text-decoration: none;"><button class="btn btn-info center-block">查看流程</button></a><br /><br />
			<a href="${APP_PATH}/books/queryCurrentTask" id="currentTask" target="right" style="text-decoration: none;"><button class="btn btn-info center-block">当前任务</button></a><br /><br />
			<a href="${APP_PATH}/books/queryHisTask/${sessionScope.student.studentId}" target="right" style="text-decoration: none;"><button class="btn btn-info center-block">历史任务</button></a><br /><br />
		</div>
		<script type="text/javascript">
			/* $("#currentTask").click(function(){
				if(${empty sessionScope.taskName}){
					$("#currentTask").attr("href","${APP_PATH}/error.jsp");
				}
			}); */
		</script>
	</body>
</html>