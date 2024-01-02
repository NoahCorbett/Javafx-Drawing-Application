package com.example.assignment3;

import javafx.scene.paint.Color;

public class XLine extends XShape{
    double x1,y1,x2, y2, acceptableSize;

    public XLine(double left, double top, double sizeX, double sizeY, double acceptableSize, Color color, int z) {
        super(left, top, sizeX, color, z);
        x1 = left;
        y1 = top;
        x2 = left + sizeX;
        y2 = top + sizeY;
        this.acceptableSize = acceptableSize;


    }

    @Override
    public void resize(double normX, double normY, double dx, double dy) {
        x2 += dx;
        y2 += dy;


    }

    @Override
    public boolean contains(double x, double y) {
        double length = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
        double A = (y1-y2) / length;
        double B = (x2-x1) / length;
        double C = -1 * ((y1-y2) * x1 + (x2-x1) * y1) / length;


        return Math.abs(A*x + B*y + C) < acceptableSize && x > Math.min(x1, x2)- acceptableSize && x <Math.max(x1,x2)+acceptableSize && y > Math.min(y1, y2) -acceptableSize && y < Math.max(y1,y2) +acceptableSize;
    }

    @Override
    public void translate(double normX, double normY, double dX, double dY) {
        x1 += dX;
        y1 += dY;
        x2 += dX;
        y2 += dY;
    }
}
