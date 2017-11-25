<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.sql.*,com.bean.dao.UserTableDB,com.bean.entity.UserTable,com.bean.dao.impl.UserTableDBImpl,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>意中人· 相亲网会员中心</title>  
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

	
 <script type="text/javascript">
function ondelete(userid){
  if(confirm("确定删除么？")){
     window.location.href="MyDeleteJsp.jsp?userid="+userid;
  }else{
  return; 
 }
  }
  function onupdate(userid){
  if(confirm("确定修改么？")){
     window.location.href="MyUpdateJsp.jsp?userid="+userid;
  }else{
  return; 
  }
  }
  function check(totalPage)
  {
  
  var reg = /^[1-9]\d*$/;
  var p = document.getElementsByName("p")[0];
 
 if(p.value=="")
  {alert("请输入页码");
  p.focus();
  return false;
  }
 else if(!reg.test(p.value))
 {
  alert("格式错误");
  p.value="";
  return false;
  }
  else if(p.value>totalPage)
  {alert("范围错误");
  p.value="";
  p.focus();
  return false;
  }
  }

  </script>
    
  </head>
    


<body>

 	 
  
  <div id="id"><img src="image/2.jpg" /></div>
  
  
	<div class="raised">
	<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
	<div class="boxcontent">
	<h1 align="center"><font style="font-family: 楷体">会员信息</font></h1>
	</div>
	<b class="b4b"></b><b class="b3b"></b><b class="b2b"></b><b class="b1b"></b>
	</div>
     
    <table align="right" >
     <tr><td align="center">
    <a href="logoutServlet"><font color="white"><b>退出登录</b></font></a></td></tr>
    </table>
    <c:choose>
    <c:when test="${empty sessionScope.username}">
    <b>请先</b><a href="${pageContext.request.contextPath }/Login.jsp"><b>登陆</b></a>
    </c:when>
    <c:otherwise>【<font size="5"><b>${sessionScope.username}</b></font>】——<font color=#FF0000><b>欢迎回来</b></font>
           <font color="#FFFFCC"><a href="${pageContext.request.contextPath }/GetOnlineServlet"><b>【查看在线登录用户】</b></a></font>
    </c:otherwise>
    </c:choose>
    <hr>
  <marquee scrollAmount="8" width="1350" height="20" direction="left" loop="-1" onmouseover="this.stop()" onmouseout="this.start()">
  <a style=color:orange><font style="font-family: 宋体"><b>欢迎来到·意中人·相亲网站！缘在天定，份在人为！意中人迟迟未现，心情是否低落？  意中人相亲网，领航你的幸福之路，帮你找到你的意中人。</b></font></a></marquee>
  <table align="right"><tr><td><a href="showMessage.jsp"><font color="lime"><b>进入论坛</b></font></a></td></tr></table>
  <%--上传头像并显示区 --%>

  <div id="img">
    <form action="${pageContext.request.contextPath }/uploadServlet"   method="post" enctype="multipart/form-data">
       		<table><tr>
       		<td ><input style="text-align:right;" type="file" name="userimg"></td></tr>
       		<tr><td align=center><input type="submit" value="上传头像" class="button pink"></td></tr>
       			</table>
       </form>
       <img width="100px" height="100px" src="${pageContext.request.contextPath}/upload/${sessionScope.userimg}" alt="无头像"/>
     
       
 </div>
 <%--主显示信息区 --%>
 <div id="main">
    <form method="post">
    <table border=1 bordercolor="orange" align="center" width="700px" bgcolor="#FFFFCC">
    <tr>
    <th>姓名搜索</th>
    <td><input type="text" name="find"></td>
    <td colspan="7" align="left"><input type="submit" value="搜索" class="button blue"></td>
    </tr>
     <tr>
     <th bgcolor="#cccccc">编号</th>
     <th bgcolor="#cccccc">姓名</th>
     <th bgcolor="#cccccc">年龄</th>
     <th bgcolor="#cccccc">性别</th>
     <th bgcolor="#cccccc">学历</th>
     <th bgcolor="#cccccc">电话</th>
     <th bgcolor="#cccccc">住址</th>
     <th bgcolor="#cccccc" colspan="2">操作</th>
     </tr>
   <%
       UserTableDBImpl show = new UserTableDBImpl();
    UserTable usertable = new UserTable();
    String p = request.getParameter("p");
    String find = request.getParameter("find");
    String username = (String)session.getAttribute("username");
    session.setAttribute("username", username);
  int curPage;
  if(p==null)
 {
   curPage=1;
 }
 else{
   curPage=Integer.parseInt(p);
 }

 Collection<UserTable> meslist = new ArrayList<UserTable>();
 meslist = show.pageSelect(curPage,find);
    Iterator<UserTable> it = meslist.iterator();
    while(it.hasNext()){
      
      usertable = it.next();
     
  %>
   <tr>
    <td align="center"><%=usertable.getUserid() %></td>
    <td align="center"><%=usertable.getUsername() %></td>
    <td align="center"><%=usertable.getUserage() %></td>
    <td align="center"><%=usertable.getUsergender() %></td>
    <td align="center"><%=usertable.getUseredu() %></td>
    <td align="center"><%=usertable.getUserphone() %></td>
    <td align="center"><%=usertable.getUseraddress() %></td>
    <%--判断非此用户按钮失效 --%>
    <%
   String usernames = usertable.getUsername();
   String[] user = usernames.split(" ");
   for(int i=0;i<user.length;i++){
   if(username!=null){
    if(username.equals(user[i])){
    %>
    <td align="center"><input type='button' value='修改' class="button blue" onclick="onupdate(<%=usertable.getUserid() %>)"/></td>
    <td align="center"><input type='button' value='删除' class="button green" onclick="ondelete(<%=usertable.getUserid() %>)"/></td>
  <%
  }
  else{
  %>
  <td align="center"><input type='button' value='修改' class="button pink" onclick="onupdate(<%=usertable.getUserid() %> disabled)"/></td>
   <td align="center"><input type='button' value='删除' class="button pink" onclick="ondelete(<%=usertable.getUserid() %> disabled)"/></td>
   <%
  }
  }
  }
  }
   %>
    </tr>
    </table>
    </form>
    <table align="center">
    <tr>
<td><b><font color="red"><font size="5">意中人</font></font>共<%=show.getRowCount() %>位会员&nbsp;</b>页次：<%=show.getCurPage() %>/<%=show.getTotalPage() %>页</td>
<%--判断首页或尾页超链接情况不同 --%>
 <%
 if(show.getTotalPage()>1){
 if(curPage==1){
 %>
 <td><a href="MyFindJsp.jsp?p=1" onclick="return false;"><em>首页</em></a></td>
 <td><a href="MyFindJsp.jsp?p=<%=curPage-1 %>" onclick="return false;"><em>上一页</em></a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="MyFindJsp.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="MyFindJsp.jsp?p=<%=curPage+1 %>" >下一页</a></td>
 <td><a href="MyFindJsp.jsp?p=<%=show.getTotalPage() %>" >尾页</a></td>
 <%
 }
 if(curPage==show.getTotalPage()){
 %>
 <td><a href="MyFindJsp.jsp?p=1">首页</a></td>
 <td><a href="MyFindJsp.jsp?p=<%=curPage-1 %>">上一页</a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="MyFindJsp.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="MyFindJsp.jsp?p=<%=curPage+1 %>" onclick="return false;"><em>下一页</em></a></td>
 <td><a href="MyFindJsp.jsp?p=<%=show.getTotalPage() %>" onclick="return false;"><em>尾页</em></a></td>
 <%
 } 
  if(curPage!=show.getTotalPage()&&curPage!=1){
 %>
 <td><a href="MyFindJsp.jsp?p=1">首页</a></td>
 <td><a href="MyFindJsp.jsp?p=<%=curPage-1 %>">上一页</a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="MyFindJsp.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="MyFindJsp.jsp?p=<%=curPage+1 %>" >下一页</a></td>
 <td><a href="MyFindJsp.jsp?p=<%=show.getTotalPage() %>" >尾页</a></td>
 <%
 } 
 }
 %>
 </tr>
 </table>
 
 <br>
 <form onsubmit="return check(<%=show.getTotalPage() %>)">
 <table align="center">
 <tr>
 <td><input type="text" size="5" name="p"></td>
 <td><input type="submit" value="跳转" class="button pink"></td>
 </tr>
 </table>
 </form>
 </div>
</body>
</html>
   
   