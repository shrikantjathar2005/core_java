package com.mmcoe.ipl.dao;
import java.util.List;
import com.mmcoe.ipl.exception.IplException;
import com.mmcoe.ipl.model.Player;
import com.mmcoe.ipl.model.Team;

public interface PlayerDao {
    void addTeam(Team team) throws IplException;
    
    boolean deleteTeam(String teamName);

    void addPlayer(String teamName, Player player) throws IplException;

    Player searchPlayer(String playerName);

    boolean deletePlayer(String playerName);

    List<Player> filterByRole(String role);

    List<Player> filterByPriceGreaterThan(double amount);

    List<Player> filterByPriceLessThan(double amount);

    void displayTeamPlayers(String teamName);

    void displayAllTeams();

}