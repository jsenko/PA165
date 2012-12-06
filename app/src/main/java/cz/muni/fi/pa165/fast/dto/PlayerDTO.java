package cz.muni.fi.pa165.fast.dto;

public class PlayerDTO
{
	private long id;
	
	private String name;
	
        private String surname;
        
	//derived
	private int goals;
	// derived
	private int assists;
	
	private int age;
	
	private int weight;
	
	private int height;
        
        private long teamId;
        
        private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public String toString(){
        return name + " " + surname;
    }
    
    public String toDebugString() {
        return "PlayerDTO{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", goals=" + goals + ", assists=" + assists + ", age=" + age + ", weight=" + weight + ", height=" + height + ", teamId=" + teamId + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerDTO other = (PlayerDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
        
        
}
