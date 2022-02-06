package Project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application implements EventHandler<ActionEvent> {

    public TextField name;
    public Stage stage;
    public Scene scene;
    public VBox layout;
    public  Statistics statistics;
    public Deck deck = new Deck();
    public VBox currentCard = new VBox();
    public int score = 1;
    public Scoreboard scoreboard = new Scoreboard();
    public History history;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Projekt 2");
        layout = new VBox(10);

        name = new TextField("");
        Button button = new Button("Let's Go!");
        button.setOnAction(this);

        layout.getChildren().addAll(new Label("What's your name?"), name, button, scoreboard.showScoreborad());

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(10);
        scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();

        stage.setScene(scene);

        stage.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if (name.getText().equals("")) {
            name.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            return;
        }

        layout.getChildren().clear();
        score = 1;
        statistics = new Statistics();
        history = new History();
        currentCard.getChildren().clear();
        currentCard.getChildren().add(displayCard(deck.getRandomCard()));
        layout.getChildren().addAll(statistics.chart, currentCard);
    }

    public GridPane displayCard(Card card) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10)); //padding around layout
        grid.setVgap(8); //padding between cells
        grid.setHgap(30); //padding between cells

        grid.add(new Label(card.story), 0, 0, 2, 1);
        grid.add(new Label("Approve to: "), 0, 2);
        int n = 3;
        for (Decision decision : card.accept) {
            grid.add(new Label(decision.toString()), 0, n);
            n++;
        }

        grid.add(new Label("Refuse to"), 1, 2);
        n = 3;
        for (Decision decision : card.discard) {
            grid.add(new Label(decision.toString()), 1, n);
            n++;
        }

        n = Math.max(card.discard.size(), card.accept.size()) + 3;

        Button approveButton = new Button("Yes");
        grid.add(approveButton, 0, n);
        approveButton.setOnAction(e -> {
            this.decisionWasMade(true, card);
        });


        Button discardButton = new Button("No");
        grid.add(discardButton, 1, n);
        discardButton.setOnAction(e -> {
            this.decisionWasMade(false, card);
        });

        return grid;
    }

    public void decisionWasMade(boolean approved, Card card) {
        history.updateHistory(approved, card, score);
        if (!card.applyCard(approved, statistics)) {
            this.gameOver();
        }
        score++;
        currentCard.getChildren().clear();
        currentCard.getChildren().add(displayCard(deck.getRandomCard()));
    }

    public void gameOver() {
        scoreboard.updateScoreboard(name.getText(), score);
        this.endScreen();

    }

    public void endScreen() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8); //padding between cells
        grid.setHgap(10); //padding between cells

        Button button = new Button("Play again");
        button.setOnAction(this);
        grid.add(button, 0, 0);

        Button historyButton = new Button("Show history");
        grid.add(historyButton, 1, 0);
        historyButton.setOnAction(e -> {
            this.showHistory();
        });

        layout.getChildren().clear();
        layout.getChildren().addAll(grid, scoreboard.showScoreborad());
    }

    public void showHistory() {

        ScrollPane scrollPane = new ScrollPane();

        GridPane grid = new GridPane();
        Button button = new Button("Play again");
        button.setOnAction(this);
        grid.add(button, 0, 0);

        Button returnButton = new Button("Return to scoreboard");
        grid.add(returnButton, 1, 0);
        returnButton.setOnAction(e -> {
            this.endScreen();
        });

        Button saveButton = new Button("Save to file");
        grid.add(saveButton, 2, 0);
        saveButton.setOnAction(e -> {
            history.saveToFile(name.getText());
        });

        scrollPane.setContent(history.showHistory());

        layout.getChildren().clear();
        layout.getChildren().addAll(grid,scrollPane);
    }
}
