package cz.muni.fi.pa165.fast.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity - User
 * 
 * @author Jakub Senko
 */
@Entity
@Table(name="usercredentials")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
 
    @Column(unique = true)
    private String login;
    private String password;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + "]";
    }
}
