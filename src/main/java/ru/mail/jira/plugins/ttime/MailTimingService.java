package ru.mail.jira.plugins.ttime;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author Andrey Markelov
 */
@Path("/mailtimingws")
public class MailTimingService
{
    /**
     * Logger.
     */
    private final Logger log = Logger.getLogger(MailTimingService.class);

    /**
     * Constructor.
     */
    public MailTimingService()
    {
        
    }

    @POST
    @Path("/initjqldlg")
    @Produces({MediaType.APPLICATION_JSON})
    public Response initJqlDialog(@Context HttpServletRequest req)
    {
        return Response.ok().build();
    }
}
