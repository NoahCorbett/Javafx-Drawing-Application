package com.example.assignment3;

import javafx.scene.paint.Color;

public class XCircle extends XShape{
    /** The center of the circle */
    double cx,cy;
    /** The size of the circle */
    double sizeX, sizeY;

    /**
     * Constructor method for a circle
     * @param left The left position of the circle
     * @param top The top of the circle
     * @param size The size of the circle
     * @param color The colour of the circle
     * @param z The z axis position of the circle
     */
    XCircle(double left, double top, double size, Color color, int z){
        super(left, top, size, color, z);
        cx = left + size/2;
        cy = top + size/2;
        sizeX = size/2;
        sizeY = size/2;


    }

    /**
     * Resize the circle using the change in position in the x and y position
     * @param dx The change in the x-axis
     * @param dy The change in the y-axis
     */
    @Override
    public void resize(double dx, double dy) {
        sizeX += dx;
        sizeY += dy;
        if (sizeX <0 && sizeY <0){
            if(-sizeX<-sizeY){
                left =sizeX + initLeft;
                top =sizeX + initTop;
                size = -sizeX;
            }else{
                left =sizeY +initLeft;
                top =sizeY + initTop;
                size = -sizeY;
            }

        }else if (sizeX<0){
            if (-sizeX<sizeY){
                left = sizeX + initLeft;
                size = -sizeX;
            }else{
                left = -sizeY + initLeft;
                size = sizeY;
            }
        }else if (sizeY<0){
            if (-sizeY<sizeX){
                top = sizeY + initTop;
                size = -sizeY;
            }else{
                top = -sizeX + initTop;
                size = sizeX;
            }
        }else{
            size = Math.min(sizeX, sizeY);
        }
        cx = left +size/2;
        cy = top +size/2;

    }

    /**
     * Checks if the coordinate is inside the circle
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the circle false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        return Math.hypot(x-cx,y-cy) <= size/2;
    }

    /**
     * Moves the circle by dX and dY amount
     * @param dX How far the shape should move in the x-axis
     * @param dY How far the shape should move in the y-axis
     */
    @Override
    public void translate(double dX, double dY) {
        left += dX;
        top += dY;
        initLeft +=dX;
        initTop +=dY;
        cx = left +size/2;
        cy = top +size/2;
    }
}
