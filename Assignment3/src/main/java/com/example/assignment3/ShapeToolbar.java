package com.example.assignment3;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ShapeToolbar extends StackPane implements DrawingIModelSubscriber, DrawingModelSubscriber{
    ToggleButton rectangleButton;
    ToggleButton squareButton;
    ToggleButton circleButton;
    ToggleButton ovalButton;
    ToggleButton lineButton;
    Rectangle rectangle;
    Rectangle square;
    Circle circle;
    Ellipse oval;
    Line line;

    InteractionModel IModel;

    /**
     * Constructor class for the shape toolbar widget
     */
    public ShapeToolbar(){
        VBox root = new VBox();
        root.setMaxSize(100, Double.POSITIVE_INFINITY);


        rectangle = new Rectangle(25, 40, Color.BLACK);
        square = new Rectangle(25, 25, Color.AQUA);
        circle = new Circle(20, Color.BLACK);
        oval = new Ellipse(15,10);
        oval.setFill(Color.BLACK);
        line = new Line(10, 10, 40, 40);
        line.setStroke(Color.BLACK);

        VBox rect = new VBox();
        rect.setAlignment(Pos.CENTER);
        rect.getChildren().addAll(rectangle, new Text("Rect"));
        rectangleButton = new ToggleButton();
        rectangleButton.setGraphic(rect);
        rectangleButton.setMinSize(50,50);
        rectangleButton.setPrefSize(50,100);
        rectangleButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        VBox squ = new VBox();
        squ.setAlignment(Pos.CENTER);
        squ.getChildren().addAll(square, new Text("Square"));
        squareButton = new ToggleButton();
        squareButton.setGraphic(squ);
        squareButton.setMinSize(50,50);
        squareButton.setPrefSize(50,100);
        squareButton.setMaxSize(50, Double.POSITIVE_INFINITY);
        squareButton.setSelected(true);


        VBox cir = new VBox();
        cir.setAlignment(Pos.CENTER);
        cir.getChildren().addAll(circle, new Text("Circle"));
        circleButton = new ToggleButton();
        circleButton.setMinSize(50,50);
        circleButton.setGraphic(cir);
        circleButton.setPrefSize(50,100);
        circleButton.setMaxSize(50, Double.POSITIVE_INFINITY);


        VBox ova = new VBox();
        ova.setAlignment(Pos.CENTER);
        ova.getChildren().addAll(oval, new Text("Oval"));
        ovalButton = new ToggleButton();
        ovalButton.setMinSize(50,50);
        ovalButton.setGraphic(ova);
        ovalButton.setPrefSize(50,100);
        ovalButton.setMaxSize(50, Double.POSITIVE_INFINITY);


        VBox lin = new VBox();
        lin.setAlignment(Pos.CENTER);
        lin.getChildren().addAll(line, new Text("Line"));
        lineButton = new ToggleButton();
        lineButton.setMinSize(50,50);
        lineButton.setGraphic(lin);
        lineButton.setPrefSize(50,100);
        lineButton.setMaxSize(50, Double.POSITIVE_INFINITY);
        
        
        
        root.setVgrow(rectangleButton, Priority.ALWAYS);
        root.setVgrow(squareButton, Priority.ALWAYS);
        root.setVgrow(circleButton, Priority.ALWAYS);
        root.setVgrow(ovalButton, Priority.ALWAYS);
        root.setVgrow(lineButton, Priority.ALWAYS);

        ToggleGroup toggleGroup = new ToggleGroup();
        rectangleButton.setToggleGroup(toggleGroup);
        squareButton.setToggleGroup(toggleGroup);
        circleButton.setToggleGroup(toggleGroup);
        ovalButton.setToggleGroup(toggleGroup);
        lineButton.setToggleGroup(toggleGroup);
        root.getChildren().addAll(squareButton, rectangleButton, circleButton, ovalButton, lineButton);

        this.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        this.getChildren().add(root);
    }

    /**
     * Sets the iModel for the ShapeToolbar
     * @param IModel The IModel in the MVC architecture
     */
    public void setIModel(InteractionModel IModel) {
        this.IModel = IModel;
    }

    /**
     * When a shape button is clicked it notifies the controller so that that shape is selected
     * @param controller The drawing controller that handles the event that a shape is selected
     */
    public void setController(DrawingController controller){
        rectangleButton.setOnAction(e -> controller.handleToolSelect(InteractionModel.Tool.RECTANGLE));
        squareButton.setOnAction(e -> controller.handleToolSelect(InteractionModel.Tool.SQUARE));
        circleButton.setOnAction(e -> controller.handleToolSelect(InteractionModel.Tool.CIRCLE));
        ovalButton.setOnAction(e -> controller.handleToolSelect(InteractionModel.Tool.OVAL));
        lineButton.setOnAction(e -> controller.handleToolSelect(InteractionModel.Tool.LINE));
    }

    /**
     * When the iModel is updated then the currently selected shape is changed to that color on the toolbar and
     * everything else is turned to black
     */
    @Override
    public void iModelChanged() {
        if(rectangleButton.isSelected()){
            rectangle.setFill(IModel.getSelectedColour());
        }else{
            rectangle.setFill(Color.BLACK);
        }

        if(squareButton.isSelected()){
            square.setFill(IModel.getSelectedColour());
        }else{
            square.setFill(Color.BLACK);
        }
        if(circleButton.isSelected()){
            circle.setFill(IModel.getSelectedColour());
        }else{
            circle.setFill(Color.BLACK);
        }
        if(ovalButton.isSelected()){
            oval.setFill(IModel.getSelectedColour());
        }else{
            oval.setFill(Color.BLACK);
        }
        if(lineButton.isSelected()){
            line.setStroke(IModel.getSelectedColour());
        }else{
            line.setStroke(Color.BLACK);
        }
    }

    /**
     * Nothing happens when the model is changed
     */
    @Override
    public void modelChanged() {

    }
}
