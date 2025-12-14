package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public final class Scoreboard {

    private String homeName, awayName;

    private int homeScore, awayScore;

    private Deque<ScoreChange> history = new ArrayDeque<>();

    public void setTeamNames(String home, String away) {
        if (home == null || home.isBlank() || away == null || away.isBlank()) {
            throw new IllegalArgumentException("Team names cannot be blank");
        }

        this.homeName = home;
        this.awayName = away;

        clearGame();
    }

    public void addPointsToHome(int pts) {
        ensureNamesSet();

        homeScore += pts;
        history.push(new ScoreChange("HOME", pts));
    }

    public void addPointsToAway(int pts) {
        ensureNamesSet();
        
        awayScore += pts;
        history.push(new ScoreChange("AWAY", pts));
    }

    public void undoLast() {
        if (history.isEmpty()) return;
        ScoreChange last = history.pop();
        if ("HOME".equals(last.team)) {
            homeScore -= last.points;
        } else if ("AWAY".equals(last.team)) {
            awayScore -= last.points;
        }
    }

    public void clearGame() {
        homeScore = 0;
        awayScore = 0;

        history.clear();
    }

    public String getHomeName() { return homeName; }
    public String getAwayName() { return awayName; }
    public int getHomeScore() { return homeScore; }
    public int getAwayScore() { return awayScore; }

    public Optional<String> getLastActionDescription() {
        if (history.isEmpty()) { return Optional.empty(); }

        ScoreChange last = history.peek();
        String teamLabel = "HOME".equals(last.team) ? homeName : awayName;

        return Optional.of(teamLabel + " +" + last.points);
    }

    private void ensureNamesSet() {
        if (homeName == null || awayName == null) {
            throw new IllegalStateException("Team names must be set first");
        }
    }
}
