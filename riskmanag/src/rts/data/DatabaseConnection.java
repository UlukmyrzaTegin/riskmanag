package rts.data;

import java.util.ResourceBundle;

import com.mirbek.struts2.common.model.constraints.GenericConstraints;

public class DatabaseConnection extends GenericConstraints {
	
	private String driverName;
	private String connectionString;
	private String username;
	private String password;
	
	public DatabaseConnection() {
		super();
		ResourceBundle bundle = ResourceBundle.getBundle(getClass().getCanonicalName());
		driverName = bundle.getString("driverName");
		connectionString = bundle.getString("connectionString");
		username = bundle.getString("username");
		password = bundle.getString("password");
	}
	

	public String getDriverName() {
		return driverName;
	}


	public String getConnectionString() {
		return connectionString;
	}

	
	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}

}
