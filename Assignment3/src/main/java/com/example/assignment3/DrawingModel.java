package com.example.assignment3;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;

public class DrawingModel {
    /** A list of subscribers that are notified when the model has changed */
    ArrayList<DrawingModelSubscriber> subs;

    /** The list of shapes created */
    ArrayList<XShape> shapes;

    /** Keeps track of the next number in the z axis so new shapes/selected shapes are put to the front */
    int nextZ;

    /**
     * The constructor method for the drawing model
     */
    public DrawingModel(){
        subs = new ArrayList<>();
        shapes = new ArrayList<>();
        nextZ = 0;
    }

    /**
     * Add a new subscriber that listens to when the model is updated
     * @param sub An object that will change when the model is updated
     */
    public void addSub(DrawingModelSubscriber sub){
        subs.add(sub);
    }

    /**
     * Notifies all the subscribers listening to the model
     */
    public void notifySubscribers(){
        subs.forEach(DrawingModelSubscriber::modelChanged);
    }

    /**
     * Returns the list of shapes in the model
     * @return the list of shapes in the model
     */
    public ArrayList<XShape> getShapes() {
        return shapes;
    }

    /**
     * Is there a shape a coordinate x, y
     * @param x the horizontal value of the coordinate
     * @param y the vertical value of the coordinate
     * @return the shape (with the highest z value) at that coordinate. Null if there is no shape
     */
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

    /**
     * Return a square based on the parameters
     * @param normX The initial position of the x-axis of the square
     * @param normY The initial position of the y-axis of the square
     * @param color The color of the square
     * @return Return the square
     */
    public XSquare AddSquare(double normX, double normY, Color color) {
        XSquare square =new XSquare(normX, normY, color, nextZ);
        shapes.add(square);
        nextZ+=1;
        notifySubscribers();
        return square;
    }

    /**
     * Return a rectangle based on the parameters
     * @param normX The initial position of the x-axis of the rectangle
     * @param normY The initial position of the y-axis of the recta
     * @param color The color of the rectangle
     * @return Return the rectangle
     */
    public XRectangle AddRectangle(double normX, double normY, Color color) {
        XRectangle rectangle = new XRectangle(normX, normY, color, nextZ);
        shapes.add(rectangle);
        nextZ+=1;
        notifySubscribers();
        return rectangle;
    }

    /**
     * Return a circle based on the parameters
     * @param normX The initial position of the x-axis of the circle
     * @param normY The initial position of the y-axis of the circle
     * @param color The color of the circle
     * @return Return the circle
     */
    public XCircle AddCircle(double normX, double normY, Color color) {
        XCircle circle = new XCircle(normX,normY, color, nextZ);
        shapes.add(circle);
        nextZ+=1;
        notifySubscribers();
        return circle;
    }
    /**
     * Return an oval based on the parameters
     * @param normX The initial position of the x-axis of the oval
     * @param normY The initial position of the y-axis of the oval
     * @param color The color of the oval
     * @return Return the oval
     */
    public XOval AddOval(double normX, double normY, Color color) {
        XOval oval = new XOval(normX, normY, color, nextZ);
        shapes.add(oval);
        nextZ+=1;
        notifySubscribers();
        return oval;
    }

    /**
     * Return a line based on the parameters
     * @param normX The initial position of the x-axis of the line
     * @param normY The initial position of the y-axis of the line
     * @param acceptableSize The size of the area around the line that will register that the line is clicked
     * @param color The color of the line
     * @return Return the line
     */
    public XLine AddLine(double normX, double normY, double acceptableSize, Color color){
        XLine line = new XLine(normX, normY, acceptableSize, color, nextZ);
        shapes.add(line);
        nextZ+=1;
        notifySubscribers();
        return line;
    }
}
