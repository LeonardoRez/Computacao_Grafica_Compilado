/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compgrafica_trabalho2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;

/**
 *
 * @author Leonardo
 */
public class testFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
         BorderPane root;
        try {
            root = new BorderPane();
            Canvas c = new Canvas(500, 500);
            c.getGraphicsContext2D().setFill(color(1,0,0));
            c.getGraphicsContext2D().fillRect(20, 20, 50, 50);
            WritableImage a = c.snapshot(null, null);
            System.out.println(a.getPixelReader().getColor(21, 21).toString());
            root.getChildren().add(c);
            Scene scene = new Scene(root,500,500);
            primaryStage.setTitle("Teste!");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Throwable ex) {
            System.out.println("Arquivo nao encontrado!");
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
