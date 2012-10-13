package cz.muni.fi.pa165.fast.model;

import javax.persistence.*;


/**
 * Player JPA entity
 * 
 * @author Jakub Senko
 * @version 1.0
 */
@Entity
public class Player
{
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    private String surname;
    
    private int age;
    
    private int height;
    
    private int weight;
    
    @ManyToOne
    private Team team;  
    
    
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
    @Override
    public int hashCode()
    {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Player {id: " + (id != null ? id.toString() : "null") + ", "
                + "name: " + (name != null ? name.toString() : "null") + ", "
                + "surname: " + (surname != null ? surname.toString() : "null") + ", "
                + "age: " + age + ", "
                + "height: " + height + ", "
                + "weight: " + weight
        		+ "}";
    }
}
