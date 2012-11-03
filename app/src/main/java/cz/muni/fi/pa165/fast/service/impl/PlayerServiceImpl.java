/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.comparator.SortByPlayerAge;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerGoals;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerHeight;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerName;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerWeight;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    
    @EJB
    GoalDAO goalDao;
    
    
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
    public List<PlayerDTO> findAll(PlayerOrderBy orderBy) {
        
        Collection<Player> allPlayers = playerDao.findAll();
        List<PlayerDTO> allDtoPlayers = new ArrayList<PlayerDTO>();
        
        for(Player player : allPlayers)
        {
            PlayerDTO dtoPlayer = new PlayerDTO();
                        
            dtoPlayer.setId(player.getId());
            dtoPlayer.setName(player.getName());
            dtoPlayer.setSurname(player.getSurname());
            dtoPlayer.setAge(player.getAge());
            dtoPlayer.setHeight(player.getHeight());
            dtoPlayer.setWeight(player.getWeight());
            dtoPlayer.setGoals(goalDao.findByScorePlayer(player).size());
            dtoPlayer.setAssists(goalDao.findByAssistPlayer(player).size());
            
            allDtoPlayers.add(dtoPlayer);
        }
        
        switch(orderBy)
        {
            
            case NAME:
                Collections.sort(allDtoPlayers, new SortByPlayerName());
                break;
            case GOALS:
                Collections.sort(allDtoPlayers, new SortByPlayerGoals());
                break;
            case AGE:
                Collections.sort(allDtoPlayers, new SortByPlayerAge());
                break;
            case WEIGHT:
                Collections.sort(allDtoPlayers, new SortByPlayerWeight());
                break;
            case HEIGHT:
                Collections.sort(allDtoPlayers, new SortByPlayerHeight());
                break;
        }
        
        return allDtoPlayers;
    }
    
}
