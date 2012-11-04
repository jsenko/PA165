package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.convert.TeamConvert;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
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
        Team team = teamConvert.fromDTOToEntity(dto);

        teamDao.create(team);
    }

    @Override
    public void update(TeamDTO dto) {
        Team team = teamConvert.fromDTOToEntity(dto);

        teamDao.update(team);
    }

    @Override
    public void delete(TeamDTO dto) {
        Team team = teamConvert.fromDTOToEntity(dto);

        teamDao.delete(team);
    }

    @Override
    public List<TeamDTO> findAll() {
        Collection<Team> teams = teamDao.findAll();
        List<TeamDTO> dtos = new ArrayList<TeamDTO>();

        for (Team entity : teams) {
            TeamDTO dto = teamConvert.fromEntityToDTO(entity);
            dtos.add(dto);
        }
        Collections.sort(dtos);

        return dtos;
    }

    @Override
    public TeamDTO findByPlayer(long playerId) {
        Team entity = teamDao.findTeamByPlayer(playerDao.getById(playerId));
        TeamDTO dto = teamConvert.fromEntityToDTO(entity);

        return dto;
    }
}
