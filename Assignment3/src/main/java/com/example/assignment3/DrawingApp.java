package com.example.assignment3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class DrawingApp extends Application {
    /**
     * Initializes the program by setting up the MVC architecture
     * @param stage The initial stage for the program.
     * @throws IOException Exception required because this function overrides a parent function
     */
    @Override
    public void start(Stage stage) throws IOException {
        MainUI view = new MainUI();
        InteractionModel interactionModel = new InteractionModel();
        DrawingModel model = new DrawingModel();
        DrawingController controller = new DrawingController();
        MiniDrawingController miniController = new MiniDrawingController();

        view.setIModel(interactionModel);
        view.setModel(model);
        view.setController(controller, miniController);

        model.addSub(view.getColor());
        model.addSub(view.getView());
        model.addSub(view.getShapeToolBar());
        model.addSub(view.getMiniView());

        interactionModel.addSub(view.getView());
        interactionModel.addSub(view.getShapeToolBar());
        interactionModel.addSub(view.getColor());
        interactionModel.addSub(view.getMiniView());

        miniController.setModel(model);
        miniController.setIModel(interactionModel);
        controller.setIModel(interactionModel);
        controller.setModel(model);

        view.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Scene scene = new Scene(view);
        scene.setOnKeyPressed(controller::handleKeyEvent);
        stage.setTitle("JavaFX Drawing Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}