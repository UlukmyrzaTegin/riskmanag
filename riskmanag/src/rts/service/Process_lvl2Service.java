package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Process_lvl2;

public class Process_lvl2Service {
	
	public static ArrayList<Process_lvl2> getProcess_lvl2 (JDBCConnectionPool connectionPool) {
		String query = "SELECT id,p_name2 FROM process_lvl2";
		ArrayList<Process_lvl2> process_lvl2s = new ArrayList<Process_lvl2>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Process_lvl2 del = new Process_lvl2();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("p_name2"));
					process_lvl2s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return process_lvl2s;
	}
	
	public static ArrayList<Process_lvl2> getProcess_lvl1ById(Integer id, JDBCConnectionPool connectionPool) {
		String query = "SELECT id, p_name2 FROM process_lvl2 WHERE id =" + id;
		ArrayList<Process_lvl2> process_lvl2By = new ArrayList<Process_lvl2>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Process_lvl2 del = new Process_lvl2();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("p_name2"));
					process_lvl2By.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return process_lvl2By;
	}
}
