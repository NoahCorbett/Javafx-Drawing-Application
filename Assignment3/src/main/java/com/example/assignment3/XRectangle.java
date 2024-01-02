package com.example.assignment3;

import javafx.scene.paint.Color;

public class XRectangle extends XShape{
    double sizeY;
    public XRectangle(double left, double top, double sizeX, double sizeY, Color color, int z){
        super(left, top, sizeX, color, z);
        this.sizeY = sizeY;
    }


    @Override
    public void resize(double normX, double normY,double dx, double dy) {
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

    @Override
    public boolean contains(double x, double y) {
        return x >= left && x <= left + size && y >= top && y <= top + sizeY;
    }

    @Override
    public void translate(double normX, double normY, double dX, double dY) {
        initLeft +=dX;
        initTop += dY;
        left += dX;
        top += dY;
    }
}
