package com.example.assignment3;

import javafx.scene.paint.Color;

import java.util.Comparator;

public class MiniDrawingView extends DrawingView{
    public MiniDrawingView(double height, double width, double documentHeight, double documentWidth) {
        super(height, width, documentHeight, documentWidth);
        this.setStyle("-fx-background-color: grey");
    }

    @Override
    public void draw(){
        gc.clearRect(0,0, myCanvas.getWidth(), myCanvas.getHeight());
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(iModel.viewLeft/ iModel.documentWidth*width, iModel.viewTop/ iModel.documentLength*height, iModel.viewWidth/ iModel.documentWidth*width, iModel.viewHeight/ iModel.documentLength*height);
        model.getShapes().sort(Comparator.comparingInt(XShape::getZ));
        model.getShapes().forEach(item ->{
            switch(item){
                case XSquare square -> super.drawSquare(square.left * width , square.top * height, square.size * Math.min(width, height), square.color);
                case XRectangle rectangle -> super.drawRectangle(rectangle.left * width , rectangle.top * height, rectangle.size * width, rectangle.sizeY *height, rectangle.color);
                case XCircle circle -> super.drawCircle(circle.left * width , circle.top * height, circle.size * Math.min(width, height), circle.color);
                case XOval oval -> super.drawOval(oval.left * width , oval.top * height, oval.size * width, oval.sizeY *height, oval.color);
                case XLine line -> super.drawLine(line.x1 * width, line.y1 * height, line.x2 * width, line.y2 *height, 3, line.color);
                case XShape XS -> System.out.println("An item did not get drawn");
            }
        });
        if(iModel.getSelectedShape()!= null){
            this.makeBoundingBox();
        }
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(3);
        gc.strokeRect(iModel.viewLeft/ iModel.documentWidth*width, iModel.viewTop/ iModel.documentLength*height, iModel.viewWidth/ iModel.documentWidth*width, iModel.viewHeight/ iModel.documentLength*height);

    }
    @Override
    public void iModelChanged(){
        draw();
    }
    @Override
    public void setController(DrawingController controller) {
        myCanvas.setOnMousePressed(e -> controller.handlePress(e.getX()/width, e.getY()/height, e));
        myCanvas.setOnMouseDragged(e -> controller.handleDrag(e.getX()/width, e.getY()/height, e));
        myCanvas.setOnMouseReleased(e -> controller.handleRelease(e.getX()/width, e.getY()/height, e ));
    }


    private void makeBoundingBox(){
        switch (iModel.getSelectedShape()){
            case XSquare square -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);

                gc.strokeRect(square.left * width, square.top * height, square.size * width, square.size* height);
            }
            case XRectangle rectangle -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeRect(rectangle.left * width, rectangle.top * height, rectangle.size * width, rectangle.sizeY* height);
            }
            case XCircle circle -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeRect(circle.left * width, circle.top * height, circle.size * width, circle.size* height);

            }
            case XOval oval -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeRect(oval.left * width, oval.top * height, oval.size * width, oval.sizeY* height);

            }
            case XLine line -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeLine(line.x1 * width, line.y1 * height, line.x2 * width, line.y2* height);

            }
            case XShape XS -> System.out.println("An item did not get drawn");
        }
    }
}
