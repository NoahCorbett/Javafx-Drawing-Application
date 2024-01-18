package com.example.assignment3;

import javafx.scene.paint.Color;

public class XLine extends XShape{
    double acceptableSize;

    /**
     * Constructor method for a line
     * @param left The left of the line (x1)
     * @param top The top of the line (y1)
     * @param acceptableSize How close to the line does a click need to be to select the line
     * @param color The color of the line
     * @param z The z position of the line
     */
    public XLine(double left, double top, double acceptableSize, Color color, int z) {
        super(left, top, color, z);
        this.acceptableSize = acceptableSize;


    }

    /**
     * Changes the size (and the direction) of the line
     * @param x The new x coordinate for the end of the line
     * @param y The new y coordinate for the end of the line
     */
    @Override
    public void resize(double x, double y) {
        right = x;
        bottom = y;


    }

    /**
     * If a coordinate is in an acceptable range of the line for it to be selected
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is close to the line false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        double length = Math.sqrt(Math.pow(right-left,2) + Math.pow(bottom-top,2));
        double A = (top-bottom) / length;
        double B = (right-left) / length;
        double C = -1 * ((top-bottom) * left + (right-left) * top) / length;


        return Math.abs(A*x + B*y + C) < acceptableSize && x > Math.min(left, right)- acceptableSize && x <Math.max(left,right)+acceptableSize && y > Math.min(top, bottom) -acceptableSize && y < Math.max(top,bottom) +acceptableSize;
    }

}
