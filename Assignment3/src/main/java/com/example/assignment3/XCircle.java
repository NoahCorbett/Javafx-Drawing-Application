package com.example.assignment3;

import javafx.scene.paint.Color;

public class XCircle extends XShape{
    double cx,cy, sizeX, sizeY;

    XCircle(double left, double top, double size, Color color, int z){
        super(left, top, size, color, z);
        cx = left + size/2;
        cy = top + size/2;
        sizeX = size/2;
        sizeY = size/2;


    }

    @Override
    public void resize(double normX, double normY, double dx, double dy) {
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

    @Override
    public boolean contains(double x, double y) {
        return Math.hypot(x-cx,y-cy) <= size/2;
    }

    @Override
    public void translate(double normX, double normY, double dX, double dY) {
        left += dX;
        top += dY;
        initLeft +=dX;
        initTop +=dY;
        cx = left +size/2;
        cy = top +size/2;
    }
}
