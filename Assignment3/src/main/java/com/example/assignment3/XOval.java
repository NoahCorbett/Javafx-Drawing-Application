package com.example.assignment3;

import javafx.scene.paint.Color;

public class XOval extends XShape{
    double sizeX, sizeY, cx, cy;
    public XOval(double left, double top, double sizeX, double sizeY, Color color, int z){
        super(left, top, sizeX, color, z);
        cx = left + size/2;
        cy = top + size/2;
        this.sizeX = sizeX/2;
        this.sizeY = sizeY/2;
    }
    @Override
    public void resize(double normX, double normY, double dx, double dy) {
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

    @Override
    public boolean contains(double x, double y) {
        if(sizeY ==0){
            return false;
        }
        double scaleFactor = size/sizeY;


        return Math.hypot((x-cx)/scaleFactor,y-cy) <= sizeY/2;
    }

    @Override
    public void translate(double normX, double normY, double dX, double dY) {
        left += dX;
        top += dY;
        initLeft +=dX;
        initTop +=dY;
        cx = left +size/2;
        cy = top +sizeY/2;
    }
}
