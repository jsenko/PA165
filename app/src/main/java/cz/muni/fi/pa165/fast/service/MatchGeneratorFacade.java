package cz.muni.fi.pa165.fast.service;


public interface MatchGeneratorFacade
{
        public void setTeamService(TeamService teamService);
        
	public void generateMatches();
	
	public void drop();
        
        public void generatePlayers();
        
        public void generateTeams();
        
        public void generateGoals();
        
        public void writeOutTeams();
}
