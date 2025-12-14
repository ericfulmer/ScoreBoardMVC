package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Scoreboard;

public class ScoreboardController {

    private final Scoreboard scoreboard = new Scoreboard();

    @FXML private TextField homeNameField, awayNameField;
    @FXML private Label homeNameDisplay, awayNameDisplay;
    @FXML private Label homeScoreLabel, awayScoreLabel, lastActionLabel;
    @FXML private RadioButton homeRadio, awayRadio;
    @FXML private ToggleGroup teamTargetGroup;

    @FXML
    private void initialize() {
        homeRadio.setSelected(true);
    }

    @FXML
    private void handleSetNames() {
        try {
            scoreboard.setTeamNames(homeNameField.getText(), awayNameField.getText());
            updateScoresAndLastAction();
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }
    }

    @FXML
    private void handleScore(ActionEvent event) {
        try {
            Button source = (Button) event.getSource();
            String text = source.getText();
            int pts = 0;
            
            if (text.contains("+6")) pts = 6;
            else if (text.contains("+3")) pts = 3;
            else if (text.contains("+2")) pts = 2;
            else if (text.contains("+1")) pts = 1;

            if (homeRadio.isSelected()) {
                scoreboard.addPointsToHome(pts);
            } else {
                scoreboard.addPointsToAway(pts);
            }
            updateScoresAndLastAction();
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }
    }

    @FXML
    private void handleUndo() {
        scoreboard.undoLast();
        updateScoresAndLastAction();
    }

    @FXML
    private void handleClear() {
        scoreboard.clearGame();
        updateScoresAndLastAction();
    }

    private void updateScoresAndLastAction() {
        homeNameDisplay.setText(scoreboard.getHomeName());
        awayNameDisplay.setText(scoreboard.getAwayName());
        homeScoreLabel.setText(String.valueOf(scoreboard.getHomeScore()));
        awayScoreLabel.setText(String.valueOf(scoreboard.getAwayScore()));

        scoreboard.getLastActionDescription().ifPresentOrElse(
            text -> lastActionLabel.setText(text),
            () -> lastActionLabel.setText("Ready")
        );
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Scoreboard Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}