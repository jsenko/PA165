package cz.muni.fi.pa165.fast.dto;


public class GoalDTO
{
    private long id;

    private long scoredPlayerId;

    private String scoredPlayerName;
    
    private long assistPlayerId;

    private String assistPlayerName;

    private boolean isHomeTeam;
    
    public long getId() { return id;   }
    public void setId(long id) { this.id = id; }

    public long getScoredPlayerId() {
        return scoredPlayerId;
    }

    public void setScoredPlayerId(long scoredPlayerId) {
        this.scoredPlayerId = scoredPlayerId;
    }

    public String getScoredPlayerName() {
        return scoredPlayerName;
    }

    public void setScoredPlayerName(String scoredPlayerName) {
        this.scoredPlayerName = scoredPlayerName;
    }

    public long getAssistPlayerId() {
        return assistPlayerId;
    }

    public void setAssistPlayerId(long assistPlayerId) {
        this.assistPlayerId = assistPlayerId;
    }

    public String getAssistPlayerName() {
        return assistPlayerName;
    }

    public void setAssistPlayerName(String assitPlayerName) {
        this.assistPlayerName = assitPlayerName;
    }
    
    
    
    public boolean isIsHomeTeam() { return isHomeTeam; }
    public void setIsHomeTeam(boolean isHomeTeam) { this.isHomeTeam = isHomeTeam; }

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

    
    
}
