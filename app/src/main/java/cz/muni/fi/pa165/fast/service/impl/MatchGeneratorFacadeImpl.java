package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.security.Acl;
import cz.muni.fi.pa165.fast.security.Role;
import cz.muni.fi.pa165.fast.security.impl.AuthorizationInterceptor;
import cz.muni.fi.pa165.fast.service.MatchGeneratorFacade;
import cz.muni.fi.pa165.fast.service.MatchService;
import cz.muni.fi.pa165.fast.service.PlayerService;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author Stefan
 */
@Stateless
public class MatchGeneratorFacadeImpl implements MatchGeneratorFacade {

    @EJB
    private TeamService teamService;
    @EJB
    private PlayerService playerService;
    @EJB
    private MatchService matchService;
    private List<String> names = new LinkedList<String>();
    private List<String> surnames = new LinkedList<String>();
    private Random rand = new Random();

    @Override
    public void generateMatches() {
        List<TeamDTO> teams = teamService.findAll();
        int matchesPerRound = teams.size() / 2;
        int roundsCount = teams.size() - 1;
        long millis = System.currentTimeMillis();
        TeamDTO fixedTeam = teams.get(0);
        teams.remove(0);

        for (int r = 1; r <= roundsCount; r++) {
            for (int i = 0; i < matchesPerRound; i++) {
                if (i == 0) {
                    //vygenerovat zapas s fixed team
                    MatchDTO match = new MatchDTO();
                    match.setHomeTeamId(fixedTeam.getId());
                    match.setAwayTeamId(teams.get(teams.size() - 1).getId());
                    match.setDate(new Date(millis));
                    match.setRound(r);
                    matchService.create(match);
                } else {
                    //vygenerovat ostatne zapasy
                    MatchDTO match = new MatchDTO();
                    match.setHomeTeamId(teams.get(i - 1).getId());
                    match.setAwayTeamId(teams.get(teams.size() - (i + 1)).getId());
                    match.setDate(new Date(millis));
                    match.setRound(r);
                    matchService.create(match);
                }
            }
            millis += 604800000;
            TeamDTO removed = teams.remove(0);
            teams.add(removed);
        }
    }

    @Override
    public void drop() {
        List<MatchDTO> allMatches = matchService.findAll();
        for (MatchDTO m : allMatches) {
            matchService.delete(m);
        }
    }

    @Override
    public void generatePlayers() {

        initializeNames();

        List<TeamDTO> list = teamService.findAll();

        for (TeamDTO team : list) {

            for (int i = 0; i < 11; i++) {
                PlayerDTO player = new PlayerDTO();
                player.setAge(20 + rand.nextInt(12));
                player.setName(getRandomName());
                player.setSurname(getRandomSurname());
                player.setWeight(60 + rand.nextInt(25));
                player.setHeight(170 + rand.nextInt(20));
                player.setTeamId(team.getId());
                playerService.create(player);
            }
        }
    }

    @Override
    public void generateTeams() {
        teamService.generate();
    }

    private void initializeNames() {
        names.add("Paul");
        names.add("James");
        names.add("Tony");
        names.add("Steven");
        names.add("Michael");
        names.add("David");
        names.add("John");
        names.add("Frank");
        names.add("Jack");

        surnames.add("Sturridge");
        surnames.add("Wellbeck");
        surnames.add("Cahill");
        surnames.add("Hart");
        surnames.add("Wilshere");
        surnames.add("Jenkinson");
        surnames.add("Terry");
        surnames.add("Ramsey");
        surnames.add("Fletcher");
        surnames.add("Emerson");
        surnames.add("Evans");
        surnames.add("Rooney");
        surnames.add("Lampard");
        surnames.add("Bale");
        surnames.add("Howard");
        surnames.add("Smith");
        surnames.add("Long");
        surnames.add("Lambert");
        surnames.add("Crouch");
        surnames.add("Neville");
        surnames.add("Giggs");
        surnames.add("Owen");
        surnames.add("Berryman");
        surnames.add("Geller");
    }

    private String getRandomName() {;
        return names.get(rand.nextInt(names.size()));
    }

    private String getRandomSurname() {;
        return surnames.get(rand.nextInt(surnames.size()));
    }
}
