import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastCollinearPoints {

    private int segmentsCount;
    private final LineSegment[] segments;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

        int i = 0;
        List<Point> skipList = new ArrayList<>();
        Point[] copy = points.clone();

        for (Point point : points) {

            Arrays.sort(copy, point.slopeOrder());


            List<Point> collinearPoints = new ArrayList<>();

            double prevSlope = 0.0;
            for (int q = 1; q < copy.length; q++) {
                double currentSlope = point.slopeTo(copy[q]);
                if (q != 1 && (Double.compare(currentSlope, prevSlope) != 0 || q == copy.length - 1)) {

                    if (Double.compare(currentSlope, prevSlope) == 0) collinearPoints.add(copy[q]);
                    int collinearPointsCount = collinearPoints.size();
                    if (collinearPointsCount >= 3) {
                        collinearPoints.add(point);

                        if(!skipList.containsAll(collinearPoints)){
                            Collections.sort(collinearPoints);
                            lineSegments.add(new LineSegment(collinearPoints.get(0), collinearPoints.get(collinearPointsCount)));
                            this.segmentsCount++;
                            skipList.addAll(collinearPoints);
                        }

                    }
                    collinearPoints.clear();
                }
                collinearPoints.add(copy[q]);
                prevSlope = currentSlope;


            }
        }
        this.segments = lineSegments.toArray(new LineSegment[segmentsCount]);

    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segmentsCount;
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

        FastCollinearPoints fast = new FastCollinearPoints(points);
        for (LineSegment lineSegment : fast.segments()) {
            lineSegment.draw();
            StdDraw.show();
        }
    }

}
