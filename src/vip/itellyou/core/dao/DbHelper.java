package vip.itellyou.core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DbHelper {
	//数据库连接池
	private static DataSource dataSource = new ComboPooledDataSource();
	//线程槽：用于维持线程内的Connection对象
	private static ThreadLocal<Connection> connectionPool = new ThreadLocal<Connection>();
	
	public static Connection getConnection() throws Exception{
		//从线程槽用获取线程内的连接对象
		Connection con = connectionPool.get();
		//如果连接对象为null，表示该线程中还没有创建连接对象
		//则从连接池中获取一个连接对象，并置入线程槽
		if(con==null){
			con = dataSource.getConnection();
			connectionPool.set(con);
		}
		
		return con;
	}
	
	public static void close() throws Exception{
		Connection con  = connectionPool.get();
		if(con!=null){
			con.close();
			connectionPool.remove();
		}
	}
	
	public static void beginTrans() throws Exception{
		Connection con = connectionPool.get();
		if(con!=null){
			con.setAutoCommit(false);
		}
	}
	
	public static void commitTrans() throws Exception{
		Connection con = connectionPool.get();
		if(con!=null){
			con.commit();
		}
	}
	
	public static void rollbackTrans() throws Exception{
		Connection con = connectionPool.get();
		if(con!=null){
			con.rollback();
		}
	}
	
	public static void closeAll(Connection con,PreparedStatement pst,ResultSet rs)throws Exception{
		if(rs!=null){
			rs.close();
		}
		if(pst!=null){
			pst.close();
		}
		if(con!=null){
			con.close();
		}
	}
}
