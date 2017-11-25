<%@ page language="java" import="java.util.*,com.bean.entity.Message,com.bean.dao.*,com.bean.dao.impl.*" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论坛首页</title>
    
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


</style>


  <script type="text/javascript">
  
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

      <table align="right" >
     <tr><td align="center"><a href="MyFindJsp.jsp"><font color="lime"><b>返回会员中心</b></font></a>
    <a href="logoutServlet"><font color="white"><b>退出登录</b></font></a></td></tr>
    </table>
    
    <c:choose>
    <c:when test="${empty sessionScope.username}">
    <b>请先</b><a href="${pageContext.request.contextPath }/Login.jsp"><b>登陆</b></a>
    </c:when>
    <c:otherwise>【<font size="5"><b>${sessionScope.username}</b></font>】————<font color="lime"><b>欢迎进入·<font style="font-size: 30" color="red">意中人</font>·论坛</b></font>
    </c:otherwise>
    </c:choose>
      <marquee scrollAmount="8" width="1350" height="120" direction="left" loop="-1" onmouseover="this.stop()" onmouseout="this.start()">
     <%
     for(int i=1;i<20;i++){
     %>
      <img width="100px" height="100px" src="${pageContext.request.contextPath}/upload/<%=i %>.jpg" alt="无头像"/>
      <%
      }
      
      %>
      </marquee>
    
    <%
   MessageDBImpl show = new MessageDBImpl();
   String name = (String)session.getAttribute("username");
   session.setAttribute("username",name);
    Message message = new Message();
  String p = request.getParameter("p");
  
  int curPage;
  if(p==null)
 {
   curPage=1;
 }
 else{
   curPage=Integer.parseInt(p);
 }
 Collection<Message> meslist = new ArrayList<Message>();
    meslist = show.pageSelect(curPage);
    Iterator<Message> it = meslist.iterator();
    while(it.hasNext()){
      message = it.next();
      %>
      <table align="center" border="3" bordercolor="white" width="230" bgcolor="pink" cellpadding="2" cellspacing="2">
      <tr>
      <td bgcolor="#cccccc" align="center">标题</td>
      <td><a href="showDitalServlet?no=<%=message.getNo() %>"><font style="font-family: 黑体"><%=message.getTitle() %></font></a></td>
      </tr>
      <tr>
      <td bgcolor="#cccccc" align="center">坛主</td>
      <td><%=message.getName() %></td>
      </tr>
      <tr>
      <td bgcolor="#cccccc" align="center">日期</td>
      <td><%=message.getMdate() %></td>
      </tr>
      </table>
      <%
    }
    %>
     <table align="center">
    <tr>
<td><b><font color="red"><font size="5" style="font-family: 宋体">意中人</font></font>共<%=show.getRowCount() %>条论坛数据&nbsp;</b>页次：<%=show.getCurPage() %>/<%=show.getTotalPage() %>页</td>
<%--判断首页或尾页超链接情况不同 --%>
 <%
 if(show.getTotalPage()>1){
 if(curPage==1){
 %>
 <td><a href="showMessage.jsp?p=1" onclick="return false;"><font color="lime"><em>首页</em></font></a></td>
 <td><a href="showMessage.jsp?p=<%=curPage-1 %>" onclick="return false;"><font color="lime"><em>上一页</em></font></a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="showMessage.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="showMessage.jsp?p=<%=curPage+1 %>" ><font color="lime">下一页</font></a></td>
 <td><a href="showMessage.jsp?p=<%=show.getTotalPage() %>" ><font color="lime">尾页</font></a></td>
 <%
 }
 if(curPage==show.getTotalPage()){
 %>
 <td><a href="showMessage.jsp?p=1"><font color="lime">首页</font></a></td>
 <td><a href="showMessage.jsp?p=<%=curPage-1 %>"><font color="lime">上一页</font></a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="showMessage.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="showMessage.jsp?p=<%=curPage+1 %>" onclick="return false;"><font color="lime"><em>下一页</em></font></a></td>
 <td><a href="showMessage.jsp?p=<%=show.getTotalPage() %>" onclick="return false;"><font color="lime"><em>尾页</em></font></a></td>
 <%
 } 
  if(curPage!=show.getTotalPage()&&curPage!=1){
 %>
 <td><a href="showMessage.jsp?p=1"><font color="lime">首页</font></a></td>
 <td><a href="showMessage.jsp?p=<%=curPage-1 %>"><font color="lime">上一页</font></a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="showMessage.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="showMessage.jsp?p=<%=curPage+1 %>" ><font color="lime">下一页</font></a></td>
 <td><a href="showMessage.jsp?p=<%=show.getTotalPage() %>" ><font color="lime">尾页</font></a></td>
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

 

 
   
    <form action="MessageServlet?action=doPostMessage" method="post">
    <table border="2" align="center" bgcolor="orange" cellpadding="2" cellspacing="1">
    <tr>
    <td bgcolor="#cccccc">标题</td><td><input type="text" name="title"></td>
    </tr>
    <tr>
    <td bgcolor="#cccccc">内容</td><td><textarea rows="5" cols="40" name="content"></textarea>
    </tr>
    <tr>
    <td align="center" colspan="2"><input type="submit" value="发布" class="button blue"></td>
    </tr>
    </table>
    </form>
  </body>
</html>
