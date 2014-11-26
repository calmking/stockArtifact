package com.jf.stock.model;

// Generated 2014-10-20 17:26:03 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Qlcy generated by hbm2java
 */
@Entity
@Table(name = "qlcy", catalog = "stock")
public class Qlcy implements java.io.Serializable {

	private QlcyId id;
	private double dkt;
	private double zkt;
	private double zf;
	private double lb;

	public Qlcy() {
	}

	public Qlcy(QlcyId id, double dkt, double zkt, double zf, double lb) {
		this.id = id;
		this.dkt = dkt;
		this.zkt = zkt;
		this.zf = zf;
		this.lb = lb;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "obj", column = @Column(name = "obj", nullable = false, length = 32)),
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false)) })
	public QlcyId getId() {
		return this.id;
	}

	public void setId(QlcyId id) {
		this.id = id;
	}

	@Column(name = "dkt", nullable = false, precision = 22, scale = 0)
	public double getDkt() {
		return this.dkt;
	}

	public void setDkt(double dkt) {
		this.dkt = dkt;
	}

	@Column(name = "zkt", nullable = false, precision = 22, scale = 0)
	public double getZkt() {
		return this.zkt;
	}

	public void setZkt(double zkt) {
		this.zkt = zkt;
	}

	@Column(name = "zf", nullable = false, precision = 22, scale = 0)
	public double getZf() {
		return this.zf;
	}

	public void setZf(double zf) {
		this.zf = zf;
	}

	@Column(name = "lb", nullable = false, precision = 22, scale = 0)
	public double getLb() {
		return this.lb;
	}

	public void setLb(double lb) {
		this.lb = lb;
	}

}