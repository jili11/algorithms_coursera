import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions - Elementary Sorts
 *
 * Question 1: Intersection of two sets
 * Given two arrays a[] and b[], each containing N distinct 2D points in the plane,
 * design a subquadratic algorithm to count the number of points that are contained
 * both in array a[] and array b[].
 */

public class Intersection {


    public static void main(String[] args) {

        Point[] a = new Point[]{new Point(1, 1), new Point(1, 2), new Point(3, 5), new Point(6, 7)};
        Point[] b = new Point[]{new Point(2, 1), new Point(1, 2), new Point(3, 5), new Point(10, 12)};

        Point[] c = new Point[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        Arrays.sort(c);
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < c.length-1; i++) {
            if (c[i].compareTo(c[i+1]) == 0) {
                points.add(c[i]);
            }
        }

        System.out.println(points);

    }

    static class Point implements Comparable<Point> {
        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            if (this.x > o.x) {
                return 1;
            } else if (this.x < o.x) {
                return -1;
            } else {
                if (this.y > o.y) return 1;
                if (this.y < o.y) return -1;
                return 0;
            }

        }
    }
}
