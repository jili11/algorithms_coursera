import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;


    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[0];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot enqueue null item");

        if (this.size == array.length) {
            resizeArray(array.length * 2 + 1);
        }
        array[size++] = item;
    }

    private void resizeArray(int length) {
        Item[] tmp = (Item[]) new Object[length];
        for (int i = 0; i < Math.min(length, array.length); i++) {
            tmp[i] = array[i];
        }
        this.array = tmp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The randomized queue is empty");
        int r = getRandomIndex();
        Item item = array[r];
        array[r] = array[--size];
        if (this.size <= array.length / 4) {
            resizeArray(array.length / 2);
        }
        return item;

    }

    private int getRandomIndex() {
        int r = StdRandom.uniform(this.size);
        while (array[r] == null) {
            r = StdRandom.uniform(this.size);
        }
        return r;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The randomized queue is empty");
        int r = getRandomIndex();
        return array[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator<>();
    }

    //how exactly iterator works?
    private class ListIterator<item> implements Iterator<item> {

        private int i = size;
        private int j = 0;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public item next() {
            if (isEmpty()) throw new java.util.NoSuchElementException();
            i--;
            int index = StdRandom.uniform(j, size);
            item item = (item) array[index];
            array[index] = array[j];
            array[j++] = (Item) item;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(286);
        rq.enqueue(810);
        rq.enqueue(852);
        rq.enqueue(933);
        rq.enqueue(888);
        rq.enqueue(388);
        rq.enqueue(188);
        rq.enqueue(167);

        Iterator<Integer> it1 = rq.iterator();
        Iterator<Integer> it2 = rq.iterator();


        while (it1.hasNext()){
            System.out.println(it1.next());
        }

        System.out.println("==================================");

        while (it2.hasNext()){
            System.out.println(it2.next());
        }

    }
}
