package Project;

import java.util.ArrayList;
import java.util.List;

public class Card {
    public String story;
    public List<Decision> accept;
    public List<Decision> discard;



    public Card(String story, List<Decision> accept, List<Decision> discard) {
        this.story = story;
        this.accept = accept;
        this.discard = discard;
    }


    public Card(String story, String[] accept, String[] discard) {
        this.story = story;
        this.accept = new ArrayList<Decision>();
        this.discard = new ArrayList<Decision>();
        for (String s : accept) {
            this.accept.add(new Decision(s));
        }

        for (String s : discard) {
            this.discard.add(new Decision(s));
        }

    }

    public boolean applyCard( boolean approved, Statistics statistics) {
        if (approved) {
            for (Decision decision : accept) {
                if (statistics.ifLost(decision.applyTo, decision.result)) {
                    return false;
                }
            }
        } else {
            for (Decision decision : discard) {
                if (statistics.ifLost(decision.applyTo, decision.result)) {
                    return false;
                }
            }
        }
        return true;
    }



}
