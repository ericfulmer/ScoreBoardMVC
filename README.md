ScoreBoardProject 🏈
Overview

ScoreBoardProject is a JavaFX application that implements a simple American football scoreboard using the Model–View–Controller (MVC) design pattern. Through the graphical interface, users can enter home and away team names, record and update scoring events in real time, undo the most recent scoring action, and clear the game when needed. The interface also displays the last action performed, making it easy for users and scoreboard operators to understand what scoring event just occurred. Basic input validation is included to ensure team names are set before scoring, with friendly on-screen alerts preventing invalid actions.

Architecture (MVC Design)

The application is cleanly separated into three MVC components:

Model (Scoreboard / ScoreChange): Contains all business logic, including score updates, undo history, clearing the game, validation, and tracking the last action. The model is completely independent of JavaFX and signals invalid actions by throwing exceptions.

View (FXML / CSS): Built using SceneBuilder, the view defines the layout and styling of the application. It contains no business logic and focuses solely on presentation.

Controller: Acts as the bridge between the UI and the model. It connects UI controls to model methods, updates displayed values, and handles JavaFX alerts when invalid input or actions occur.

The application is launched through the App class, which loads the FXML layout and CSS stylesheet.

Features

Real-time score updates for home and away teams

Undo functionality for scoring actions

Clear/reset game state

Display of the most recent scoring action

Input validation with user-friendly alerts

CSS-styled JavaFX interface

Model-level unit testing via ScoreboardTests

Testing

The ScoreboardTests class validates both normal and error cases for the model logic. Tests are run outside of the UI and print clear pass/fail messages to ensure correctness of scoring, undo behavior, validation, and game resets.

Running the Application

This project requires JavaFX to be available on the module path.

Example command:

java --module-path /path/to/javafx/lib \
     --add-modules javafx.controls,javafx.fxml \
     App


Make sure the JavaFX SDK version matches your JDK.

Limitations & Future Improvements

Some limitations remain, such as the lack of data persistence and advanced football features like possession tracking or quarter management. However, the project demonstrates a complete and functional implementation of the MVC design pattern, a JavaFX FXML-based interface, CSS styling, robust input validation, undo functionality, and independent model testing.

If you want, I can also:

Tailor this README to match a class rubric

Add screenshots / GIF placeholders

Create a “How to Use” section for non-technical users

Adjust wording to explicitly defend the two-view (operator + display) design if you’re resubmitting or appealing points
