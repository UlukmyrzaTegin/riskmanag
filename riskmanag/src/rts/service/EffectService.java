package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Effect;

public class EffectService {
	
	public static ArrayList<Effect> getEffect (JDBCConnectionPool connectionPool) {
		String query = "SELECT id, e_name FROM effect";
		ArrayList<Effect> effects = new ArrayList<Effect>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Effect del = new Effect();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("e_name"));
					effects.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return effects;
	}

}
