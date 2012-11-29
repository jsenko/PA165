package cz.muni.fi.pa165.fast.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity - Goal
 * 
 * @author Michal Kimle
 */
@Entity
public class Goal implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private int goalMinute;
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

    public int getGoalMinute() {
        return goalMinute;
    }

    public void setGoalMinute(int goalMinute) {
        this.goalMinute = goalMinute;
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
        return "Goal{" + "id=" + id + ", goalMinute=" + goalMinute + ", scorePlayer=" + scorePlayer + ", assistPlayer=" + assistPlayer + "match=" + match + '}';
    }
}
