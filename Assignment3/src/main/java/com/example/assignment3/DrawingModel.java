package com.example.assignment3;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;

public class DrawingModel {
    ArrayList<DrawingModelSubscriber> subs;
    ArrayList<XShape> shapes;
    int nextZ;

    public DrawingModel(){
        subs = new ArrayList<>();
        shapes = new ArrayList<>();
        nextZ = 0;
    }

    public void addSub(DrawingModelSubscriber sub){
        subs.add(sub);
    }
    public void notifySubscribers(){
        subs.forEach(s -> s.modelChanged());
    }

    public ArrayList<XShape> getShapes() {
        return shapes;
    }

    public XShape whichShape(double x, double y) {
        shapes.sort(Comparator.comparingInt(XShape::getZ).reversed());
        XShape found = null;
        for (XShape s : shapes) {
            if (s.contains(x,y)) {
                found = s;
                return found;
            }
        }
        return null;
    }

    public XSquare AddSquare(double normX, double normY, double size, Color color) {
        XSquare square =new XSquare(normX, normY, size, color, nextZ);
        shapes.add(square);
        nextZ+=1;
        notifySubscribers();
        return square;
    }
    public XRectangle AddRectangle(double normX, double normY, double sizeX, double sizeY, Color color) {
        XRectangle rectangle = new XRectangle(normX, normY, sizeX, sizeY, color, nextZ);
        shapes.add(rectangle);
        nextZ+=1;
        notifySubscribers();
        return rectangle;
    }

    public XCircle AddCircle(double normX, double normY, double size, Color selectedColour) {
        XCircle circle = new XCircle(normX,normY, size, selectedColour, nextZ);
        shapes.add(circle);
        nextZ+=1;
        notifySubscribers();
        return circle;
    }
    public XOval AddOval(double normX, double normY, double sizeX, double sizeY, Color color) {
        XOval oval = new XOval(normX, normY, sizeX, sizeY, color, nextZ);
        shapes.add(oval);
        nextZ+=1;
        notifySubscribers();
        return oval;
    }
    public XLine AddLine(double normX, double normY, double sizeX, double sizeY, double acceptableSize, Color color){
        XLine line = new XLine(normX, normY, sizeX, sizeY, acceptableSize, color, nextZ);
        shapes.add(line);
        nextZ+=1;
        notifySubscribers();
        return line;
    }
}
