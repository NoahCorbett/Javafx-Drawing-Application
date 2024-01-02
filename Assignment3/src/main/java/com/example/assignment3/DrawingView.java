package com.example.assignment3;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Comparator;

public class DrawingView extends StackPane implements DrawingIModelSubscriber, DrawingModelSubscriber{

    Canvas myCanvas;
    GraphicsContext gc;
    DrawingModel model;
    InteractionModel iModel;
    double width, height, worldWidth, worldHeight;


    public DrawingView(double height, double width, double documentHeight, double documentWidth){


        this.width = width;
        this.height = height;
        worldWidth = documentWidth;
        worldHeight = documentHeight;
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        this.getChildren().add(myCanvas);

    }

    public void draw(){
        gc.clearRect(0,0, myCanvas.getWidth(), myCanvas.getHeight());
        model.getShapes().sort(Comparator.comparingInt(XShape::getZ));
        model.getShapes().forEach(item ->{
            switch(item){
                case XSquare square -> this.drawSquare(square.left * worldWidth- iModel.viewLeft,square.top * worldHeight- iModel.viewTop, square.size * Math.min(worldWidth,worldHeight) , square.color);
                case XRectangle rectangle -> this.drawRectangle(rectangle.left * worldWidth- iModel.viewLeft, rectangle.top * worldHeight- iModel.viewTop, rectangle.size * worldWidth , rectangle.sizeY * worldHeight, rectangle.color);
                case XCircle circle -> this.drawCircle(circle.left * worldWidth- iModel.viewLeft,circle.top * worldHeight- iModel.viewTop, circle.size * Math.min(worldWidth,worldHeight) , circle.color);
                case XOval oval -> this.drawOval(oval.left * worldWidth- iModel.viewLeft, oval.top * worldHeight- iModel.viewTop, oval.size * worldWidth , oval.sizeY * worldHeight, oval.color);
                case XLine line -> this.drawLine(line.x1 * worldWidth- iModel.viewLeft, line.y1 * worldHeight- iModel.viewTop, line.x2 * worldWidth- iModel.viewLeft, line.y2 * worldHeight- iModel.viewTop, 5,line.color);
                case XShape XS -> System.out.println("An item did not get drawn");
            }
        });
        if(iModel.getSelectedShape()!= null){
            makeBoundingBox();
        }
    }

    private void makeBoundingBox() {
        switch (iModel.getSelectedShape()){
            case XSquare square -> {
                gc.setLineWidth(2);
                gc.setStroke(Color.RED);
                gc.setLineDashes(5);
                gc.strokeRect(square.left * worldWidth- iModel.viewLeft , square.top * worldHeight- iModel.viewTop, square.size * worldWidth , square.size * worldHeight);


                gc.setLineDashes(0);
                gc.setFill(Color.YELLOW);
                gc.fillOval((square.left+ square.size) * worldWidth- iModel.viewLeft-5, (square.top+ square.size)* worldHeight- iModel.viewTop-5,10, 10);
                gc.strokeOval((square.left+ square.size) * worldWidth- iModel.viewLeft-5, (square.top+ square.size)* worldHeight- iModel.viewTop-5,10, 10);
            }


            case XRectangle rectangle -> {
                gc.setLineWidth(2);
                gc.setStroke(Color.RED);
                gc.setLineDashes(5);
                gc.strokeRect(rectangle.left * worldWidth- iModel.viewLeft, rectangle.top * worldHeight- iModel.viewTop, rectangle.size * worldWidth, rectangle.sizeY* worldHeight);

                gc.setLineDashes(0);
                gc.setFill(Color.YELLOW);
                gc.fillOval((rectangle.left+ rectangle.size) * worldWidth- iModel.viewLeft-5, (rectangle.top+ rectangle.sizeY)* worldHeight- iModel.viewTop-5,10, 10);
                gc.strokeOval((rectangle.left+ rectangle.size) * worldWidth- iModel.viewLeft-5, (rectangle.top+ rectangle.sizeY)* worldHeight- iModel.viewTop-5,10, 10);
            }


            case XCircle circle -> {
                gc.setLineWidth(2);
                gc.setStroke(Color.RED);
                gc.setLineDashes(5);
                gc.strokeRect(circle.left  * worldWidth- iModel.viewLeft, circle.top *worldHeight- iModel.viewTop, circle.size * worldWidth, circle.size* worldHeight);

                gc.setLineDashes(0);
                gc.setFill(Color.YELLOW);
                gc.fillOval((circle.left+ circle.size) *  worldWidth- iModel.viewLeft-5, (circle.top+ circle.size)* worldHeight- iModel.viewTop-5,10, 10);
                gc.strokeOval((circle.left+ circle.size) *  worldWidth- iModel.viewLeft-5, (circle.top+ circle.size)* worldHeight- iModel.viewTop-5,10, 10);

            }


            case XOval oval -> {
                gc.setLineWidth(2);
                gc.setStroke(Color.RED);
                gc.setLineDashes(5);
                gc.strokeRect(oval.left * worldWidth- iModel.viewLeft, oval.top * worldHeight- iModel.viewTop, oval.size * worldWidth, oval.sizeY* worldHeight);

                gc.setLineDashes(0);
                gc.setFill(Color.YELLOW);
                gc.fillOval((oval.left+ oval.size) * worldWidth- iModel.viewLeft-5, (oval.top+ oval.sizeY)* worldHeight- iModel.viewTop-5,10, 10);
                gc.strokeOval((oval.left+ oval.size) * worldWidth- iModel.viewLeft-5, (oval.top+ oval.sizeY)* worldHeight- iModel.viewTop-5,10, 10);


            }

            case XLine line -> {
                gc.setLineWidth(2);
                gc.setStroke(Color.RED);
                gc.setLineDashes(5);
                gc.strokeLine(line.x1 * worldWidth- iModel.viewLeft, line.y1 * worldHeight- iModel.viewTop, line.x2 * worldWidth- iModel.viewLeft, line.y2 * worldHeight- iModel.viewTop);

                gc.setLineDashes(0);
                gc.setFill(Color.YELLOW);
                gc.fillOval((line.x2) * worldWidth- iModel.viewLeft-5, (line.y2)* worldHeight- iModel.viewTop-5,10, 10);
                gc.strokeOval((line.x2) * worldWidth- iModel.viewLeft-5, (line.y2)* worldHeight- iModel.viewTop-5,10, 10);

            }


            case XShape XS -> System.out.println("An item did not get drawn");
        }
    }

    protected void drawSquare(double left, double top, double size,  Color color) {
        gc.setFill(Color.BLACK);

        gc.fillRect(left , top, size , size);
        gc.setFill(color);
        gc.fillRect(left+1, top+1, size-2 , size-2);

    }

    protected void drawRectangle(double left, double top, double sizeX, double sizeY,  Color color){
        gc.setFill(Color.BLACK);
        gc.fillRect(left , top, sizeX, sizeY);
        gc.setFill(color);
        gc.fillRect(left+1, top+1, sizeX- 2, sizeY-2);

    }

    protected void drawCircle(double left, double top, double size,  Color color){
        gc.setFill(Color.BLACK);
        gc.fillOval(left, top, size, size);
        gc.setFill(color);
        gc.fillOval(left + 1, top + 1, size -2 , size-2);


    }

    protected void drawOval(double left, double top, double sizeX, double sizeY,  Color color){
        gc.setFill(Color.BLACK);
        gc.fillOval(left, top, sizeX, sizeY);
        gc.setFill(color);
        gc.fillOval(left+1, top+1, sizeX-2 , sizeY-2);

    }
    protected void drawLine(double x1, double y1, double x2, double y2, double width,  Color color){
        gc.setStroke(color);
        gc.setLineWidth(width);
        gc.strokeLine(x1 , y1, x2 , y2);
    }


    public void modelChanged() {
        draw();
    }


    public void iModelChanged() {
        myCanvas.setWidth(iModel.viewWidth);
        myCanvas.setHeight(iModel.viewHeight);
        draw();

    }

    public void setModel(DrawingModel drawingModel){
        model = drawingModel;
    }

    public void setIModel(InteractionModel interactionModel){
        iModel = interactionModel;
        iModel.setDocumentSize(worldWidth, worldHeight);
    }

    public void setController(DrawingController controller) {
        myCanvas.setOnMousePressed(e -> controller.handlePress(e.getX()/worldWidth, e.getY()/worldHeight, e));
        myCanvas.setOnMouseDragged(e -> controller.handleDrag(e.getX()/worldWidth, e.getY()/worldHeight, e));
        myCanvas.setOnMouseReleased(e -> controller.handleRelease(e.getX()/worldWidth, e.getY()/worldHeight, e ));
        this.widthProperty().addListener((observable, oldVal, newVal) -> {
            controller.windowSizeChanged(newVal.doubleValue(), myCanvas.getHeight());
        });
        this.heightProperty().addListener((observable, oldVal, newVal) -> {
            controller.windowSizeChanged(myCanvas.getWidth(), newVal.doubleValue());
        });
    }
}
