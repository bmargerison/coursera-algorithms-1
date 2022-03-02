import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int ln = Integer.parseInt(args[0]);
        RandomizedQueue<String> random = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            random.enqueue(input);
        }
        Iterator<String> iter = random.iterator();
        for (int i = 0; i < ln; i++) {
            StdOut.println(iter.next());
        }
    }
}
