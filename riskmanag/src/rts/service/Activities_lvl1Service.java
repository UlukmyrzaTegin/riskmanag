package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Activities_lvl1;

public class Activities_lvl1Service {
	
	public static ArrayList<Activities_lvl1> getActivities_lvl1(JDBCConnectionPool connectionPool) {
		String query = " SELECT id, a_name1 FROM activities_lvl1";
		ArrayList<Activities_lvl1> activities_lvl1s = new ArrayList<Activities_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activities_lvl1 del = new Activities_lvl1();
					del.setId(rs.getInt("id"));
					del.setA_name1(rs.getString("a_name1"));
					activities_lvl1s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return activities_lvl1s;
	}
	
	////Activities
	public static ArrayList<Activities_lvl1> getActivitiesById (Integer id, JDBCConnectionPool connectionPool) {
		String query = " SELECT id, a_name1 FROM activities_lvl1 WHERE id = "+ id;
		ArrayList<Activities_lvl1> activitiesBy = new ArrayList<Activities_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activities_lvl1 del = new Activities_lvl1();
					del.setId(rs.getInt("id"));
					del.setA_name1(rs.getString("a_name1"));
					activitiesBy.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return activitiesBy;
	}

}
