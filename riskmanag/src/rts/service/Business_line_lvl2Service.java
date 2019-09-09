package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Business_line_lvl1;
import rts.domain.Business_line_lvl2;

public class Business_line_lvl2Service {
	
	public static ArrayList<Business_line_lvl2> getBusiness_line_lvl2 (JDBCConnectionPool connectionPool) {
		String query = "SELECT id_bl2, bl_name2 FROM business_line_lvl2";
		ArrayList<Business_line_lvl2> business_line_lvl2s = new ArrayList<Business_line_lvl2>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while(rs.next()) {
					Business_line_lvl2 del = new Business_line_lvl2();
					del.setId_bl2(rs.getInt("id_bl2"));
					del.setBl_name(rs.getString("bl_name2"));
					business_line_lvl2s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return business_line_lvl2s;
	} 
	
	public static ArrayList<Business_line_lvl2> getBusiness_line_lvl1ById (Integer id, JDBCConnectionPool connectionPool) {
		String query = "SELECT id_bl2, bl_name2 FROM business_line_lvl2";
		ArrayList<Business_line_lvl2> businesslvl1 = new ArrayList<Business_line_lvl2>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while(rs.next()) {
					Business_line_lvl2 del = new Business_line_lvl2();
					del.setId_bl2(rs.getInt("id_bl2"));
					del.setBl_name(rs.getString("bl_name2"));
					businesslvl1.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return businesslvl1;
	} 
	
	public static ArrayList<Business_line_lvl1> getBusiness_lineByID (Integer id, JDBCConnectionPool connectionPool) {
		String query = "SELECT id_bl2, bl_name2 FROM business_line_lvl2 WHERE id_bl = " + id;
		ArrayList<Business_line_lvl1> businesslvlBy = new ArrayList<Business_line_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Business_line_lvl1 del = new Business_line_lvl1();
					del.setId_bl1(rs.getInt("id_bl2"));
					del.setBl_name(rs.getString("bl_name2"));
					businesslvlBy.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		return businesslvlBy;
	}
}
