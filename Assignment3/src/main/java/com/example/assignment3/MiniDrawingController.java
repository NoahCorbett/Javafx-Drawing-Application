package com.example.assignment3;

import javafx.scene.input.MouseEvent;

public class MiniDrawingController extends DrawingController{
    public MiniDrawingController(){
        super();
    }


    @Override
    public void handlePress(double normX, double normY, MouseEvent e){
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

    @Override
    public void handleDrag(double normX, double normY, MouseEvent e) {
        double dX = normX - prevX;
        double dY = normY - prevY;
        prevX = normX;
        prevY = normY;

        switch (curState) {
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
                            iModel.setSelectedShape(model.AddLine(normX, normY, 0, 0, 5/ iModel.documentWidth, iModel.getSelectedColour()));
                        }
                    }
                    curState = State.RESIZE;
                }
            }
            case RESIZE -> {

                iModel.resizeShape(dX, dY);
            }
            case SELECTED -> {
                if (iModel.viewPortSelected) {
                    iModel.setViewLocation(iModel.viewLeft + dX * iModel.documentWidth, iModel.viewTop + dY * iModel.documentLength);
                } else {
                    iModel.moveShape(dX, dY);
                }
            }
        }
    }
    @Override
    public void handleRelease(double normX, double normY, MouseEvent e) {
        switch (curState) {
            case SELECTED -> {
                if (iModel.viewPortSelected) {
                    iModel.viewPortSelected = false;
                }
            }
        }
        super.handleRelease(normX -iModel.viewLeft/ iModel.documentWidth, normY - iModel.viewTop/ iModel.documentLength, e);
    }

    private boolean inViewPort(double x, double y){
        x=x* iModel.documentWidth;
        y=y* iModel.documentLength;

        return x >= iModel.viewLeft && x <= iModel.viewLeft + iModel.viewWidth && y >= iModel.viewTop && y <= iModel.viewTop + iModel.viewHeight;

    }
}
