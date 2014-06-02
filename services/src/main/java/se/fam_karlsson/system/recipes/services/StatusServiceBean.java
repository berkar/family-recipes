package se.fam_karlsson.system.recipes.services;

import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("status")
public class StatusServiceBean {

	private transient Logger itsLog = Logger.getLogger(StatusServiceBean.class);

	@HEAD
	@Path("/ping")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Response getStatus() {
		itsLog.trace("Status Ping received!");
		return Response.ok().build();
	}
}
