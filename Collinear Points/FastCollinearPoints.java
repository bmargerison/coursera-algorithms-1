/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        Point[] arr = points.clone();
        Arrays.sort(arr);
        for (int i = 0; i < points.length - 1; i++) {
            if (arr[i] == null) { throw new NullPointerException(); }
            if (arr[i].compareTo(arr[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        for (int a = 0; a < arr.length; a++) {
            Point[] sortedArr = arr.clone();
            Arrays.sort(sortedArr);
            Arrays.sort(sortedArr,  arr[a].slopeOrder());
            for (int ln = 2, start = 1, end = 2; end < sortedArr.length;) {
                while (end < sortedArr.length
                        && sortedArr[0].slopeTo(sortedArr[start]) == sortedArr[0].slopeTo(sortedArr[end])) {
                    end++;
                    ln++;
                }
                if (ln >= 4 && sortedArr[0].compareTo(sortedArr[start]) < 0) {
                    segments.add(new LineSegment(sortedArr[0], sortedArr[end - 1]));
                }
                start = end;
                ln = 2;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
