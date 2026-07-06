package com.mmcoe.ipl.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mmcoe.ipl.exception.IplException;
import com.mmcoe.ipl.model.Player;
import com.mmcoe.ipl.model.Team;

public class PlayerDaoCollectionImpl implements PlayerDao {
    private static final int MAX_TEAMS = 5;
    private static final int MAX_PLAYERS = 5;
    private Map<Team, List<Player>> teams;

    public PlayerDaoCollectionImpl() {
        teams = new HashMap<>();
    }

    
    @Override
    public void addTeam(Team team) throws IplException {
        if (teams.size() == MAX_TEAMS) {
            throw new IplException("Maximum Team Limit Reached");
        }
        teams.put(team, new ArrayList<>());
    }

    
    @Override
    public void addPlayer(String teamName, Player player) throws IplException {
        Team team = findTeam(teamName);
        if (team == null) {
            throw new IplException("Team Not Found");
        }
        List<Player> players = teams.get(team);
        if (players.size() == MAX_PLAYERS) {
            throw new IplException(teamName + " Team is Full");
        }
        players.add(player);
    }

    
    private Team findTeam(String teamName) {
        for (Team team : teams.keySet()) {
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        return null;
    }

    
    @Override
    public Player searchPlayer(String playerName) {
        return teams.values().stream().flatMap(List::stream).filter(p -> p.getPlayerName().equalsIgnoreCase(playerName))
                .findFirst().orElse(null);
    }

    
    @Override
    public boolean deletePlayer(String playerName) {
        Player player = searchPlayer(playerName);
        if (player == null) {
            return false;
        }
        teams.values().forEach(list -> list.remove(player));
        return true;
    }

    
    @Override
    public List<Player> filterByRole(String role) {
        return teams.values().stream().flatMap(List::stream).filter(p -> p.getRole().equalsIgnoreCase(role))
                .toList();
    }

    
    @Override
    public List<Player> filterByPriceGreaterThan(double amount) {
        return teams.values().stream().flatMap(List::stream)
                .filter(p -> p.getBidAmount() > amount).toList();
    }

    
    @Override
    public List<Player> filterByPriceLessThan(double amount) {
        return teams.values().stream().flatMap(List::stream)
                .filter(p -> p.getBidAmount() < amount).toList();
    }

    
    @Override
    public void displayTeamPlayers(String teamName) {
        Team team = findTeam(teamName);
        if (team == null) {
            System.out.println("Team Not Found");
            return;
        }
        System.out.println("\nPlayers of " + team.getTeamName());
        System.out.println("-----------------------------------------");
        teams.get(team).forEach(System.out::println);
    }

    
    @Override
    public void displayAllTeams() {
        teams.forEach((team, players) -> {
            System.out.println("\n=================================");
            System.out.println(team.getTeamName());
            System.out.println("=================================");
            players.forEach(System.out::println);
        });
    }


	@Override
	public boolean deleteTeam(String teamName) {
		Team team = findTeam(teamName);
	    if(team == null) {
	        return false;
	    }
	    teams.remove(team);
	    return true;
	}
}