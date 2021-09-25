package com.learn.algorithms1.week3;

import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {  // constructs the point (x, y)
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {                             // draws this point
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that)                   // draws the line segment from this point to that point
    {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString()                           // string representation
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
    {
        if(y == that.y && x == that.x) {
            return 0;
        }

        if(y < that.y || (y == that.y && x < that.x)) {
            return -1;
        }

        return 1;
    }

    public double slopeTo(Point that)       // the slope between this point and that point
    {
        int denom = that.x - x;
        int num = that.y - y;
        if (denom == 0 && that.y >= y) {
            return Double.POSITIVE_INFINITY;
        }
        else if (denom == 0 && num < 0) {
            return Double.NEGATIVE_INFINITY;
        }
        return (num / denom);
    }
    public Comparator<Point> slopeOrder()
    {
        return new SlopeComparator();
    }

    private class SlopeComparator implements Comparator<Point>
    {
        public int compare(Point a, Point b) {
            double sa = slopeTo(a);
            double sb = slopeTo(b);
            if (sa < sb) return -1;
            if (sb > sa) return 1;
            return 0;
        }
    }
}
