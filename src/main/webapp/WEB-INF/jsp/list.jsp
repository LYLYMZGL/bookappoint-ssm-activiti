<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>图书列表</title>
    <%@include file="common/head.jsp" %>
    <link rel="stylesheet" href="${APP_PATH}/css/bootstrap.min.css" />
    <script type="text/javascript" src="${APP_PATH}/js/jquery-1.11.0.min.js" ></script>
    <script type="text/javascript" src="${APP_PATH}/js/bootstrap.min.js" ></script>
</head>
<body>
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
<div class="container-fluid">
    
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>图书列表</h2>
        </div>
        <form name="firstForm" action="<%= request.getContextPath()%>/books/search" method="post">
        	<div class="panel-heading ">
        	    <table class="table table-bookName">
        	       <thead>
        	       		<tr> 
        					<th width="90" align="lift">图书名称：</th>
        					<th width="150" align="lift">
        						<input class="form-control" type="text" name="name" class="allInput" value="${name}" placeholder="输入检索书名^o^" />
        					</th>
        					<th> 
        						<input class="btn btn-primary" type="submit" id="tabSub" value="检索" /> 
        					</th> 
        				</tr> 
        	       </thead> 
        	    </table> 
         	</div>
        </form>
       
        
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>图书ID</th>
                    <th>图书名称</th>
                    <th>馆藏数量</th> 
                    <th>详细</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="list">
                    <tr>
                        <td>${list.bookId}</td>
                        <td>${list.name}</td>
                        <td>${list.number}</td>
                        <td><a class="btn btn-info" href="${APP_PATH}/books/${list.bookId}/detail">详细</a></td>
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