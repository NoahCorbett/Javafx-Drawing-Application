package com.example.assignment3;

import javafx.scene.paint.Color;

public class XOval extends XShape{
    double sizeX, sizeY, cx, cy;

    /**
     * Constructor method for a oval
     * @param left The left position of the oval
     * @param top The top of the oval
     * @param sizeX The size of the horizontal of the oval
     * @param sizeY The size of the vertical of the oval
     * @param color The colour of the oval
     * @param z The z axis position of the oval
     */
    public XOval(double left, double top, double sizeX, double sizeY, Color color, int z){
        super(left, top, sizeX, color, z);
        cx = left + size/2;
        cy = top + size/2;
        this.sizeX = sizeX/2;
        this.sizeY = sizeY/2;
    }

    /**
     * Resize the oval with the change in x and y
     * @param dx The change in the x-axis
     * @param dy The change in the y-axis
     */
    @Override
    public void resize(double dx, double dy) {
        if((this.initLeft> left || size <0) && (this.initTop > top || sizeY<0)){
            left+= dx;
            top+= dy;
            size-=dx;
            sizeY-=dy;
            if (left > initLeft) {
                left = initLeft;
                size = 0;
            }
            if (top > initTop) {
                top = initTop;
                sizeY = 0;
            }


        } else if(this.initLeft> left || size <0) {
            left = left + dx;
            size -= dx;
            sizeY += dy;
            if (left > initLeft) {
                left = initLeft;
                size = 0;
            }
        }else if(this.initTop> top || sizeY <0) {
            top += dy;
            sizeY -= dy;
            if (top > initTop) {
                top = initTop;
                sizeY = 0;
            }
            size += dx;
        }else {
            size += dx;
            sizeY += dy;
        }

        cx = left +size/2;
        cy = top +sizeY/2;
    }

    /**
     * Checks if the coordinate is inside the oval
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the oval false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        if(sizeY ==0){
            return false;
        }
        double scaleFactor = size/sizeY;


        return Math.hypot((x-cx)/scaleFactor,y-cy) <= sizeY/2;
    }

    /**
     * Move the oval by dX and dY amount
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
        cy = top +sizeY/2;
    }
}
