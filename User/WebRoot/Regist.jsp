<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.bean.dao.*,com.bean.entity.*,java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>意中人· 相亲网注册页面</title>
    
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
    .raised{background:transparent;width:20%; margin-left: 540px}
	.raised h1,.raised p{margin:0 0 0 2px;}
	.raised h1{font-size:2em;color:#000000 ;}
	.raised p{padding-bottom:0.5em;}
	.raised .b1,.raised .b2,.raised .b3,.raised .b4,.raised .b1b,.raised .b2b,.raised .b3b,.raised .b4b{display:block;overflow:hidden;font-size:1px;}
	.raised .b1,.raised .b2,.raised .b3,.raised .b1b,.raised .b2b,.raised .b3b{height:1px;}
	.raised .b2{background:#FFFFFF ;border-left:1px solid #FFFFFF ;border-right:1px solid #FFFFFF ;}
	.raised .b3{background:#FFFFFF ;border-left:1px solid #FFFFFF ;border-right:1px solid #FFFFFF ;}
	.raised .b4{background:#FFFFFF ;border-left:1px solid #FFFFFF ;border-right:1px solid #FFFFFF ;}
	.raised .b4b{background:#FFFFFF ;border-left:1px solid #FFFFFF ;border-right:1px solid #FFFFFF ;}
	.raised .b3b{background:#FFFFFF ;border-left:1px solid #FFFFFF ;border-right:1px solid #FFFFFF ;}
	.raised .b2b{background:#FFFFFF ;border-left:1px solid #FFFFFF ;border-right:1px solid #FFFFFF ;}
	.raised .b1{margin:0 5px;background:#FF99CC ;}
	.raised .b2, .raised .b2b{margin:0 3px;border-width:0 2px;}
	.raised .b3, .raised .b3b{margin:0 2px;}
	.raised .b4, .raised .b4b{height:1px; margin:0 1px;}
	.raised .b1b{margin:0 5px; background:#FFCCFF;}
	.raised .boxcontent{display:block;background:#FFCCFF;border-left:1px solid #FFCCFF;border-right:1px solid #FFCCFF;}
 

	</style>	
  </head>
  <body>
  <div id="id"><img src="image/2.jpg" /></div>
  	<div class="raised">
	<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
	<div class="boxcontent">
	<h1 align="center"><font style="font-family: 楷体">会员注册信息表</font></h1>
	</div>
	<b class="b4b"></b><b class="b3b"></b><b class="b2b"></b><b class="b1b"></b>
	</div>
  <h5 align="center">（标记<font color=red>*</font>为必填项）</h5>
  <hr>
  
   			
   			
 
   		
<form action="${pageContext.request.contextPath}/RegistServlet" method="post" >
  <table border="3" cellspacing="2" cellpadding="2" bordercolor="white" align="center" width="320" bgcolor="#FFCCFF">
  <tr>
  <td>用户名<font color=red>*</font>&nbsp;<input type="text" id="nameinput" name="username" onblur="checkName()"><span id="nameTip"></span><br></td>
  </tr>
  <tr>
  <td>密&nbsp;码<font color=red>*</font>&nbsp;<input type="text" id="pwdinput" name="userpwd" onblur="checkPwd()"><span id="pwdTip"></span><br></td>
  </tr>
  <tr>
  <td>年&nbsp;龄<font color=red>*</font>&nbsp;<input type="text" id="ageinput" name="userage" onblur="checkAge()"><span id="ageTip"></span><br></td>
  </tr>
  <tr>
  <td>性&nbsp;别&nbsp;&nbsp;&nbsp;<input type="radio" id="genderinput" name="usergender" value="男" checked>男&nbsp;<input type="radio" name="usergender" value="女">女</td>
  </tr>
  <tr>
  <td>学&nbsp;历&nbsp;&nbsp;&nbsp;&nbsp;<select name="useredu">
      <option value="小学">小学</option>
      <option value="初中">初中</option>
      <option value="高中">高中</option>
      <option value="大学" selected>大学</option>
      <option value="研究生">研究生</option>
      <option value="研究生">硕士</option>
      <option value="研究生">博士</option>
  </select></td>
  </tr>
  <tr>
  <td>电&nbsp;话<font color=red>*</font>&nbsp;<input type="text" name="userphone" id="phoneinput" onblur="checkPhone()"><span id="phoneTip"></span><br></td>
  </tr>
  <tr>
  <td>邮&nbsp;箱<font color=red>*</font>&nbsp;<input type="text" name="email" id="emailinput" onblur="checkEmail()"><span id="emailTip"></span><br></td>
  </tr>
  <tr>
  <td>住&nbsp;址<font color=red>*</font>&nbsp;<input type="text" name="useraddress" id="addressinput" onblur="checkAddress()"><span id="addressTip"></span><br></td>
  </tr>
  <tr>
  <td colspan="2" align="center"><input type="submit" id="submit" class="button blue" value="注册" disabled >
  <input type="reset" value="重置" class="button orange"></td>
  </tr>
  </table>
  </form>

   <table align="center">
   <tr>
   <td align="center"><a href="Login.jsp">返回首页登录</a></td>
   </tr>
   </table>
  
  </body>
  <script type="text/javascript">
    function checkName(){
		  var nameinput = document.getElementById("nameinput").value;
		  var reg = /^[\u4e00-\u9fa5]+$/;
		  var nameTip = document.getElementById("nameTip");
		  
		  if(reg.test(nameinput)){
		  document.getElementById("submit").disabled=false;
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
		document.getElementById("submit").disabled=false;
			pwdTip.innerHTML = "格式正确".fontcolor("green");
			return true;
		}else{
			pwdTip.innerHTML = "格式错误".fontcolor("red");
			return false;
		}
	}
  function checkAge(){
		  var ageinput = document.getElementById("ageinput").value;
		  var reg = /^[0-9]{1,2}$/;
		  var ageTip = document.getElementById("ageTip");
		  if(reg.test(ageinput)){
		  document.getElementById("submit").disabled=false;
		  ageTip.innerHTML = "格式正确".fontcolor("green");
		  return true;
		  }else{
		  ageTip.innerHTML = "格式错误".fontcolor("red");
		  return false;
		  }
	}
  function checkPhone(){
		  var phoneinput = document.getElementById("phoneinput").value;
		  var reg = /^[0-9]{11}$/;
		  document.getElementById("submit").disabled=false;
		  var phoneTip = document.getElementById("phoneTip");
		  if(reg.test(phoneinput)){
		  phoneTip.innerHTML = "格式正确".fontcolor("green");
		  return true;
		  }else{
		  phoneTip.innerHTML = "格式错误".fontcolor("red");
		  return false; 
		  }
	 }
	  function checkEmail(){
		  var emailinput = document.getElementById("emailinput").value;
		  var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  var emailTip = document.getElementById("emailTip");
		  if(reg.test(emailinput)){
		  document.getElementById("submit").disabled=false;
		  emailTip.innerHTML = "格式正确".fontcolor("green");
		  return true;
		  }else{
		  emailTip.innerHTML = "格式错误".fontcolor("red");
		  return false;
		  }
		}
   function checkAddress(){
		  var addressinput = document.getElementById("addressinput").value;
		  var reg = /^[\u4e00-\u9fa5]+$/;
		  var addressTip = document.getElementById("addressTip");
		  if(reg.test(addressinput)){
		  document.getElementById("submit").disabled=false;
		  addressTip.innerHTML = "格式正确".fontcolor("green");
		  return true;
		  }else{
		  addressTip.innerHTML = "格式错误".fontcolor("red");
		  return false;
		  }
		}
  
  </script>
</html>
