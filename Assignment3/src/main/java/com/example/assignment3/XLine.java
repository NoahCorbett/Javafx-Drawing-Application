package com.example.assignment3;

import javafx.scene.paint.Color;

public class XLine extends XShape{
    double x1,y1,x2, y2, acceptableSize;

    /**
     * Constructor method for a line
     * @param left The left of the line (x1)
     * @param top The top of the line (y1)
     * @param sizeX Size of the line in the x-axis
     * @param sizeY Size of the line in the y-axis
     * @param acceptableSize How close to the line does a click need to be to select the line
     * @param color The color of the line
     * @param z The z position of the line
     */
    public XLine(double left, double top, double sizeX, double sizeY, double acceptableSize, Color color, int z) {
        super(left, top, sizeX, color, z);
        x1 = left;
        y1 = top;
        x2 = left + sizeX;
        y2 = top + sizeY;
        this.acceptableSize = acceptableSize;


    }

    /**
     * The change in how far (and the direction) of the line
     * @param dx The change in the x-axis
     * @param dy The change in the y-axis
     */
    @Override
    public void resize(double dx, double dy) {
        x2 += dx;
        y2 += dy;


    }

    /**
     * If a coordinate is in an acceptable range of the line for it to be selected
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is close to the line false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        double length = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
        double A = (y1-y2) / length;
        double B = (x2-x1) / length;
        double C = -1 * ((y1-y2) * x1 + (x2-x1) * y1) / length;


        return Math.abs(A*x + B*y + C) < acceptableSize && x > Math.min(x1, x2)- acceptableSize && x <Math.max(x1,x2)+acceptableSize && y > Math.min(y1, y2) -acceptableSize && y < Math.max(y1,y2) +acceptableSize;
    }

    /**
     * Move the line by dX and dY
     * @param dX How far the line should move in the x-axis
     * @param dY How far the line should move in the y-axis
     */
    @Override
    public void translate(double dX, double dY) {
        x1 += dX;
        y1 += dY;
        x2 += dX;
        y2 += dY;
    }
}
