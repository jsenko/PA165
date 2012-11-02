package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.convert.TeamConvert;
import cz.muni.fi.pa165.fast.dao.impl.MatchDAOImpl;
import cz.muni.fi.pa165.fast.dao.impl.PlayerDAOImpl;
import cz.muni.fi.pa165.fast.dao.impl.TeamDAOImpl;
import cz.muni.fi.pa165.fast.dto.MatchResult;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TeamServiceImpl implements TeamService{

    @EJB
    private TeamDAOImpl teamDao;
    @EJB
    private MatchDAOImpl matchDao;
    @EJB
    private PlayerDAOImpl playerDao;
    
    @Override
    public void create(TeamDTO dto) {
        Team team = TeamConvert.fromDTOToEntity(dto);
        
        teamDao.create(team);
    }

    @Override
    public void update(TeamDTO dto) {
        Team team = TeamConvert.fromDTOToEntity(dto);
        
        teamDao.update(team);
    }

    @Override
    public void delete(TeamDTO dto) {
        Team team = TeamConvert.fromDTOToEntity(dto);
        
        teamDao.delete(team);
    }

    @Override
    public List<TeamDTO> findAll() {
        Collection<Team> teams = teamDao.findAll();
        List<TeamDTO> dtos = new ArrayList<TeamDTO>();
        
        for(Team entity:teams){
            TeamDTO dto = getTeamInfo(entity);
            dtos.add(dto);
        }
        Collections.sort(dtos);
        
        return dtos;
    }

    @Override
    public TeamDTO findByPlayer(long playerId) {
        Player player = playerDao.getById(playerId);
        Team entity = teamDao.findTeamByPlayer(player);
        TeamDTO dto = getTeamInfo(entity);
        
        return dto;
    }
    
    private TeamDTO getTeamInfo(Team entity){
        TeamDTO dto = TeamConvert.fromEntityToDTO(entity);
        
        List<Match> matches = matchDao.findByAwayTeam(entity);
        matches.addAll(matchDao.findByHomeTeam(entity));
        Collections.sort(matches);
        int i=0;
        MatchResult[] trend = new MatchResult[5];
        
        for(Match match:matches){
            Collection<Goal> goals = match.getGoals();
            int inGoals = 0, outGoals = 0;
            for(Goal goal:goals){
                if(entity.getPlayers().contains(goal.getScorePlayer())){
                    outGoals++;
                }else{
                    inGoals++;
                }
            }
            dto.setGoalsFor(dto.getGoalsFor()+outGoals);
            dto.setGoalsAgainst(dto.getGoalsAgainst()+inGoals);
            
            if(outGoals > inGoals){
                dto.setWon(dto.getWon()+1);
                if(i<5){
                    trend[i] = MatchResult.WON;
                }
            }else if(outGoals < inGoals){
                dto.setLost(dto.getLost()+1);
                if(i<5){
                    trend[i] = MatchResult.LOST;
                }
            }else{
                dto.setDrawn(dto.getDrawn()+1);
                if(i<5){
                    trend[i] = MatchResult.DRAWN;
                }
            }
            
            i++;
        }
        
        dto.setPoints(dto.getWon()*2+dto.getDrawn());
        dto.setTrend(trend);
        
        return dto;
    }
}
