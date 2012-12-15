package cz.muni.fi.pa165.fast.dto;

import java.util.Date;

/**
 * Match Data Transfer Object.
 *
 * Comparable is ordering by match date.
 *
 * @author Jakub Senko
 */
public class MatchDTO implements Comparable<MatchDTO> {

    private long id;
    private int round;
    private long homeTeamId;
    private String homeTeamName; //derived
    private long awayTeamId;
    private String awayTeamName; //derived
    private Integer homeTeamGoals; //derived
    private Integer awayTeamGoals; // derived
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date time) {
        this.date = time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        MatchDTO other = (MatchDTO) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MatchDTO {id=" + id + ", round=" + round + ", homeTeamId="
                + homeTeamId + ", homeTeamName=" + homeTeamName
                + ", awayTeamId=" + awayTeamId + ", awayTeamName="
                + awayTeamName + ", homeTeamGoals=" + homeTeamGoals
                + ", awayTeamGoals=" + awayTeamGoals + ", time=" + date + "}";
    }

    @Override
    public int compareTo(MatchDTO o) {
        return date.compareTo(o.date);
    }
}
