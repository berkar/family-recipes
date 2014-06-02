package se.fam_karlsson.system.recipes.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COOKBOOK")
@SuppressWarnings("unused")
public class Cookbook {
	private String itsGuid;
	private String itsName;
	private String itsDescription;
	private String itsOwner;
	private Set<Recipe> itsRecipes;

	@Id
	@Column(name = "GUID")
	public String getGuid() {
		return itsGuid;
	}

	public void setGuid(String theGuid) {
		itsGuid = theGuid;
	}

	@Column(name = "NAME")
	public String getName() {
		return itsName;
	}

	public void setName(String theName) {
		itsName = theName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return itsDescription;
	}

	public void setDescription(String theDescription) {
		itsDescription = theDescription;
	}

	@Column(name = "OWNER")
	public String getOwner() {
		return itsOwner;
	}

	public void setOwner(String theOwner) {
		itsOwner = theOwner;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "COOKBOOK_GUID", referencedColumnName = "GUID", nullable = false)
	public Set<Recipe> getRecipes() {
		return itsRecipes;
	}

	public void setRecipes(Set<Recipe> theRecipes) {
		itsRecipes = theRecipes;
	}
}
