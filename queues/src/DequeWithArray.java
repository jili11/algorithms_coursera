import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeWithArray<Item> implements Iterable<Item> {

    private Item[] items;
    private int head = 0;
    private int tail = 1;

    // construct an empty deque
    public DequeWithArray() {
        items = (Item[]) new Object[0];

    }

    // is the deque empty?
    public boolean isEmpty() {
        return tail - head > 1;
    }

    // return the number of items on the deque
    public int size() {
        return tail - head;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item is null");
        if (head <= 0) resizeArray(items.length * 2 + 1);
        items[head--] = item;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item is null");
        if (tail >= items.length - 1) resizeArray(items.length * 2 + 1);
        items[tail++] = item;
    }

    private void resizeArray(int i) {
        Item[] tmp = (Item[]) new Object[i];
        for (int j = 0, k = i / 4; j < items.length; j++) {
            tmp[k] = items[j];
        }
        head = head + i / 4;
        tail = tail + i / 4;
        items = tmp;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (head + (items.length - tail) > 0.75 * items.length) {
            resizeArray(items.length / 2);
        }
        return items[++head];
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (head + (items.length - tail) > 0.75 * items.length) {
            resizeArray(items.length / 2);
        }
        return items[--tail];
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private final Item[] iteratorQueue;
        private int i = 0;


        ListIterator() {
            iteratorQueue = (Item[]) new Object[tail];
            //Copy items into iterator queue only from head to tail
            for (int i = head - 1, j = 0; i <= tail; i++, j++) {
                iteratorQueue[j] = (Item) items[i];
            }
        }


        @Override
        public boolean hasNext() {
            return i < tail - head;
        }

        @Override
        public Item next() {
            if (i >= iteratorQueue.length) throw new NoSuchElementException();
            return iteratorQueue[++i];
        }

    }

    // unit testing (required)
    public static void main(String[] args) {

        DequeWithArray<String> deque = new DequeWithArray<>();
        deque.addFirst("pp");
        deque.addLast("yu");
        deque.addFirst("tyty");
        deque.addFirst("yuyu");
        deque.removeFirst();


        for (String s : deque) {
            System.out.println(s);
        }

        System.out.println(Arrays.toString(deque.items));


    }
}
