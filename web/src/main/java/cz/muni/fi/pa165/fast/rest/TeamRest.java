package cz.muni.fi.pa165.fast.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.TeamService;

@Stateless
@Path("/team")
public class TeamRest
{   
    @EJB
    private TeamService ts;
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<TeamDTO> findAll()
    {
        return ts.findAll();
    }
    
    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("playerId/{id}") // different url had to be used
    public TeamDTO findByTeam(@PathParam("id") long playerId)
    {
        return ts.findByPlayer(playerId);
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("{id}")
    public TeamDTO getById(@PathParam("id") long id)
    {
        return ts.getById(id);
    }
    
    @POST
    @Consumes(MediaType.TEXT_XML)
    public Response create(TeamDTO teamDTO)
    {
        ts.create(teamDTO);
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public Response update(TeamDTO teamDTO)
    {
        ts.update(teamDTO);
        
        return Response.ok().build();
    }
    
    @DELETE
    @Consumes(MediaType.TEXT_XML)
    @Path("{id}")
    public Response delete(@PathParam("id") long id)
    {
        
        ts.delete(ts.getById(id));
        
        return Response.ok().build();
    }
}
