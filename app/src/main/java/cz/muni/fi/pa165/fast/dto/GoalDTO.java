package cz.muni.fi.pa165.fast.dto;

import java.util.GregorianCalendar;

public class GoalDTO
{
    private long id;

    private long playerId;

    private String playerName;

    private boolean isHomeTeam;
    
    public long getId() { return id;   }
    public void setId(long id) { this.id = id; }
    
    public long getPlayerId() { return playerId;}
    public void setPlayerId(long playerId) { this.playerId = playerId; }
    
    public String getPlayerName() { return playerName;}
    public void setPlayerName(String playerName) { this.playerName = playerName;}
    
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
