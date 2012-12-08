package cz.muni.fi.pa165.fast.service;

import cz.muni.fi.pa165.fast.dto.GoalDTO;
import java.util.List;

public interface GoalService {

    public long create(GoalDTO dto);

    public long update(GoalDTO dto);

    public void delete(GoalDTO dto);

    /**
     *
     * @return list of Goal data transfer objects by id of the match
     */
    public List<GoalDTO> findByMatch(long matchId);

    public GoalDTO getById(long id);
}
