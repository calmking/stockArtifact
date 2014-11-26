package com.jf.stock.model;

// Generated 2014-10-20 17:26:03 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * KlyhId generated by hbm2java
 */
@Embeddable
public class KlyhId implements java.io.Serializable {

	private String obj;
	private int date;

	public KlyhId() {
	}

	public KlyhId(String obj, int date) {
		this.obj = obj;
		this.date = date;
	}

	@Column(name = "obj", nullable = false, length = 32)
	public String getObj() {
		return this.obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	@Column(name = "date", nullable = false)
	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KlyhId))
			return false;
		KlyhId castOther = (KlyhId) other;

		return ((this.getObj() == castOther.getObj()) || (this.getObj() != null
				&& castOther.getObj() != null && this.getObj().equals(
				castOther.getObj())))
				&& (this.getDate() == castOther.getDate());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getObj() == null ? 0 : this.getObj().hashCode());
		result = 37 * result + this.getDate();
		return result;
	}

}
