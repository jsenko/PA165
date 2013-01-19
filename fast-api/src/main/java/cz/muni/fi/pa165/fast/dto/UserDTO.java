package cz.muni.fi.pa165.fast.dto;


/**
 * @author Jakub Senko
 */
public class UserDTO
{
    private long id;
    private String login;
    private String password;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + "]";
    }
}
