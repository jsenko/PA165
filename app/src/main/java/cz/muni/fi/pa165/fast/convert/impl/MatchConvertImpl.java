package cz.muni.fi.pa165.fast.convert.impl;

import cz.muni.fi.pa165.fast.convert.MatchConvert;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Implementation of the Convert interface for match service.
 *
 * @author Jakub Senko
 */
@Local(value = MatchConvert.class)
@Stateless
public class MatchConvertImpl implements MatchConvert {

    @EJB
    TeamDAO teamDAO;
    
    @EJB
    GoalDAO goalDAO;

    @Override
    public MatchDTO fromEntityToDTO(Match entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Match entity is null.");
        }
        if (entity.getHomeTeam() == null || entity.getAwayTeam() == null) {
            throw new RuntimeException("Home/Away Team cannot be null");
        }

        MatchDTO dto = new MatchDTO();
        if (entity.getId() == null) {
            dto.setId(0);
        } else {
            dto.setId(entity.getId());
        }
        dto.setRound(entity.getRound());
        dto.setDate(entity.getMatchDate());

        dto.setHomeTeamId(entity.getHomeTeam().getId());
        dto.setHomeTeamName(entity.getHomeTeam().getName());

        dto.setAwayTeamId(entity.getAwayTeam().getId());
        dto.setAwayTeamName(entity.getAwayTeam().getName());

        int homeGoals = 0;
        int awayGoals = 0;

        for (Goal g : goalDAO.findByMatch(entity)) {
            if (entity.getHomeTeam().equals(
                    teamDAO.findTeamByPlayer(g.getScorePlayer()))) {
                    //g.getScorePlayer().getTeam())){

                homeGoals++;
            } else {
                awayGoals++;
            }
        }

        dto.setHomeTeamGoals(homeGoals);
        dto.setAwayTeamGoals(awayGoals);

        return dto;
    }

    @Override
    public Match fromDTOToEntity(MatchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("MatchDTO is null.");
        }

        // get home team by id
        Team homeTeam = null;
        try {
            homeTeam = teamDAO.getById(dto.getHomeTeamId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find team with an id = "
                    + dto.getHomeTeamId(), e);
        }

        // get away team by id
        Team awayTeam = null;
        try {
            awayTeam = teamDAO.getById(dto.getAwayTeamId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find team with an id = "
                    + dto.getAwayTeamId(), e);
        }

        // create the match
        Match m = new Match();
        if (dto.getId() == 0) {
            m.setId(null);
        } else {
            m.setId(dto.getId());
        }
        m.setHomeTeam(homeTeam);
        m.setAwayTeam(awayTeam);
        // goals is mappedBy attribute, we will ignore it
        m.setMatchDate(dto.getDate());
        m.setRound(dto.getRound());
        return m;
    }
}
