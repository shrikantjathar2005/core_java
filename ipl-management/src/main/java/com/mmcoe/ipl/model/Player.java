package com.mmcoe.ipl.model;

public class Player {
    private int jerseyNumber;
    private String playerName;
    private double bidAmount;
    private String role;

    public Player(int jerseyNumber, String playerName, double bidAmount, String role) {
        this.jerseyNumber = jerseyNumber;
        this.playerName = playerName;
        this.bidAmount = bidAmount;
        this.role = role;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-20s ₹%-8.2fCR %-10s", 			//used this insted of \t 
                jerseyNumber, playerName, bidAmount , role);
    }
}