/*
 Trabalho 1 de Computação Gráfica (2016/1): Traçar o segmento de reta que passa por dois
pontos quaisquer da tela que o usuário clicar.
 */
package compgrafica_trabalho1;

import java.util.Collections;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author LeonardoRez
 */
public class CompGrafica_Trabalho1 extends Application {

    MouseEvent firstClick;
    int aux;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(500, 500);
        AnchorPane root = new AnchorPane(canvas);
        Scene cena = new Scene(root);
        aux = 0;
        cena.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                canvas.getGraphicsContext2D().setFill(color(1, 1, 0));
                if (aux == 0) {
                    firstClick = e;
                    aux = 1;
                } else {
                    aux = 0;
                    double a = (e.getSceneY() - firstClick.getSceneY()) / (e.getSceneX() - firstClick.getSceneX());
                    double b = e.getSceneY() - a * e.getSceneX();
                    if ((e.getSceneX() - firstClick.getSceneX()) > (e.getSceneY() - firstClick.getSceneY())) {
                        double maior, menor;
                        if (e.getSceneX() > firstClick.getSceneX()) {
                            maior = e.getSceneX();
                            menor = firstClick.getSceneX();
                        } else {
                            maior = firstClick.getSceneX();
                            menor = e.getSceneX();
                        }
                        for (double i = menor; i < maior; i += 0.1) {
                            canvas.getGraphicsContext2D().fillRect(i, (a*i+b), 10, 10);
                        }
                    }else{
                        double maior, menor;
                        if (e.getSceneY() > firstClick.getSceneY()) {
                            maior = e.getSceneY();
                            menor = firstClick.getSceneY();
                        } else {
                            maior = firstClick.getSceneY();
                            menor = e.getSceneY();
                        }
                        for (double i = menor; i < maior; i += 0.1) {
                            canvas.getGraphicsContext2D().fillRect(((i-b)/a), i, 10, 10);
                        }
                    }

                }
//                canvas.getGraphicsContext2D().fillRect(e.getSceneX(), e.getSceneY(), 1, 1);
            }
        });
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
