package cz.muni.fi.pa165.fast.comparator;

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import java.util.Comparator;

/**
 *
 * @author Peter Laurencik
 */
public class SortByPlayerName implements Comparator<PlayerDTO> {

    @Override
    public int compare(PlayerDTO o1, PlayerDTO o2) {
        if (o1.getSurname().compareTo(o2.getSurname()) > 0) {
            return 1;
        } else if (o1.getSurname().compareTo(o2.getSurname()) < 0) {
            return -1;
        } else {
            if (o1.getName().compareTo(o2.getName()) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
