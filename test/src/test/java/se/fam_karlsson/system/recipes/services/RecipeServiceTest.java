package se.fam_karlsson.system.recipes.services;

import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Rule;
import org.junit.runner.RunWith;
import se.fam_karlsson.system.recipes.helpers.StreamHelper;
import se.fam_karlsson.system.recipes.model.Recipe;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import static com.eclipsesource.restfuse.Assert.assertNoContent;
import static com.eclipsesource.restfuse.Assert.assertOk;
import static org.junit.Assert.assertTrue;

@RunWith(HttpJUnitRunner.class)
public class RecipeServiceTest {

	private final static ObjectMapper itsJsonMapper = new ObjectMapper();

	private static final String itsHost = System.getProperty("remote-test-host", "localhost:8080");

	private static final String itsUser = ""; // System.getProperty("remote-test-user");
	private static final String itsPwd = ""; //System.getProperty("remote-test-pwd");

	// Set this if running in IDEA!
//	private static final String itsRoot = System.getProperty("testRoot", "test/target/test-classes");

	// Set this if running in maven
	private static final String itsRoot = System.getProperty("testRoot", "target/test-classes");

	private static Recipe itsObjekt = null;

	@Rule
	public Destination itsDestination = getDestination();

	private Destination getDestination() {
		Destination destination = new Destination(this, "http://" + itsHost + "/recipe/rest/");
		RequestContext context = destination.getRequestContext();
		context.addPathSegment("id", itsObjekt != null ? itsObjekt.getGuid().toString() : "99");
		return destination;
	}

	@Context
	private Response response; // will be injected after every request

	@HttpTest(order = 0, method = Method.GET, path = "recipe/ping")
	public void ping() {
		assertOk(response);
	}

	@HttpTest(order = 10, method = Method.POST, path = "recipe", file = "/Recipe.json", type = MediaType.APPLICATION_JSON, authentications = {@Authentication(type = AuthenticationType.BASIC, user = itsUser, password = itsPwd)})
	public void create() throws Exception {
		assertOk(response);
//		assertCreated(response); TODO Should we use this (201) instead as a response?? More "correct"?
		itsObjekt = itsJsonMapper.readValue(response.getBody(), Recipe.class);
	}

	@HttpTest(order = 20, method = Method.GET, path = "recipe/{id}", authentications = {@Authentication(type = AuthenticationType.BASIC, user = "bertil@fam-karlsson.com", password = "M354UwbTY4")})
	public void get() throws Exception {
		assertTrue("Expected object to compare with!", itsObjekt != null);
		assertOk(response);
		Recipe aObjekt = itsJsonMapper.readValue(response.getBody(), Recipe.class);
		assertTrue(aObjekt != null);
		assertTrue(itsObjekt.getGuid().equals(aObjekt.getGuid()));
		// Write the objekt to file after a change that will be used later! This will preserve all identities
//		aObjekt.setKortAnteckning(aObjekt.getKortAnteckning() + "-CHANGE");
		itsJsonMapper.writeValue(new File(itsRoot + "/Recipe-CHANGE.json"), aObjekt);
	}

	@HttpTest(order = 40, method = Method.PUT, path = "recipe/{id}", file = "/Recipe-CHANGE.json", type = MediaType.APPLICATION_JSON, authentications = {@Authentication(type = AuthenticationType.BASIC, user = "bertil@fam-karlsson.com", password = "M354UwbTY4")})
	public void update() throws Exception {
		assertTrue("Expected object to compare with!", itsObjekt != null);
		assertOk(response);
		Recipe aObjekt = itsJsonMapper.readValue(response.getBody(), Recipe.class);
		assertTrue(aObjekt != null);
		assertTrue(itsObjekt.getGuid().equals(aObjekt.getGuid()));
//		assertTrue((itsObjekt.getKortAnteckning() + "-CHANGE").equals(aObjekt.getKortAnteckning()));
	}

	@HttpTest(order = 50, method = Method.DELETE, path = "recipe/{id}", authentications = {@Authentication(type = AuthenticationType.BASIC, user = "bertil@fam-karlsson.com", password = "M354UwbTY4")})
	public void delete() {
		assertNoContent(response);
	}

	@HttpTest(order = 60, method = Method.GET, path = "recipe/{id}", authentications = {@Authentication(type = AuthenticationType.BASIC, user = "bertil@fam-karlsson.com", password = "M354UwbTY4")})
	public void checkDelete() throws Exception {
		assertNoContent(response);
	}

	private static void save(Response theResponse, String theFilename) throws Exception {
		try (FileOutputStream aStream = new FileOutputStream(theFilename)) {
			StreamHelper.copyStream(new ByteArrayInputStream(theResponse.getBody().getBytes()), aStream);
		}
	}
}
