package domain;

import java.io.Serializable;

public class Key implements Serializable{
	private int id;
	private String code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Key(String code) {
		super();
		this.code = code;
	}
	
}