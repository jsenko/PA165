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
            query="SELECT t FROM Team t WHERE t.players = :player"),
@NamedQuery(name="Team.findHomeTeamByMatch",
            query="SELECT t FROM Team t JOIN :match m WHERE m.homeTeam = t"),
@NamedQuery(name="Team.findAwayTeamByMatch",
            query="SELECT t FROM Team t JOIN :match m WHERE m.awayTeam = t")/*,
@NamedQuery(name="Team.findScoringTeamByGoal",
            query="SELECT t FROM Team t WHERE t.name = :name"),
@NamedQuery(name="Team.findTeamIncassingByGoal",
            query="SELECT t FROM Team t WHERE t.name = :name")*/
})
public class Team
{
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Player> players;

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
    
    
}
