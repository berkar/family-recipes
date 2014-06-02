package se.fam_karlsson.system.recipes.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RECIPE")
@NamedQuery(name = Recipe.FIND_ALL, query = "SELECT r FROM Recipe r")
@SuppressWarnings("unused")
public class Recipe {

	public final static String FIND_ALL = "Recipe.FIND_ALL";

	private String itsGuid;
	private String itsName;
	private String itsDescription;
	private String itsType;
	private Set<Ingredient> itsIngredients;

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

	@Column(name = "TYPE")
	public String getType() {
		return itsType;
	}

	public void setType(String theType) {
		itsType = theType;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "RECIPE_GUID", referencedColumnName = "GUID", nullable = false)
	public Set<Ingredient> getIngredients() {
		return itsIngredients;
	}

	public void setIngredients(Set<Ingredient> theIngredients) {
		itsIngredients = theIngredients;
	}
}
