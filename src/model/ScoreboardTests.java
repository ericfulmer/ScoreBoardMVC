package model;

public final class ScoreboardTests {

    public static void main(String[] args) {

        testCannotScoreBeforeNames();
        testBlankNames();
        testScoringAndUndo();
        testClearGame();
    }

    private static void testCannotScoreBeforeNames() {
        Scoreboard sb = new Scoreboard();

        try {
            sb.addPointsToHome(6);
            System.out.println("FAIL: should not score before names");
        } catch (IllegalStateException e) {
            System.out.println("PASS: cannot score before names");
        }
    }

    private static void testBlankNames() {
        Scoreboard sb = new Scoreboard();

        try {
            sb.setTeamNames("", "Aggies");
            System.out.println("FAIL: blank name allowed");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: blank names rejected");
        }
    }

    private static void testScoringAndUndo() {
        Scoreboard sb = new Scoreboard();

        sb.setTeamNames("Home", "Away");
        sb.addPointsToHome(6);
        sb.addPointsToHome(3);

        if (sb.getHomeScore() == 9) System.out.println("PASS: scoring works");
        else System.out.println("FAIL: scoring incorrect");

        sb.undoLast();

        if (sb.getHomeScore() == 6) System.out.println("PASS: undo works");
        else System.out.println("FAIL: undo incorrect");
    }

    private static void testClearGame() {
        Scoreboard sb = new Scoreboard();
        
        sb.setTeamNames("H", "A");
        sb.addPointsToAway(6);
        sb.clearGame();

        if (sb.getAwayScore() == 0 && sb.getHomeScore() == 0) {
            System.out.println("PASS: clear resets scores");
        } else {
            System.out.println("FAIL: clear not working");
        }
    }
}
