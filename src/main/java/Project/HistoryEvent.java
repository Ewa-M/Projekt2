package Project;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HistoryEvent {
    final boolean agree;
    final Card card;
    final int date;

    public HistoryEvent(boolean agree, Card card, int date) {
        this.agree = agree;
        this.card = card;
        this.date = date;
    }

    public GridPane showEvent() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.add(new Label(String.valueOf(date) + ". " + card.story), 0, 0);
        int n= 0;
        if (agree) {
            for (Decision decision : card.accept) {
                n++;
                grid.add(new Label(decision.toString()), 0, n);
            }
        } else {
            for (Decision decision : card.discard) {
                n++;
                grid.add(new Label(decision.toString()), 0, n);
            }
        }
        return grid;
    }

    @Override
    public String toString() {
        if (agree)  return String.valueOf(date) + "," + card.story + ",agreed";
        else return String.valueOf(date) + "," + card.story + ",discarded";
    }
}
