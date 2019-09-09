package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Branch;

public class BranchService {
	public static ArrayList<Branch> getBranch(JDBCConnectionPool connectionPool) {
		String query = "SELECT id_branch, branch_name FROM branch;";
		ArrayList<Branch> branchs = new ArrayList<Branch>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Branch bran = new Branch();
					bran.setId_branch(rs.getInt("id_branch"));
					bran.setBranch_name(rs.getString("branch_name"));
					branchs.add(bran);					
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return branchs;
		
	}

}

