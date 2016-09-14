package compgrafica_trabalho2.view;

import static java.lang.Math.round;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;

/**
 * FXML Controller class
 *
 * @author LeonardoRez
 */
public class InterfacePrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //CONTROLADORES
    @FXML
    private Canvas canvas;
    @FXML
    private GridPane ferramentas;

    //ATRIBUTOS AUXILIARES
    private int ferramenta = 0;
    private int cont;
    private MouseEvent fClick;
    private MouseEvent lastClick;
    private boolean polyF = false;

    private void zeraAuxs() {
        if (polyF) {
            desenharLinha(fClick, lastClick);
        }
        polyF = false;
        fClick = null;
        lastClick = null;
        cont = 0;
    }

    @FXML
    public void retangulo() {
        zeraAuxs();
        ferramenta = 1;
    }

    @FXML
    public void polyAberta() {
        zeraAuxs();
        ferramenta = 2;
    }

    @FXML
    public void polyfechada() {
        zeraAuxs();
        polyF = true;
        ferramenta = 2;
    }

    @FXML
    public void circulo() {
        zeraAuxs();
        ferramenta = 4;
    }

    @FXML
    public void baldeDeTinta() {
        zeraAuxs();
        ferramenta = 5;
    }

    @FXML
    public void line() {
        zeraAuxs();
        ferramenta = 6;
    }

    private void desenharLinha(MouseEvent e1, MouseEvent e2) {
        double a = (e1.getY() - e2.getY()) / (e1.getX() - e2.getX()); //calcula coeficiente angular
        double b = e1.getY() - a * e1.getX(); //calcula coeficiente linear

        if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY())) { // vê se a reta é maior em X ou Y
            double maior, menor;
            if (e1.getX() > e2.getX()) { //armazena o range (menor e maior) de X para pintar o segmento de reta
                maior = e1.getX();
                menor = e2.getX();
            } else {
                maior = e2.getX();
                menor = e1.getX();
            }
            for (double i = menor; i < maior; i += 0.1) { //pinta de "menor" até "maior"
                canvas.getGraphicsContext2D().fillRect(i, (a * i + b), 2, 2); //pinta o pixel da reta equivalente ao valor X, com 2x2 pixels de área
            }
        } else { //mesma coisa da parte de cima, mas se Y for maior que X
            double maior, menor;
            if (e1.getY() > e2.getY()) {
                maior = e1.getY();
                menor = e2.getY();
            } else {
                maior = e2.getY();
                menor = e1.getY();
            }
            for (double i = menor; i < maior; i += 0.1) {
                canvas.getGraphicsContext2D().fillRect(((i - b) / a), i, 2, 2);
            }
        }
    }

    private boolean compareColor(Color c1, Color c2) {
        if (c1.getBlue() == c2.getBlue() && c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen()) {
            return true;
        }
        return false;
    }

    private void bucket(int x, int y, int v[][], Color oldColor, WritableImage r) {
        if (v[x][y] == -1) {
            return;
        } else {
            v[x][y] = -1;
            if (compareColor(r.getPixelReader().getColor(x, y), oldColor)) {
                canvas.getGraphicsContext2D().fillRect(x, y, 1, 1);
                if (x + 1 < v[0].length) {
                    bucket(x + 1, y, v, oldColor, r);
                }
                if (x - 1 >= 0) {
                    bucket(x - 1, y, v, oldColor, r);
                }
                if (y + 1 < v.length) {
                    bucket(x, y + 1, v, oldColor, r);
                }
                if (x - 1 >= 0) {
                    bucket(x, y - 1, v, oldColor, r);
                }
                return;
            } else {
                return;
            }
        }
    }

    @FXML
    public void handle(MouseEvent e) {
        switch (ferramenta) {
            case 1: //RETANGULO
                canvas.getGraphicsContext2D().setFill(color(1, 0, 0)); //setando a cor da reta
                if (cont == 0) { // se for o primeiro click,
                    fClick = e; //armazena a posição
                    cont = 1; //incrementa o contador de clicks
                } else { // se for o segundo,
                    cont = 0;
                    double maior, menor;
                    //pintando retas horizontais
                    if (e.getX() > fClick.getX()) { //armazena o range (menor e maior) de X para pintar o segmento de reta
                        maior = e.getX();
                        menor = fClick.getX();
                    } else {
                        maior = fClick.getX();
                        menor = e.getX();
                    }
                    for (double i = menor; i < maior; i += 0.1) { //pinta de "menor" até "maior"
                        canvas.getGraphicsContext2D().fillRect(i, fClick.getY(), 2, 2); //pinta o pixel da reta equivalente ao valor X, com 2x2 pixels de área
                    }
                    for (double i = menor; i < maior; i += 0.1) { //pinta de "menor" até "maior"
                        canvas.getGraphicsContext2D().fillRect(i, e.getY(), 2, 2); //pinta o pixel da reta equivalente ao valor X, com 2x2 pixels de área
                    }

                    //pintando retas verticais
                    if (e.getY() > fClick.getY()) { //armazena o range (menor e maior) de X para pintar o segmento de reta
                        maior = e.getY();
                        menor = fClick.getY();
                    } else {
                        maior = fClick.getY();
                        menor = e.getY();
                    }
                    for (double i = menor; i < maior; i += 0.1) { //pinta de "menor" até "maior"
                        canvas.getGraphicsContext2D().fillRect(fClick.getX(), i, 2, 2); //pinta o pixel da reta equivalente ao valor X, com 2x2 pixels de área
                    }
                    for (double i = menor; i < maior; i += 0.1) { //pinta de "menor" até "maior"
                        canvas.getGraphicsContext2D().fillRect(e.getX(), i, 2, 2); //pinta o pixel da reta equivalente ao valor X, com 2x2 pixels de área
                    }
                }
                break;
            case 2: //POLYLINE 

                canvas.getGraphicsContext2D().setFill(color(1, 0, 0)); //setando a cor da reta
                if (cont == 0) { // se for o primeiro click,
                    fClick = e; //armazena a posição
                    lastClick = e;
                    cont = 1; //incrementa o contador de clicks
                } else { // se for o segundo,
                    desenharLinha(e, lastClick);
                    lastClick = e;
                }
                break;
            case 4: //CIRCULO
                canvas.getGraphicsContext2D().setFill(color(1, 0, 0)); //setando a cor da reta
                if (cont == 0) { // se for o primeiro click,
                    fClick = e; //armazena a posição
                    cont = 1; //incrementa o contador de clicks
                } else { // se for o segundo,
                    cont = 0;
                    double raio = Math.sqrt(Math.pow((fClick.getY() - e.getY()), 2) + Math.pow((fClick.getX() - e.getX()), 2));
                    double yInit = fClick.getY() - raio;
                    double yFinal = fClick.getY() + raio;
                    for (double i = yInit; i <= yFinal; i += 0.1) {
                        //i = - Math.sqrt(raio²-(yo-y)²) + fClick.getX();
                        double x = -Math.sqrt(raio * raio - Math.pow((fClick.getY() - i), 2)) + fClick.getX();
                        canvas.getGraphicsContext2D().fillRect(x, i, 2, 2);
                        x = Math.sqrt(raio * raio - Math.pow((fClick.getY() - i), 2)) + fClick.getX();
                        canvas.getGraphicsContext2D().fillRect(x, i, 2, 2);
                    }
//                    canvas.getGraphicsContext2D().fillRect(fClick.getX()+1,fClick.getY()+raio, 2, 2);
//                    canvas.getGraphicsContext2D().fillRect(fClick.getX()-1,fClick.getY()-raio, 2, 2);
//                    canvas.getGraphicsContext2D().fillRect(fClick.getX()+2,fClick.getY()+raio, 2, 2);
//                    canvas.getGraphicsContext2D().fillRect(fClick.getX()-2,fClick.getY()-raio, 2, 2);
                }
                break;
            case 5: //BALDE DE TINTA
                WritableImage a = canvas.snapshot(null, null); //leitor de pixels
                int x = (int) Math.round(e.getX());
                int y = (int) Math.round(e.getY());
                int v[][] = new int[(int) Math.round(canvas.getWidth())][(int) Math.round(canvas.getHeight())];
                canvas.getGraphicsContext2D().setFill(color(0, 0, 1));
                bucket(x, y, v, a.getPixelReader().getColor(x, y), a);

                break;
            case 6://RETA

                canvas.getGraphicsContext2D().setFill(color(1, 0, 0)); //setando a cor da reta
                if (cont == 0) { // se for o primeiro click,
                    fClick = e; //armazena a posição
                    cont = 1; //incrementa o contador de clicks
                } else { // se for o segundo,
                    cont = 0; //zera o contador
                    desenharLinha(e, fClick);
                }
                break;

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
