package com.mmcoe.ipl.service;
import java.util.List;
import com.mmcoe.ipl.dao.PlayerDao;
import com.mmcoe.ipl.dao.PlayerDaoCollectionImpl;
import com.mmcoe.ipl.dao.PlayerDaoJdbcImpl;
import com.mmcoe.ipl.exception.IplException;
import com.mmcoe.ipl.model.Player;
import com.mmcoe.ipl.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IplService {
    private PlayerDao dao;

    public IplService() {
//        dao = new PlayerDaoCollectionImpl();
        dao = new PlayerDaoJdbcImpl();
    }
    
    private static final Logger logger =
            LogManager.getLogger(IplService.class);

    
    public void addTeam(Team team) throws IplException {
        dao.addTeam(team);
    }

    
    public void addPlayer(String teamName, Player player) throws IplException {
        dao.addPlayer(teamName, player);
    }

    
    public void searchPlayer(String playerName) {
        Player player = dao.searchPlayer(playerName);
        if(player != null) {
            System.out.println(player);
        }
        else {
            System.out.println("Player Not Found");
        }
    }

    
    public void deletePlayer(String playerName) {
        if(dao.deletePlayer(playerName)) {
            System.out.println("Player Deleted Successfully");
        }
        else {
            System.out.println("Player Not Found");
        }
    }

    
    public void filterByRole(String role) {
        List<Player> players = dao.filterByRole(role);
        if(players.isEmpty()) {
            System.out.println("No Players Found");
            return;
        }
        players.forEach(System.out::println);
    }

    
    public void filterByPriceGreaterThan(double amount) {
        List<Player> players = dao.filterByPriceGreaterThan(amount);
        if(players.isEmpty()) {
            System.out.println("No Players Found");
            return;
        }
        players.forEach(System.out::println);
    }

    
    public void filterByPriceLessThan(double amount) {
        List<Player> players = dao.filterByPriceLessThan(amount);
        if(players.isEmpty()) {
            System.out.println("No Players Found");
            return;
        }
        players.forEach(System.out::println);
    }

    
    public void displayTeamPlayers(String teamName) {
        dao.displayTeamPlayers(teamName);
    }

    
    public void displayAllTeams() {
        dao.displayAllTeams();
    }

}