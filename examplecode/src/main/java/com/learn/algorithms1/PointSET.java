package com.learn.algorithms1;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> setOfPoints;

    public PointSET()                               // construct an empty set of points
    {
        setOfPoints = new TreeSet<>();
    }
    public boolean isEmpty()                      // is the set empty?
    {
        return setOfPoints.isEmpty();
    }
    public int size()                         // number of points in the set
    {
        return setOfPoints.size();
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null) throw new IllegalArgumentException();
        if (!contains(p)) {
            setOfPoints.add(p);
        }
    }
    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException();

        return setOfPoints.contains(p);
    }
    public void draw()                         // draw all points to standard draw
    {
        for (Point2D pt : setOfPoints) {
            pt.draw();
        }
        StdDraw.show();
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> retList = new LinkedList<>();
        for (Point2D p : setOfPoints) {
            if (rect.contains(p)) {
                retList.add(p);
            }
        }
        return retList;
    }
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (isEmpty()) {
            return null;
        }
        double dist = Double.POSITIVE_INFINITY;
        Point2D retPoint = p;
        for (Point2D pt : setOfPoints) {
            if (p.distanceSquaredTo(pt) < dist) {
                dist = p.distanceSquaredTo(pt);
                retPoint = pt;
            }
        }
        return retPoint;
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        Point2D pt1 = new Point2D(1d, 2d);
        Point2D pt2 = new Point2D(2d, 4d);
        Point2D pt3 = new Point2D(6d, 10d);

        PointSET ps = new PointSET();
        ps.insert(pt1);
        ps.insert(pt2);
        ps.insert(pt3);

        ps.draw();
    }
}
