<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
	<link rel="stylesheet" href="${APP_PATH}/css/bootstrap.min.css" />
	<script type="text/javascript" src="${APP_PATH}/js/jquery-1.11.0.min.js" ></script>
	<script type="text/javascript" src="${APP_PATH}/js/bootstrap.min.js" ></script>
<html>
<head>
	<%-- <nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<div class="navbar-brand">
					<div><a style="color: gray;text-decoration:none; " href="/BookAppointment/books/list">图书预约系统</a></div>
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
	</nav> --%>
    <title>预约图书列表</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>您已预约图书列表</h2>
        </div>
		<div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr> 
                    <th>预定学号</th>
                    <th>预约时间</th>
                    <th>图书ID</th> 
                    <th>图书名称</th>
                    <th>图书简介</th>
                    <th>预约数量</th>  
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appointList}" var="sk">
                    <tr>
                    	<td>${sk.studentId}</td> 
                        <td>${sk.appointTime}</td>
                        <td>${sk.bookId}</td>
                        <td>${sk.book.getName()}</td>
                        <td>${sk.book.getIntrod()}</td> 
                        <td>1</td> 
                    </tr>
                </c:forEach>
                </tbody>
            </table> 
        </div> 
    </div>
</div> 
<script type="text/javascript">
	$("#exit").click(function(){
		window.location.href="exit";
	});
</script>
</body>
</html>