package com.jf.stock.model;

// Generated 2014-10-20 17:26:03 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Userinfo generated by hbm2java
 */
@Entity
@Table(name = "userinfo", catalog = "stock")
public class Userinfo implements java.io.Serializable {

	private String username;
	private String passwd;
	private String privilege;

	public Userinfo() {
	}

	public Userinfo(String username, String passwd, String privilege) {
		this.username = username;
		this.passwd = passwd;
		this.privilege = privilege;
	}

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 32)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "passwd", nullable = false, length = 32)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "privilege", nullable = false, length = 32)
	public String getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}