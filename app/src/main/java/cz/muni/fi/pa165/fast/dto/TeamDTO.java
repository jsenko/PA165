package cz.muni.fi.pa165.fast.dto;

public class TeamDTO
{
	private long id;
	
	private String name;
	// all below are derived data
	private int points;
	
	private int goalsFor;
	
	private int goalsAgainst;
	
	private int won;
	
	private int lost;
	
	private int drawn;
	
	private MatchResult[] trend;
}
