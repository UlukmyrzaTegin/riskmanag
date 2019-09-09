package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Business_line_lvl1;

public class Business_line_lvl1Service {
	
	public static ArrayList<Business_line_lvl1> getBusiness_line_lvl1 (JDBCConnectionPool connectionPool) {
		String query = "SELECT id_bl1, bl_name1 FROM business_line_lvl1;";
		ArrayList<Business_line_lvl1> business_line_lvl1s = new ArrayList<Business_line_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Business_line_lvl1 del = new Business_line_lvl1();
					del.setId_bl1(rs.getInt("id_bl1"));
					del.setBl_name(rs.getString("bl_name1"));
					business_line_lvl1s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		return business_line_lvl1s;
	}
	
	public static ArrayList<Business_line_lvl1> getBusiness_lineByID (Integer id, JDBCConnectionPool connectionPool) {
		String query = "SELECT id_bl1, bl_name1 FROM business_line_lvl1 WHERE id_bl = " + id;
		ArrayList<Business_line_lvl1> businesslvl1By = new ArrayList<Business_line_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Business_line_lvl1 del = new Business_line_lvl1();
					del.setId_bl1(rs.getInt("id_bl1"));
					del.setBl_name(rs.getString("bl_name1"));
					businesslvl1By.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		return businesslvl1By;
	}
}
