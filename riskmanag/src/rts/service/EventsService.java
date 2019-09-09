package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Events;

public class EventsService {
	
	public static ArrayList<Events> getEvent(JDBCConnectionPool connectionPool) {
		String query = "SELECT id_event, event_name FROM events;";
		ArrayList<Events> events = new ArrayList<Events>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Events del = new Events();
					del.setId_event(rs.getInt("id_event"));
					del.setEvent_name(rs.getString("event_name"));
					events.add(del);
					
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return events;
	}

}
