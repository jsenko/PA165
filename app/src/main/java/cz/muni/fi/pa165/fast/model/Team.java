package cz.muni.fi.pa165.fast.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Team entity represents team with players.
 * 
 * @author Peter Laurencik
 * @version 1.0
 */
@Entity
public class Team
{
    @Id
    private Long id;
    
    private String name;

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
