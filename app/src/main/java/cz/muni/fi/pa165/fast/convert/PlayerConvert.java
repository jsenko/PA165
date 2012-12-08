package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;

/**
 *
 * @author Peter Laurencik
 */
public interface PlayerConvert extends Convert<Player, PlayerDTO> {

    @Override
    public PlayerDTO fromEntityToDTO(Player player);

    @Override
    public Player fromDTOToEntity(PlayerDTO dto);
}
