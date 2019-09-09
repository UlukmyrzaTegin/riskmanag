package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Process_lvl1;

public class Process_lvl1Service {
	
	public static ArrayList<Process_lvl1> getProcess_lvl1 (JDBCConnectionPool connectionPool) {
		String quety = "SELECT id,p_name1 FROM process_lvl1";
		ArrayList<Process_lvl1> process_lvl1s = new ArrayList<Process_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(quety);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Process_lvl1 del = new Process_lvl1();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("p_name1"));
					process_lvl1s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return process_lvl1s;		
	} 
	
	public static ArrayList<Process_lvl1> getActivities_lvl1ById (Integer id, JDBCConnectionPool connectionPool) {
		String query = " SELECT id,p_name1 FROM process_lvl1 WHERE id = "+ id;
		ArrayList<Process_lvl1> processBy = new ArrayList<Process_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Process_lvl1 del = new Process_lvl1();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("p_name1"));
					processBy.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return processBy;
	}

}
