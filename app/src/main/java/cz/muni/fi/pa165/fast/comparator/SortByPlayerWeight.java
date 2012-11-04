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
public class SortByPlayerWeight implements Comparator<PlayerDTO> {

    @Override
    public int compare(PlayerDTO o1, PlayerDTO o2) {
        return o1.getWeight()-o2.getWeight();
    }
    
}
