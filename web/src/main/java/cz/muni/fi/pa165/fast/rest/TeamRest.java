package cz.muni.fi.pa165.fast.rest;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/team")
public class TeamRest {

    @EJB
    private TeamService ts;
    
    @EJB
    SecurityFacade sf;

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<TeamDTO> findAll() {
        sf.setUser(null);
        sf.login("admin", "password"); // to enable full access to services via rest
        return ts.findAll();
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("playerId/{id}") // different url had to be used
    public TeamDTO findByTeam(@PathParam("id") long playerId) {
        sf.setUser(null);
        sf.login("admin", "password"); // to enable full access to services via rest
        return ts.findByPlayer(playerId);
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("{id}")
    public TeamDTO getById(@PathParam("id") long id) {
        sf.setUser(null);
        sf.login("admin", "password"); // to enable full access to services via rest
        return ts.getById(id);
    }

    @POST
    @Consumes(MediaType.TEXT_XML)
    public Response create(TeamDTO teamDTO) {
        sf.setUser(null);
        sf.login("admin", "password"); // to enable full access to services via rest
        ts.create(teamDTO);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.TEXT_XML)
    public Response update(TeamDTO teamDTO) {
        sf.setUser(null);
        sf.login("admin", "password"); // to enable full access to services via rest
        ts.update(teamDTO);

        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.TEXT_XML)
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        sf.setUser(null);
        sf.login("admin", "password"); // to enable full access to services via rest

        ts.delete(ts.getById(id));

        return Response.ok().build();
    }
}
