import java.util.Arrays;
import java.util.Comparator;

public class BitonicArraySearch {

    public static void main(String[] args) {

        int[] array = {-1, 2, 4, 5, 8, 10, -9, -100};
        int valueToFind = -200;


        int center = binarySearchPeak(array, 0, array.length);
        int found = ascendingBinarySearch(array, 0, center, valueToFind);
        if(found == -1){
            found = descendingBinarySearch(array, center+1, array.length-1, valueToFind);
        }

        System.out.println(center);
        System.out.println(found);

    }

    // Function for binary search in ascending part
    static int ascendingBinarySearch(int[] arr, int low,
                                     int high, int key)
    {
        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key)
            {
                return mid;
            }
            if (arr[mid] > key)
            {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // Function for binary search in descending part of
    // array
    static int descendingBinarySearch(int[] arr, int low,
                                      int high, int key)
    {
        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key)
            {
                return mid;
            }
            if (arr[mid] < key)
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int binarySearchPeak(int[] array, int start, int end) {
        int mid = (start + end) / 2;
        if (start == end) {
            return mid;
        } else if (array[mid] < array[mid+1]) {
            return binarySearchPeak(array, mid+1, end);
        } else {
            return binarySearchPeak(array, start, mid);
        }
    }
}
