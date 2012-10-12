package cz.muni.fi.pa165.fast.model;

import java.util.Date;
import javax.persistence.*;

/**
 * Entity - Goal
 * 
 * @author Michal Kimle
 */
@Entity
public class Goal
{
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date goalTime;
    @OneToOne
    private Player scorePlayer;
    @OneToOne
    private Player assistPlayer;
    @ManyToOne
    private Match match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(Date goalTime) {
        this.goalTime = goalTime;
    }

    public Player getScorePlayer() {
        return scorePlayer;
    }

    public void setScorePlayer(Player scorePlayer) {
        this.scorePlayer = scorePlayer;
    }

    public Player getAssistPlayer() {
        return assistPlayer;
    }

    public void setAssistPlayer(Player assistPlayer) {
        this.assistPlayer = assistPlayer;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Goal other = (Goal) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Goal{" + "id=" + id + ", goalTime=" + goalTime + ", scorePlayer=" + scorePlayer + ", assistPlayer=" + assistPlayer + '}';
    }
}
