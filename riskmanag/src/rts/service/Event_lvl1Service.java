package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Event_lvl1;


public abstract class Event_lvl1Service {
	
	// sozdaui event_lvl1Service
	
	public static ArrayList<Event_lvl1> getEvent_lvl1(JDBCConnectionPool connectionPool) {
		String query = " SELECT id_event1, event_name1 FROM event_lvl1";
		ArrayList<Event_lvl1> event_lvl1s = new ArrayList<Event_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()){
					Event_lvl1 del = new Event_lvl1();
					del.setId_event1(rs.getInt("id_event1"));
					del.setEvent_name(rs.getString("event_name1"));
					event_lvl1s.add(del);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return event_lvl1s;
	}
	
	// sozdaui getEventById
	public static ArrayList<Event_lvl1> getEventById(Integer id, JDBCConnectionPool connectionPool) {
		String query = "SELECT id_event1, event_name FROM event_lvl1 WHERE id_event =" +id;
		ArrayList<Event_lvl1> event_lvlBy = new ArrayList<Event_lvl1>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Event_lvl1 del = new Event_lvl1();
					del.setId_event1(rs.getInt("id_event1"));
					del.setEvent_name(rs.getString("event_name"));
					event_lvlBy.add(del);					
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return event_lvlBy;
	}  
}
