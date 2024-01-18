package com.example.assignment3;

import javafx.scene.paint.Color;

import java.util.Comparator;

public class MiniDrawingView extends DrawingView{
    /**
     * The constructor method that creates
     * @param height The height of the mini view
     * @param width The width of the mini view
     * @param documentHeight The height of the entire canvas
     * @param documentWidth The width of the entire canvas
     */
    public MiniDrawingView(double height, double width, double documentHeight, double documentWidth) {
        super(height, width, documentHeight, documentWidth);
        this.setStyle("-fx-background-color: grey");
    }

    /**
     * Draw all the shapes and the yellow viewport
     */
    @Override
    public void draw(){
        gc.clearRect(0,0, myCanvas.getWidth(), myCanvas.getHeight());
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(iModel.viewLeft/ iModel.documentWidth*width, iModel.viewTop/ iModel.documentLength*height, iModel.viewWidth/ iModel.documentWidth*width, iModel.viewHeight/ iModel.documentLength*height);
        model.getShapes().sort(Comparator.comparingInt(XShape::getZ));
        model.getShapes().forEach(item ->{
            switch(item){
                case XSquare square -> super.drawSquare(square.left * width , square.top * height, square.getSizeX() * Math.min(width, height), square.color);
                case XRectangle rectangle -> super.drawRectangle(rectangle.left * width , rectangle.top * height, rectangle.getSizeX() * width, rectangle.getSizeY() *height, rectangle.color);
                case XCircle circle -> super.drawCircle(circle.left * width , circle.top * height, circle.getSizeX() * Math.min(width, height), circle.color);
                case XOval oval -> super.drawOval(oval.left * width , oval.top * height, oval.getSizeX() * width, oval.getSizeY() *height, oval.color);
                case XLine line -> super.drawLine(line.left * width, line.top * height, line.right * width, line.bottom *height, 3, line.color);
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

    /**
     * When the iModel changes redraw everything
     */
    @Override
    public void iModelChanged(){
        draw();
    }

    /**
     * Sets the controller and when events occur it notifies the controller
     * @param controller The controller for the mini view
     */
    @Override
    public void setController(DrawingController controller) {
        myCanvas.setOnMousePressed(e -> controller.handlePress(e.getX()/width, e.getY()/height, e));
        myCanvas.setOnMouseDragged(e -> controller.handleDrag(e.getX()/width, e.getY()/height, e));
        myCanvas.setOnMouseReleased(e -> controller.handleRelease(e.getX()/width, e.getY()/height, e ));
    }

    /**
     * Makes the bounding box in the mini view for the selected shape
     */
    private void makeBoundingBox(){
        switch (iModel.getSelectedShape()){
            case XSquare square -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);

                gc.strokeRect(square.left * width, square.top * height, square.getSizeX() * width, square.getSizeY() * height);
            }
            case XRectangle rectangle -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeRect(rectangle.left * width, rectangle.top * height, rectangle.getSizeX() * width, rectangle.getSizeY()* height);
            }
            case XCircle circle -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeRect(circle.left * width, circle.top * height, circle.getSizeX() * width, circle.getSizeY() * height);

            }
            case XOval oval -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeRect(oval.left * width, oval.top * height, oval.getSizeX() * width, oval.getSizeY()* height);

            }
            case XLine line -> {
                gc.setLineWidth(1);
                gc.setStroke(Color.RED);
                gc.strokeLine(line.left * width, line.top * height, line.right * width, line.bottom* height);

            }
            case XShape XS -> System.out.println("An item did not get drawn");
        }
    }
}
