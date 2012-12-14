/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.service;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import java.util.List;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Stefan
 */
public class PlayerRestService implements PlayerService {

    public WebResource resource;

    public PlayerRestService(WebResource resource) {
        this.resource = resource;
    }

    public List<PlayerDTO> findAll() {
        ClientResponse response = resource
                .type(MediaType.TEXT_XML)
                .get(ClientResponse.class);
        return response.getStatus() == 200 ? response.getEntity(new GenericType<List<PlayerDTO>>() {
        }) : null;
    }

    public PlayerDTO getById(Long id) {
        ClientResponse response = resource
                .path(id.toString())
                .type(MediaType.TEXT_XML)
                .get(ClientResponse.class);

        return response.getStatus() == 200 ? response.getEntity(PlayerDTO.class) : null;
    }

    @Override
    public long create(PlayerDTO dto) {

        ClientResponse response = resource
                .type(MediaType.TEXT_XML)
                //.accept(MediaType.TEXT_XML)
                .post(ClientResponse.class, dto);

        // if http status is not 200 OK then fail
        if (response.getStatus() != 200) {
            throw new IllegalArgumentException("Operation failed!");
        }
        return 0; //UPRAVIT
    }

    @Override
    public long update(PlayerDTO dto) {
        ClientResponse response = resource
                .type(MediaType.TEXT_XML)
                //.accept(MediaType.TEXT_XML)
                .put(ClientResponse.class, dto);
        
        if(response.getStatus() != 200) throw new IllegalArgumentException("Operation failed!");
        return 0; //UPRAVIT
    }

    @Override
    public void delete(PlayerDTO dto) {
        ClientResponse response = resource
                .path(String.valueOf(dto.getId()))
                .type(MediaType.TEXT_XML)
                .delete(ClientResponse.class);

        if(response.getStatus() != 200) throw new IllegalArgumentException("Operation failed!");
    }

    @Override
    public List<PlayerDTO> findPlayersByTeam(Long teamId, PlayerOrderBy orderBy) {
        ClientResponse response = resource
                .path("teamId")
                .path(teamId.toString())
                .type(MediaType.TEXT_XML)
                .get(ClientResponse.class);

        return response.getStatus() == 200 ? response.getEntity(new GenericType<List<PlayerDTO>>() {
        }) : null;
    }
}
