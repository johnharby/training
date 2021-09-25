package com.deanza;

// The World Series Champions program

// CIS36a Lab 3

// John Harby

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WorldSeries {
    private static final String TEAM_FILE = "/Users/johnharby/deanza/Teams.txt";
    private static final String SERIES_FILE =
            "/Users/johnharby/deanza/WorldSeriesWinners.txt";
    private static final String OUTPUT_FILE =
            "/Users/johnharby/deanza/TotalSeriesWins.txt";

    private List<String> teamList;
    private Map<Integer, String> seriesMap;

    /**
     * Constructor uses methods to populate the team list and the series map
     * from the given files.
     */
    public WorldSeries() {
        teamList = new ArrayList<>();
        seriesMap = new HashMap<>();
        populateTeamList();
        populateSeriesMap();
    }

    private List<String> getTeamList() {
        return teamList;
    }

    private Map<Integer, String> getSeriesMap() {
        return seriesMap;
    }

    /**
     * Read from the WorldSeriesWinners.txt file and populate the map
     */
    private void populateSeriesMap() {
        File seriesFile =
                new File(SERIES_FILE);
        try (Scanner scan = new Scanner(seriesFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                int year = Integer.parseInt(line.substring(0, 4));
                String team = line.substring(5);
                seriesMap.put(year, team);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read from the Teams.txt file and populate the team list
     */
    private void populateTeamList() {
        File teamFile = new File(TEAM_FILE);
        try (Scanner scan = new Scanner(teamFile)) {
            while (scan.hasNextLine()) {
                teamList.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles item number 3 calculating the number of times the team won
     *
     * @param ws   World Series instance
     * @param name team to find wins for
     */
    private static void printTeamsWon(WorldSeries ws, String name) {
        if (name.equals("No World Series")) {
            System.out.println("There was no world series once in 1904");
            return;
        }
        Collection<String> teamNames = ws.getSeriesMap().values();
        if (!teamNames.contains(name)) {
            System.out.println("Invalid entry, " +
                    "please run again using valid team name.");
            return;
        }
        int wins = 0;
        for (String teamName : teamNames) {
            if (teamName.equals(name)) {
                wins++;
            }
        }
        System.out.println("Team " + name + " won " + wins + " times.");
    }

    /**
     * Creates the list of teams sorted by wins for item number 4
     *
     * @param winMap
     * @return List containing the teams in order
     */
    private static List<String> createSortedWinList(Map<String, Integer> winMap) {
        return winMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(o -> o.getKey())
                .collect(Collectors.toList());
    }

    /**
     * For item number 4 to write the teams out to a file sorted by highest wins
     *
     * @param ws WorldSeries class instance
     */
    private static void writeSortedFile(WorldSeries ws) {
        File outFile = new File(OUTPUT_FILE);
        Map<String, Integer> winMap = new HashMap<>();
        for (int yr : ws.getSeriesMap().keySet()) {
            String team = ws.getSeriesMap().get(yr);
            if (winMap.containsKey(team)) {
                winMap.put(team, winMap.get(team) + 1);
            } else {
                winMap.put(team, 1);
            }
        }
        List<String> sorted = createSortedWinList(winMap);
        try (FileWriter fw = new FileWriter(outFile)) {
            for (String s : sorted) {
                fw.write(s + " " + winMap.get(s) + "\n");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void printOptions() {
        String options = "1. Show the list of all teams\n" +
                "2. Search for the name of the team that won specifying the year\n" +
                "3. Calculate how many total wins an individual team won the World Series\n" +
                "4. Output a report file that shows the total number of wins of each team sorted by the teams having " +
                "the highest number of wins.";
        System.out.println("Please enter an integer selecting an option: \n" + options);
    }

    public static void main(String[] args) {
        WorldSeries ws = new WorldSeries();
        printOptions();
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if (choice == -1) {
            return;
        }
        switch (choice) {
            case 1:
                ws.getTeamList().stream().forEach(System.out::println);
                System.exit(0);
            case 2:
                System.out.print("Please enter the year: ");
                int year = scan.nextInt();
                System.out.println(ws.getSeriesMap().get(year));
                System.exit(0);
            case 3:
                scan = new Scanner(System.in);
                System.out.print("Please enter the team name: \n");
                String name = scan.nextLine();
                printTeamsWon(ws, name);
                System.exit(0);
            case 4:
                writeSortedFile(ws);
                System.exit(0);
            default:
                System.out.println("Invalid selection, " +
                        "please run again choosing between 1 and 4 inclusive.");
                System.exit(0);
        }
    }
}

