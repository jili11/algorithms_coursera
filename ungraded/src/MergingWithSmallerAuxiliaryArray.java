import java.util.Arrays;

public class MergingWithSmallerAuxiliaryArray {

    /**
     * Question 1
     * Merging with smaller auxiliary array. Suppose that the subarray \mathtt{a[0]}a[0]
     * to \mathtt{a[n-1]}a[n−1] is sorted and the subarray \mathtt{a[n]}a[n] to
     * \mathtt{a[2*n-1]}a[2∗n−1] is sorted. How can you merge the two subarrays so that
     * \mathtt{a[0]}a[0] to \mathtt{a[2*n-1]}a[2∗n−1] is sorted using an auxiliary array of
     * length nn (instead of 2n2n)?
     */

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 8, 9, 2, 4, 6, 7, 10};

        int[] aux = new int[5];
        merge(a, aux, 0, 4, 9);
        System.out.println(Arrays.toString(a));
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= mid; i++) {
            aux[i] = a[i];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if(i > mid) return;
            else if(j > hi) a[k] = aux[i++];
            else if(a[j] > aux[i]) a[k] = aux[i++];
            else a[k] = a[j++];

        }
    }
}
