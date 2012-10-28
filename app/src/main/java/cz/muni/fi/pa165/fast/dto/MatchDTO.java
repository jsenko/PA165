package cz.muni.fi.pa165.fast.dto;

import java.util.GregorianCalendar;

public class MatchDTO
{
	private long id;
	
	private int round;
	
	private long homeTeamId;
	
	// derived, do not use in create
	private String homeTeamName;
	
	private long awayTeamId;
	
	//detto
	private String awayTeamName;
	
	private Integer homeTeamGoals;
	
	private Integer awayTeamGoals;
	
	private GregorianCalendar time;
}
