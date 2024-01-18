package com.example.assignment3;

import javafx.scene.paint.Color;

public class XCircle extends XShape{


    /**
     * Constructor method for a circle
     * @param left The left position of the circle
     * @param top The top of the circle
     * @param color The colour of the circle
     * @param z The z axis position of the circle
     */
    XCircle(double left, double top, Color color, int z){
        super(left, top,  color, z);



    }

    /**
     * Resize the circle using the change in position in the x and y position
     * @param x The change in the x-axis
     * @param y The change in the y-axis
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
     * Checks if the coordinate is inside the circle
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the circle false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        return Math.hypot(x-(right + left)/2,y-(bottom + top)/2) <= this.getSizeX()/2;
    }


}
