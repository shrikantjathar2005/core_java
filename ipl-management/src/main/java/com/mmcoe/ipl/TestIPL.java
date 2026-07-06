package com.mmcoe.ipl;
import java.util.Scanner;
import com.mmcoe.ipl.exception.IplException;
import com.mmcoe.ipl.model.Team;
import com.mmcoe.ipl.service.IplService;
import com.mmcoe.ipl.util.CsvReader;

public class TestIPL {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IplService service = new IplService();

        try {
            service.addTeam(new Team(1, "MI"));
            service.addTeam(new Team(2, "RCB"));
            service.addTeam(new Team(3, "GT"));
            service.addTeam(new Team(4, "CSK"));
            service.addTeam(new Team(5, "KKR"));

        } catch (IplException e) {
            System.out.println(e.getMessage());
        }

        int choice;
        do {
            System.out.println("\n========== IPL MANAGEMENT ==========");
            System.out.println("1. Import Players From CSV");
            System.out.println("2. Search Player");
            System.out.println("3. Delete Player");
            System.out.println("4. Filter By Role");
            System.out.println("5. Filter Price Greater Than");
            System.out.println("6. Filter Price Less Than");
            System.out.println("7. Display Team Players");
            System.out.println("8. Display All Teams");
            System.out.println("9. Exit");
            System.out.print("Enter Choice : ");
            
            choice = sc.nextInt();
            switch (choice) {

            case 1:
                CsvReader.loadPlayers(service);
                break;

            case 2:
                sc.nextLine();
                System.out.print("Enter Player Name : ");
                String playerName = sc.nextLine();
                service.searchPlayer(playerName);
                break;

            case 3:
                sc.nextLine();
                System.out.print("Enter Player Name : ");
                String deletePlayer = sc.nextLine();
                service.deletePlayer(deletePlayer);
                break;

            case 4:
                sc.nextLine();
                System.out.print("Enter Role (BATTER/BOWLER/ALL_ROUNDER/WICKET_KEEPER) : ");
                String role = sc.nextLine();
                service.filterByRole(role);
                break;

            case 5:
                System.out.print("Enter Amount : ");
                double greater = sc.nextDouble();
                service.filterByPriceGreaterThan(greater);
                break;

            case 6:
                System.out.print("Enter Amount : ");
                double less = sc.nextDouble();
                service.filterByPriceLessThan(less);
                break;

            case 7:
                sc.nextLine();
                System.out.print("Enter Team Name : ");
                String teamName = sc.nextLine();
                service.displayTeamPlayers(teamName);
                break;

            case 8:
                service.displayAllTeams();
                break;

            case 9:
                System.out.println("Thank You");
                break;

            default:
                System.out.println("Invalid Choice");
            }
        } while (choice != 9);
        sc.close();
    }
}