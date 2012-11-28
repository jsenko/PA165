package cz.muni.fi.pa165.fast.dto;

import java.util.Date;

public class GoalDTO {

    private long id;
    private long scoredPlayerId;
    private String scoredPlayerName;
    private long assistPlayerId;
    private String assistPlayerName;
    private boolean isHomeTeam;
    private Date goalTime;
    private long matchId;

    public Date getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(Date goalTime) {
        this.goalTime = goalTime;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScoredPlayerId() {
        return scoredPlayerId;
    }

    public void setScoredPlayerId(long scoredPlayerId) {
        this.scoredPlayerId = scoredPlayerId;
    }

    public long getAssistPlayerId() {
        return assistPlayerId;
    }

    public void setAssistPlayerId(long assistPlayerId) {
        this.assistPlayerId = assistPlayerId;
    }
    
    public String getScoredPlayerName() {
        return scoredPlayerName;
    }

    public void setScoredPlayerName(String scoredPlayerName) {
        this.scoredPlayerName = scoredPlayerName;
    }

    public String getAssistPlayerName() {
        return assistPlayerName;
    }

    public void setAssistPlayerName(String assistPlayerName) {
        this.assistPlayerName = assistPlayerName;
    }

    public boolean isIsHomeTeam() {
        return isHomeTeam;
    }

    public void setIsHomeTeam(boolean isHomeTeam) {
        this.isHomeTeam = isHomeTeam;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GoalDTO other = (GoalDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GoalDTO{" + "id=" + id + ", scoredPlayerId=" + scoredPlayerId + ", assistPlayerId=" + assistPlayerId + ", isHomeTeam=" + isHomeTeam + ", goalTime=" + goalTime + ", matchId=" + matchId + '}';
    }
}
