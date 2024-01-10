package com.example.assignment3;

import javafx.scene.paint.Color;

public class XSquare extends XShape{
    private double sizeX, sizeY;

    /**
     * Constructor method for a square
     * @param left The left position of the square
     * @param top The top of the square
     * @param size The size of the square
     * @param color The colour of the square
     * @param z The z axis position of the square
     */
    public XSquare(double left, double top, double size, Color color, int z){
        super(left, top, size, color, z);
    }

    /**
     * Resize the square using the change in the x and y positions
     * @param dx The change in the x-axis
     * @param dy The change in the y-axis
     */
    @Override
    public void resize(double dx, double dy) {
        sizeX += dx;
        sizeY += dy;
        if (sizeX <0 && sizeY <0){
            if(sizeX>sizeY){
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

    }

    /**
     * Checks if the coordinates are inside the square
     * @param x The x position of the coordinate
     * @param y The y position of the coordinate
     * @return True if the coordinate is inside the square false otherwise
     */
    @Override
    public boolean contains(double x, double y) {

        return x >= left && x <= left + size && y >= top && y <= top + size;
    }

    /**
     * Moves the square by dX and dY amount
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
