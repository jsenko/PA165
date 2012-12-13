package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.convert.MatchConvert;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.service.MatchService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Jakub Senko
 */
@Stateless
public class MatchServiceImpl implements MatchService {

    @EJB
    MatchDAO matchDAO;
    @EJB
    MatchConvert convert;
    @EJB
    TeamDAO teamDAO;

    @Override
    public void create(MatchDTO dto) {
        try {
            Match m = convert.fromDTOToEntity(dto);

            
        } catch (Exception ex) {
            throw new RuntimeException("Create operation failed.", ex);
        }
    }

    @Override
    public void update(MatchDTO dto) {
        try {
            Match m = convert.fromDTOToEntity(dto);
            matchDAO.update(m);
        } catch (Exception e) {
            throw new RuntimeException("Update opration failed", e);
        }
    }

    @Override
    public void delete(MatchDTO dto) {
        try {
            // to reduce unnecessary overhead we will not use converter
            Match m = new Match();
            m.setId(dto.getId());
            matchDAO.delete(m);
        } catch (Exception e) {
            throw new RuntimeException("Delete operation failed", e);
        }
    }

    @Override
    public List<MatchDTO> findAll() {
        try {
            Collection<Match> matches = matchDAO.findAll();
            List<MatchDTO> dtos = new ArrayList<MatchDTO>();
            for (Match m : matches) {
                dtos.add(convert.fromEntityToDTO(m));
            }
            Collections.sort(dtos);

            return dtos;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving matches.", ex);
        }
    }

    @Override
    public MatchDTO getById(long id) {
        try {
            return convert.fromEntityToDTO(matchDAO.getById(id));
        } catch (Exception e) {
            throw new RuntimeException("getById operation failed", e);
        }
    }
}
