/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.convert.GoalConvert;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.service.GoalService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Stefan
 */
@Stateless
public class GoalServiceImpl implements GoalService{

    @EJB
    private GoalConvert goalConvert;
    
    @EJB
    private GoalDAO goalDAO;
    
    @Override
    public long create(GoalDTO dto) {
        Goal g = goalConvert.fromDTOToEntity(dto);
        goalDAO.create(g);
        return g.getId();        
    }

    @Override
    public long update(GoalDTO dto) {
        Goal g = goalConvert.fromDTOToEntity(dto);
        goalDAO.update(g);
        return g.getId();
    }

    @Override
    public void delete(GoalDTO dto) {
        Goal g = goalConvert.fromDTOToEntity(dto);
        goalDAO.delete(g);
    }

    @Override
    public List<GoalDTO> findByMatch(long matchId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
