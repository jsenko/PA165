package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Match;

/**
 *
 * @author Jakub Senko
 */
public interface MatchConvert extends Convert<Match, MatchDTO> {

    @Override
    public MatchDTO fromEntityToDTO(Match entity);

    @Override
    public Match fromDTOToEntity(MatchDTO dto);
}
