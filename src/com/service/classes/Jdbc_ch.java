package com.service.classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Jdbc_ch {
	
	
	final static String dbname = "medcine";
	public static Connection getConn() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		//String url = "jdbc:mysql://127.0.0.1:3306/"+dbname+"?useUnicode=true&CharacterEncoding=utf-8";
		
		String url="jdbc:mysql://localhost:3306/"+dbname+"?user=root&password=cx&CharacterEncoding=utf-8";

		//Connection con = DriverManager.getConnection(url);
		
		String user = "root";
		String password = "123456";
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(url, user, password);
			//conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static String useSQL(String sql) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String re = "0";
		try {
			conn = Jdbc_ch.getConn();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			re = "1";
		} catch (Exception e) {
			e.printStackTrace();
			re = "0";
		} finally {
			Jdbc_ch.close(rs, stmt, conn);
		}
		return re;
	}
	
	public String useBatch(List<String> list){
		Statement stmt = null;
		Connection conn = null;
		
		try {
			conn = Jdbc_ch.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.execute("SET NAMES 'GBK'");
			for(int i=0;i<list.size();i++){
				String sql=list.get(i);
				System.out.println(sql);
				stmt.addBatch(sql);
			}			
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			stmt.close();
			
			return "1";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
		
	}

	@SuppressWarnings("unchecked")
	public List query(String sql) {
		Vector content = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = Jdbc_ch.getConn();
			stmt = conn.createStatement();
			stmt.execute("SET NAMES 'GBK'");
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsMeta = rs.getMetaData();
			while (rs.next()) {
				int columnNum = rsMeta.getColumnCount();
				String[] field = new String[columnNum];
				String fieldValue = null;
				//System.out.println("columnNum   =="+columnNum);
				
				for (int i = 1; i <= columnNum; i++) {
					fieldValue = rs.getString(i);
				//	System.out.println("fieldValue   =="+fieldValue);
					if (fieldValue == null) {
						fieldValue = "";
					}
					field[i - 1] = fieldValue;
				}
				content.add(field);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			Jdbc_ch.close(rs, stmt, conn);
		}
		
		//System.out.println("content   =="+content);
		return content;
	}
	
	 /**  
   	 * 执行sql事务
   	 * @param  comsql   要执行的sql语句串
   	 * @return 成功1，失败0
   	 */
	public String fun_sql_shiwu1(String comsql) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String jud = "0";
		try {
			conn = getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String[] zsql = comsql.split("&");
			// String[] zsql=comsql.split("@");
			for (int i = 0; i < zsql.length; i++) {
				System.out.println(zsql[i]);
				stmt.addBatch(zsql[i]);

			}
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			stmt.close();
			System.out.println("操作成功！");
		} catch (Exception e) {
			jud = "1";
			System.out.println("操作失败、任务撤消！");
			try {
				// 回滚、取消前述操作
				conn.rollback();
			} catch (Exception e1) {
				jud = "1";
				e.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				jud = "1";
				e.printStackTrace();
			}
		}
		return jud;
	}
/*	
	/**
	 * 
	 */
	public void createTableByProcedure(String str1, String str2) {

		CallableStatement cstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = Jdbc_ch.getConn();
			System.out.println("str1:  "+str1);
			System.out.println("str2:  "+str2);
			cstmt = conn.prepareCall("{call Proc_Create_Table(?,?,?)}");
			cstmt.setString(1, str1);
			cstmt.setString(2, str2);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			String sql = cstmt.getString(3);
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Jdbc_ch.close(rs, cstmt, conn);
		}

	}

	/**
	 *
	 */
	public void procedureNoBack(String str1, String str2) {

		CallableStatement cstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = Jdbc_ch.getConn();
			cstmt = (CallableStatement) conn.prepareCall("{call Proc_Insert_Values(?,?)}");
			cstmt.setString(1, str1);
			cstmt.setString(2, str2);
			cstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("qqqqqqqqq" + str1+ ":+"+str2);
			e.printStackTrace();
		} finally {
			Jdbc_ch.close(rs, cstmt, conn);
		}

	}

	/**
	 * 
	 */
	public void procedureOutTwo(String str1, String str2) {

		CallableStatement cstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = Jdbc_ch.getConn();
			cstmt = (CallableStatement) conn.prepareCall("{call Proc_Spliter_String(?,?)}");
			cstmt.setString(1, str1);
			cstmt.setString(2, str2);
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			cstmt.getString(1);
			cstmt.getString(2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Jdbc_ch.close(rs, cstmt, conn);
		}

	}

	/**
	 * 
	 */
	public void procedureByThree(String str1, String str2, String str3) {
		
        int ID=Integer.parseInt(str2);
		CallableStatement cstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = Jdbc_ch.getConn();
			cstmt = (CallableStatement) conn
					.prepareCall("{call Proc_Update_Values(?,?,?)}");
			cstmt.setString(1, str1);
			cstmt.setInt(2, ID);
			//cstmt.setString(2, str2);
			cstmt.setString(3, str3);
			cstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Jdbc_ch.close(rs, cstmt, conn);
		}

	}

	/**
	 * 
	 */
	public void CreateAllTableByProcedure() {

		CallableStatement cstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = Jdbc_ch.getConn();
			cstmt = (CallableStatement) conn
					.prepareCall("{call Proc_Create_All_Table()}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			cstmt.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Jdbc_ch.close(rs, cstmt, conn);
		}

	}
	
	

	public static void close(ResultSet rs, Statement stmt,
			java.sql.Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//创建表格
	public void createtable(String tbl, String beizhu) {
		String sql=createsql(tbl,beizhu);
		System.out.println(sql);
		Jdbc_ch.useSQL(sql);
	}

	public String createsql(String tablename, String beizhu) {
		
		Jdbc_ch jdbc_ch = new Jdbc_ch();
		String tmssql = "select fname,ftype,flength,fdef,kmName from tablems where tablename in ('"
				+ tablename+"','temp_zd');";
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("CREATE TABLE `"+tablename+"` ( `Id` int(11) NOT NULL AUTO_INCREMENT, ");
		List tmsList = jdbc_ch.query(tmssql);
		Object[] tmsObject=new Object[5];
		
		for(int i=0;i<tmsList.size();i++){
			tmsObject=(Object[])tmsList.get(i);
			String fname=(String)tmsObject[0];
			
			String ftype=(String)tmsObject[1];
			String flength=(String)tmsObject[2];
			String fdef=(String)tmsObject[3];
			String kmName=(String)tmsObject[4];
			String re="`"+fname+"` ";
			//System.out.println(flength);
			if(flength!=null&&!flength.equals("")){
				re=re+ftype+"("+flength+") ";
			}else{
				re=re+ftype+" ";
			}
			if(fdef!=null&&!fdef.equals("")){
				re=re+"NOT NULL DEFAULT '"+fdef+"' ";
			}else{
				re=re+"DEFAULT NULL ";
			}
			if(kmName!=null&&!kmName.equals("")){
				re=re+"COMMENT '"+kmName+"'";
			}
			re=re+",";
			sqlBuffer.append(re);
		}
		
		sqlBuffer.append(" PRIMARY KEY (`Id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='"+beizhu+"';");
		return sqlBuffer.toString();
	}
	
	public List selectTablename(String tablename){
		String sql = "select `TABLE_NAME` from `INFORMATION_SCHEMA`.`TABLES` where `TABLE_SCHEMA`='"+dbname+"' and `TABLE_NAME`='"+tablename+"'";
		List list=query(sql);
		return list;
	}
}
