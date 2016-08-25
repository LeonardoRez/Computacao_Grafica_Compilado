/*
 Trabalho 1 de Computação Gráfica (2016/1): Traçar o segmento de reta que passa por dois
pontos quaisquer da tela que o usuário clicar.
 */
package compgrafica_trabalho1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author LeonardoRez
 */
public class CompGrafica_Trabalho1 extends Application {

    MouseEvent firstClick; //objeto que guarda o primeiro click
    int aux;// contador de clicks (para saber quando traçar a reta)

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(500, 500); // tipo de panel que suporta "pintura de pixels"
        AnchorPane root = new AnchorPane(canvas);// construção da interface gráfica
        Scene cena = new Scene(root);
        aux = 0; 
        cena.setOnMouseClicked(new EventHandler<MouseEvent>() { //evento para pintar a reta
            @Override
            public void handle(MouseEvent e) {
                canvas.getGraphicsContext2D().setFill(color(1, 1, 0)); //setando a cor da reta
                if (aux == 0) { // se for o primeiro click,
                    firstClick = e; //armazena a posição
                    aux = 1; //incrementa o contador de clicks
                } else { // se for o segundo,
                    aux =0;
                    canvas.getGraphicsContext2D().strokeLine(firstClick.getX(), firstClick.getY(), e.getX(),e.getY());
                    /*aux = 0; //zera o contador
                    double a = (e.getSceneY() - firstClick.getSceneY()) / (e.getSceneX() - firstClick.getSceneX()); //calcula coeficiente angular
                    double b = e.getSceneY() - a * e.getSceneX(); //calcula coeficiente linear
                    
                    if (Math.abs(e.getSceneX() - firstClick.getSceneX()) > Math.abs(e.getSceneY() - firstClick.getSceneY())) { // vê se a reta é maior em X ou Y
                    double maior, menor;
                    if (e.getSceneX() > firstClick.getSceneX()) { //armazena o range (menor e maior) de X para pintar o segmento de reta
                    maior = e.getSceneX();
                    menor = firstClick.getSceneX();
                    } else {
                    maior = firstClick.getSceneX();
                    menor = e.getSceneX();
                    }
                    for (double i = menor; i < maior; i += 0.1) { //pinta de "menor" até "maior"
                    canvas.getGraphicsContext2D().fillRect(i, (a*i+b), 10, 10); //pinta o pixel da reta equivalente ao valor X, com 10x10 pixels de área
                    }
                    }else{ //mesma coisa da parte de cima, mas se Y for maior que X
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
                    }*/

                }
            }
        });
        primaryStage.setScene(cena); //adicionando a "cena" (do javaFX) no "palco principal"
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
