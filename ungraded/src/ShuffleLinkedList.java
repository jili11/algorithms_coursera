import edu.princeton.cs.algs4.StdRandom;

public class ShuffleLinkedList {

    public static void main(String[] args) {


        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);

        System.out.println(list.toString());
        shuffle(list);
        System.out.println(list.toString());
    }

    public static void shuffle(LinkedList l)
    {
        if (l.size() == 1) return;

        LinkedList.Node current = l.first;


        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();

        while (! l.isEmpty())
        {
            l1.push(l.pop());
            if (! l.isEmpty()) l2.push(l.pop());
        }
        shuffle(l1);
        shuffle(l2);

        /*------------------------------------------
        * if (l2.size() < l1.size())
        * introduce a dummy node to ensure the
        * randomness in the process of shuffling
        -----------------------------------------*/

        merge(l, l1, l2);

        /*-----------------------------------------------
         * remove the dummy variable
         * ----------------------------------------------*/
    }

    public static void merge (LinkedList l, LinkedList l1, LinkedList l2)
    {
        while (l1.size() != 0 && l2.size() != 0)
        {
            double chance = StdRandom.uniform(1);
            if (chance < 0.5) l.push(l1.pop());
            else              l.push(l2.pop());
        }

        if (! l1.isEmpty())
            while (! l1.isEmpty()) l.push(l1.pop());
        if (! l2.isEmpty())
            while (! l2.isEmpty()) l.push(l2.pop());
    }

    static public class LinkedList<E>       // static nested class
    {
        private int N = 0;
        private Node first = null;

        public class Node {
            E elem;
            Node next;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public void push(E elem) {
            Node oldfirst = first;
            first = new Node();
            first.elem = elem;
            first.next = oldfirst;
            N++;
        }

        public E pop() {
            E elem = first.elem;
            first = first.next;
            N--;
            return elem;
        }

        public int size() {
            return N;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node node = this.first;
            while (node != null) {
                sb.append(node.elem.toString()).append(" -> ");
                node = node.next;
            }
            return sb.toString();
        }
    }
}
