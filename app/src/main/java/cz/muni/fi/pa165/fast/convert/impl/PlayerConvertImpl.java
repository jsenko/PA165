package cz.muni.fi.pa165.fast.convert.impl;

import cz.muni.fi.pa165.fast.convert.PlayerConvert;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author Peter Laurencik
 */
@Local(value = PlayerConvert.class)
@Stateless
public class PlayerConvertImpl implements PlayerConvert {

    @EJB
    PlayerDAO playerDao;
    @EJB
    GoalDAO goalDao;
    @EJB
    TeamDAO teamDao;

    @Override
    public PlayerDTO fromEntityToDTO(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player entity is null.");
        }

        PlayerDTO dtoPlayer = new PlayerDTO();

        if (player.getId() == null) {
            dtoPlayer.setId(0);
        } else {
            dtoPlayer.setId(player.getId());
        }
        dtoPlayer.setName(player.getName());
        dtoPlayer.setSurname(player.getSurname());
        dtoPlayer.setAge(player.getAge());
        dtoPlayer.setHeight(player.getHeight());
        dtoPlayer.setWeight(player.getWeight());
        dtoPlayer.setGoals(goalDao.findByScorePlayer(player).size());
        dtoPlayer.setAssists(goalDao.findByAssistPlayer(player).size());
        dtoPlayer.setTeamId(player.getTeam().getId().longValue());
        dtoPlayer.setTeamName(teamDao.getById(dtoPlayer.getTeamId()).getName());

        return dtoPlayer;
    }

    @Override
    public Player fromDTOToEntity(PlayerDTO dto) {
        if (dto == null) {
            return null;
        }
        Player player = new Player();

        if (dto.getId() == 0) {
            player.setId(null);
        } else {
            player.setId(dto.getId());
        }
        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setAge(dto.getAge());
        player.setHeight(dto.getHeight());
        player.setWeight(dto.getWeight());
        if (dto.getTeamId() != 0) {
            player.setTeam(teamDao.getById(dto.getTeamId()));
        }

        return player;
    }
}
