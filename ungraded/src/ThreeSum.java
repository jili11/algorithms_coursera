import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {


    public static void main(String[] args) {
        int[] array = new int[]{5, 8, -3, -2, -8, 0};
        Arrays.sort(array);
        for (int i = 0; i < array.length - 2; ++i) {
            int j = i + 1;
            int k = array.length - 1;

            while (j < k) {
                int sum = array[i] + array[j] + array[k];
                if (sum == 0) {
                    System.out.println(i + ":" + array[i] + ", " + j + ":" + array[j] + ", " + k + ":" + array[k]);
                }
                if (sum >= 0) {
                    --k;
                } else {
                    ++j;
                }
            }
        }

    }


}
