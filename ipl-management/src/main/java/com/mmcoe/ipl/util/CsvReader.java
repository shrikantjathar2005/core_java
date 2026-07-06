package com.mmcoe.ipl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.mmcoe.ipl.exception.IplException;
import com.mmcoe.ipl.model.Player;
import com.mmcoe.ipl.service.IplService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CsvReader {
	
	private static final Logger logger = LogManager.getLogger(CsvReader.class);
	
    public static void loadPlayers(IplService service) {
        try {
            InputStream is = CsvReader.class.getClassLoader()
                                            .getResourceAsStream("players.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                String[] data = line.split(",");
                String teamName = data[0];
                int jersey = Integer.parseInt(data[1]);
                String playerName = data[2];
                double bid = Double.parseDouble(data[3]);
                String role = data[4];
                Player player = new Player(jersey, playerName, bid, role);

                try {
                    service.addPlayer(teamName, player);
                } catch (IplException e) {
                    System.out.println(playerName + " : " + e.getMessage());
                    logger.warn("Failed to import player : {}", playerName);
                }
            }
            br.close();
            logger.info("Players imported successfully.");

        } catch (IOException e) {
        	logger.error("CSV reading failed.", e);
        }
    }
}