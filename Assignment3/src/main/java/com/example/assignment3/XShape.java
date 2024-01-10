package com.example.assignment3;

import javafx.scene.paint.Color;


public abstract class XShape {
    double left, top, size;

    protected double initLeft, initTop;
    Color color;
    int z;

    /**
     * Constructor method with no parameters
     */
    public XShape() {
        left = 0;
        top = 0;
        size = 0;
        color = Color.BLACK;
    }

    /**
     * Constructor method that sets shape parameters
     * @param newLeft The left position of the shape
     * @param newTop The top of the shape
     * @param newSize The size of the shape
     * @param newColor The colour of the shape
     * @param z The z axis position
     */
    public XShape(double newLeft, double newTop, double newSize, Color newColor, int z) {
        initLeft = newLeft;
        initTop = newTop;
        left = newLeft;
        top = newTop;
        size = newSize;
        color = newColor;
        this.z = z;
    }

    /**
     * Resize the shape using the change in the x and y positions
     * @param dx The change in the x-axis
     * @param dy The change in the y-axis
     */
    public abstract void resize(double dx, double dy);

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
    public abstract void translate(double dX, double dY);
}
