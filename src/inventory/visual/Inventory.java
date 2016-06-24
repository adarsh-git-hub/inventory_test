/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Eddy
 */
public class Inventory extends Application {

    static Stage stage;
    static Scene scene;
    static Parent rootParent;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
//        Parent rootParent = FXMLLoader.load(getClass().getResource("loginCSMBL.fxml"));
        rootParent = FXMLLoader.load(getClass().getResource("loginCSMBL.fxml"));
        Scene scene = new Scene(rootParent, Color.BLACK);
        stage.setTitle("CSM BatchLess Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
