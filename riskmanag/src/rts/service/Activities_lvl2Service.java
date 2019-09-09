package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Activities_lvl2;

public class Activities_lvl2Service {
	
	public static ArrayList<Activities_lvl2> getActivities_lvl2 (JDBCConnectionPool connectionPool) {
		String query = " Select id, a_name2 From activities_lvl2 ";
		ArrayList<Activities_lvl2> activities_lvl2s = new ArrayList<Activities_lvl2>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while(rs.next()) {
					Activities_lvl2 del = new Activities_lvl2();
					del.setId(rs.getInt("id"));
					del.setA_name2(rs.getString("a_name2"));
					activities_lvl2s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		return activities_lvl2s;
	}
	
	///// Activities
		public static ArrayList<Activities_lvl2> getActivitiesById (Integer id, JDBCConnectionPool connectionPool) {
		String query = " Select id, a_name2 From activities_lvl2 WHERE id = "+id;
		ArrayList<Activities_lvl2> activities2s = new ArrayList<Activities_lvl2>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while(rs.next()) {
					Activities_lvl2 del = new Activities_lvl2();
					del.setId(rs.getInt("id"));
					del.setA_name2(rs.getString("a_name2"));
					activities2s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		return activities2s;
	}
	
	//// Activities_lvl1
	public static ArrayList<Activities_lvl2> getActivities_lvl1ById (Integer id, JDBCConnectionPool connectionPool) {
		String query = " Select id, a_name2 From activities_lvl2 WHERE id = " +id;
		ArrayList<Activities_lvl2> activities2 = new ArrayList<Activities_lvl2>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while(rs.next()) {
					Activities_lvl2 del = new Activities_lvl2();
					del.setId(rs.getInt("id"));
					del.setA_name2(rs.getString("a_name2"));
					activities2.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		return activities2;
	}

}
