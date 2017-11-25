package com.bean.dao.impl;

import com.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;



import com.bean.dao.MessageDB;
import com.bean.entity.Message;





public class MessageDBImpl implements MessageDB{


	
	private int rowCount;
	private int totalPage;
    private int curPage;
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	/*public MessageDBImpl() {
		String ClassName = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
		String user="scott";
		String pwd="admin";
		try {
			Class.forName(ClassName);
			cn = DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	//注册
	public void postMessage(Message message){
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = JdbcUtil.getConnection();
		    String sql = "insert into message values(mseq.nextval,?,?,?,sysdate)";
				 ps = cn.prepareStatement(sql);
					ps.setString(1, message.getTitle());
					ps.setString(2, message.getName());
					System.out.println(message.getName());
					ps.setString(3, message.getContent());
					ps.executeUpdate();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();	
			throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(ps,cn);	
			}
	}
	//修改
	public void doUpdate(Message message){
		Connection cn = null;
		PreparedStatement ps = null;
		  try {
			  cn = JdbcUtil.getConnection();
			  String sql = "update message set content=? where no=?";
			  System.out.println(sql);
			    ps = cn.prepareStatement(sql);
				ps.setString(1, message.getContent());
				ps.setInt(2, message.getNo());
				ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(ps,cn);	
			}
	  }
	//删除
	public void doDelete(Message message){
		Connection cn = null;
		PreparedStatement ps = null;
		  try {
			  cn = JdbcUtil.getConnection();
			  String sql = "delete from message where no=?";
			    ps = cn.prepareStatement(sql);
				ps.setInt(1, message.getNo());
				ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(ps,cn);	
			}
	  }
	//查询
	public Message showDital(int no){
		ResultSet rs=null;
		Connection cn = null;
		Statement stm = null;
		Message message = new Message();
		try {
			cn = JdbcUtil.getConnection();
				  String sql = "select * from message where no="+no;
			      stm = cn.createStatement();
				   rs = stm.executeQuery(sql);
					while(rs.next()){
					message.setNo(rs.getInt("no"));
				    message.setName(rs.getString("name"));
					message.setTitle(rs.getString("title"));
					message.setContent(rs.getString("content"));
					message.setMdate(rs.getDate("mdate"));
					}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
			}
		return message;
	}
	//查询所有
	 public Collection<Message> showMessage(){
	 	  Collection<Message> rt = new ArrayList<Message>();
		  String sql = "select * from Message";
		  Statement stm;
		  Connection cn = null;
		  Message message = new Message();
		try {
			cn = JdbcUtil.getConnection();
			stm = cn.createStatement();
		  ResultSet rs = stm.executeQuery(sql);
		  while(rs.next()){
			  message = new Message();
			  message.setNo(rs.getInt("no"));
			  message.setName(rs.getString("name"));
			  message.setTitle(rs.getString("title"));
			  message.setContent(rs.getString("content"));
			  message.setMdate(rs.getDate("mdate"));
			  rt.add(message);
		  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
			}
		  return rt;
	  }
   //分页查询与姓名搜索
	public Collection<Message> pageSelect(int curPage){
		Collection<Message> rt = new ArrayList<Message>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = JdbcUtil.getConnection();
			String sql = "select * from message order by no desc";
			System.out.println(sql);
			ps = cn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 rs = ps.executeQuery();
			rs.last();
			int rowCount = rs.getRow();
			System.out.println("总数："+rowCount);
			 int pageSize=3;
			 int totalPage;
			 if(rowCount%pageSize==0){
				 totalPage=rowCount/pageSize;
				 }else{
				 totalPage=rowCount/pageSize+1;
				 }
				 System.out.println("共"+totalPage+"页");
				 this.setRowCount(rowCount);
		         this.setTotalPage(totalPage);
		         this.setCurPage(curPage);
				 if(rowCount>0){
					 rs.absolute((curPage-1)*pageSize+1);
					 int i=0;
					 while(i<pageSize&&!rs.isAfterLast()){
						 
							 Message message = new Message();
							  message.setNo(rs.getInt("no"));
							  message.setName(rs.getString("name"));
							  message.setTitle(rs.getString("title"));
							  message.setContent(rs.getString("content"));
							  message.setMdate(rs.getDate("mdate"));
							  rt.add(message);  
							  rs.next();
							  i++;
					 }
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(rs,ps,cn);	
		}
		return rt;
		
	}

	
}
