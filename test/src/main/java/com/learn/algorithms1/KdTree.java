package com.learn.algorithms1;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class KdTree
{

    private Node root;
    private int span;

    public KdTree()
    {
        span = 0;
    }
    public boolean isEmpty()                      // is the set empty?
    {
        return span == 0;
    }
    public int size()                         // number of points in the set
    {
        return span;
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null)
        {
            throw new IllegalArgumentException("Can't insert null point");
        }
        if (root == null)
        {
            root = new Node(p, null, null, 0);
            span = 1;
        }
        else if (!contains(p))
        {
            insert(root, p, true);
            span++;
        }

    }

    private void insert(Node node, Point2D p, boolean axis)
    {
        Comparator<Point2D> cmp = axis ? Point2D.X_ORDER : Point2D.Y_ORDER;
        if (cmp.compare(node.pt, p) > 0)
        {
            if (node.left == null)
            {
                node.left = new Node(p, null, null, node.level + 1);
            } else
                {
                insert(node.left, p, !axis);
            }
        } else if (node.right == null)
        {
            node.right = new Node(p, null, null, node.level + 1);
        } else
            {
            insert(node.right, p, !axis);
        }
    }

    public boolean contains(Point2D p)  // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException("Can't pass null point to contains");
        if (root == null)
        {
            return false;
        }
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean axis)
    {
        Comparator<Point2D> cmp = axis ? Point2D.X_ORDER : Point2D.Y_ORDER;
        if (node.pt.equals(p)) return true;
        if (cmp.compare(node.pt, p) > 0)
        {
            if (node.left == null)
            {
                return false;
            } else
                {
                return contains(node.left, p, !axis);
            }
        } else if (node.right == null)
        {
            return false;
        } else
            {
            return contains(node.right, p, !axis);
        }
    }

    public void draw()
    {
        draw(root, new RectHV(0, 0, 1, 1));
    }

    private void draw(Node node, RectHV rect)
    {
        // draw all points to standard draw
        if (node == null)
        {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02d);
        node.pt.draw();

        StdDraw.setPenRadius(0.001d);
        if (node.level % 2 == 0)
        {
            drawEvenLevel(node, rect);
        }
        else
            {
                drawOddLevel(node, rect);
            }
        StdDraw.show();
    }

    private void drawOddLevel(Node node, RectHV rect) {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(rect.xmin(), node.pt.y(), rect.xmax(), node.pt.y());
        draw(node.left, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.pt.y()));
        draw(node.right, new RectHV(rect.xmin(), node.pt.y(), rect.xmax(), rect.ymax()));
    }

    private void drawEvenLevel(Node node, RectHV rect) {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(node.pt.x(), rect.ymin(), node.pt.x(), rect.ymax());
        draw(node.left, new RectHV(rect.xmin(), rect.ymin(), node.pt.x(), rect.ymax()));
        draw(node.right, new RectHV(node.pt.x(), rect.ymin(), rect.xmax(), rect.ymax()));
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> result = new LinkedList<>();
        range(this.root, rect, result);
        return result;
    }

    private void range(Node n, RectHV rect, List<Point2D> pList)
    {
        if (n == null)
        {
            return;
        }

        if (rect.contains(n.pt))
        {
            pList.add(n.pt);
        }

        if (n.level % 2 == 0)
        {
            if (rect.xmin() <= n.pt.x() && n.pt.x() <= rect.xmax())
            {
                range(n.left, rect, pList);
                range(n.right, rect, pList);
            } else if (rect.xmin() > n.pt.x())
            {
                range(n.right, rect, pList);
            } else
                {
                range(n.left, rect, pList);
            }
        } else
            {
            if (rect.ymin() <= n.pt.y() && n.pt.y() <= rect.ymax())
            {
                range(n.left, rect, pList);
                range(n.right, rect, pList);
            } else if (rect.ymin() > n.pt.y())
            {
                range(n.right, rect, pList);
            } else
                {
                range(n.left, rect, pList);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null || isEmpty()) throw new IllegalArgumentException();
        return nearest(p, root.pt, root);
    }

    private Point2D nearest(Point2D p, Point2D nearestPt, Node n)
    {
        if (n == null)
        {
            return nearestPt;
        }
        if (n.level % 2 == 0)
        {
            return processEvenLevel(p, nearestPt, n);
        } else
            {
            if (p.y() > n.pt.y())
            {
                return checkUpTree(p, nearestPt, n);
            } else
                {
                return checkDownTree(p, nearestPt, n);
            }
        }
    }
    private Point2D processEvenLevel(Point2D p, Point2D nearestPt, Node n)
    {
        if (p.x() > n.pt.x())
        {
            return checkRightTree(p, nearestPt, n, n.pt.x(), p.x());
        }
        else
        {
            return checkLeftTree(p, nearestPt, n, n.pt.x(), p.x());
        }
    }

    private Point2D checkLeftTree(Point2D p, Point2D nearestPt, Node n, double x, double x2)
    {
        Point2D npl = nearest(p, n.pt.distanceSquaredTo(p) < nearestPt.distanceSquaredTo(p) ? n.pt :
                nearestPt, n.left);
        if (npl.distanceSquaredTo(p) > Math.abs(x - x2)) {
            Point2D npr = nearest(p, npl, n.right);
            return npr.distanceSquaredTo(p) > npl.distanceSquaredTo(p) ? npl : npr;
        } else {
            return npl;
        }
    }

    private Point2D checkRightTree(Point2D p, Point2D nearestPt, Node n, double x, double x2)
    {
        Point2D npr = nearest(p, n.pt.distanceSquaredTo(p) <
                nearestPt.distanceSquaredTo(p) ? n.pt : nearestPt, n.right);
        if (npr.distanceSquaredTo(p) > Math.abs(x - x2)) {
            Point2D npl = nearest(p, npr, n.left);
            return npr.distanceSquaredTo(p) > npl.distanceSquaredTo(p) ? npl : npr;
        } else {
            return npr;
        }
    }

    private Point2D checkDownTree(Point2D p, Point2D nearestPt, Node n) {
        Point2D npd = nearest(p, n.pt.distanceSquaredTo(p) <
                nearestPt.distanceSquaredTo(p) ? n.pt : nearestPt, n.left);
        if (npd.distanceSquaredTo(p) > Math.abs(n.pt.y() - p.y()))
        {
            Point2D npu = nearest(p, npd, n.right);
            return npu.distanceSquaredTo(p) > npd.distanceSquaredTo(p) ? npd : npu;
        } else {
            return npd;
        }
    }

    private Point2D checkUpTree(Point2D p, Point2D nearestPt, Node n) {
        Point2D npu = nearest(p, n.pt.distanceSquaredTo(p) <
                nearestPt.distanceSquaredTo(p) ? n.pt : nearestPt, n.right);
        if (npu.distanceSquaredTo(p) > Math.abs(n.pt.y() - p.y()))
        {
            Point2D npd = nearest(p, npu, n.left);
            return npu.distanceSquaredTo(p) > npd.distanceSquaredTo(p) ? npd : npu;
        } else {
            return npu;
        }
    }

    private static class Node {
        private final Point2D pt;
        private Node left;
        private Node right;
        private final int level;

        public Node(Point2D pt, Node left, Node right, int level) {
            this.pt = pt;
            this.level = level;
            this.right = right;
            this.left = left;
        }
    }

}
