public class SelectionInSortedArrays {

    /**
     * Selection in two sorted arrays. Given two sorted arrays a[\; ]a[] and b[ \; ]b[], of lengths n1
     * and n2 and an integer 0 \le k < n_1 + n_20≤k<n1+n2, design an algorithm to find a key of rank kk.
     * The order of growth of the worst case running time of your algorithm should be \log nlogn, where n = n1 + n2
     * Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
     * Version 2: k=n/2 (median).
     * Version 3: no restrictions.
     */
    public static void main(String[] args) {
        //Input : Array 1 - 2 3 6 7 9
        //        Array 2 - 1 4 8 10
        //        k = 5
        //Output : 6
        //Explanation: The final sorted array would be -
        //1, 2, 3, 4, 6, 7, 8, 9, 10
        //The 5th element of this array is 6.
        //
        //Input : Array 1 - 100 112 256 349 770
        //        Array 2 - 72 86 113 119 265 445 892
        //        k = 7
        //Output : 256
        //Explanation: Final sorted array is -
        //72, 86, 100, 112, 113, 119, 256, 265, 349, 445, 770, 892
        //7th element of this array is 256.

        int arr1[] = {2, 3, 6, 7, 9};
        int arr2[] = {1, 4, 8, 10};
        int k = 5;
        System.out.print(kth(arr1, arr2, 5, 4, k));

    }

    static int kth(int arr1[], int arr2[], int m, int n, int k) {
        int[] sorted1 = new int[m + n];
        int i = 0, j = 0, d = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j])
                sorted1[d++] = arr1[i++];
            else
                sorted1[d++] = arr2[j++];
        }
        while (i < m)
            sorted1[d++] = arr1[i++];
        while (j < n)
            sorted1[d++] = arr2[j++];
        return sorted1[k - 1];
    }
}
