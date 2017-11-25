<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.bean.entity.*,com.bean.dao.*,com.bean.dao.impl.*,java.sql.*,java.util.Random,java.lang.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>意中人· 相亲网登陆首页</title>
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
    #id1{
    position:fixed;
    top:0;
    left:0;
    bottom:0;
    right:0;
    z-index:-1;
}
    #id1 > img {
    height:100%;
    width:100%;
    border:0;
}    
    #id2{
    
    top:0;
    left:0;
    bottom:0;
    right:0;
    z-index:-1;
    margin:180px 0 0 490px;
}
 
    #main{
	width:400px;
	height:300px;	
	margin:0 0 0 490px;
	background-repeat:no-repeat;
	background-position:top center;
    }
	#table{
	     margin:-210px 50px 0 70px;
	}
	#btn{
	     margin:10px 0 0 120px;

	}
	#code {
	margin:0 0 0 -170px; 
	
	}
  
</style>
</head>
<body>
<div id="id1"><img src="image/1.jpg" /></div>
<div id="id2"><img src="image/3.png" /></div>
<div id="main">
   <form action="doLoginServlet" method="post">
     <div id="table">
     <table style="width:300px;">
       <tr>
        <td width="20%"><font style="font-family: 宋体" color="#663399"><b>用户名</b></font></td>
        <td ><input style="width:130px;" id="nameinput" type="text" name="username" onblur="checkName()"><span id="nameTip" ></span></td>
       </tr>

       <tr>
        <td width="20%"><font style="font-family: 宋体" color="#663399"><b>密&nbsp;码</b></font></td>
        <td><input style="width:130px;" id="pwdinput" type="password" name="userpwd" onblur="checkPwd()"><span id="pwdTip"></span></td>
       </tr>
      <tr>
	  	<td width="20%"><font style="font-family: 宋体" color="#663399"><b>验证码</b></font></td>
	  	<td>
	  		<input id="codeinput" class="txt" type="text" style="width:60px;" name="code" onblur="checkCode()"/></td>
	  
	  <td><div id="code">
	  <img id="imageCode" src="${pageContext.request.contextPath }/ImageServlet" title="看不清？点击换一张" onmousedown="changeImage()"/>
	  </div>
	  </td>
	  </tr>
	  <tr>
	  	<td id="msg" colspan="2" style="color:red;" align="center">
	  	</td>
	  </tr>
     
     </table>
     </div>
   <div id="btn">
   <input type="submit" class="button blue" value="登陆" />&nbsp;
   <input type="button" class="button orange" value="注册" onclick="window.location.href='Regist.jsp'"/> 
   </div>
   
   </form>
</div>

</body>

<script type="text/javascript">
  function checkName(){
        var nameinput = document.getElementById("nameinput").value;
		var reg = /^[\u4e00-\u9fa5]+$/;
		var nameTip = document.getElementById("nameTip");
			if(reg.test(nameinput)){
		    
			nameTip.innerHTML = "格式正确".fontcolor("green");
			return true;
		}else{
			nameTip.innerHTML = "格式错误".fontcolor("red");
			return false;
		}
	}
function checkPwd(){
        var pwdinput = document.getElementById("pwdinput").value;
		var reg = /^[a-zA-Z0-9]+$/;
		var pwdTip = document.getElementById("pwdTip");
		if(reg.test(pwdinput)){
		    
			pwdTip.innerHTML = "格式正确".fontcolor("green");
			
			return true;
		}else{
			pwdTip.innerHTML = "格式错误".fontcolor("red");
			return false;
		}
   }

   function changeImage(){
   
  				var code = document.getElementById("imageCode");
  				code.src = "${pageContext.request.contextPath }/ImageServlet?code="+Math.random();
  				//带个随机数，以免浏览器中存留缓存相同
  				
  }
 
</script>
</html>