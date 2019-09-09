package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.LossCurrency;

public class LossCurrencyService {
	
	public static ArrayList<LossCurrency> getLossCurrency (JDBCConnectionPool connectionPool) {
		String query = "Select id, l_name From loss_currency";
		ArrayList<LossCurrency> lossCurrencies = new ArrayList<LossCurrency>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					LossCurrency del = new LossCurrency();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("l_name"));
					lossCurrencies.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return lossCurrencies;
	}

}
