package com.mmcoe.ipl.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mmcoe.ipl.exception.IplException;
import com.mmcoe.ipl.model.Player;
import com.mmcoe.ipl.model.Team;
import com.mmcoe.ipl.util.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerDaoJdbcImpl implements PlayerDao {
    private Connection con;
    public PlayerDaoJdbcImpl() {
        con = DbUtil.getConnection();
    }
    
    private static final Logger logger = LogManager.getLogger(PlayerDaoJdbcImpl.class);
    
    @Override
    public void addTeam(Team team) throws IplException {
        String sql = "INSERT INTO team VALUES(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, team.getTeamId());
            ps.setString(2, team.getTeamName());
            ps.executeUpdate();
            logger.info("Team added : {}", team.getTeamName());
        } catch (SQLException e) {
            throw new IplException(e.getMessage());
        }
    }
    
    
    @Override
    public void addPlayer(String teamName, Player player) throws IplException {
        String searchTeam = "SELECT team_id FROM team WHERE team_name = ?";
        String insertPlayer = "INSERT INTO player(jersey_number, player_name, bid_amount, role, team_id) VALUES(?,?,?,?,?)";;
        try {
            PreparedStatement ps1 = con.prepareStatement(searchTeam);
            ps1.setString(1, teamName);
            ResultSet rs = ps1.executeQuery();
            if(!rs.next()) {
                throw new IplException("Team Not Found");
            }
            int teamId = rs.getInt("team_id");
            PreparedStatement ps2 = con.prepareStatement(insertPlayer);

            ps2.setInt(1, player.getJerseyNumber());
            ps2.setString(2, player.getPlayerName());
            ps2.setDouble(3, player.getBidAmount());
            ps2.setString(4, player.getRole());
            ps2.setInt(5, teamId);
            ps2.executeUpdate();
            logger.info("Player added : {}", player.getPlayerName());
        } catch (SQLException e) {
            throw new IplException(e.getMessage());
        }
    }
    
    
    @Override
    public Player searchPlayer(String playerName) {
        String sql = " SELECT * FROM player WHERE player_name = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Player(
                        rs.getInt("jersey_number"),
                        rs.getString("player_name"),
                        rs.getDouble("bid_amount"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
        return null;
    }
    

    @Override
    public boolean deletePlayer(String playerName) {
        String sql = "DELETE FROM player WHERE player_name = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, playerName);
            
            boolean deleted = ps.executeUpdate() > 0;
            if(deleted)
                logger.info("Player deleted : {}", playerName);
            else
                logger.warn("Player not found : {}", playerName);
            return deleted;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
        return false;
    }

    
    @Override
    public List<Player> filterByRole(String role) {
        List<Player> players = new ArrayList<>();
        String sql = " SELECT * FROM player WHERE role = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                players.add(new Player(
                        rs.getInt("jersey_number"),
                        rs.getString("player_name"),
                        rs.getDouble("bid_amount"),
                        rs.getString("role")));
            }
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
        return players;
    }
    

    @Override
    public List<Player> filterByPriceGreaterThan(double amount) {
        List<Player> players = new ArrayList<>();
        String sql = " SELECT * FROM player WHERE bid_amount > ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, amount);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                players.add(new Player(
                        rs.getInt("jersey_number"),
                        rs.getString("player_name"),
                        rs.getDouble("bid_amount"),
                        rs.getString("role")));
            }
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
        return players;
    }
    

    @Override
    public List<Player> filterByPriceLessThan(double amount) {
        List<Player> players = new ArrayList<>();
        String sql = " SELECT * FROM player WHERE bid_amount < ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, amount);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                players.add(new Player(
                        rs.getInt("jersey_number"),
                        rs.getString("player_name"),
                        rs.getDouble("bid_amount"),
                        rs.getString("role")));
            }
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
        return players;
    }

    
    @Override
    public void displayTeamPlayers(String teamName) {
        String sql = """
                SELECT p.* FROM player p
                JOIN team t
                ON p.team_id = t.team_id
                WHERE t.team_name = ?
                """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, teamName);
            ResultSet rs = ps.executeQuery();
            System.out.println("\nPlayers of " + teamName);
            System.out.println("--------------------------------------");
            while(rs.next()) {
                System.out.println(new Player(
                        rs.getInt("jersey_number"),
                        rs.getString("player_name"),
                        rs.getDouble("bid_amount"),
                        rs.getString("role")));
            }
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
    }

    
    @Override
    public void displayAllTeams() {
        String sql = """
                SELECT t.team_name,
                       p.jersey_number,
                       p.player_name,
                       p.bid_amount,
                       p.role
                FROM team t JOIN player p
                ON t.team_id = p.team_id
                ORDER BY t.team_name
                """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String currentTeam = "";
            while(rs.next()) {
                if(!currentTeam.equals(rs.getString("team_name"))) {
                    currentTeam = rs.getString("team_name");
                    System.out.println("\n==============================");
                    System.out.println(currentTeam);
                    System.out.println("==============================");
                }
                System.out.println(new Player(
                        rs.getInt("jersey_number"),
                        rs.getString("player_name"),
                        rs.getDouble("bid_amount"),
                        rs.getString("role")));
            }
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
    }
    
    
    @Override
    public boolean deleteTeam(String teamName) {
        String sql = """
                DELETE FROM team
                WHERE team_name = ?
                """;
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setString(1, teamName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        }
        return false;
    }
}
