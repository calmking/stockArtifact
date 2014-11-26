package com.jf.stock.model;

// Generated 2014-10-20 17:26:03 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Code generated by hbm2java
 */
@Entity
@Table(name = "code", catalog = "stock")
public class Code implements java.io.Serializable {

	private String obj;
	private String name;

	public Code() {
	}

	public Code(String obj, String name) {
		this.obj = obj;
		this.name = name;
	}

	@Id
	@Column(name = "obj", unique = true, nullable = false, length = 32)
	public String getObj() {
		return this.obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	@Column(name = "name", nullable = false, length = 24)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
