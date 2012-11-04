/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import javax.ejb.EJB;

/**
 *
 * @author Stefan
 */
public class GoalConvert implements Convert<Goal, GoalDTO> {


    @EJB
    private PlayerDAO playerDAO;
    
    @Override
    public GoalDTO fromEntityToDTO(Goal entity) {
        GoalDTO goalDTO = new GoalDTO();
        
        Team homeTeam = entity.getMatch().getHomeTeam();
        Team scoringPlayerTeam = entity.getScorePlayer().getTeam();
        
        goalDTO.setId(entity.getId());
        
        if(homeTeam.equals(scoringPlayerTeam)) {
            goalDTO.setIsHomeTeam(true);
        }
        else {
            goalDTO.setIsHomeTeam(false);
        }
        
        goalDTO.setPlayerId(entity.getScorePlayer().getId());
        goalDTO.setPlayerName(entity.getScorePlayer().getName());
        
        return goalDTO;
    }

    @Override
    public Goal fromDTOToEntity(GoalDTO dto) {
        Goal goal = new Goal();
        goal.setId(dto.getId());
        
        Player p = playerDAO.getById(dto.getId());
        goal.setScorePlayer(p);
        
        return goal;
    }
    
}
