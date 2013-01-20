package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.comparator.SortByPlayerAge;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerGoals;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerHeight;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerName;
import cz.muni.fi.pa165.fast.comparator.SortByPlayerWeight;
import cz.muni.fi.pa165.fast.convert.PlayerConvert;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.security.Acl;
import cz.muni.fi.pa165.fast.security.Role;
import cz.muni.fi.pa165.fast.security.impl.AuthorizationInterceptor;
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
import javax.interceptor.Interceptors;

/**
 *
 * @author Peter Laurencik
 */
@Stateless
@Interceptors({AuthorizationInterceptor.class})
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PlayerServiceImpl implements PlayerService {

    @EJB
    PlayerDAO playerDao;
    @EJB
    TeamDAO teamDao;
    @EJB
    PlayerConvert convert;

    @Override
    @Acl(Role.ADMIN)
    public long create(PlayerDTO dto) {
        Player player = convert.fromDTOToEntity(dto);

        playerDao.create(player);

        return player.getId();
    }

    @Override
    @Acl(Role.ADMIN)
    public long update(PlayerDTO dto) {
        Player player = convert.fromDTOToEntity(dto);

        playerDao.update(player);

        return player.getId();
    }

    @Override
    @Acl(Role.ADMIN)
    public void delete(PlayerDTO dto) {
        Player player = convert.fromDTOToEntity(dto);

        playerDao.delete(player);
    }

    @Override
    public PlayerDTO getById(Long id) {
        try {
            Player player = playerDao.getById(id);
            PlayerDTO dto = convert.fromEntityToDTO(player);

            return dto;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving player by its id.", ex);
        }
    }

    @Override
    public List<PlayerDTO> findPlayersByTeam(Long teamId, PlayerOrderBy orderBy) {
        Collection<Player> players = playerDao.findPlayersByTeam(teamDao.getById(teamId));
        List<PlayerDTO> dtoPlayers = new ArrayList<PlayerDTO>();

        for (Player player : players) {
            PlayerDTO dtoPlayer = convert.fromEntityToDTO(player);

            dtoPlayers.add(dtoPlayer);
        }

        switch (orderBy) {
            case NAME:
                Collections.sort(dtoPlayers, new SortByPlayerName());
                break;
            case GOALS:
                Collections.sort(dtoPlayers, new SortByPlayerGoals());
                break;
            case AGE:
                Collections.sort(dtoPlayers, new SortByPlayerAge());
                break;
            case WEIGHT:
                Collections.sort(dtoPlayers, new SortByPlayerWeight());
                break;
            case HEIGHT:
                Collections.sort(dtoPlayers, new SortByPlayerHeight());
                break;
        }

        return dtoPlayers;
    }
}
