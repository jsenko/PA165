package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.convert.GoalConvert;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.security.Acl;
import cz.muni.fi.pa165.fast.security.Role;
import cz.muni.fi.pa165.fast.security.impl.AuthorizationInterceptor;
import cz.muni.fi.pa165.fast.service.GoalService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author Stefan
 */
@Stateless
@Interceptors({AuthorizationInterceptor.class})
public class GoalServiceImpl implements GoalService {

    @EJB
    private GoalConvert goalConvert;
    @EJB
    private GoalDAO goalDAO;
    @EJB
    private MatchDAO matchDAO;

    @Override
    @Acl(Role.ADMIN)
    public long create(GoalDTO dto) {
        try {
            Goal g = goalConvert.fromDTOToEntity(dto);
            goalDAO.create(g);
            return g.getId();
        } catch (Exception ex) {
            throw new RuntimeException("Create operation failed.", ex);
        }
    }

    @Override
    @Acl(Role.ADMIN)
    public long update(GoalDTO dto) {
        try {
            Goal g = goalConvert.fromDTOToEntity(dto);
            goalDAO.update(g);
            return g.getId();
        } catch (Exception ex) {
            throw new RuntimeException("Update operation failed.", ex);
        }
    }

    @Override
    @Acl(Role.ADMIN)
    public void delete(GoalDTO dto) {
        try {
            Goal g = goalConvert.fromDTOToEntity(dto);
            goalDAO.delete(g);
        } catch (Exception ex) {
            throw new RuntimeException("Delete operation failed.", ex);
        }
    }

    @Override
    public List<GoalDTO> findByMatch(long matchId) {
        try {
            Match m = matchDAO.getById(matchId);

            Collection<Goal> matchGoals = goalDAO.findByMatch(m);
            List<GoalDTO> matchGoalsDto = new ArrayList();

            for (Goal g : matchGoals) {
                GoalDTO dto = goalConvert.fromEntityToDTO(g);
                matchGoalsDto.add(dto);
            }

            return matchGoalsDto;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving goals.", ex);
        }
    }

    @Override
    public GoalDTO getById(long id) {
        try {
            return goalConvert.fromEntityToDTO(goalDAO.getById(id));
        } catch (Exception e) {
            throw new RuntimeException("getById operation failed", e);
        }
    }
}
