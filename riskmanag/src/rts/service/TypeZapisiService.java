package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.TypeZapisi;

public class TypeZapisiService {
	
	public static ArrayList<TypeZapisi> getTypeZapisi (JDBCConnectionPool connectionPool) {
		String query = "SELECT id, t_name FROM type_zapisi";
		ArrayList<TypeZapisi> typeZapisis = new ArrayList<TypeZapisi>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					TypeZapisi del = new TypeZapisi();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("t_name"));
					typeZapisis.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return typeZapisis;
	}

}
