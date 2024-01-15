package de.exxcellent.challenge.tasks;

import java.util.List;

public class FootballAnalyzer {

  private static final float INITIAL_MIN_GOAL_SPREAD = 1000000000;

  /**
   * This method finds the team with the smallest goal spread in the given data
   * @param footballData Data of the teams
   * @return Team with the smallest goal spread
   */
  public static String findSmallestSpread(List<String[]> footballData) {
    // First we check if the columns Team, Goals and Goals allowed exist
    int columnTeam = findColumnIndex(footballData, "Team");
    if (columnTeam == -1) {
      throw new IllegalArgumentException("Missing column: Team");
    }
    int columnGoals = findColumnIndex(footballData, "Goals");
    if (columnGoals == -1) {
      throw new IllegalArgumentException("Missing column: Goals");
    }
    int columnGoalsAllowed = findColumnIndex(footballData, "Goals Allowed");
    if (columnGoalsAllowed == -1) {
      throw new IllegalArgumentException("Missing column: Goals Allowed");
    }

    float minGoalSpread= INITIAL_MIN_GOAL_SPREAD;
    String teamSmallestSpread = "";
    for (int i = 1; i < footballData.size(); i++) {
      int goals = Integer.parseInt(footballData.get(i)[columnGoals]);
      int goalsAllowed = Integer.parseInt(footballData.get(i)[columnGoalsAllowed]);
      int goalSpread = Math.abs(goals - goalsAllowed);
      if (goalSpread < minGoalSpread) {
        teamSmallestSpread = footballData.get(i)[columnTeam];
        minGoalSpread = goalSpread;
      }
    }
    return teamSmallestSpread;
  }

  private static int findColumnIndex(List<String[]> data, String target) {
    for (String[] line : data) {
      for (int i = 0; i < line.length; i++) {
        if (line[i].equals(target)) {
          return i;
        }
      }
    }
    return -1;
  }
}
