import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
            return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // resize array as necessary
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        if (size == arr.length) { resize(2 * arr.length); }
        arr[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) { throw new NoSuchElementException(); }
        int randomIndex = StdRandom.uniform(size);
        Item randomItem = arr[randomIndex];
        // move last element to the index of deleted element
        arr[randomIndex] = arr[size-1];
        // make last element null
        arr[size-1] = null;
        size--;
        if (size > 0 && size == arr.length / 4) { resize(arr.length/2); }
        return randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) { throw new NoSuchElementException(); }
        int randomIndex = StdRandom.uniform(size);
        Item randomItem = arr[randomIndex];
        return randomItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int element = 0;
        private int[] randomized;
        private Item item;

        public RandomizedQueueIterator() {
            // new array holding randomized items
            randomized = new int[size];
            for (int i = 0; i < size; i++) {
                randomized[i] = i;
            }
            StdRandom.shuffle(randomized, 0, size - 1);
        }

        public boolean hasNext() {
            return element < size;
        }

        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            item = arr[randomized[element]];
            element++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> random = new RandomizedQueue<Integer>();
        random.enqueue(1);
        random.enqueue(2);
        random.isEmpty();
        Iterator<Integer> iter = random.iterator();
        try {
            iter.remove();
        } catch (UnsupportedOperationException e) {
            /* */
        }
        if (!random.isEmpty()) {
            iter.next();
        }
        if (iter.hasNext()) {
            iter.next();
        }
        random.dequeue();
        random.dequeue();
    }

}

