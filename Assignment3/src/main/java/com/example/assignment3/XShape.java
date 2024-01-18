package com.example.assignment3;

import javafx.scene.paint.Color;


public abstract class XShape {
    /** Numbers indicating the location and size of the object */
    double left, top, right, bottom;


    protected double initLeft, initTop;
    Color color;
    int z;


    /**
     * Constructor method that sets shape parameters
     * @param x The left position of the shape
     * @param y The top of the shape
     * @param newColor The colour of the shape
     * @param z The z axis position
     */
    public XShape(double x, double y, Color newColor, int z) {
        initLeft = x;
        initTop = y;
        left = x;
        top = y;
        right = x;
        bottom = y;
        color = newColor;
        this.z = z;
    }

    /**
     * Resize the shape using the new x coordinate of the mouse
     * @param x The x coordinate of the mouse
     * @param y The y coordinate of the mouse
     */
    public void resize(double x, double y){
        left = Math.min(x, initLeft);
        right = Math.max(x, initLeft);
        top = Math.min(y, initTop);
        bottom = Math.max(y, initTop);
    }

    /**
     * Checks if the coordinates are inside the shape
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the shape false otherwise
     */
    public abstract boolean contains(double x, double y);

    /**
     * Gets the z position of the shape
     * @return The z position of the shape
     */
    public int getZ() {
        return z;
    }

    /**
     * Set the z position of the shape
     * @param z the position in the z axis compared to other shapes
     */
    public void setZ(int z){
        this.z = z;
    }

    /**
     * Moves the shape by dX and dY amount
     * @param dX How far the shape should move in the x-axis
     * @param dY How far the shape should move in the y-axis
     */
    public void translate(double dX, double dY) {
        initLeft +=dX;
        left += dX;
        right += dX;
        initTop += dY;
        top += dY;
        bottom += dY;
    }

    public double getSizeX() {
        return this.right - this.left;
    }

    public double getSizeY() {
        return this.bottom - this.top;
    }
}
