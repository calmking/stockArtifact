package com.jf.stock.model;

// Generated 2014-10-20 17:26:03 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * St22041Main generated by hbm2java
 */
@Entity
@Table(name = "st22041_main", catalog = "stock")
public class St22041Main implements java.io.Serializable {

	private St22041MainId id;
	private Long cv;
	private Long c1;
	private Integer c3;
	private Double c4;
	private Double c5;
	private Double c6;
	private Double c7;
	private Double c8;
	private Double c9;
	private Double c10;
	private Double c11;
	private String c12;
	private Long c13;
	private Double c14;
	private Double c15;
	private Double c16;

	public St22041Main() {
	}

	public St22041Main(St22041MainId id) {
		this.id = id;
	}

	public St22041Main(St22041MainId id, Long cv, Long c1, Integer c3,
			Double c4, Double c5, Double c6, Double c7, Double c8, Double c9,
			Double c10, Double c11, String c12, Long c13, Double c14,
			Double c15, Double c16) {
		this.id = id;
		this.cv = cv;
		this.c1 = c1;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.c6 = c6;
		this.c7 = c7;
		this.c8 = c8;
		this.c9 = c9;
		this.c10 = c10;
		this.c11 = c11;
		this.c12 = c12;
		this.c13 = c13;
		this.c14 = c14;
		this.c15 = c15;
		this.c16 = c16;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "co", column = @Column(name = "CO", nullable = false, length = 32)),
			@AttributeOverride(name = "c2", column = @Column(name = "C2", nullable = false, length = 165)) })
	public St22041MainId getId() {
		return this.id;
	}

	public void setId(St22041MainId id) {
		this.id = id;
	}

	@Column(name = "CV")
	public Long getCv() {
		return this.cv;
	}

	public void setCv(Long cv) {
		this.cv = cv;
	}

	@Column(name = "C1")
	public Long getC1() {
		return this.c1;
	}

	public void setC1(Long c1) {
		this.c1 = c1;
	}

	@Column(name = "C3")
	public Integer getC3() {
		return this.c3;
	}

	public void setC3(Integer c3) {
		this.c3 = c3;
	}

	@Column(name = "C4", precision = 22, scale = 0)
	public Double getC4() {
		return this.c4;
	}

	public void setC4(Double c4) {
		this.c4 = c4;
	}

	@Column(name = "C5", precision = 22, scale = 0)
	public Double getC5() {
		return this.c5;
	}

	public void setC5(Double c5) {
		this.c5 = c5;
	}

	@Column(name = "C6", precision = 22, scale = 0)
	public Double getC6() {
		return this.c6;
	}

	public void setC6(Double c6) {
		this.c6 = c6;
	}

	@Column(name = "C7", precision = 22, scale = 0)
	public Double getC7() {
		return this.c7;
	}

	public void setC7(Double c7) {
		this.c7 = c7;
	}

	@Column(name = "C8", precision = 22, scale = 0)
	public Double getC8() {
		return this.c8;
	}

	public void setC8(Double c8) {
		this.c8 = c8;
	}

	@Column(name = "C9", precision = 22, scale = 0)
	public Double getC9() {
		return this.c9;
	}

	public void setC9(Double c9) {
		this.c9 = c9;
	}

	@Column(name = "C10", precision = 22, scale = 0)
	public Double getC10() {
		return this.c10;
	}

	public void setC10(Double c10) {
		this.c10 = c10;
	}

	@Column(name = "C11", precision = 22, scale = 0)
	public Double getC11() {
		return this.c11;
	}

	public void setC11(Double c11) {
		this.c11 = c11;
	}

	@Column(name = "C12")
	public String getC12() {
		return this.c12;
	}

	public void setC12(String c12) {
		this.c12 = c12;
	}

	@Column(name = "C13")
	public Long getC13() {
		return this.c13;
	}

	public void setC13(Long c13) {
		this.c13 = c13;
	}

	@Column(name = "C14", precision = 22, scale = 0)
	public Double getC14() {
		return this.c14;
	}

	public void setC14(Double c14) {
		this.c14 = c14;
	}

	@Column(name = "C15", precision = 22, scale = 0)
	public Double getC15() {
		return this.c15;
	}

	public void setC15(Double c15) {
		this.c15 = c15;
	}

	@Column(name = "C16", precision = 22, scale = 0)
	public Double getC16() {
		return this.c16;
	}

	public void setC16(Double c16) {
		this.c16 = c16;
	}

}
