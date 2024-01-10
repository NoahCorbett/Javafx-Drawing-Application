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

    public void windowSizeChanged(double width, double height) {
        iModel.setViewSize(width, height);
    }


    protected enum State{
        READY, RESIZE, SELECTED, DRAG
    }
    protected State curState;

    public DrawingController(){
        curState = State.READY;

    }

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
                iModel.moveShape(normX, normY, dX, dY);
            }
            case DRAG -> {
                if(e.getButton() == MouseButton.SECONDARY) {
                    iModel.setViewLocation(iModel.viewLeft - dX * iModel.viewWidth, iModel.viewTop - dY * iModel.viewHeight);
                }
            }
        }
    }
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




    public void handleColorSelect(Color color){
        iModel.setSelectedColour(color);
    }
    public void handleToolSelect(InteractionModel.Tool tool){
        iModel.setCurrentTool(tool);
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }

    public void setIModel(InteractionModel iModel) {
        this.iModel = iModel;
    }
}