package Project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scoreboard {
    public List<Record> records = new ArrayList<Record>();

    public Scoreboard() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("scoreboard.csv"));
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                records.add(new Record(data[0], Integer.parseInt(data[1])));
            }
            csvReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.err.println("File exists, but there was IOException");
        }
        Collections.sort( records );
        System.out.println(records.size());
    }

    public void updateScoreboard(String name, int score) {

        this.records.add(new Record(name, score));
        Collections.sort( records );

        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter("scoreboard.csv", true));
            csvWriter.write(name + "," + String.valueOf(score));
            csvWriter.newLine();
            csvWriter.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    public ScrollPane showScoreborad() {
        int n = 1;
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10)); //padding around layout
        grid.setVgap(10); //padding between cells
        grid.setHgap(70); //padding between cells

        for (Record record : records) {
            grid.add(new Label(String.valueOf(n) + "."),0 ,n-1);
            grid.add(new Label(record.name),1 ,n-1);
            grid.add(new Label(String.valueOf(record.score)),2 ,n-1);
            n++;
        }
        ScrollPane scroll = new ScrollPane(grid);
        scroll.setFitToHeight(true);
        return scroll;
    }

}
