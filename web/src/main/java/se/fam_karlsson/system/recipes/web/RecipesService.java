package se.fam_karlsson.system.recipes.web;

import se.fam_karlsson.system.recipes.model.Recipe;
import se.fam_karlsson.system.recipes.services.RecipesServiceBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@SessionScoped
@ManagedBean(name = "recipesService")
public class RecipesService {

	@Inject
	private RecipesServiceBean itsService;

	public List<Recipe> findAll() {
		return itsService.findAll();
	}

}
