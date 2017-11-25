<%@ page language="java" import="java.util.*,com.bean.entity.Message,com.bean.dao.impl.MessageDBImpl,com.bean.entity.Discuss,com.bean.dao.impl.DiscussDBImpl" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论坛详细内容</title>
    
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
function onDelete(no){
if(confirm("确定删除么?")){
   window.location.href="MessageServlet?action=doDeleteMessage&no="+no;
}
}
function onDeleteDiscuss(id){
if(confirm("确定删除该评论么?")){
   window.location.href="DiscussServlet?action=doDeleteDiscuss&id="+id;
}
}
</script>
  </head>
  
  <body> 
   <div id="id"><img src="image/2.jpg" /></div>
  
  <table align="center">
  <tr><td>
  <img width="150px" height="150px" src="${pageContext.request.contextPath}/upload/${sessionScope.userimg }" alt="无头像"/>
  </td></tr>
  <tr align="center"><td>
  <a href="${pageContext.request.contextPath }/DownServlet?userimg=${sessionScope.userimg }"><font color="purple"><b>下载该头像</b></font></a>
</td></tr></table>
    <%
     String name = (String)session.getAttribute("name");
    %>
    <form action="MessageServlet?action=doUpdateMessage" method="post">
    <table align="center" border="3" bordercolor="white" bgcolor="pink" cellpadding="2" cellspacing="2">
    <tr>
    <td colspan="2" align="center"><b>${sessionScope.no }</b></td>
    </tr>
    <tr>
    <td bgcolor="#cccccc">坛主</td><td><%=name %></td>
    </tr>
    <tr>
    <td bgcolor="#cccccc">日期</td><td>${sessionScope.mdate }</td>
    </tr>
    </table>
    
    <table border="2" align="center" bgcolor="pink" bordercolor="white" cellpadding="1" cellspacing="1">
    <tr>
    <td bgcolor="#cccccc">标题</td><td><input type="text" name="title" value="${sessionScope.title }" readonly></td>
    </tr>
    <tr>
    <td bgcolor="#cccccc">内容</td>
    <td><textarea rows="5" cols="60" name="content">${sessionScope.content }</textarea>
    <input type="hidden" name="no" value="${sessionScope.no }"></td>
    </tr>
    <%
    String username = (String)session.getAttribute("username");
    if(username != null && username.equals(name)){
     %>
  
    <tr>
    <td align="center" colspan="2">
    <input type="submit" value="修改" class="button blue">
    <input type="reset" value="重置" class="button blue">
    <input type="button" value="删除" onclick="onDelete(${sessionScope.no})" class="button blue">
    <input type="button" value="返回" onclick="window.location.href='showMessage.jsp'" class="button orange">
    </td>
    </tr>
    <%
    } 
    else{
    %>
    <tr><td colspan="2" align="center">
    <input type="button" value="返回" onclick="window.location.href='showMessage.jsp'" class="button orange">
    </td></tr>
    <%
    }
    %>
    </table>
    </form>
    <hr>
  <%
   DiscussDBImpl show = new DiscussDBImpl();
   Discuss discuss = new Discuss();
   int no = 0; 
   if(session.getAttribute("no") != null) {
   		
   		Integer o = (Integer)session.getAttribute("no");
   		no = o.intValue();
   } 
	String p = request.getParameter("p");
  int curPage;
  if(p==null)
 {
   curPage=1;
 }
 else{
   curPage=Integer.parseInt(p);
 }
 Collection<Discuss> meslist = new ArrayList<Discuss>();
    meslist = show.pageSelect(curPage,no);
    Iterator<Discuss> it = meslist.iterator();
    while(it.hasNext()){
      discuss = it.next();
      %>
      <table align="center" border="1" bordercolor="#FF6600" width="600" bgcolor="#FFFFCC" cellspacing="2">
     <tr>
       <td width="30" bgcolor="#cccccc" align="center"><%=discuss.getId() %></td>
      <td width="80" bgcolor="#FFFF33" align="center"><%=discuss.getName() %></td>
      <td><textarea rows="1" cols="40" name="content" readonly="readonly"><%=discuss.getContent() %></textarea></td>
      <td bgcolor="#cccccc" align="center"><%=discuss.getMdate() %></td>
     <% 
      if(username != null && (username.equals(discuss.getName())||username.equals(name))){
     %>
      <td width="40"><input type="button" value="删除" onclick="onDeleteDiscuss(<%=discuss.getId() %>)" class="button blue"></td>
      <%}else{    
       %>
     <td width="40"><img src="image/4.png" align="middle"></td>    
      </tr>
      
      </table>
      <%
      }
    }
    %>
     <table align="center">
    <tr>
<td><b><font color="red"><font size="5" style="font-family: 宋体">意中人</font></font>共<%=show.getRowCount() %>条评论&nbsp;</b>页次：<%=show.getCurPage() %>/<%=show.getTotalPage() %>页</td>
<%--判断首页或尾页超链接情况不同 --%>
 <%
 
 if(show.getTotalPage()>1){
 if(curPage==1){
 %>
 <td><a href="showDital.jsp?p=1" onclick="return false;"><font color="lime"><em>首页</em></font></a></td>
 <td><a href="showDital.jsp?p=<%=curPage-1 %>" onclick="return false;"><font color="lime"><em>上一页</em></font></a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="showDital.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="showDital.jsp?p=<%=curPage+1 %>" ><font color="lime">下一页</font></a></td>
 <td><a href="showDital.jsp?p=<%=show.getTotalPage() %>" ><font color="lime">尾页</font></a></td>
 <%
 }
 if(curPage==show.getTotalPage()){
 %>
 <td><a href="showDital.jsp?p=1"><font color="lime">首页</font></a></td>
 <td><a href="showDital.jsp?p=<%=curPage-1 %>"><font color="lime">上一页</font></a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="showDital.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="showDital.jsp?p=<%=curPage+1 %>" onclick="return false;"><font color="lime"><em>下一页</em></font></a></td>
 <td><a href="showDital.jsp?p=<%=show.getTotalPage() %>" onclick="return false;"><font color="lime"><em>尾页</em></font></a></td>
 <%
 } 
  if(curPage!=show.getTotalPage()&&curPage!=1){
 %>
 <td><a href="showDital.jsp?p=1"><font color="lime">首页</font></a></td>
 <td><a href="showDital.jsp?p=<%=curPage-1 %>"><font color="lime">上一页</font></a></td>
 <%
 for(int i=1;i<=show.getTotalPage();i++){
 %>
 <td><a href="showDital.jsp?p=<%=i %>"><%if(curPage!=i){%><%=i %><%}%>
 <%if(curPage==i){%><b><%=i %></b><%}%></a></td>
 <% 
 }
 %>
 <td><a href="showDital.jsp?p=<%=curPage+1 %>" ><font color="lime">下一页</font></a></td>
 <td><a href="showDital.jsp?p=<%=show.getTotalPage() %>" ><font color="lime">尾页</font></a></td>
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
     
    <form action="DiscussServlet?action=doPostDiscuss" method="post">
    <table border="2" align="center" bordercolor="white" bgcolor="orange" cellpadding="2" cellspacing="1">
   
    <tr>
    <td bgcolor="#cccccc">内容</td><td><textarea rows="3" cols="30" name="content"></textarea>
    <td align="center" colspan="2"><input type="submit" value="评论" class="button blue"></td>
    </tr>
    </table>
    </form>
  </body>
  
  
</html>
