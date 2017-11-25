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
	//��ѯ�Ƿ���ڴ��û�
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
	//ע��
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
	//���������֤���ѯ�û�
		public ResultSet queryUser(String code){
			   ResultSet rs = null;
				Connection cn = null;
				PreparedStatement ps = null;
				try {			
					cn = JdbcUtil.getConnection();
					String sql = "select * from usertable where validatecode=?";
					ps = cn.prepareStatement(sql);
					//����Ԥ�������
					ps.setString(1, code);
					//ִ��
				 rs = ps.executeQuery();			
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return rs;
		}
		
		//�޸��û�����״̬
		public void updateCode(int userid){
			
				Connection cn = null;
				PreparedStatement ps = null;
				try {
					//��ȡ����
					cn = JdbcUtil.getConnection();
					String sql = "update usertable set status=1 where userid="+userid;
					System.out.println(sql);
					ps = cn.prepareStatement(sql);				   
					//ִ��
					ps.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(ps,cn);	
			}
		}
	
	//�޸�
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
	//ɾ��
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
	//��ѯ
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
   //��ҳ��ѯ����������
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
			System.out.println("������"+rowCount);
			 int pageSize=4;
			 int totalPage;
			 if(rowCount%pageSize==0){
				 totalPage=rowCount/pageSize;
				 }else{
				 totalPage=rowCount/pageSize+1;
				 }
				 System.out.println("��"+totalPage+"ҳ");
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
	//�޸�ͷ��
	public void updateImg(int userid,UserTable usertable){
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//��ȡ����
			cn = JdbcUtil.getConnection();
			String sql = "update usertable set userimg=? where userid="+userid;
			ps = cn.prepareStatement(sql);
         
           System.out.println(sql);
		   
			ps.setString(1, usertable.getUserimg());
			//ִ��
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(ps,cn);	
		}
	}
	//�����û�����ѯ������Ϣ
	public ResultSet findUserByUsername(String username){
		ResultSet rs=null;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			
			cn = JdbcUtil.getConnection();
			String sql = "select * from usertable where username=?";
			ps = cn.prepareStatement(sql);
			//����Ԥ�������
			ps.setString(1, username);
			//ִ��
		 rs = ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return rs;
	}
	
	
}
