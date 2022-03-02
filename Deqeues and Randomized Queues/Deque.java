import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return last == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node newNode = new Node(item);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.previous = newNode;
            newNode.next = first;
        }
        first = newNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
        }
        last = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Node firstNode = first;
        Item deletedItem = first.item;
        if (first == last) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        firstNode.next = null;
        size--;
        return deletedItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Node lastNode = last;
        Item deletedItem = last.item;
        if (first == last) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        lastNode.previous = null;
        size--;
        return deletedItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.isEmpty();
        Iterator<Integer> iter = deque.iterator();
        try {
            iter.remove();
        } catch (UnsupportedOperationException e) {
            /* */
        }
        iter.next();
        iter.hasNext();
        deque.removeFirst();
        deque.removeLast();
    }

}
