package com.example.assignment3;

import javafx.scene.paint.Color;

public class XRectangle extends XShape{
    /** The height of the rectangle */
    double sizeY;

    /**
     * Constructor method for a rectangle
     * @param left The left position of the rectangle
     * @param top The top of the rectangle
     * @param sizeX The size of the horizontal of the rectangle
     * @param sizeY The size of the vertical of the rectangle
     * @param color The colour of the rectangle
     * @param z The z axis position of the rectangle
     */
    public XRectangle(double left, double top, double sizeX, double sizeY, Color color, int z){
        super(left, top, sizeX, color, z);
        this.sizeY = sizeY;
    }

    /**
     * Resize the rectangle with the change in x and y
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
    }

    /**
     * If the coordinates are within the rectangle
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the rectangle false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        return x >= left && x <= left + size && y >= top && y <= top + sizeY;
    }

    /**
     * Move the rectangle by dx and dy
     * @param dX How far the shape should move in the x-axis
     * @param dY How far the shape should move in the y-axis
     */
    @Override
    public void translate(double dX, double dY) {
        initLeft +=dX;
        initTop += dY;
        left += dX;
        top += dY;
    }
}
