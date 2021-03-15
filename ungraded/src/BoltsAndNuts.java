import java.util.Arrays;

public class BoltsAndNuts {

    /**
     * Question 1
     * Nuts and bolts. A disorganized carpenter has a mixed pile of nn nuts and nn bolts.
     * The goal is to find the corresponding pairs of nuts and bolts. Each nut fits exactly
     * one bolt and each bolt fits exactly one nut. By fitting a nut and a bolt together,
     * the carpenter can see which one is bigger (but the carpenter cannot compare two nuts
     * or two bolts directly). Design an algorithm for the problem that uses at most proportional
     * to n \log nnlogn compares (probabilistically).
     */
    public static void main(String[] args) {


        // Pay attention:
        // Constraint: Comparison of a nut to another nut or a bolt to another bolt is not allowed.
        // It means nut can only be compared with bolt and bolt can only be compared with nut to see
        // which one is bigger/smaller.
        // Other way of asking this problem is, given a box with locks and keys where one lock can be opened
        // by one key in the box. We need to match the pair.

        // Nuts and bolts are represented as array of characters
        char[] nuts = {'@', '#', '$', '%', '^', '&'};
        char[] bolts = {'$', '%', '&', '^', '@', '#'};

        // Method based on quick sort which matches nuts and bolts
        matchPairs(nuts, bolts, 0, 5);

        System.out.println("Matched nuts and bolts are : ");
        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));

    }

    // Method which works just like quick sort
    private static void matchPairs(char[] nuts, char[] bolts, int low, int high) {
        if (low < high) {
            // Choose last character of bolts array for nuts partition.
            int pivot = partition(nuts, low, high, bolts[high]);

            // Now using the partition of nuts choose that for bolts
            // partition.
            partition(bolts, low, high, nuts[pivot]);

            // Recur for [low...pivot-1] & [pivot+1...high] for nuts and
            // bolts array.
            matchPairs(nuts, bolts, low, pivot - 1);
            matchPairs(nuts, bolts, pivot + 1, high);
        }
    }

    // Similar to standard partition method. Here we pass the pivot element
    // too instead of choosing it inside the method.
    private static int partition(char[] arr, int low, int high, char pivot) {
        int i = low;
        char temp1, temp2;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                i++;
            } else if (arr[j] == pivot) {
                temp1 = arr[j];
                arr[j] = arr[high];
                arr[high] = temp1;
                j--;
            }
        }
        temp2 = arr[i];
        arr[i] = arr[high];
        arr[high] = temp2;

        // Return the partition index of an array based on the pivot
        // element of other array.
        return i;
    }


}
