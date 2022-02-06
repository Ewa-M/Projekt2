package Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    public List<Card> deck;

    Deck() {
        deck = new ArrayList<>();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("cards.txt"));
            String story;
            String accept;
            String discard;
            while ((story = csvReader.readLine()) != null) {
                accept = csvReader.readLine();
                discard = csvReader.readLine();
                deck.add(new Card(story, accept.split(","), discard.split(",")));
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.err.println("File exists, but there was IOException");
        }

    }

    public Card getRandomCard() {
        Random r = new Random();
        return deck.get(r.nextInt(deck.size()));
    }

}
