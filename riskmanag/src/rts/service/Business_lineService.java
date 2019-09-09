package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Business_line;

public class Business_lineService {
	
	public static ArrayList<Business_line> getBusiness_line (JDBCConnectionPool connectionPool) {
		String query = "SELECT id_bl, bl_name FROM business_line";
		ArrayList<Business_line> business_lines = new ArrayList<Business_line>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Business_line del = new Business_line();
					del.setId_bl(rs.getInt("id_bl"));
					del.setBl_name(rs.getString("bl_name"));
					business_lines.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		
		return business_lines;
	}

}
