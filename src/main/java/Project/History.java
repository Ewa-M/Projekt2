package Project;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class History {
    public List<HistoryEvent> events;

    History() {
        events = new ArrayList<>();
    }

    void updateHistory(boolean agree, Card card, int date) {
        events.add(new HistoryEvent(agree, card, date));
    }

    VBox showHistory() {
        VBox vbox = new VBox();
        for (HistoryEvent event : events) {
            vbox.getChildren().add(event.showEvent());
        }
        return vbox;
    }

    void saveToFile(String name) {

        for (HistoryEvent event : events) {
            try {
                BufferedWriter csvWriter = new BufferedWriter(new FileWriter(name + "_game_history.csv", true));
                csvWriter.write(event.toString());
                csvWriter.newLine();
                csvWriter.close();
            }
            catch (IOException e) {
                System.out.println("exception occurred" + e);
            }
        }
    }
}
