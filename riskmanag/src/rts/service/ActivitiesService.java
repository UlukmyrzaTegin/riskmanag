package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Activities;

public class ActivitiesService {
	
	public static ArrayList<Activities> getActivities (JDBCConnectionPool connectionPool) {
		String query = " Select id, a_name From activities ";
		ArrayList<Activities> activities = new ArrayList<Activities>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activities del = new Activities();
					del.setId(rs.getInt("id"));
					del.setA_name(rs.getString("a_name"));
					activities.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
				
		return activities;
	}

}
