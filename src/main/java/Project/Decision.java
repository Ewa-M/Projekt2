package Project;

public class Decision {
    int applyTo;
    int result;

    Decision(int applyTo, int result) {
        this.applyTo = applyTo;
        this.result = result;
    }

    Decision(String s) {
        String[] data = s.split(" ");
        this.applyTo = Integer.parseInt(data[0]);
        this.result = Integer.parseInt(data[1]);
    }

    @Override
    public String toString() {

        String change;
        if (result > 0) change = "increase";
        else change = "decrease";

        switch (applyTo) {
            case 0:
                return change + " military by " + Math.abs(result);
            case 1:
                return change + " gold by " + Math.abs(result);
            case 2:
                return change + " food by " + Math.abs(result);
            case 3:
                return change + " advancement by " + Math.abs(result);
        }
        return "";
    }
}
