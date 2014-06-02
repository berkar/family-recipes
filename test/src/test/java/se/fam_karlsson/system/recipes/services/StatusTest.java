package se.fam_karlsson.system.recipes.services;

import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.Assert.assertUnauthorized;

/**
 * Created with IntelliJ IDEA.
 * User: berkar
 * Date: 2012-12-12
 * Time: 15:48
 */
@RunWith(HttpJUnitRunner.class)
public class StatusTest {

	private static final String itsHost = System.getProperty("remote-test-host", "localhost:8080");

	@Rule
	public Destination itsDestination = new Destination(this, "http://" + itsHost);

	@Context
	private Response response; // will be injected after every request

	@HttpTest(method = Method.GET, path = "/recipe/rest/status/ping")
	public void testAuthentication() {
		assertUnauthorized(response);
	}

	@HttpTest(method = Method.GET, path = "/recipe/rest/status/ping", authentications = {@Authentication(type = AuthenticationType.BASIC, user = "__FloggSvc_metria.se@Z62.tconet.net", password = "M354UwbTY4")})
	public void checkStatusPing() {
		assertOk(response);
	}

}