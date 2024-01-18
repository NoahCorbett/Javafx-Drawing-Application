package com.example.assignment3;

import javafx.scene.paint.Color;

public class XOval extends XShape{
    /**
     * Constructor method for an oval
     * @param left The left position of the oval
     * @param top The top of the oval
     * @param color The colour of the oval
     * @param z The z axis position of the oval
     */
    public XOval(double left, double top, Color color, int z){
        super(left, top, color, z);
    }


    /**
     * Checks if the coordinate is inside the oval
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the oval false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        if(this.getSizeY() ==0){
            return false;
        }
        double scaleFactor = this.getSizeX()/this.getSizeY();


        return Math.hypot((x-(right+left)/2)/scaleFactor,y-(top+bottom)/2) <= this.getSizeY()/2;
    }

}
