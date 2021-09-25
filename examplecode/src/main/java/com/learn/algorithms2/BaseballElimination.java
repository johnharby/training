package com.learn.algorithms2;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseballElimination {
    private static final String INVALID_ARG = "Invalid argument: no team found";
    private String lastTeam;
    private String trivialTeam;
    private int numTeams;
    private String[] teamArr;
    private HashMap<String, Integer> teamMap;
    private FlowNetwork flow;
    private FordFulkerson ford;
    private int[] remainArr;
    private int[] winArr;
    private int[] lossArr;
    private int[][] remGamesAgainst;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        numTeams = Integer.parseInt(in.readLine());
        teamArr = new String[numTeams];
        winArr = new int[numTeams];
        lossArr = new int[numTeams];
        remainArr = new int[numTeams];
        remGamesAgainst = new int[numTeams][numTeams];
        teamMap = new HashMap<>();
        int i = 0;
        processCtorData(in, i);
    }

    private void processCtorData(In in, int i) {
        while (in.hasNextLine()) {
            String s = in.readLine();
            s = s.trim();
            String[] info = s.split(" +");
            teamArr[i] = (info[0]);
            winArr[i] = Integer.parseInt(info[1]);
            lossArr[i] = Integer.parseInt(info[2]);
            remainArr[i] = Integer.parseInt(info[3]);
            for (int j = 4; j < info.length; j++) {
                remGamesAgainst[i][j - 4] = Integer.parseInt(info[j]);
            }
            teamMap.put(info[0], i);
            i++;
        }
    }

    // number of teams
    public int numberOfTeams() {
        return numTeams;
    }

    // all teams
    public Iterable<String> teams() {
        return teamMap.keySet();
    }

    // number of wins for given team
    public int wins(String team) {
        if (!validateTeamName(team)) {
            throw new IllegalArgumentException(INVALID_ARG);
        }
        return winArr[teamMap.get(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        if (!validateTeamName(team))
            throw new IllegalArgumentException(INVALID_ARG);
        return lossArr[teamMap.get(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (!validateTeamName(team))
            throw new IllegalArgumentException(INVALID_ARG);
        return remainArr[teamMap.get(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (!validateTeamName(team1) || !validateTeamName(team2))
            throw new IllegalArgumentException(INVALID_ARG);
        return remGamesAgainst[teamMap.get(team1)][teamMap.get(team2)];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        lastTeam = team;
        if (!validateTeamName(team)) {
            throw new IllegalArgumentException(INVALID_ARG);
        }
        int remainingWin = wins(team) + remaining(team);
        for (int i = 0; i < winArr.length; i++) {
            if (remainingWin < winArr[i]) {
                trivialTeam = teamArr[i];
                ford = null;
                return true;
            }
        }
        int curTeam = teamMap.get(team);
        int vertices = 2 + (numTeams - 1) + (numTeams * numTeams - 3 * numTeams + 2) / 2;
        populateFlow(curTeam, 0, vertices, remainingWin);
        ford = new FordFulkerson(flow, teamMap.get(team), vertices - 1);
        for (FlowEdge e : flow.edges()) {
            if (e.from() == teamMap.get(team) && e.flow() < e.capacity())
                return true;
        }
        return false;
    }

    private void populateFlow(int curTeam, int x, int vertices, int remainingWin) {
        flow = new FlowNetwork(vertices);
        for (int i = 0; i < numTeams; i++) {
            if (i == curTeam) {
                continue;
            }
            flow.addEdge(new FlowEdge(i, vertices - 1, (double) remainingWin - wins(teamArr[i])));
        }

        for (int i = 0; i < numTeams; i++) {
            if (i == curTeam)
                continue;
            for (int j = i + 1; j < numTeams; j++) {
                if (j == curTeam) {
                    continue;
                }
                flow.addEdge(new FlowEdge(curTeam, numTeams + x, remGamesAgainst[i][j]));
                flow.addEdge(new FlowEdge(numTeams + x, i, Double.POSITIVE_INFINITY));
                flow.addEdge(new FlowEdge(numTeams + x, j, Double.POSITIVE_INFINITY));
                x++;
            }
        }
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (!validateTeamName(team)) {
            throw new IllegalArgumentException(INVALID_ARG);
        }
        boolean eliminated = true;
        if (!team.equals(lastTeam)) {
            eliminated = isEliminated(team);
        }
        if (eliminated) {
            int excludeTeam = teamMap.get(team);
            List<String> retList = new ArrayList<>();
            if (ford == null) {
                retList.add(trivialTeam);
                return retList;
            }
            for (int i = 0; i < numTeams; i++) {
                if (i == excludeTeam)
                    continue;
                if (ford.inCut(i)) {
                    retList.add(teamArr[i]);
                }
            }
            return retList;
        }
        else {
            return null;
        }
    }

    private boolean validateTeamName(String team) {
        return teamMap.containsKey(team);
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}