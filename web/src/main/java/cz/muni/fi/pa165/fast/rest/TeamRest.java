package cz.muni.fi.pa165.fast.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
    @Path("playerId/{id}")
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
    /*
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
    }*/
}
