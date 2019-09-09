package rts.domain;

import java.io.Serializable;

public class UserRole implements Serializable {
	private static final long serialVersionUID = -9003849024552277834L;
	
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
