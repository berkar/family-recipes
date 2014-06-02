package se.fam_karlsson.system.recipes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT")
@SuppressWarnings("unused")
public class Ingredient {
	private String itsGuid;
	private Integer itsQty;
	private String itsUnit;
	private String itsName;
	private String itsType;

	@Id
	@Column(name = "GUID")
	public String getGuid() {
		return itsGuid;
	}

	public void setGuid(String theGuid) {
		itsGuid = theGuid;
	}

	@Column(name = "QUANTITY")
	public Integer getQty() {
		return itsQty;
	}

	public void setQty(Integer theQty) {
		itsQty = theQty;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return itsUnit;
	}

	public void setUnit(String theUnit) {
		itsUnit = theUnit;
	}

	@Column(name = "NAME")
	public String getName() {
		return itsName;
	}

	public void setName(String theName) {
		itsName = theName;
	}

	@Column(name = "TYPE")
	public String getType() {
		return itsType;
	}

	public void setType(String theType) {
		itsType = theType;
	}
}
