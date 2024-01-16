package com.example.assignment3;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InteractionModel {
    /** A list of subscribers that listen to the interaction model */
    ArrayList<DrawingIModelSubscriber> subs;

    /** Number indicating the width and height of the view */
    double viewWidth, viewHeight;

    /** The currently selected color in the iModel */
    Color selectedColour;

    /** The currently selected shape object in the iModel */
    XShape selectedShape;

    /** Two numbers that indicate where the top left corner of the current view window */
    double viewLeft, viewTop;

    /** Two numbers that indicate the dimension of the whole document */
    double documentWidth, documentLength;

    /** Boolean that keeps track if the view port is selected */
    boolean viewPortSelected;

    /** Create the enum tool to keep track of what shape to create if a new shape needs creating */
    public enum Tool{
        RECTANGLE, SQUARE, OVAL, CIRCLE, LINE
    }
    /** The currently selected shape that will be created if a new shape needs creating */
    Tool currentTool;

    /**
     * The constructor method for the interaction model
     */
    InteractionModel(){
        subs = new ArrayList<>();
        currentTool = Tool.SQUARE;
        selectedColour = Color.AQUA;
        viewPortSelected = false;
        viewLeft =0;
        viewTop=0;
        notifySubscribers();
    }

    /**
     * Move the selected shape
     * @param dX The amount to move the shape in the x direction
     * @param dY The amount to move the shape in the y direction
     */
    public void moveShape(double dX, double dY) {
        selectedShape.translate(dX, dY);
        notifySubscribers();
    }

    /**
     * Resize the shape
     * @param dX The amount the shape changes in the x direction
     * @param dY The amount the shape changes in the y direction
     */
    public void resizeShape(double dX, double dY) {
        selectedShape.resize(dX, dY);
        notifySubscribers();
    }

    /**
     * Returns the selected color in the interaction model
     * @return the selected color
     */
    public Color getSelectedColour() {
        return selectedColour;
    }

    /**
     * Sets the color in the interaction model
     * @param selectedColour The color to be set as the selected color
     */
    public void setSelectedColour(Color selectedColour) {
        this.selectedColour = selectedColour;
        notifySubscribers();
    }

    /**
     * Get the type of the selected shape
     * @return The type of the selected shape
     */
    public Tool getCurrentTool() {
        return currentTool;
    }

    /**
     * Changes the selected tool to the current shape
     * @param currentTool The tool that matches with the newly selected shape
     */
    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
        notifySubscribers();
    }

    /**
     * Set where the drawing view is located on the canvas
     * @param viewLeft The new left side of the view
     * @param viewTop The new top side of the view
     */
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

    /**
     * Set the size for the view size
     * @param viewWidth The new size of the view in the x-axis
     * @param viewHeight The new size of the view in the y-axis
     */
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
    /**
     * Sets the size for the entire document
     * @param documentWidth The width of the entire document
     * @param documentLength The height of the entire document
     */
    public void setDocumentSize(double documentWidth, double documentLength){
        this.documentWidth = documentWidth;
        this.documentLength = documentLength;
    }

    /**
     * Returns the selected shape
     * @return the selected shape
     */
    public XShape getSelectedShape() {
        return selectedShape;
    }

    /**
     * Sets the selected shape
     * @param selectedShape the new shape that is selected
     */
    public void setSelectedShape(XShape selectedShape) {
        this.selectedShape = selectedShape;
        notifySubscribers();
    }

    /**
     * Unselect the current shape
     */
    public void unSelect(){
        selectedShape = null;
        notifySubscribers();
    }

    /**
     * Takes an x, y coordinates and sees if it is inside the resize tab for the currently selected shape
     * @param normX The x coordinate for the click
     * @param normY The y coordinate for the click
     * @return True if the click is inside the resize tab, false otherwise
     */
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
                double cx = circle.left + circle.sizeX;
                double cy = circle.top + circle.sizeX;
                return Math.hypot(((normX - cx) *viewWidth) ,((normY - cy) *viewWidth)) <= 1;
            }
            case XOval oval ->{
                double cx = oval.left + oval.sizeX;
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

    /**
     * Add subscriber that needs updating whenever the interaction model changes
     * @param sub A subscriber that needs updating whenever the interaction model changes
     */
    public void addSub(DrawingIModelSubscriber sub){
        subs.add(sub);
    }

    /**
     * Notifies subscriber when the interaction model has changed
     */
    public void notifySubscribers(){
        subs.forEach(s -> s.iModelChanged());
    }

}
