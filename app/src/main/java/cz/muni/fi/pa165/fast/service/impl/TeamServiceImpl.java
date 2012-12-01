package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.convert.PlayerConvert;
import cz.muni.fi.pa165.fast.convert.TeamConvert;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
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
public class TeamServiceImpl implements TeamService {

    @EJB
    private TeamDAO teamDao;
    @EJB
    private PlayerDAO playerDao;
    @EJB
    private TeamConvert teamConvert;

    @Override
    public void create(TeamDTO dto) {
        try {
            Team team = teamConvert.fromDTOToEntity(dto);

            System.out.println("call from service");
            if (team == null) {
                System.out.println("team is null");
            } else {
                System.out.println("team is not null");
            }

            teamDao.create(team);
        } catch (Exception ex) {
            throw new RuntimeException("Create operation failed.", ex);
        }
    }

    @Override
    public void update(TeamDTO dto) {
        try {
            Team team = teamConvert.fromDTOToEntity(dto);

            teamDao.update(team);
        } catch (Exception ex) {
            throw new RuntimeException("Update operation failed.", ex);
        }
    }

    @Override
    public void delete(TeamDTO dto) {
        try {
            Team team = teamConvert.fromDTOToEntity(dto);

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
        System.out.println("call form team service generate");
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
