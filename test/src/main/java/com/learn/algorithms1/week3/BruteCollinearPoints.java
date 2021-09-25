package com.learn.algorithms1.week3;

import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;

public class BruteCollinearPoints
{
    private int count;
    private final Point[] points;
    private LineSegment segments[];

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        validateInput(points);
        this.count = 0;
        this.points = points.clone();
        this.segments = new LineSegment[2];
        Arrays.sort(this.points);
        processCtorPoints();
    }

    private void processCtorPoints()
    {
        for (int i = 0; i < this.points.length - 3; i++)
        {
            for (int j = i + 1; j < this.points.length - 2; j++)
            {
                for (int k = j + 1; k < this.points.length - 1; k++)
                {
                    loopToEndSegments(i, j, k);
                }
            }
        }
    }

    private void loopToEndSegments(int i, int j, int k)
    {
        for (int l = k + 1; l < points.length; l++)
        {
            if(points[i].slopeTo(points[j]) == points[j].slopeTo(points[k]) &&
                    points[j].slopeTo(points[k]) == points[k].slopeTo(points[l]))
            {
                enqueue(new LineSegment(points[i], points[l]));
                this.points[i].drawTo(points[l]);
                StdDraw.show();
            }
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return count;
    }
    public LineSegment[] segments()      // the line segments
    {
        return Arrays.copyOf(this.segments, this.count);
    }

    private void enqueue(LineSegment item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("cannot enqueue null LineSegment");
        }
        if(this.count == this.segments.length)
        {
            resize(2 * this.segments.length);
        }
        this.segments[this.count++] = item;
    }

    private void resize(int capacity)
    {
        assert capacity >= this.count;
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(this.segments, 0, temp, 0, this.count);
        this.segments = temp;
    }

    private void validateInput(Point[] points){
        if(points == null) {
            throw new IllegalArgumentException("input array cannot be null");
        }

        for (int i = 0; i < points.length; i ++) {
            for(int j = 0; j < points.length; j++) {

                if(points[i] == null || points[j] == null) {
                    throw new IllegalArgumentException("no point in input array can be null");
                }

                if(i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("points in array cannot be equal");
                }
            }
        }
    }
}
