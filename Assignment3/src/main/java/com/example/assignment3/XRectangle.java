package com.example.assignment3;

import javafx.scene.paint.Color;

public class XRectangle extends XShape{

    /**
     * Constructor method for a rectangle
     * @param left The left position of the rectangle
     * @param top The top of the rectangle
     * @param color The colour of the rectangle
     * @param z The z axis position of the rectangle
     */
    public XRectangle(double left, double top, Color color, int z){
        super(left, top, color, z);
    }


    /**
     * If the coordinates are within the rectangle
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the rectangle false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        return x >= left && x <= right && y >= top && y <= bottom;
    }

}
