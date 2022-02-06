package Project;

public class Record implements Comparable<Record> {
    public final String name;
    public final int score;

    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Record o) {
        return this.score - o.score;
    }
}
