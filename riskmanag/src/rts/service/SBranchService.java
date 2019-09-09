package rts.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import rts.domain.Department;
import rts.domain.Section;
import rts.domain.Subdivision;

public abstract class SBranchService {
	
	////////////// Subdivision - begin
	
	public static ArrayList<Subdivision> getSubdivision (JDBCConnectionPool connectionPool) {
		String query = "SELECT id_subdivision, subdivision_name FROM subdivision";
		ArrayList<Subdivision> subdivisions = new ArrayList<Subdivision>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Subdivision subd = new Subdivision();
					subd.setId_subdivision(rs.getInt("id_subdivision"));
					subd.setSubdivision(rs.getString("subdivision_name"));
					subdivisions.add(subd);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return subdivisions;
	}
	
	public static ArrayList<Subdivision> getSubdivisionById(Integer id, JDBCConnectionPool connectionPool) {
		String query = "SELECT id_subdivision, subdivision_name FROM subdivision WHERE id_branch =" + id;
		ArrayList<Subdivision> subds = new ArrayList<Subdivision>();
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Subdivision subd = new Subdivision();
					subd.setId_subdivision(rs.getInt("id_subdivision"));
					subd.setSubdivision(rs.getString("subdivision_name"));
					subds.add(subd);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return subds;
	}
//// end
		
		/////// department - begin
		
		public static ArrayList<Department> getDepartment (JDBCConnectionPool connectionPool) {
			String query = "SELECT id_department, department_name FROM department;";
			ArrayList<Department> departments = new ArrayList<Department>();
			Connection conn = null;
			try {
				conn = connectionPool.reserveConnection();
				CallableStatement proc = conn.prepareCall(query);
				ResultSet rs = proc.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						Department dep = new Department();
						dep.setId_department(rs.getInt("id_department"));
						dep.setDepartment_name(rs.getString("department_name"));
						departments.add(dep);
					}
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connectionPool.releaseConnection(conn);
			}
			return departments;
		}
		
		public static ArrayList<Department> getDepartmentById (Integer id, JDBCConnectionPool connectionPool) {
			String query = "SELECT id_department, department_name FROM department WHERE id_subdivision = " + id;
			ArrayList<Department> departments = new ArrayList<Department>();
			Connection conn = null;
			try {
				conn = connectionPool.reserveConnection();
				CallableStatement proc = conn.prepareCall(query);
				ResultSet rs = proc.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						Department dep = new Department();
						dep.setId_department(rs.getInt("id_department"));
						dep.setDepartment_name(rs.getString("department_name"));
						departments.add(dep);
					}
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connectionPool.releaseConnection(conn);
			}
			return departments;
		}
//// end
		
		///// sector - begin
		public static ArrayList<Section> getSection (JDBCConnectionPool connectionPool) {
			String query = "SELECT id_section, section_name FROM section;";
			ArrayList<Section> sections = new ArrayList<Section>();
			Connection conn = null;
			try {
				conn = connectionPool.reserveConnection();
				CallableStatement proc = conn.prepareCall(query);
				ResultSet rs = proc.executeQuery();
				if(rs!=null) {
					while (rs.next()) {
						Section sec = new Section();
						sec.setId_section(rs.getInt("id_section"));
						sec.setSection_name(rs.getString("section_name"));
						sections.add(sec);
					}
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}  finally {
				connectionPool.releaseConnection(conn);
			}
			return sections;
		}
		
		public static ArrayList<Section> getSectionById (Integer id, JDBCConnectionPool connectionPool) {
			String query = "SELECT id_section, section_name FRON section WHERE id_department = " + id;
			ArrayList<Section> sections = new ArrayList<Section>();
			Connection conn = null;
			try {
				conn = connectionPool.reserveConnection();
				CallableStatement proc = conn.prepareCall(query);
				ResultSet rs = proc.executeQuery();
				if(rs!=null) {
					while (rs.next()) {
						Section sec = new Section();
						sec.setId_section(rs.getInt("id_section"));
						sec.setSection_name(rs.getString("section_name"));
						sections.add(sec);
					}
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connectionPool.releaseConnection(conn);
			}
			return sections;
		}
}
