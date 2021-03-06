package cz.muni.fi.pa165.fast.convert.impl;

import cz.muni.fi.pa165.fast.convert.GoalConvert;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author Stefan
 */
@Local(value = GoalConvert.class)
@Stateless
public class GoalConvertImpl implements GoalConvert {

    @EJB
    private PlayerDAO playerDAO;
    @EJB
    private MatchDAO matchDAO;

    @Override
    public GoalDTO fromEntityToDTO(Goal entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Goal entity is null.");
        }
        if (entity.getMatch() == null) {
            throw new RuntimeException("Match cannot be null");
        }
        if (entity.getScorePlayer() == null) {
            throw new RuntimeException("Scoring player cannot be null");
        }
        if (entity.getAssistPlayer() == null) {
            throw new RuntimeException("Assisting player cannot be null");
        }

        GoalDTO goalDTO = new GoalDTO();

        if (entity.getId() == null) {
            goalDTO.setId(0);
        } else {
            goalDTO.setId(entity.getId());
        }
        goalDTO.setScoredPlayerId(entity.getScorePlayer().getId());
        goalDTO.setScoredPlayerName(entity.getScorePlayer().getName() + " " + entity.getScorePlayer().getSurname());
        goalDTO.setAssistPlayerId(entity.getAssistPlayer().getId());
        goalDTO.setAssistPlayerName(entity.getAssistPlayer().getName() + " " + entity.getAssistPlayer().getSurname());
        goalDTO.setMatchId(entity.getMatch().getId());
        goalDTO.setGoalMinute(entity.getGoalMinute());

        Team homeTeam = entity.getMatch().getHomeTeam();
        Team scoringPlayerTeam = entity.getScorePlayer().getTeam();

        goalDTO.setId(entity.getId());

        if (homeTeam.equals(scoringPlayerTeam)) {
            goalDTO.setIsHomeTeam(true);
        } else {
            goalDTO.setIsHomeTeam(false);
        }

        return goalDTO;
    }

    @Override
    public Goal fromDTOToEntity(GoalDTO dto) {
        Goal goal = new Goal();

        if (dto.getId() == 0) {
            goal.setId(null);
        } else {
            goal.setId(dto.getId());
        }

        Player scorer;
        if (dto.getScoredPlayerId() == 0) {
            scorer = null;
        } else {
            scorer = playerDAO.getById(dto.getScoredPlayerId());
        }
        goal.setScorePlayer(scorer);

        Player assistant;
        if (dto.getAssistPlayerId() == 0) {
            assistant = null;
        } else {
            assistant = playerDAO.getById(dto.getAssistPlayerId());
        }
        goal.setAssistPlayer(assistant);

        if (dto.getMatchId() == 0) {
            goal.setMatch(null);
        } else {
            goal.setMatch(matchDAO.getById(dto.getMatchId()));
        }

        goal.setGoalMinute(dto.getGoalMinute());

        return goal;
    }
}
