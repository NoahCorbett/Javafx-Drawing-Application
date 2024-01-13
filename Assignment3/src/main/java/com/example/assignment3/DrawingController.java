package com.example.assignment3;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingController {
    DrawingModel model;
    InteractionModel iModel;
    double prevX, prevY;
    protected State curState;
    protected enum State{
        READY, RESIZE, SELECTED, DRAG
    }

    /**
     * The constructor method for the drawing controller
     */
    public DrawingController(){
        curState = State.READY;

    }

    /**
     * Handles the mouse press event
     * @param normX Where the mouse was clicked in the x-axis
     * @param normY Where the mouse was clicked in the y-axis
     * @param e The mouse event
     */
    public void handlePress(double normX, double normY, MouseEvent e) {
        normX += iModel.viewLeft/ iModel.documentWidth;
        normY += iModel.viewTop/ iModel.documentLength;
        prevX = normX;
        prevY = normY;

        switch(curState){
            case READY -> {
                if(model.whichShape(normX, normY) != null) {
                    XShape selectedShape= model.whichShape(normX, normY);
                    model.nextZ += 1;
                    selectedShape.setZ(model.nextZ);
                    iModel.setSelectedShape(selectedShape);

                    curState = State.SELECTED;
                }else if(e.getButton() == MouseButton.SECONDARY){
                    curState = State.DRAG;
                }
            }
            case SELECTED -> {
                if(iModel.withinResizeTab(normX, normY)) {
                    curState = State.RESIZE;
                }else if(e.getButton() == MouseButton.SECONDARY) {
                    iModel.unSelect();
                    curState = State.DRAG;
                }else{
                    if (model.whichShape(normX, normY) == null) {
                        iModel.unSelect();
                        curState = State.READY;
                    } else {
                        XShape selectedShape = model.whichShape(normX, normY);
                        selectedShape.setZ(model.nextZ);
                        model.nextZ++;
                        iModel.setSelectedShape(selectedShape);
                    }
                }
            }
        }
    }

    /**
     * Handles the mouse drag event
     * @param normX Where the mouse was dragged to in the x-axis
     * @param normY Where the mouse was dragged to in the y-axis
     * @param e The mouse event
     */
    public void handleDrag(double normX, double normY, MouseEvent e){
        normX += iModel.viewLeft/ iModel.documentWidth;
        normY += iModel.viewTop/ iModel.documentLength;
        double dX = normX - prevX;
        double dY = normY - prevY;
        prevX = normX;
        prevY = normY;

        switch (curState){
            case READY -> {
                if (model.whichShape(normX, normY) == null) {
                    switch (iModel.getCurrentTool()) {
                        case SQUARE -> {
                            iModel.setSelectedShape(model.AddSquare(normX, normY, 0, iModel.getSelectedColour()));
                        }
                        case RECTANGLE -> {
                            iModel.setSelectedShape(model.AddRectangle(normX, normY, 0, 0, iModel.getSelectedColour()));
                        }
                        case CIRCLE -> {
                            iModel.setSelectedShape(model.AddCircle(normX, normY, 0, iModel.getSelectedColour()));
                        }
                        case OVAL -> {
                            iModel.setSelectedShape(model.AddOval(normX, normY, 0, 0, iModel.getSelectedColour()));
                        }
                        case LINE -> {
                            iModel.setSelectedShape(model.AddLine(normX, normY, 0, 0, 5/ iModel.viewWidth, iModel.getSelectedColour()));
                        }
                    }
                    curState = State.RESIZE;
                }
            }
            case RESIZE -> {

                iModel.resizeShape(dX, dY);
            }
            case SELECTED -> {
                iModel.moveShape(dX, dY);
            }
            case DRAG -> {
                if(e.getButton() == MouseButton.SECONDARY) {
                    iModel.setViewLocation(iModel.viewLeft - dX * iModel.viewWidth, iModel.viewTop - dY * iModel.viewHeight);
                }
            }
        }
    }

    /**
     * Handles the mouse release event
     * @param normX Where the mouse was released in the x-axis
     * @param normY Where the mouse was released in the y-axis
     * @param e The mouse event
     */
    public void handleRelease(double normX, double normY, MouseEvent e) {
        switch (curState){
            case RESIZE -> {
                curState = State.SELECTED;
            }
            case DRAG -> {
                curState = State.READY;
            }
        }
    }
    public void handleKeyEvent(KeyEvent e){
        switch(curState){
            case SELECTED -> {
                if(e.getCode() == KeyCode.DELETE){
                    model.getShapes().remove(iModel.getSelectedShape());
                    iModel.unSelect();
                }
            }
        }

    }

    /**
     * Handles the color select and passes it off to the iModel
     * @param color The new color selected
     */
    public void handleColorSelect(Color color){
        iModel.setSelectedColour(color);
    }

    /**
     * Handles the tool select and passes it off to the iMOdel
     * @param tool The new shape that has been selected
     */
    public void handleToolSelect(InteractionModel.Tool tool){
        iModel.setCurrentTool(tool);
    }

    /**
     * When the window size has changed passes off parameters to the iModel
     * @param width The width of the new window size
     * @param height The height of the new window size
     */
    public void windowSizeChanged(double width, double height) {
        iModel.setViewSize(width, height);
    }

    /**
     * Sets the model
     * @param model The model of the drawing system
     */
    public void setModel(DrawingModel model) {
        this.model = model;
    }
    /**
     * Sets the iModel
     * @param iModel The iModel of the drawing system
     */
    public void setIModel(InteractionModel iModel) {
        this.iModel = iModel;
    }
}