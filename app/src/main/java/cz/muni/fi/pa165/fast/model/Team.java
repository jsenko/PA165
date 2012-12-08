package cz.muni.fi.pa165.fast.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @NamedQuery(name = "Team.findAll",
    query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findHomeTeamByMatch",
    query = "SELECT t FROM Team t WHERE :match member of t.homeMatches"),
    @NamedQuery(name = "Team.findAwayTeamByMatch",
    query = "SELECT t FROM Team t WHERE :match member of t.awayMatches")
})
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", orphanRemoval = true)
    private List<Player> players;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "homeTeam", orphanRemoval = true)
    private List<Match> homeMatches;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "awayTeam", orphanRemoval = true)
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Team other = (Team) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team {id=" + id + ", name=" + name + ", playersSize=" + ((players == null) ? 0 : players.size())
                + ", homeMatchesSize=" + ((homeMatches == null) ? 0 : homeMatches.size()) + ", awayMatchesSize="
                + ((awayMatches == null) ? 0 : awayMatches.size()) + "}";
    }
}
