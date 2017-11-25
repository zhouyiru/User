<%@ page language="java" import="java.util.*,com.bean.entity.OnLineBean" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'online.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->



<style type="text/css">
	.orange { 
	color: #fef4e9; 
	border: solid 1px #da7c0c; 
	background: #f78d1d; 
	background: -webkit-gradient(linear, left top, left bottom, from(#faa51a), to(#f47a20)); 
	background: -moz-linear-gradient(top, #faa51a, #f47a20); 
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a', endColorstr='#f47a20'); 
	} 
	.blue { 
	color: #d9eef7; 
	border: solid 1px #0076a3; 
	background: #0095cd; 
	background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5)); 
	background: -moz-linear-gradient(top, #00adee, #0078a5); 
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#0078a5'); 
	} 
	.pink { 
	color: #feeef5; 
	border: solid 1px #d2729e; 
	background: #f895c2; 
	background: -webkit-gradient(linear, left top, left bottom, from(#feb1d3), to(#f171ab)); 
	background: -moz-linear-gradient(top, #feb1d3, #f171ab); 
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#feb1d3', endColorstr='#f171ab'); 
	} 
	.green { 
	color: #e8f0de; 
	border: solid 1px #538312; 
	background: #64991e; 
	background: -webkit-gradient(linear, left top, left bottom, from(#7db72f), to(#4e7d0e)); 
	background: -moz-linear-gradient(top, #7db72f, #4e7d0e); 
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#7db72f', endColorstr='#4e7d0e'); 
	} 
	 #id{
    position:fixed;
    top:0;
    left:0;
    bottom:0;
    right:0;
    z-index:-1;
}
    #id > img {
    height:100%;
    width:100%;
    border:0;
}    
    #img{
    margin:0 0 0 0;
    }
    #main{
    margin:-100px 0 300px 0;
    }
	.raised{background:transparent;width:13%; margin-left: 575px}
	.raised h1,.raised p{margin:0 0 0 2px;}
	.raised h1{font-size:2em;color:#000000 ;}
	.raised p{padding-bottom:0.5em;}
	.raised .b1,.raised .b2,.raised .b3,.raised .b4,.raised .b1b,.raised .b2b,.raised .b3b,.raised .b4b{display:block;overflow:hidden;font-size:1px;}
	.raised .b1,.raised .b2,.raised .b3,.raised .b1b,.raised .b2b,.raised .b3b{height:1px;}
	.raised .b2{background:#FF99CC ;border-left:1px solid #99FF99 ;border-right:1px solid #99FF99 ;}
	.raised .b3{background:#FF99CC ;border-left:1px solid #99FF99 ;border-right:1px solid #99FF99 ;}
	.raised .b4{background:#FF99CC ;border-left:1px solid #99FF99 ;border-right:1px solid #99FF99 ;}
	.raised .b4b{background:#FF99CC ;border-left:1px solid #99FF99 ;border-right:1px solid #99FF99 ;}
	.raised .b3b{background:#FF99CC ;border-left:1px solid #99FF99 ;border-right:1px solid #99FF99 ;}
	.raised .b2b{background:#FF99CC ;border-left:1px solid #99FF99 ;border-right:1px solid #99FF99 ;}
	.raised .b1{margin:0 5px;background:#FF99CC ;}
	.raised .b2, .raised .b2b{margin:0 3px;border-width:0 2px;}
	.raised .b3, .raised .b3b{margin:0 2px;}
	.raised .b4, .raised .b4b{height:1px; margin:0 1px;}
	.raised .b1b{margin:0 5px; background:#FFFFCC;}
	.raised .boxcontent{display:block;background:#FFFFCC;border-left:1px solid #FFFFCC;border-right:1px solid #FFFFCC;}
    
</style>
  </head>
  
  <body>
    
  <div id="id"><img src="image/2.jpg" /></div>
  
	<div class="raised">
	<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
	<div class="boxcontent">
	<h1 align="center"><font style="font-family: 楷体">在线用户</font></h1>
	</div>
	<b class="b4b"></b><b class="b3b"></b><b class="b2b"></b><b class="b1b"></b>
	</div>
	<a href="MyFindJsp.jsp"><b>返回会员中心</b></a>
  <hr>
    <table align="center" border="2" width="600px" bgcolor="white">
    	<tr bgcolor="white">
    		<th>编号</th>
    		<th>登陆名</th>
    		<th>登录时间</th>
    		<th>最后访问时间</th>
    		<th>IP</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${sessionScope.list }" var="bean" varStatus="varSta">
    	<tr>
    		<td >${varSta.count }</td>
    		<td >${bean.name }</td>
    		<td >${bean.loginTime }</td>
    		<td >${bean.lastTime }</td>
    		<td >${bean.ip }</td>
    		<td ><a href="${pageContext.request.contextPath }/KickOutServlet?sessionId=${bean.sessionId}">踢除</a></td>
    	</tr>
    	</c:forEach>
    </table>
    
  </body>
</html>
