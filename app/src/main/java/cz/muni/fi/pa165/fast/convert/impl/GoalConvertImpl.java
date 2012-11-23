package cz.muni.fi.pa165.fast.convert.impl;

import cz.muni.fi.pa165.fast.convert.GoalConvert;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import javax.ejb.EJB;

/**
 *
 * @author Stefan
 */
public class GoalConvertImpl implements GoalConvert {

    @EJB
    private PlayerDAO playerDAO;

    @Override
    public GoalDTO fromEntityToDTO(Goal entity) {
        GoalDTO goalDTO = new GoalDTO();

        Team homeTeam = entity.getMatch().getHomeTeam();
        Team scoringPlayerTeam = entity.getScorePlayer().getTeam();

        goalDTO.setId(entity.getId());

        if (homeTeam.equals(scoringPlayerTeam)) {
            goalDTO.setIsHomeTeam(true);
        } else {
            goalDTO.setIsHomeTeam(false);
        }

        goalDTO.setScoredPlayerId(entity.getScorePlayer().getId());
        goalDTO.setScoredPlayerName(entity.getScorePlayer().getName());

        goalDTO.setAssistPlayerId(entity.getAssistPlayer().getId());
        goalDTO.setAssistPlayerName(entity.getAssistPlayer().getName());

        return goalDTO;
    }

    @Override
    public Goal fromDTOToEntity(GoalDTO dto) {
        Goal goal = new Goal();
        goal.setId(dto.getId());

        Player scorer;
        if (dto.getScoredPlayerId() == 0) {
            scorer = null;
        } else {
            scorer = playerDAO.getById(dto.getScoredPlayerId());
        }
        goal.setScorePlayer(scorer);

        Player assistant;
        if (dto.getScoredPlayerId() == 0) {
            assistant = null;
        } else {
            assistant = playerDAO.getById(dto.getScoredPlayerId());
        }
        goal.setAssistPlayer(assistant);

        return goal;
    }
}
