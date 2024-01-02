package com.example.assignment3;

import javafx.scene.paint.Color;

public abstract class XShape {
    double left, top, size;
    protected double initLeft, initTop;
    Color color;
    int z;


    public XShape() {
        left = 0;
        top = 0;
        size = 0;
        color = Color.BLACK;
    }
    public XShape(double newLeft, double newTop, double newSize, Color newColor, int z) {
        initLeft = newLeft;
        initTop = newTop;
        left = newLeft;
        top = newTop;
        size = newSize;
        color = newColor;
        this.z = z;
    }

    public abstract void resize(double normX, double normY, double dx, double dy);

    public abstract boolean contains(double x, double y);

    public int getZ() {
        return z;
    }
    public void setZ(int z){
        this.z = z;
    }

    public abstract void translate(double normX, double normY, double dX, double dY);
}
