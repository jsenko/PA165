package cz.muni.fi.pa165.fast.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cz.muni.fi.pa165.fast.convert.TeamConvert;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.security.Acl;
import cz.muni.fi.pa165.fast.security.Role;
import cz.muni.fi.pa165.fast.security.impl.AuthorizationInterceptor;
import cz.muni.fi.pa165.fast.service.TeamService;

@Stateless
@Interceptors({AuthorizationInterceptor.class})
public class TeamServiceImpl implements TeamService {

    @EJB
    private TeamDAO teamDao;
    @EJB
    private PlayerDAO playerDao;
    @EJB
    private TeamConvert teamConvert;
    @EJB
    private MatchDAO matchDao;

    @Override
    @Acl(Role.ADMIN)
    public void create(TeamDTO dto) {
        try {
            Team team = teamConvert.fromDTOToEntity(dto);

            teamDao.create(team);
        } catch (Exception ex) {
            throw new RuntimeException("Create operation failed.", ex);
        }
    }

    @Override
    @Acl(Role.ADMIN)
    public void update(TeamDTO dto) {
        try {
            Team team = teamConvert.fromDTOToEntity(dto);

            teamDao.update(team);
        } catch (Exception ex) {
            throw new RuntimeException("Update operation failed.", ex);
        }
    }

    @Override
    @Acl(Role.ADMIN)
    public void delete(TeamDTO dto) {
        try {
            Team team = teamConvert.fromDTOToEntity(dto);
            // delete matches
            List<Match> matches = matchDao.findByAwayTeam(team);
            if (matches != null) {
                for (Match m : matches) {
                    matchDao.delete(m);
                }
            }
            matches = matchDao.findByHomeTeam(team);
            if (matches != null) {
                for (Match m : matches) {
                    matchDao.delete(m);
                }
            }
            //delete players
            List<Player> players = playerDao.findPlayersByTeam(team);
            if (players != null) {
                for (Player p : players) {
                    playerDao.delete(p);
                }
            }
            teamDao.delete(team);
        } catch (Exception ex) {
            throw new RuntimeException("Delete operation failed.", ex);
        }
    }

    @Override
    public TeamDTO getById(Long id) {
        try {
            Team team = teamDao.getById(id);
            TeamDTO dto = teamConvert.fromEntityToDTO(team);

            return dto;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving team by its id.", ex);
        }
    }

    @Override
    public List<TeamDTO> findAll() {
        try {
            Collection<Team> teams = teamDao.findAll();
            List<TeamDTO> dtos = new ArrayList<TeamDTO>();

            for (Team entity : teams) {
                TeamDTO dto = teamConvert.fromEntityToDTO(entity);
                dtos.add(dto);
            }
            Collections.sort(dtos);

            return dtos;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving teams.", ex);
        }
    }

    @Override
    public TeamDTO findByPlayer(long playerId) {
        try {
            Team entity = teamDao.findTeamByPlayer(playerDao.getById(playerId));
            TeamDTO dto = teamConvert.fromEntityToDTO(entity);

            return dto;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving team.", ex);
        }
    }

    @Override
    public void generate() {
        for (int i = 0; i < 10; i++) {
            TeamDTO team = new TeamDTO();
            switch (i) {
                case 0:
                    team.setName("Arsenal FC");
                    break;
                case 1:
                    team.setName("Chelsea FC");
                    break;
                case 2:
                    team.setName("FC Manchester United");
                    break;
                case 3:
                    team.setName("FC Manchester City");
                    break;
                case 4:
                    team.setName("FC Liverpool");
                    break;
                case 5:
                    team.setName("Tottenham Hotspur FC");
                    break;
                case 6:
                    team.setName("FC Newcastle United");
                    break;
                case 7:
                    team.setName("Everton FC");
                    break;
                case 8:
                    team.setName("Nottingham Forest FC");
                    break;
                case 9:
                    team.setName("FC Shefield Wednesday");
                    break;
            }
            this.create(team);
        }
    }
}
