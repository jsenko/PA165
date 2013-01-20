package cz.muni.fi.pa165.fast.rest;

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
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
@Path("/player")
public class PlayerRest {

    @EJB
    private PlayerService ps;
    @EJB
    private TeamService ts;
    
    @EJB
    SecurityFacade sf;

    @GET
    @Path("teamId/{id}")
    @Produces(MediaType.TEXT_XML)
    public List<PlayerDTO> findPlayersBytTeamId(@PathParam("id") Long id) {
        sf.login("admin", "password"); // to enable full access to services via rest
        return ps.findPlayersByTeam(id, PlayerOrderBy.NAME);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_XML)
    public PlayerDTO findPlayerById(@PathParam("id") Long id) {
        sf.login("admin", "password"); // to enable full access to services via rest
        return ps.getById(id);
    }

    @POST
    @Consumes(MediaType.TEXT_XML)
    public Response create(PlayerDTO playerDTO) {
        sf.login("admin", "password"); // to enable full access to services via rest
        ps.create(playerDTO);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.TEXT_XML)
    public Response update(PlayerDTO playerDTO) {
        sf.login("admin", "password"); // to enable full access to services via rest
        ps.update(playerDTO);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.TEXT_XML)
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        sf.login("admin", "password"); // to enable full access to services via rest
        ps.delete(ps.getById(id));
        return Response.ok().build();
    }
}
