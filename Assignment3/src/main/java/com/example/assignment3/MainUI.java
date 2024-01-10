package com.example.assignment3;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainUI extends StackPane  {
    private ShapeToolbar shape;
    private ColorToolbar color;
    private DrawingView view;
    private MiniDrawingView miniView;




    public MainUI(){
        BorderPane root = new BorderPane();
        shape = new ShapeToolbar();
        shape.setMaxSize(50, Double.POSITIVE_INFINITY);

        color = new ColorToolbar();
        color.setMaxSize(50, Double.POSITIVE_INFINITY);

        StackPane views = new StackPane();

        miniView = new MiniDrawingView(100,100, 2000, 2000);
        miniView.setMinSize(100,100);
        miniView.setMaxSize(100,100);
        miniView.setAlignment(Pos.TOP_LEFT);

        view = new DrawingView(500, 500, 2000, 2000);
        view.setMinSize(200,200);
        view.setMaxSize(2000,2000);
        view.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);


        views.getChildren().addAll(view, miniView);
        views.setAlignment(Pos.TOP_LEFT);

        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setLeft(shape);
        root.setRight(color);
        root.setCenter(views );
        this.getChildren().add(root);

    }

    public void setModel(DrawingModel model){
        view.setModel(model);
        miniView.setModel(model);
    }

    public void setIModel(InteractionModel iModel){
        view.setIModel(iModel);
        color.setIModel();
        shape.setIModel(iModel);
        miniView.setIModel(iModel);
    }
    public void setController(DrawingController controller, MiniDrawingController miniController){
        color.setController(controller);
        shape.setController(controller);
        view.setController(controller);
        miniView.setController(miniController);
    }

    public ColorToolbar getColor() {
        return color;
    }

    public DrawingView getView() {
        return view;
    }

    public ShapeToolbar getShapeToolBar() {
        return shape;
    }

    public MiniDrawingView getMiniView(){
        return miniView;
    }


}
