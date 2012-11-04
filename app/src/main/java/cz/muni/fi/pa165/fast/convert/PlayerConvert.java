/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;
import javax.ejb.EJB;

/**
 *
 * @author Peter Laurencik
 */
public class PlayerConvert implements Convert<Player, PlayerDTO> {

    @EJB
    PlayerDAO playerDao;
    
    @EJB
    GoalDAO goalDao;
    
    @Override
    public PlayerDTO fromEntityToDTO(Player player) {
        if(player == null) throw new IllegalArgumentException("Player entity is null.");
        
        PlayerDTO dtoPlayer = new PlayerDTO();
        
        dtoPlayer.setId(player.getId());
        dtoPlayer.setName(player.getName());
        dtoPlayer.setSurname(player.getSurname());
        dtoPlayer.setAge(player.getAge());
        dtoPlayer.setHeight(player.getHeight());
        dtoPlayer.setWeight(player.getWeight());
        dtoPlayer.setGoals(goalDao.findByScorePlayer(player).size());
        dtoPlayer.setAssists(goalDao.findByAssistPlayer(player).size());
        
        return dtoPlayer;
    }

    @Override
    public Player fromDTOToEntity(PlayerDTO dto) {
        Player player = new Player();
        
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setAge(dto.getAge());
        player.setHeight(dto.getHeight());
        player.setWeight(dto.getWeight());
        
        return player;
    }
    
}
