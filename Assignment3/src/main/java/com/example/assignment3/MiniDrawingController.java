package com.example.assignment3;

import javafx.scene.input.MouseEvent;

public class MiniDrawingController extends DrawingController{
    /**
     * Constructor method for mini drawing controller
     */
    public MiniDrawingController(){
        super();
    }

    /**
     * Handles the mouse press event
     * @param normX Where the mouse was clicked in the x-axis
     * @param normY Where the mouse was clicked in the y-axis
     * @param e The mouse event
     */
    @Override
    public void handlePress(double normX, double normY, MouseEvent e){
        // If the view port is selected unselect the current shape then select the view port and move the state to selected
        if (inViewPort(normX, normY)){
            prevX = normX;
            prevY = normY;
            iModel.unSelect();
            iModel.viewPortSelected = true;
            curState = State.SELECTED;
        }else {
            super.handlePress(normX -iModel.viewLeft/ iModel.documentWidth, normY - iModel.viewTop/ iModel.documentLength, e);
        }

    }
    /**
     * Handles the mouse drag event
     * @param normX Where the mouse was dragged to in the x-axis
     * @param normY Where the mouse was dragged to in the y-axis
     * @param e The mouse event
     */
    @Override
    public void handleDrag(double normX, double normY, MouseEvent e) {
        double dX = normX - prevX;
        double dY = normY - prevY;
        prevX = normX;
        prevY = normY;

        switch (curState) {
            case READY -> {
                if (model.whichShape(normX, normY) == null) {
                    // If in the ready state and there are no shapes the coordinates then add a shape
                    switch (iModel.getCurrentTool()) {
                        case SQUARE -> iModel.setSelectedShape(model.AddSquare(normX, normY, iModel.getSelectedColour()));
                        case RECTANGLE -> iModel.setSelectedShape(model.AddRectangle(normX, normY, iModel.getSelectedColour()));
                        case CIRCLE -> iModel.setSelectedShape(model.AddCircle(normX, normY, iModel.getSelectedColour()));
                        case OVAL -> iModel.setSelectedShape(model.AddOval(normX, normY, iModel.getSelectedColour()));
                        case LINE -> iModel.setSelectedShape(model.AddLine(normX, normY, 5/ iModel.documentWidth, iModel.getSelectedColour()));
                    }
                    curState = State.RESIZE;
                }
            }
            case RESIZE ->
                    // If in the resize state then resize the shape
                    iModel.resizeShape(dX, dY);

            case SELECTED -> {
                // If the viewport is selected then move the viewport else move the shape
                if (iModel.viewPortSelected) {
                    iModel.setViewLocation(iModel.viewLeft + dX * iModel.documentWidth, iModel.viewTop + dY * iModel.documentLength);
                } else {
                    iModel.moveShape(dX, dY);
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
    @Override
    public void handleRelease(double normX, double normY, MouseEvent e) {
        // In the selected state if the viewport is selected unselect it else pass off to the super class
        if (curState == State.SELECTED) {
            if (iModel.viewPortSelected) {
                iModel.viewPortSelected = false;
            }
        }
        super.handleRelease(normX -iModel.viewLeft/ iModel.documentWidth, normY - iModel.viewTop/ iModel.documentLength, e);
    }

    /**
     * Is the coordinate in the view port
     * @param x Where the mouse was clicked in the x-axis
     * @param y Where the mouse was clicked in the y-axis
     * @return True if the coordinate is in the view port false otherwise
     */
    private boolean inViewPort(double x, double y){
        x=x* iModel.documentWidth;
        y=y* iModel.documentLength;

        return x >= iModel.viewLeft && x <= iModel.viewLeft + iModel.viewWidth && y >= iModel.viewTop && y <= iModel.viewTop + iModel.viewHeight;

    }
}
