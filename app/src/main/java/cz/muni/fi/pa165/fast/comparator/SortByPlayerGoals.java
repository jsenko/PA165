package cz.muni.fi.pa165.fast.comparator;

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import java.util.Comparator;

/**
 *
 * @author Peter Laurencik
 */
public class SortByPlayerGoals implements Comparator<PlayerDTO> {

    private SortByPlayerName byName;

    @Override
    public int compare(PlayerDTO o1, PlayerDTO o2) {

        if (o1.getGoals() > o2.getGoals()) {
            return -1;
        } else if (o1.getGoals() < o2.getGoals()) {
            return 1;
        } else {
            if (o1.getAssists() > o2.getAssists()) {
                return -1;
            } else if (o1.getAssists() < o2.getAssists()) {
                return 1;
            } else {
                byName = new SortByPlayerName();
                return byName.compare(o1, o2);
            }
        }
    }
}
