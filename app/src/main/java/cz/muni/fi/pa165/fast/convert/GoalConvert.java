package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.model.Goal;

/**
 *
 * @author Stefan
 */
public interface GoalConvert extends Convert<Goal, GoalDTO> {
    
    @Override
    public GoalDTO fromEntityToDTO(Goal entity);

    @Override
    public Goal fromDTOToEntity(GoalDTO dto);
    
}
