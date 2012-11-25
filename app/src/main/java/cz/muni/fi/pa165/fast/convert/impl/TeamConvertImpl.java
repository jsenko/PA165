package cz.muni.fi.pa165.fast.convert.impl;

import cz.muni.fi.pa165.fast.convert.TeamConvert;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dto.MatchResult;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local(value=TeamConvert.class)
@Stateless
public class TeamConvertImpl implements TeamConvert{

    @EJB
    private MatchDAO matchDao;

    @Override
    public TeamDTO fromEntityToDTO(Team entity) {
        if (entity == null) {
            return null;
        }
        TeamDTO dto = new TeamDTO();
        if (entity.getId() == null) {
            dto.setId(0);
        } else {
            dto.setId(entity.getId());
        }
        dto.setName(entity.getName());

        List<Match> matches = matchDao.findByAwayTeam(entity);
        matches.addAll(matchDao.findByHomeTeam(entity));
        Collections.sort(matches);
        int i = 0;
        MatchResult[] trend = new MatchResult[5];

        for (Match match : matches) {
            Collection<Goal> goals = match.getGoals();
            int inGoals = 0, outGoals = 0;
            for (Goal goal : goals) {
                if (entity.getPlayers().contains(goal.getScorePlayer())) {
                    outGoals++;
                } else {
                    inGoals++;
                }
            }
            dto.setGoalsFor(dto.getGoalsFor() + outGoals);
            dto.setGoalsAgainst(dto.getGoalsAgainst() + inGoals);

            if (outGoals > inGoals) {
                dto.setWon(dto.getWon() + 1);
                if (i < 5) {
                    trend[i] = MatchResult.WON;
                }
            } else if (outGoals < inGoals) {
                dto.setLost(dto.getLost() + 1);
                if (i < 5) {
                    trend[i] = MatchResult.LOST;
                }
            } else {
                dto.setDraw(dto.getDraw() + 1);
                if (i < 5) {
                    trend[i] = MatchResult.DRAWN;
                }
            }

            i++;
        }

        dto.setPoints(dto.getWon() * 2 + dto.getDraw());
        dto.setTrend(trend);

        return dto;
    }

    @Override
    public Team fromDTOToEntity(TeamDTO dto) {
        if (dto == null) {
            return null;
        }
        Team entity = new Team();
        if (dto.getId() == 0) {
            entity.setId(null);
        } else {
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());

        return entity;
    }
}