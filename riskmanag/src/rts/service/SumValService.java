package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.SumVal;

public class SumValService { 
	public static ArrayList<SumVal> getSumVal (JDBCConnectionPool connectionPool) {
		String query = " SELECT id, s_name FROM sum_val";
		ArrayList<SumVal> sumVals = new ArrayList<SumVal>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					SumVal del = new SumVal();
					del.setId(rs.getInt("id"));
					del.setName(rs.getString("s_name"));
					sumVals.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		
		return sumVals;
	}

}
