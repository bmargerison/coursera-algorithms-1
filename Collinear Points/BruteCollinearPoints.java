/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        Point[] arr = points.clone();
        Arrays.sort(arr);
        for (int i = 0; i < points.length - 1; i++) {
            if (arr[i] == null) { throw new IllegalArgumentException(); }
            if (arr[i].compareTo(arr[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        List<LineSegment> list = new LinkedList<>();
        for (int a = 0; a < arr.length - 3; a++) {
            for (int b = a + 1; b < arr.length - 2; b++) {
                for (int c = b + 1; c < arr.length - 1; c++) {
                    if (arr[a].slopeTo(arr[b]) == arr[a].slopeTo(arr[c])) {
                        for (int d = c + 1; d < arr.length; d++) {
                            if (arr[a].slopeTo(arr[b]) == arr[a].slopeTo(arr[d])) {
                                // StdOut.println(arr[a]);
                                // StdOut.println(arr[b]);
                                // StdOut.println(arr[c]);
                                // StdOut.println(arr[d]);
                                list.add(new LineSegment(arr[a], arr[d]));
                            }
                        }
                    }
                }
            }
        }
        segments = list.toArray(new LineSegment[0]);
    }


    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
