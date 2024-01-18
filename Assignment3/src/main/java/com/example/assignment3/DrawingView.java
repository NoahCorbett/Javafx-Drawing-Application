package com.example.assignment3;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Comparator;

public class DrawingView extends StackPane implements DrawingIModelSubscriber, DrawingModelSubscriber{

    /** The canvas which the shapes are placed in */
    Canvas myCanvas;
    /** A shortcut to get the graphical context from the canvas instead of calling the function everytime */
    GraphicsContext gc;
    /** The model that the view uses to store and retrieve shapes */
    DrawingModel model;
    /** Passes off the current state to the iModel for the controller to use */
    InteractionModel iModel;
    /** The dimensions of the current viewport */
    double width, height;
    /** The size of the entire document */
    double worldWidth, worldHeight;

    /**
     * Constructor method for the DrawingView
     * @param height The height of the visible canvas
     * @param width The width of the visible canvas
     * @param documentHeight The height of the entire canvas
     * @param documentWidth The width of the entire canvas
     */
    public DrawingView(double height, double width, double documentHeight, double documentWidth){


        this.width = width;
        this.height = height;
        worldWidth = documentWidth;
        worldHeight = documentHeight;
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        this.getChildren().add(myCanvas);

    }

    /**
     * Clears everything on the canvas and redraws everything
     */
    public void draw(){
        gc.clearRect(0,0, myCanvas.getWidth(), myCanvas.getHeight());
        model.getShapes().sort(Comparator.comparingInt(XShape::getZ));
        model.getShapes().forEach(item ->{
            switch(item){
                case XSquare square -> this.drawSquare(square.left * worldWidth- iModel.viewLeft,square.top * worldHeight- iModel.viewTop, (square.right - square.left) * Math.min(worldWidth,worldHeight) , square.color);
                case XRectangle rectangle -> this.drawRectangle(rectangle.left * worldWidth- iModel.viewLeft, rectangle.top * worldHeight- iModel.viewTop, rectangle.getSizeX() * worldWidth , rectangle.getSizeY() * worldHeight, rectangle.color);
                case XCircle circle -> this.drawCircle(circle.left * worldWidth- iModel.viewLeft,circle.top * worldHeight- iModel.viewTop, circle.getSizeX() * Math.min(worldWidth,worldHeight) , circle.color);
                case XOval oval -> this.drawOval(oval.left * worldWidth- iModel.viewLeft, oval.top * worldHeight- iModel.viewTop, oval.getSizeX() * worldWidth , oval.getSizeY() * worldHeight, oval.color);
                case XLine line -> this.drawLine(line.left * worldWidth- iModel.viewLeft, line.top * worldHeight- iModel.viewTop, line.right * worldWidth- iModel.viewLeft, line.bottom * worldHeight- iModel.viewTop, 5,line.color);
                case XShape XS -> System.out.println("An item did not get drawn");
            }
        });
        if(iModel.getSelectedShape()!= null){
            switch (iModel.getSelectedShape()){
                case XSquare square -> {
                    makeBoundingBox(square.left, square.top, square.getSizeX(), square.getSizeX());
                    makeResizeTab(square.right, square.bottom);
                }


                case XRectangle rectangle -> {
                    makeBoundingBox(rectangle.left, rectangle.top, rectangle.getSizeX(), rectangle.getSizeY());
                    makeResizeTab(rectangle.right, rectangle.bottom);
                }
                case XCircle circle -> {
                    makeBoundingBox(circle.left, circle.top, circle.getSizeX(), circle.getSizeX());

                    makeResizeTab(circle.left + circle.getSizeX(), circle.top + circle.getSizeX());
                }


                case XOval oval -> {
                    makeBoundingBox(oval.left, oval.top, oval.getSizeX(), oval.getSizeY());
                    makeResizeTab(oval.left + oval.getSizeX(), oval.top + oval.getSizeY());

                }

                case XLine line -> {
                    gc.setLineWidth(2);
                    gc.setStroke(Color.RED);
                    gc.setLineDashes(5);
                    gc.strokeLine(line.left * worldWidth- iModel.viewLeft, line.top * worldHeight- iModel.viewTop, line.right * worldWidth- iModel.viewLeft, line.bottom * worldHeight- iModel.viewTop);

                    makeResizeTab(line.right, line.bottom);
                }


                case XShape XS -> System.out.println("An item did not get drawn");
            }
        }
    }

    /**
     * Makes a bounding box around the selected shape (not line)
     * @param left The left side of the shape
     * @param top The top of the object
     * @param sizeX The size in the horizontal direction
     * @param sizeY The size in the vertical direction
     */
    private void makeBoundingBox(double left, double top, double sizeX, double sizeY) {
        gc.setLineWidth(2);
        gc.setStroke(Color.RED);
        gc.setLineDashes(5);
        gc.strokeRect(left * worldWidth- iModel.viewLeft , top * worldHeight- iModel.viewTop, sizeX * worldWidth , sizeY * worldHeight);
    }

    /**
     * Makes a resize tab at the bottom right of the object
     * @param right The right coordinate of the shape
     * @param bottom The bottom coordinate of the shape
     */
    private void makeResizeTab (double right, double bottom) {
        gc.setLineDashes(0);
        gc.setFill(Color.YELLOW);

        gc.fillOval(right * worldWidth- iModel.viewLeft-5, bottom * worldHeight- iModel.viewTop-5,10, 10);
        gc.strokeOval(right * worldWidth- iModel.viewLeft-5, bottom * worldHeight- iModel.viewTop-5,10, 10);
    }

    /**
     * Draws a square on the canvas
     * @param left The left side of the square
     * @param top The top side of the square
     * @param size The width and height of the square
     * @param color The color of the square
     */
    protected void drawSquare(double left, double top, double size,  Color color) {
        //System.out.println(left + " " + top + " "+  size);
        gc.setFill(Color.BLACK);
        gc.fillRect(left , top, size , size);
        gc.setFill(color);
        gc.fillRect(left+1, top+1, size-2 , size-2);

    }

    /**
     * Draws a rectangle on the canvas
     * @param left The left side of the rectangle
     * @param top The top side of the rectangle
     * @param sizeX The width of the rectangle
     * @param sizeY The height of the rectangle
     * @param color The color of the rectangle
     */
    protected void drawRectangle(double left, double top, double sizeX, double sizeY,  Color color){
        gc.setFill(Color.BLACK);
        gc.fillRect(left , top, sizeX, sizeY);
        gc.setFill(color);
        gc.fillRect(left+1, top+1, sizeX- 2, sizeY-2);

    }

    /**
     * Draws a circle on the canvas
     * @param left The left most point of the circle
     * @param top The top most point of the circle
     * @param size The diameter of the circle
     * @param color The color of the circle
     */
    protected void drawCircle(double left, double top, double size,  Color color){
        gc.setFill(Color.BLACK);
        gc.fillOval(left, top, size, size);
        gc.setFill(color);
        gc.fillOval(left + 1, top + 1, size -2 , size-2);


    }

    /**
     * Draws an oval on the canvas
     * @param left The left most point of the oval
     * @param top The top most point of the oval
     * @param sizeX The horizontal diameter of the oval
     * @param sizeY The vertical diameter of the oval
     * @param color The color of the oval
     */
    protected void drawOval(double left, double top, double sizeX, double sizeY,  Color color){
        gc.setFill(Color.BLACK);
        gc.fillOval(left, top, sizeX, sizeY);
        gc.setFill(color);
        gc.fillOval(left+1, top+1, sizeX-2 , sizeY-2);

    }

    /**
     * Draws a line on the canvas
     * @param x1 The starting point on the x-axis
     * @param y1 The starting point on the y-axis
     * @param x2 The end point on the x-axis
     * @param y2 The end point on the y-axis
     * @param width The width of the line
     * @param color The color of the line
     */
    protected void drawLine(double x1, double y1, double x2, double y2, double width,  Color color){
        gc.setStroke(color);
        gc.setLineWidth(width);
        gc.strokeLine(x1 , y1, x2 , y2);
    }

    /**
     * Redraws everything on the canvas when notified that the model has changed
     */
    public void modelChanged() {
        draw();
    }

    /**
     * Sets the width and height of the canvas to the view width/height of the iModel and redraws the shapes
     */
    public void iModelChanged() {
        myCanvas.setWidth(iModel.viewWidth);
        myCanvas.setHeight(iModel.viewHeight);
        draw();

    }

    /**
     * Sets the drawing model for the DrawingView to use
     * @param drawingModel The drawingModel of the system
     */
    public void setModel(DrawingModel drawingModel){
        model = drawingModel;
    }
    /**
     * Sets the interaction model for the DrawingView to use
     * @param interactionModel The interactionModel of the system
     */
    public void setIModel(InteractionModel interactionModel){
        iModel = interactionModel;
        iModel.setDocumentSize(worldWidth, worldHeight);
    }

    /**
     * Sets the controller and when events occur it notifies the controller
     * @param controller The controller for the drawing view
     */
    public void setController(DrawingController controller) {
        myCanvas.setOnMousePressed(e -> controller.handlePress(e.getX()/worldWidth, e.getY()/worldHeight, e));
        myCanvas.setOnMouseDragged(e -> controller.handleDrag(e.getX()/worldWidth, e.getY()/worldHeight, e));
        myCanvas.setOnMouseReleased(e -> controller.handleRelease(e.getX()/worldWidth, e.getY()/worldHeight, e ));
        this.widthProperty().addListener((observable, oldVal, newVal) -> controller.windowSizeChanged(newVal.doubleValue(), myCanvas.getHeight()));
        this.heightProperty().addListener((observable, oldVal, newVal) -> controller.windowSizeChanged(myCanvas.getWidth(), newVal.doubleValue()));
    }

}
