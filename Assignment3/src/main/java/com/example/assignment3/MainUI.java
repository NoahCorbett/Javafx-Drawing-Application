package com.example.assignment3;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainUI extends StackPane  {

    /** The widget for the shape toolbar */
    private final ShapeToolbar shapeToolbar;
    /** The widget for the color toolbar */
    private final ColorToolbar color;
    /** The main canvas of the system */
    private final DrawingView view;
    /** A smaller canvas in the corner */
    private final MiniDrawingView miniView;


    /**
     * The constructor class for the UI, initializes the canvas as well as the miniUI, the color toolbar and the shape
     * toolbar
     */
    public MainUI(){
        BorderPane root = new BorderPane();
        shapeToolbar = new ShapeToolbar();
        shapeToolbar.setMaxSize(50, Double.POSITIVE_INFINITY);

        color = new ColorToolbar();
        color.setMaxSize(50, Double.POSITIVE_INFINITY);

        StackPane views = new StackPane();

        miniView = new MiniDrawingView(100,100, 2000, 2000);
        miniView.setMinSize(100,100);
        miniView.setMaxSize(100,100);
        miniView.setAlignment(Pos.TOP_LEFT);

        view = new DrawingView(500, 500, 2000, 2000);
        view.setMinSize(200,200);
        view.setMaxSize(500,500);
        view.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);


        views.getChildren().addAll(view, miniView);
        views.setAlignment(Pos.TOP_LEFT);

        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setLeft(shapeToolbar);
        root.setRight(color);
        root.setCenter(views );
        this.getChildren().add(root);

    }

    /**
     * Passes off the model to be set in the view and miniView
     * @param model The model to be set in the view and miniView
     */
    public void setModel(DrawingModel model){
        view.setModel(model);
        miniView.setModel(model);
    }

    /**
     * Passes off the iModel to be set in the view, miniView and the shapeToolbar
     * @param iModel the iModel to be set in the view, miniView and the shapeToolbar
     */
    public void setIModel(InteractionModel iModel){
        view.setIModel(iModel);
        shapeToolbar.setIModel(iModel);
        miniView.setIModel(iModel);
    }

    /**
     * Sets the controller and the miniController in the
     * @param controller The controller that interacts with the view and both of the toolbars
     * @param miniController The controller that interacts with the miniView
     */
    public void setController(DrawingController controller, MiniDrawingController miniController){
        color.setController(controller);
        shapeToolbar.setController(controller);
        view.setController(controller);
        miniView.setController(miniController);
    }

    /**
     * Returns the colorToolbar
     * @return the colorToolbar
     */
    public ColorToolbar getColor() {
        return color;
    }

    /**
     * Returns the main canvas
     * @return the main canvas
     */
    public DrawingView getView() {
        return view;
    }

    /**
     * Returns the shapeToolbar
     * @return the shapeToolbar
     */
    public ShapeToolbar getShapeToolBar() {
        return shapeToolbar;
    }

    /**
     * Returns the mini view
     * @return the mini view
     */
    public MiniDrawingView getMiniView(){
        return miniView;
    }


}
