package com.jf.stock.model;

// Generated 2014-10-20 17:26:03 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Macd generated by hbm2java
 */
@Entity
@Table(name = "macd", catalog = "stock")
public class Macd implements java.io.Serializable {

	private MacdId id;
	private Double ema12;
	private Double ema26;
	private Double dif;
	private Double dea;

	public Macd() {
	}

	public Macd(MacdId id) {
		this.id = id;
	}

	public Macd(MacdId id, Double ema12, Double ema26, Double dif, Double dea) {
		this.id = id;
		this.ema12 = ema12;
		this.ema26 = ema26;
		this.dif = dif;
		this.dea = dea;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "obj", column = @Column(name = "obj", nullable = false, length = 32)),
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false)) })
	public MacdId getId() {
		return this.id;
	}

	public void setId(MacdId id) {
		this.id = id;
	}

	@Column(name = "ema12", precision = 22, scale = 0)
	public Double getEma12() {
		return this.ema12;
	}

	public void setEma12(Double ema12) {
		this.ema12 = ema12;
	}

	@Column(name = "ema26", precision = 22, scale = 0)
	public Double getEma26() {
		return this.ema26;
	}

	public void setEma26(Double ema26) {
		this.ema26 = ema26;
	}

	@Column(name = "dif", precision = 22, scale = 0)
	public Double getDif() {
		return this.dif;
	}

	public void setDif(Double dif) {
		this.dif = dif;
	}

	@Column(name = "dea", precision = 22, scale = 0)
	public Double getDea() {
		return this.dea;
	}

	public void setDea(Double dea) {
		this.dea = dea;
	}

}
