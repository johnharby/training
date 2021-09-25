package com.learn;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    private double rx, ry;
    private double vx, vy;
    private final double radius;

    public Ball() {
        radius = 0.25;
        rx = StdRandom.uniform(100);
        ry = StdRandom.uniform(100);
        vx = 1.0;
        vy = 1.0;
    }

    public void move(double dt) {
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) {
            vx = -vx;
        }
        if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) {
            vy = -vy;
        }
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}
