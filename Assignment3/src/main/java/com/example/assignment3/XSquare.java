package com.example.assignment3;
import javafx.scene.paint.Color;


public class XSquare extends XShape{

    /**
     * Constructor method for a square
     * @param left The left position of the square
     * @param top The top of the square
     * @param color The colour of the square
     * @param z The z axis position of the square
     */
    public XSquare(double left, double top, Color color, int z){
        super(left, top, color, z);
    }

    /**
     * Resize the square using the change in the x and y positions of the mouse
     *  The x coordinate of the mouse
     *  The y coordinate of the mouse
     */
    @Override
    public void resize(double x, double y) {
        super.resize(x, y);
        double minDim = Math.min(bottom-top,right-left);
        if (x < initLeft) {
            left = initLeft - minDim;
            right = initLeft;
        } else {
            left = initLeft;
            right = initLeft + minDim;
        }
        if (y < initTop) {
            top = initTop - minDim;
            bottom = initTop;
        } else {
            top = initTop;
            bottom = initTop + minDim;
        }

    }


    /**
     * Checks if the coordinates are inside the square
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the square false otherwise
     */
    @Override
    public boolean contains(double x, double y) {

        return x >= left && x <= right && y >= top && y <= bottom;
    }


}
