package cz.muni.fi.pa165.fast.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Entity Match
 * 
 * @author Stefan Uhercik
 */
@Entity
@Table(name="FootballMatch")
public class Match implements Comparable<Match>, Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date matchDate;
    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private Collection<Goal> goals;
    
    private int round;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }


    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
    
    public Collection<Goal> getGoals() {
        return goals;
    }

    public void setGoals(Collection<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return "Match{" 
        		+ "id=" + id
        		+ ", matchDate=" + matchDate 
        		+ ", homeTeam=" + homeTeam 
        		+ ", awayTeam=" + awayTeam 
        		+ ", round=" + round
        		+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Match other = (Match) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Match t) {
        return this.getMatchDate().compareTo(t.getMatchDate());
    }

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
