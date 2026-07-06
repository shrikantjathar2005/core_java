package com.mmcoe.ipl;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mmcoe.ipl.dao.PlayerDao;
import com.mmcoe.ipl.dao.PlayerDaoJdbcImpl;
import com.mmcoe.ipl.exception.IplException;
import com.mmcoe.ipl.model.Player;
import com.mmcoe.ipl.model.Team;

public class TestIplManagement {
    private static PlayerDao dao;

    @BeforeAll
    static void init() {
        dao = new PlayerDaoJdbcImpl();
    }

    
    // Adding a test team and player for testing features
    @BeforeEach
    void setUp() throws IplException { 
        try {
            dao.addTeam(new Team(100, "TEST"));
        } catch (Exception e) {
        }
        try {
            dao.addPlayer("TEST",
            		new Player(999,"Test Player",5.5,"BATTER"));
        } catch (Exception e) {
        }
    }

    
    @AfterEach
    void cleanUp() {
        dao.deletePlayer("Test Player"); //delete player first due to foreign key constrint
        dao.deleteTeam("TEST");
    }
    

    @Test
    void testSearchPlayer() {
        Player p =
                dao.searchPlayer("Test Player");
        assertNotNull(p);
        assertEquals("Test Player",
                p.getPlayerName());
    }

    
    @Test
    void testDeletePlayer() {
        boolean deleted =
                dao.deletePlayer("Test Player");
        assertTrue(deleted);
    }
    

    @Test
    void testFilterByRole() {
        List<Player> players =
                dao.filterByRole("BATTER");
        assertFalse(players.isEmpty());
    }
    

    @Test
    void testFilterByPriceGreaterThan() {
        List<Player> players =
                dao.filterByPriceGreaterThan(5);
        assertFalse(players.isEmpty());
    }
    

    @Test
    void testFilterByPriceLessThan() {
        List<Player> players =
                dao.filterByPriceLessThan(10);
        assertFalse(players.isEmpty());
    }
}