/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.service.impl;


import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.MatchGeneratorFacade;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Stefan
 */
@Stateless
public class MatchGeneratorFacadeImpl implements MatchGeneratorFacade {

    @EJB
    private TeamService teamService;
    
    @Override
    public void setTeamService(TeamService teamService){
        this.teamService = teamService;
    }
    
    @Override
    public void generateMatches() {
        
    }
    @Override
    public void drop() {
    }

    @Override
    public void generatePlayers() {
    }

    @Override
    public void generateTeams() {
        System.out.println("call from facade");
        teamService.generate();
    }

    @Override
    public void generateGoals() {
    }

    @Override
    public void writeOutTeams() {
        
        System.out.println("this are all teams");
        
        List<TeamDTO> list = teamService.findAll();
        for(TeamDTO t:list){
            System.out.println(t.getName());
        }
    }
    
}
