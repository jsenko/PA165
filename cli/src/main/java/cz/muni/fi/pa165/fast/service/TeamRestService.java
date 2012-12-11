package cz.muni.fi.pa165.fast.service;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import cz.muni.fi.pa165.fast.dto.TeamDTO;

public class TeamRestService implements TeamService
{

    public WebResource resource;
    
    public TeamRestService(WebResource resource)
    {
        this.resource = resource;
    }

    @Override
    public void create(TeamDTO dto)
    {
        ClientResponse response = resource
                .type(MediaType.TEXT_XML)
                //.accept(MediaType.TEXT_XML)
                .post(ClientResponse.class, dto);
    }

    @Override
    public void update(TeamDTO dto)
    {
        ClientResponse response = resource
                .type(MediaType.TEXT_XML)
                //.accept(MediaType.TEXT_XML)
                .put(ClientResponse.class, dto);
    }

    @Override
    public void delete(TeamDTO dto)
    {
        ClientResponse response = resource
                .path(String.valueOf(dto.getId()))
                .accept(MediaType.TEXT_XML)
                .delete(ClientResponse.class);

        //return response.getStatus() == 200 ? response.getEntity(TeamDTO.class) : null;
    }

    @Override
    public TeamDTO getById(Long id)
    {
        ClientResponse response = resource
                .path(id.toString())
                .accept(MediaType.TEXT_XML)
                .get(ClientResponse.class);

        return response.getStatus() == 200 ? response.getEntity(TeamDTO.class) : null;
    }

    @Override
    public List<TeamDTO> findAll()
    {
        ClientResponse response = resource
                .accept(MediaType.TEXT_XML)
                .get(ClientResponse.class);

        return response.getStatus() == 200 ? response.getEntity(new GenericType<List<TeamDTO>>(){}) : null;
    }

    @Override
    public TeamDTO findByPlayer(long playerId)
    {
        ClientResponse response = resource
                .path("playerId").path(String.valueOf(playerId))
                .accept(MediaType.TEXT_XML)
                .get(ClientResponse.class);

        return response.getStatus() == 200 ? response.getEntity(TeamDTO.class) : null;
    }

    @Override
    public void generate()
    {
        throw new UnsupportedOperationException("TODO");
    }
}
