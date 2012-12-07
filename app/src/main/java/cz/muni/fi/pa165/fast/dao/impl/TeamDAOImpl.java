package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the TeamDAO interface.
 *
 * @author Michal Kimle
 */
@Local(value=TeamDAO.class)
@Stateless(mappedName="TeamDaoImpl")
public class TeamDAOImpl implements TeamDAO {

    @PersistenceContext(name = "TestPU")
    private EntityManager em;

    @Override
    public void create(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team is null");
        }

        em.persist(team);
    }

    @Override
    public void update(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team is null");
        }

        if (em.find(Team.class, team.getId()) == null) {
            throw new IllegalArgumentException("team not in DB");
        }

        em.merge(team);
    }

    @Override
    public void delete(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team is null");
        }

        Team manTeam = em.find(Team.class, team.getId());
        if (manTeam == null) {
            throw new IllegalArgumentException("team not in DB");
        }

        em.remove(manTeam);
    }

    @Override
    public Team getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Team manTeam = em.find(Team.class, id);
        if (manTeam == null) {
            throw new IllegalArgumentException("team not in DB");
        }

        return manTeam;
    }

    @Override
    public Collection<Team> findAll() {
        Collection<Team> teams = em.createNamedQuery("Team.findAll").getResultList();

        return teams;
    }

    @Override
    public Team findTeamByPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player is null");
        }
        
        /*System.out.println(player);

        //Team team = (Team) em.createNamedQuery("Team.findTeamByPlayer").setParameter("player", player.getId()).getSingleResult();
        Query q = em.createNamedQuery("Team.findTeamByPlayer").setParameter("player", player.getId());
        System.out.println(q.unwrap(org.hibernate.Query.class).getQueryString());
        Team team = (Team) q.getSingleResult();
        System.out.println("============" + team);
        return team;*/
        
        ////////dirty hack//////
        Collection<Team> teams = findAll();
        
        for(Team t : teams){
            if(player.getTeam().getId().equals(t.getId())) {
                return t;
            }
        }
        
        throw new IllegalArgumentException("player doesn't have a team");
    }

    @Override
    public Team findHomeTeamByMatch(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("match is null");
        }

        Team team = (Team) em.createNamedQuery("Team.findHomeTeamByMatch").setParameter("match", match).getSingleResult();

        return team;
    }

    @Override
    public Team findAwayTeamByMatch(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("match is null");
        }

        Team team = (Team) em.createNamedQuery("Team.findAwayTeamByMatch").setParameter("match", match).getSingleResult();

        return team;
    }
}
