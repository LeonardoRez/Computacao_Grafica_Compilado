/*
 Trabalho 2 de Computação Gráfica (2016/1): Implementar algoritmo que
desenhe: Reta, Retangulo, Polilinha (aberta e fechada), Circunferencia
e Algoritmo recursivo de pintura.
 */
package compgrafica_trabalho2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author VAL
 */
public class CompGrafica_Trabalho2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader interfaceGrafica = new FXMLLoader();
        //System.out.println(getClass().getResource("view/InterfacePrincipal.fxml"));
        interfaceGrafica.setLocation(getClass().getResource("view/InterfacePrincipal.fxml"));
        BorderPane root;
        try {
            root = (BorderPane) interfaceGrafica.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Trabalho 2!");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println("Arquivo nao encontrado!");
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
