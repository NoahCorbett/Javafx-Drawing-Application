package com.example.assignment3;

import javafx.css.converter.PaintConverter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class ColorToolbar extends StackPane implements DrawingIModelSubscriber, DrawingModelSubscriber{

    private InteractionModel iModel;
    ToggleGroup toggleGroup;
    ArrayList<ToggleButton> colorButtons;
    public ColorToolbar(){
        VBox root = new VBox();
        ToggleButton aqua = new ToggleButton("Aqua");
        aqua.setBackground(new Background(new BackgroundFill( Color.AQUA, CornerRadii.EMPTY, new Insets(1,2,1,2))));
        aqua.setMaxSize(50, Double.POSITIVE_INFINITY);
        aqua.setSelected(true);


        ToggleButton blue = new ToggleButton("Blue");
        blue.setBackground(new Background(new BackgroundFill( Color.BLUE, CornerRadii.EMPTY, new Insets(1,2,1,2))));
        blue.setMaxSize(50, Double.POSITIVE_INFINITY);

        ToggleButton plum = new ToggleButton("Plum");
        plum.setBackground(new Background(new BackgroundFill( Color.PLUM, CornerRadii.EMPTY, new Insets(1,2,1,2))));
        plum.setMaxSize(50, Double.POSITIVE_INFINITY);

        ToggleButton gold = new ToggleButton("Gold");
        gold.setBackground(new Background(new BackgroundFill( Color.GOLD, CornerRadii.EMPTY, new Insets(1,2,1,2))));
        gold.setMaxSize(50, Double.POSITIVE_INFINITY);

        ToggleButton coral = new ToggleButton("Coral");
        coral.setBackground(new Background(new BackgroundFill( Color.CORAL, CornerRadii.EMPTY, new Insets(1,2,1,2))));
        coral.setMaxSize(50, Double.POSITIVE_INFINITY);

        ToggleButton green = new ToggleButton("Green");
        green.setBackground(new Background(new BackgroundFill( Color.GREEN, CornerRadii.EMPTY, new Insets(1,2,1,2))));
        green.setMaxSize(50, Double.POSITIVE_INFINITY);

        ToggleButton red = new ToggleButton("Red");
        red.setBackground(new Background(new BackgroundFill( Color.RED, CornerRadii.EMPTY, new Insets(2.5,5,2.5,5))));
        red.setMaxSize(50, Double.POSITIVE_INFINITY);

        ToggleButton violet = new ToggleButton("Violet");
        violet.setBackground(new Background(new BackgroundFill( Color.VIOLET, CornerRadii.EMPTY, new Insets(2.5,5,2.5,5))));
        violet.setMaxSize(50, Double.POSITIVE_INFINITY);


        VBox.setVgrow(aqua, Priority.ALWAYS);
        VBox.setVgrow(green, Priority.ALWAYS);
        VBox.setVgrow(coral, Priority.ALWAYS);
        VBox.setVgrow(gold, Priority.ALWAYS);
        VBox.setVgrow(red, Priority.ALWAYS);
        VBox.setVgrow(violet, Priority.ALWAYS);
        VBox.setVgrow(blue, Priority.ALWAYS);
        VBox.setVgrow(plum, Priority.ALWAYS);

        toggleGroup = new ToggleGroup();
        aqua.setToggleGroup(toggleGroup);
        gold.setToggleGroup(toggleGroup);
        green.setToggleGroup(toggleGroup);
        red.setToggleGroup(toggleGroup);
        violet.setToggleGroup(toggleGroup);
        coral.setToggleGroup(toggleGroup);
        blue.setToggleGroup(toggleGroup);
        plum.setToggleGroup(toggleGroup);


        colorButtons = new ArrayList<>();
        colorButtons.add(aqua);
        colorButtons.add(coral);
        colorButtons.add(green);
        colorButtons.add(gold);
        colorButtons.add(red);
        colorButtons.add(violet);
        colorButtons.add(blue);
        colorButtons.add(plum);



        root.getChildren().addAll(aqua, blue, coral, gold, green, plum, red, violet);
        this.getChildren().addAll(root);
    }


    public void setIModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void setController(DrawingController controller){
        colorButtons.forEach(cb -> {
            cb.setOnAction(e -> controller.handleColorSelect(Color.valueOf(cb.getBackground().getFills().get(0).getFill().toString())));

        });

    }

    @Override
    public void iModelChanged() {

        colorButtons.forEach(cb -> {
            if (cb.isSelected()){
                cb.setBackground(new Background(new BackgroundFill(cb.getBackground().getFills().get(0).getFill(), CornerRadii.EMPTY, new Insets(1,2,1,2))));
            }else{
                cb.setBackground(new Background(new BackgroundFill(cb.getBackground().getFills().get(0).getFill(), CornerRadii.EMPTY, new Insets(2.5,5,2.5,5))));
            }
        });
    }

    @Override
    public void modelChanged() {

    }
}
