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
public class PlayerRestService {
    
    public WebResource resource;

    public PlayerRestService(WebResource resource) {
        this.resource = resource;
    }
   
    public List<PlayerDTO> findAll(){
        ClientResponse response = resource
                .type(MediaType.TEXT_XML)
                .get(ClientResponse.class);
        return response.getStatus() == 200 ? response.getEntity(new GenericType<List<PlayerDTO>>(){}) : null;
    }
    
    public PlayerDTO getById(Long id){
        ClientResponse response = resource
                .path("playerId")
                .path(id.toString())
                .type(MediaType.TEXT_XML)
                .get(ClientResponse.class);
        
        return response.getStatus() == 200 ? response.getEntity(PlayerDTO.class) : null;
    }
    
    
}
