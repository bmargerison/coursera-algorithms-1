import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double inputs = 0.0;
        String champion = "";
        while (!StdIn.isEmpty()) {
            inputs += 1.0;
            String var = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / inputs)) { champion = var; }
        }
        System.out.println(champion);
    }
}
