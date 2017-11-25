package com.bean.dao.impl;

import com.util.JdbcUtil;




import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;



import com.bean.dao.UserTableDB;
import com.bean.entity.UserTable;




public class UserTableDBImpl implements UserTableDB{


	
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


	/*public UserTableDBImpl() {
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
	//查询是否存在此用户
	public boolean isLogin(UserTable usertable){
		Connection cn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			cn = JdbcUtil.getConnection();
			String sql = "select * from usertable where username=? and userpwd=?";
			ps = cn.prepareStatement(sql);
			ps.setString(1,usertable.getUsername());
			ps.setString(2, usertable.getUserpwd());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				flag = true;
			}else{
				flag = false;
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(ps,cn);	
			}
		return flag;
	}
	//注册
	public void Regist(UserTable usertable){
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = JdbcUtil.getConnection();
			String sql = "insert into usertable(userid,username,userpwd,userage,usergender,useredu,userphone,useraddress,status,validatecode,email) values(userseq.nextval,?,?,?,?,?,?,?,?,?,?)";
			ps = cn.prepareStatement(sql);	
		    ps.setString(1, usertable.getUsername());
		    ps.setString(2, usertable.getUserpwd());
		    ps.setInt(3, usertable.getUserage());
		    ps.setString(4, usertable.getUsergender());
		    ps.setString(5, usertable.getUseredu());
		    ps.setLong(6, usertable.getUserphone());
		    ps.setString(7, usertable.getUseraddress());
		    ps.setInt(8, 0);
		    ps.setString(9, usertable.getValidatecode());	
		    ps.setString(10, usertable.getEmail());
		   
		   
		    ps.executeUpdate();
		   
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();	
			throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(ps,cn);	
			}
	}
	//根据随机验证码查询用户
		public ResultSet queryUser(String code){
			   ResultSet rs = null;
				Connection cn = null;
				PreparedStatement ps = null;
				try {			
					cn = JdbcUtil.getConnection();
					String sql = "select * from usertable where validatecode=?";
					ps = cn.prepareStatement(sql);
					//设置预处理参数
					ps.setString(1, code);
					//执行
				 rs = ps.executeQuery();			
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return rs;
		}
		
		//修改用户激活状态
		public void updateCode(int userid){
			
				Connection cn = null;
				PreparedStatement ps = null;
				try {
					//获取连接
					cn = JdbcUtil.getConnection();
					String sql = "update usertable set status=1 where userid="+userid;
					System.out.println(sql);
					ps = cn.prepareStatement(sql);				   
					//执行
					ps.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(ps,cn);	
			}
		}
	
	//修改
	public void doUpdate(UserTable usertable){
		Connection cn = null;
		PreparedStatement ps = null;
		  try {
			  cn = JdbcUtil.getConnection();
			  String sql = "update usertable set username=?,userpwd=?,userage=?,usergender=?,useredu=?,userphone=?,useraddress=? where userid=?";
			 ps = cn.prepareStatement(sql);
		    ps.setString(1, usertable.getUsername());
		    ps.setString(2, usertable.getUserpwd());
		    ps.setInt(3, usertable.getUserage());
		    ps.setString(4, usertable.getUsergender());
		    ps.setString(5, usertable.getUseredu());
		    ps.setLong(6, usertable.getUserphone());
		    ps.setString(7, usertable.getUseraddress());
		    ps.setInt(8, usertable.getUserid());
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
	public void doDelete(int userid){
		Connection cn = null;
		PreparedStatement ps = null;
		  try {
			  cn = JdbcUtil.getConnection();
			  String sql = "delete from usertable where userid=?";
			 ps = cn.prepareStatement(sql);
			ps.setInt(1, userid);
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
	public ResultSet doQuery(String SQL){
		ResultSet rSet=null;
		Connection cn = null;
		Statement sm = null;
		try {
			cn = JdbcUtil.getConnection();
			sm = cn.createStatement();
				rSet=sm.executeQuery(SQL);
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
			}
		return rSet;
	}
   //分页查询与姓名搜索
	public Collection<UserTable> pageSelect(int curPage,String find){
		Collection<UserTable> rt = new ArrayList<UserTable>();
		if(find==null){
			find="";
		}
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = JdbcUtil.getConnection();
			String sql = "select * from usertable where username like '%"+find+"%' order by userid";
			System.out.println(sql);
			ps = cn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 rs = ps.executeQuery();
			rs.last();
			int rowCount = rs.getRow();
			System.out.println("总数："+rowCount);
			 int pageSize=4;
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
						 
							  UserTable usertable = new UserTable();
							  usertable.setUserid(rs.getInt("userid"));
							  usertable.setUsername(rs.getString("username"));
							  usertable.setUserpwd(rs.getString("userpwd"));
							  usertable.setUserage(rs.getInt("userage"));
							  usertable.setUsergender(rs.getString("usergender"));
							  usertable.setUseredu(rs.getString("useredu"));
							  usertable.setUserphone(rs.getLong("userphone"));
							  usertable.setUseraddress(rs.getString("useraddress"));
							  rt.add(usertable);  
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
	//修改头像
	public void updateImg(int userid,UserTable usertable){
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//获取连接
			cn = JdbcUtil.getConnection();
			String sql = "update usertable set userimg=? where userid="+userid;
			ps = cn.prepareStatement(sql);
         
           System.out.println(sql);
		   
			ps.setString(1, usertable.getUserimg());
			//执行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(ps,cn);	
		}
	}
	//根据用户名查询此人信息
	public ResultSet findUserByUsername(String username){
		ResultSet rs=null;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			
			cn = JdbcUtil.getConnection();
			String sql = "select * from usertable where username=?";
			ps = cn.prepareStatement(sql);
			//设置预处理参数
			ps.setString(1, username);
			//执行
		 rs = ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return rs;
	}
	
	
}
