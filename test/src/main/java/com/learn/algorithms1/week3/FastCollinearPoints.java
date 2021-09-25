package com.learn.algorithms1.week3;

import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints
{
    private int count;
    private LineSegment[] segments;
    private Point[] points;

    public FastCollinearPoints(Point[] points)
    {
        validateInput(points);
        this.count = 0;
        this.points = points;
        this.segments = new LineSegment[2];
        LinkedList<Point> pointList = new LinkedList<Point>();
        processCtorPoints(pointList);
    }

    private void processCtorPoints(LinkedList<Point> pointList)
    {
        for (Point point : this.points)
        {
            Arrays.sort(this.points, point.slopeOrder());
            double prevSlope = 0.0;
            processDrawPoints(pointList, point, prevSlope);
        }
    }

    private void processDrawPoints(LinkedList<Point> pointList, Point point, double prevSlope)
    {
        for (int j = 0; j < this.points.length; j++)
        {
            double currentSlope = point.slopeTo(this.points[j]);
            if(j == 0 || currentSlope != prevSlope)
            {
                if(pointList.size() >= 3)
                {
                    this.enqueue(new LineSegment(pointList.getFirst(), pointList.getLast()));
                    pointList.getFirst().drawTo(pointList.getLast());
                    StdDraw.show();
                }
                pointList.clear();
            }
            pointList.add(this.points[j]);
            prevSlope = currentSlope;
        }
    }

    public LineSegment[] segments()
    {
        return Arrays.copyOf(segments, count);
    }

    public int numberOfSegments()
    {
        return count;
    }

    private void enqueue(LineSegment ls)
    {
        if (ls == null)
        {
            throw new IllegalArgumentException("can't enqueue null LineSegment");
        }
        if (count == segments.length) {
            resize(segments.length * 2);
        }
        segments[count++] = ls;
    }

    private void resize(int cap)
    {
        assert cap >= count;
        LineSegment[] temp = new LineSegment[cap];
        System.arraycopy(segments, 0, temp, 0, count);
        segments = temp;
    }

    private void validateInput(Point[] points)
    {
        if(points == null)
        {
            throw new IllegalArgumentException("input points[] is null");
        }

        for (int i = 0; i < points.length; i ++)
        {
            for(int j = 0; j < points.length; j++)
            {

                if(points[i] == null || points[j] == null) {
                    throw new IllegalArgumentException("one of input points is null");
                }

                if(i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("duplicate points exist:" +
                            points[i].toString() + " & " + points[j].toString());
                }
            }
        }
    }

}
