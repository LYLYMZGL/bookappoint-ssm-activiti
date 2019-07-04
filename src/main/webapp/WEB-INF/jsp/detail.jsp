<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>预约详情页</title>
    <%@include file="common/head.jsp" %>
    <%--jQery文件,务必在bootstrap.min.js之前引入--%>
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
	
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
     	   	<h2>图书详情</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>图书ID</th>
                    <th>图书名称</th> 
                    <th>图书简介</th>
                    <th>剩余数量</th>
                    <th>预约数量</th>
                </tr>
                </thead>
                <tbody>
                	<tr>
                		<td id="bookid">${book.bookId}</td>
                		<td>${book.name}</td>
                		<td>${book.introd}</td>
                		<td>${book.number}</td>
                		<td>1</td>
                	</tr>  
                </tbody>
             </table> 
           </div>  
           <div class="panel-body text-center">
            	<h2 class="text-danger">  
            		<!--用来展示预约控件-->
            		<span class="glyphicon btn btn-default btn-lg" id="appoint-box">预约</span> <!--在js里面调用这个id还可以动态显示一些其他东西，例如动态时间等（需要插件）-->
            		 
            		<%-- <span class="glyphicon"><a id="appointed" class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/books/appoint?studentId=${s.studentId}" target="_blank">查看我的已预约书籍</a></span> --%>
            		<span class="glyphicon"><a id="appointed" class="btn btn-primary btn-lg" href="${APP_PATH}/books/appoint?studentId=${sessionScope.student.studentId}">查看我的已预约书籍</a></span>
            	</h2>           <!--如何获取该页面弹出层输入的学生ID， 传给上面的url-->
        	</div>
    </div>	 	
            		  
</div>

</body>



<script type="text/javascript">
	$("#appoint-box").click(function(){
		$.ajax({
			url:"${APP_PATH}/books/appointment",
			method:"post",
			data:{
				"bookId":$("#bookid").text(),
				"studentId":"${sessionScope.student.studentId}"
			},
			success:function(data){
				if(data.success){
					if(data.data.state==1){
						$("#appoint-box").text(data.data.stateInfo);
						$("#appoint-box").removeClass("btn-default");
						$("#appoint-box").addClass("btn-success");
					}else if(data.data.state==0){
						$("#appoint-box").text(data.data.stateInfo);
						$("#appoint-box").removeClass("btn-default");
						$("#appoint-box").addClass("btn-danger");
					}else if(data.data.state==-1){
						$("#appoint-box").text(data.data.stateInfo);
						$("#appoint-box").removeClass("btn-default");
						$("#appoint-box").addClass("btn-danger");
					}else if(data.data.state==-2){
						$("#appoint-box").text(data.data.stateInfo);
						$("#appoint-box").removeClass("btn-default");
						$("#appoint-box").addClass("btn-danger");
					}
				}
				/* else{
					alert("预约失败");
				} */
			},
			dataType:"json"
		});
	});
	
	$("#exit").click(function(){
		window.location.href="${APP_PATH}/books/exit";
	});
</script>
</html> 