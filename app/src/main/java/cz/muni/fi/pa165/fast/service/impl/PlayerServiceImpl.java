/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PlayerServiceImpl implements PlayerService{

    @EJB
    PlayerDAO playerDao;
    
    @Override
    public long create(PlayerDTO dto) {
        
        Player player = new Player();
        
        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setAge(dto.getAge());
        player.setHeight(dto.getHeight());
        player.setWeight(dto.getWeight());
        
        playerDao.create(player);
        
        return player.getId();
    }

    @Override
    public long update(PlayerDTO dto) {
        Player player = new Player();
        
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setAge(dto.getAge());
        player.setHeight(dto.getHeight());
        player.setWeight(dto.getWeight());
        
        playerDao.update(player);
        
        return player.getId();
    }

    @Override
    public void delete(PlayerDTO dto) {
        Player player = new Player();
        
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setAge(dto.getAge());
        player.setHeight(dto.getHeight());
        player.setWeight(dto.getWeight());
        
        playerDao.delete(player);
    }

    @Override
    public List<MatchDTO> findAll(PlayerOrderBy orderBy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
