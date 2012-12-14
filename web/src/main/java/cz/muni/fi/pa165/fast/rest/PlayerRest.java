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
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.LinkedList;

@Stateless
@Path("/player")
public class PlayerRest
{   
    @EJB
    private PlayerService ps;
    @EJB
    private TeamService ts;
    
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<PlayerDTO> findAll(){
        List<PlayerDTO> allPlayers = new LinkedList<PlayerDTO>();
        List<TeamDTO> allTeams = ts.findAll();
        for(TeamDTO t:allTeams){
            allPlayers.addAll(ps.findPlayersByTeam(t.getId(), PlayerOrderBy.NAME));
        }
        return allPlayers;
    }
    
    
    @GET
    @Path("teamId/{id}")
    @Produces(MediaType.TEXT_XML)
    public List<PlayerDTO> findPlayersBytTeamId(@PathParam("id") Long id)
    {
        return ps.findPlayersByTeam(id, PlayerOrderBy.NAME);
    }    
    
    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_XML)
    public PlayerDTO findPlayerById(@PathParam("id") Long id)
    {
        return ps.getById(id);
    }    
    
}
