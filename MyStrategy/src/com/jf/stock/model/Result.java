package com.jf.stock.model;

// Generated 2014-10-20 17:26:03 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Result generated by hbm2java
 */
@Entity
@Table(name = "result", catalog = "stock")
public class Result implements java.io.Serializable {

	private ResultId id;
	private String name;

	public Result() {
	}

	public Result(ResultId id, String name) {
		this.id = id;
		this.name = name;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false)),
			@AttributeOverride(name = "type", column = @Column(name = "type", nullable = false)),
			@AttributeOverride(name = "obj", column = @Column(name = "obj", nullable = false, length = 20)) })
	public ResultId getId() {
		return this.id;
	}

	public void setId(ResultId id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 24)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}