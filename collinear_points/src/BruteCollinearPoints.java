import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private int segmentsCount;
    private final LineSegment[] segments;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int numOfPoints = points.length;
        for (int i = 0; i < numOfPoints; i++) {
            for (int j = 0; j < numOfPoints; j++) {
                if (points[i] == null || points[j] == null)
                    throw new IllegalArgumentException("The one point in input is null!");
                if (i != j && points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Points are equal");

            }
        }

        List<LineSegment> lineSegments = new ArrayList<>();

        Arrays.sort(points);
        for (int p = 0; p < numOfPoints - 3; p++) {
            for (int q = p + 1; q < numOfPoints - 2; q++) {
                for (int k = q + 1; k < numOfPoints - 1; k++) {
                    for (int s = k + 1; s < numOfPoints; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[k]) &&
                                points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                            lineSegments.add(new LineSegment(points[p], points[s]));
                            segmentsCount++;
                        }
                    }
                }
            }
        }
        this.segments = lineSegments.toArray(new LineSegment[segmentsCount]);


    }

    // the number of line segments
    public int numberOfSegments() {
        return segmentsCount;
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments;
    }


    public static void main(String[] args) {
        In in = new In("collinear\\vertical5.txt");      // input file
        int n = in.readInt();

        // padding for drawing
        int padding = 1000;

        // set draw scale
        StdDraw.setXscale(-padding, Short.MAX_VALUE + padding);
        StdDraw.setYscale(-padding, Short.MAX_VALUE + padding);

        // Index of array
        int index = 0;

        // turn on animation mode
        StdDraw.enableDoubleBuffering();

        // Create array
        Point[] points = new Point[n];

        points[index] = new Point(in.readInt(), in.readInt());
        points[index].draw();
        StdDraw.show();

        index++;

        while (!in.isEmpty()) {
            points[index] = new Point(in.readInt(), in.readInt());
            points[index].draw();
            StdDraw.show();

            index++;
        }

        BruteCollinearPoints bfcp = new BruteCollinearPoints(points);
        for (LineSegment lineSegment : bfcp.segments()) {
            lineSegment.draw();
            StdDraw.show();
        }
    }

}
