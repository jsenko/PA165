package cz.muni.fi.pa165.fast.service;

import cz.muni.fi.pa165.fast.dto.PlayerGraphDTO;

public interface PlayerGraphFacade {

    public PlayerGraphDTO generate(long playerId);
}
