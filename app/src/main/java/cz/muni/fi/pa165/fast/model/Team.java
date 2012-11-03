package cz.muni.fi.pa165.fast.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Team entity represents team with players.
 * 
 * @author Peter Laurencik
 * @version 1.0
 */
@Entity
@NamedQueries({
@NamedQuery(name="Team.findAll",
            query="SELECT t FROM Team t"),
@NamedQuery(name="Team.findTeamByPlayer",
            query="SELECT t FROM Team t WHERE :player member of t.players"),
@NamedQuery(name="Team.findHomeTeamByMatch",
            query="SELECT t FROM Team t WHERE :match member of t.homeMatches"),
@NamedQuery(name="Team.findAwayTeamByMatch",
            query="SELECT t FROM Team t WHERE :match member of t.awayMatches")
})

public class Team
{

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    
    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;
   
    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public List<Match> getHomeMatches() {
		return homeMatches;
	}

    public void setHomeMatches(List<Match> homeMatches) {
	this.homeMatches = homeMatches;
    }

    public List<Match> getAwayMatches() {
	return awayMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
	this.awayMatches = awayMatches;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Team other = (Team) obj;
        if (this.id != other.id && (this.id == null)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
	return "Team {id=" + id + ", name=" + name + ", players=" + players.size()
				+ ", homeMatches=" + homeMatches.size() + ", awayMatches="
				+ awayMatches.size() + "}";
    }
}
