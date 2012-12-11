package cz.muni.fi.pa165.fast.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;

@Stateless
@Path("/player")
public class PlayerRest
{   
    @EJB
    private PlayerService ps;
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<PlayerDTO> findAll(
            @QueryParam("orderBy") @DefaultValue("NAME") PlayerOrderBy orderBy,
            @QueryParam("teamId") long teamId)
    {
        if(teamId > 0)
        {
            return ps.findPlayersByTeam(teamId, orderBy);
        }
        
        return ps.findAll(orderBy);
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("{id}")
    public PlayerDTO getById(@PathParam("id") long id)
    {
        return ps.getById(id);
    }
    
    @POST
    @Consumes(MediaType.TEXT_XML)
    public Response create(PlayerDTO playerDTO)
    {
        long id = ps.create(playerDTO);
        return Response.created(URI.create("/" + id)).build();
    }
    
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public Response update(PlayerDTO playerDTO)
    {
        long id = 0;
        if(ps.getById(playerDTO.getId()) != null)
        {
            id = ps.update(playerDTO);
        }
        else
        {
            playerDTO.setId(0);
            System.out.println(">>>>>>>>>>>"+playerDTO);
            id = ps.create(playerDTO);
        }
        
        return Response.created(URI.create("/" + id)).build();
    }
}
