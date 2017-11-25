package com.bean.dao.impl;


import com.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;





import com.bean.dao.DiscussDB;

import com.bean.entity.Discuss;

	public class DiscussDBImpl implements DiscussDB{

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


		/*public DiscussDBImpl() {
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
		public void postDiscuss(Discuss discuss,int no){
			Connection cn = null;
			PreparedStatement ps = null;
			
			try {
				cn = JdbcUtil.getConnection();
			    String sql = "insert into discuss values("+no+",?,?,sysdate,dseq.nextval)";
					 ps = cn.prepareStatement(sql);
						ps.setString(1, discuss.getName());
						ps.setString(2, discuss.getContent());
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
		public void doDelete(Discuss discuss){
			Connection cn = null;
			PreparedStatement ps = null;
			  try {
				  cn = JdbcUtil.getConnection();
				  String sql = "delete from discuss where id=?";
				    ps = cn.prepareStatement(sql);
					ps.setInt(1, discuss.getId());
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
		public Discuss showDital(int no){
			ResultSet rs=null;
			Connection cn = null;
			Statement stm = null;
			Discuss discuss = new Discuss();
			try {
				cn = JdbcUtil.getConnection();
					  String sql = "select * from message where no="+no;
				      stm = cn.createStatement();
					   rs = stm.executeQuery(sql);
						while(rs.next()){
						discuss.setNo(rs.getInt("no"));
					    discuss.setName(rs.getString("name"));
					    discuss.setContent(rs.getString("content"));
					    discuss.setMdate(rs.getDate("mdate"));
					    discuss.setId(rs.getInt("id"));
						
						
						}
				}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
				}
			return discuss;
		}
		
	   //分页查询与姓名搜索
		public Collection<Discuss> pageSelect(int curPage,int no){
			Collection<Discuss> rt = new ArrayList<Discuss>();
			Connection cn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				cn = JdbcUtil.getConnection();
				String sql = "select * from discuss where no="+no+" order by id";
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
							 
								 Discuss discuss = new Discuss();
								 discuss.setNo(rs.getInt("no"));
								 discuss.setName(rs.getString("name"));
			
								 discuss.setContent(rs.getString("content"));
								 discuss.setMdate(rs.getDate("mdate"));
								 discuss.setId(rs.getInt("id"));
								  rt.add(discuss);  
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


