package com.example.assignment3;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InteractionModel {
    ArrayList<DrawingIModelSubscriber> subs;
    double viewWidth, viewHeight;
    Color selectedColour;
    XShape selectedShape;
    double viewLeft, viewTop;
    double documentWidth, documentLength;
    boolean viewPortSelected;

    public void moveShape(double normX, double normY, double dX, double dY) {
        selectedShape.translate(normX, normY, dX, dY);
        notifySubscribers();
    }

    public enum Tool{
        RECTANGLE, SQUARE, OVAL, CIRCLE, LINE
    }
    Tool currentTool;

    InteractionModel(){
        subs = new ArrayList<>();
        currentTool = Tool.SQUARE;
        selectedColour = Color.AQUA;
        viewPortSelected = false;
        viewLeft =0;
        viewTop=0;
        notifySubscribers();
    }

    public void addSub(DrawingIModelSubscriber sub){
        subs.add(sub);
    }
    public void notifySubscribers(){
        subs.forEach(s -> s.iModelChanged());
    }

    public void resizeShape(double normX, double normY, double dX, double dY) {
        selectedShape.resize(normX, normY, dX, dY);
        notifySubscribers();
    }

    public Color getSelectedColour() {
        return selectedColour;
    }

    public void setSelectedColour(Color selectedColour) {
        this.selectedColour = selectedColour;
        notifySubscribers();
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
        notifySubscribers();
    }
    public void setViewLocation(double viewLeft, double viewTop){
        this.viewLeft = viewLeft;
        this.viewTop = viewTop;
        if(this.viewLeft<0){
            this.viewLeft=0;
        }
        if(this.viewLeft+this.viewWidth>this.documentWidth){
            this.viewLeft= this.documentWidth-this.viewWidth;
        }
        if(this.viewTop+this.viewHeight>this.documentLength){
            this.viewTop= this.documentLength-this.viewHeight;
        }
        if(this.viewTop<0){
            this.viewTop=0;
        }
        notifySubscribers();
    }
    public void setViewSize(double viewWidth, double viewHeight) {
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
        if(this.viewLeft+this.viewWidth>this.documentWidth){
            this.viewLeft= this.documentWidth-this.viewWidth;
        }
        if(this.viewTop+this.viewHeight>this.documentLength){
            this.viewTop= this.documentLength-this.viewHeight;
        }
        notifySubscribers();
    }
    public void setDocumentSize(double documentWidth, double documentLength){
        this.documentWidth = documentWidth;
        this.documentLength = documentLength;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setSelectedShape(XShape selectedShape) {
        this.selectedShape = selectedShape;
        notifySubscribers();
    }

    public XShape getSelectedShape() {
        return selectedShape;
    }
    public void unSelect(){
        selectedShape = null;
        notifySubscribers();
    }

    public boolean withinResizeTab(double normX, double normY){
        switch (getSelectedShape()){
            case XSquare square -> {
                double cx = square.left + square.size;
                double cy = square.top + square.size;
                return Math.hypot(((normX - cx) *viewWidth) ,((normY - cy) *viewWidth)) <= 1;
            }
            case XRectangle rectangle ->{
                double cx = rectangle.left + rectangle.size;
                double cy = rectangle.top + rectangle.sizeY;
                return Math.hypot(((normX - cx) *viewWidth) ,((normY - cy) *viewWidth)) <= 1;
            }
            case XCircle circle -> {
                double cx = circle.left + circle.size;
                double cy = circle.top + circle.size;
                return Math.hypot(((normX - cx) *viewWidth) ,((normY - cy) *viewWidth)) <= 1;
            }
            case XOval oval ->{
                double cx = oval.left + oval.size;
                double cy = oval.top + oval.sizeY;
                return Math.hypot(((normX - cx) *viewWidth) ,((normY - cy) *viewWidth)) <= 1;
            }
            case XLine line ->{
                double cx = line.x2;
                double cy = line.y2;
                return Math.hypot(((normX - cx) *viewWidth) ,((normY - cy) *viewWidth)) <= 1;
            }
            case XShape xShape->{
                return false;
            }
        }


    }

}
