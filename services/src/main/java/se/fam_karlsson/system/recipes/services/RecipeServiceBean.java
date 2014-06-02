package se.fam_karlsson.system.recipes.services;

import se.fam_karlsson.system.recipes.model.Recipe;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("recipe")
public class RecipeServiceBean {

	@PersistenceContext(unitName = "recipes-db")
	private EntityManager itsEntityManager;

	@HEAD
	@Path("/ping")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Response getStatus() {
		System.out.println("******** HEAD ***********");
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Response get(@PathParam("id") String theId) {
		System.out.println("********GET: " + theId + " ***********");
		Recipe aRecipe = itsEntityManager.find(Recipe.class, theId);
		if (aRecipe != null) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	Response create(Recipe theRecipe) {
		System.out.println("******** POST: " + theRecipe + " ***********");
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	Response update(@PathParam("id") String theId, Recipe theRecipe) {
		System.out.println("******** PUT: " + theRecipe + " ***********");
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	Response delete(@PathParam("id") String theId) {
		System.out.println("******** DELETE: " + theId + " ***********");
		return Response.ok().build();
	}
}
