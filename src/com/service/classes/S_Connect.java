package com.service.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class S_Connect {
	public static Connection getConn() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/medcine?useUnicode=true&CharacterEncoding=utf-8";	
    	String user = "root";
		String password = "123456";
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//返回结果对象
	public ResultSet getStmt(String sql){
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = S_Connect.getConn();
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//S_Connect.close(rs, stmt, conn);
			//S_Connect.close2(stmt, conn);
		}
		return rs;
	} 
	
	
	public static void close2(Statement stmt,java.sql.Connection conn) {
		try {
			
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
	
	
	/**
	 * 插入使用，删除使用，更新使用
	 * @param sql
	 */
	public static void useSQL(String sql) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = S_Connect.getConn();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("\n*******************************S_Connect.useSQL异常***********************************");
			System.out.println("***********异常sql语句："+sql+"\n");
		} finally {
			S_Connect.close(rs, stmt, conn);
		}
	}
	/**
	 * 查询使用
	 * @param sql
	 * @return
	 */
	public List query(String sql) {
		Vector content = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = S_Connect.getConn();
			stmt = conn.createStatement();
			stmt.execute("SET NAMES 'GBK'");
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsMeta = rs.getMetaData();
			while (rs.next()) {
				int columnNum = rsMeta.getColumnCount();
				String[] field = new String[columnNum];
				String fieldValue = null;
				for (int i = 1; i <= columnNum; i++) {
					fieldValue = rs.getString(i);
					if (fieldValue == null) {
						fieldValue = "";
					}
					field[i - 1] = fieldValue;
					
				}
				content.add(field);
			}

		} catch (SQLException e) {
			System.out.println("\n*******************query异常***********************");
			//e.printStackTrace();
			System.out.println("异常语句："+sql);
			System.out.println("*******************query异常***********************\n");
		} catch (ClassNotFoundException e) {
			System.out.println("\n*******************query异常***********************");
			//e.printStackTrace();
			System.out.println("异常语句："+sql);
			System.out.println("*******************query异常***********************\n");
		} finally {
			S_Connect.close(rs, stmt, conn);
		}
		//System.out.println("content  0000 ===="+content);
		return content;
	}
	 /**  
   	 * 执行sql事务
   	 * @param  comsql   要执行的sql语句串
   	 * @return 成功1，失败0
   	 */
	public String fun_sql_shiwu1(String comsql,String fgf) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String jud = "0";
		try {
			conn = getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String[] zsql = comsql.split(fgf);
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

	

}

