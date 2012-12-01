/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.comparator;

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import java.util.Comparator;

/**
 *
 * @author Peter Laurencik
 */
public class SortByTeam implements Comparator<PlayerDTO>{

    @Override
    public int compare(PlayerDTO o1, PlayerDTO o2) {
        int o1Team = (int)o1.getTeamId();
        int o2Team = (int)o2.getTeamId();
        return o1Team-o2Team;
    }
    
}
