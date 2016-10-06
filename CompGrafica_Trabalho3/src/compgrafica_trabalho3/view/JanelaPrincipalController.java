package compgrafica_trabalho3.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class JanelaPrincipalController implements Initializable {

    @FXML
    ImageView img;
    @FXML
    TextField quantCores, grausRotac, sx, sy, tx, ty, Ch, Cv;
    @FXML
    CheckBox vertical;
    @FXML
    CheckBox horizontal;

    boolean selecionada;

    File f;

    @FXML
    public void pickImage() throws IOException {
        FileChooser fc = new FileChooser();
        f = fc.showOpenDialog(img.getScene().getWindow());
//        System.out.println(f.getPath());
//        System.out.println(f.toURI().toString());
//        System.out.println(f.getAbsolutePath());
//        System.out.println(f.getCanonicalPath().toString());
        if (f != null) {
            img.setImage(new Image(f.toURI().toString()));
            selecionada = true;
        }
    }

    public Color[] coresMaisOcorrencias(WritableImage wi, int quant) {
        Color[] c = new Color[quant];

        int m[][][] = new int[256][256][256];
        for (int i = 0; i < wi.getWidth(); i++) {
            for (int j = 0; j < wi.getHeight(); j++) {
                Color temp = wi.getPixelReader().getColor(i, j);
                int R = (int) Math.floor(temp.getRed() == 1 ? 255 : temp.getRed() * 256);
                int G = (int) Math.floor(temp.getGreen() == 1 ? 255 : temp.getGreen() * 256);
                int B = (int) Math.floor(temp.getBlue() == 1 ? 255 : temp.getBlue() * 256);
                m[R][G][B]++;
            }
        }
        int[][] i = new int[quant][4];
        for (int r = 0; r < 256; r++) {
            for (int g = 0; g < 256; g++) {
                for (int b = 0; b < 256; b++) {
                    for (int q = 0; q < quant; q++) {
                        if (m[r][g][b] > i[q][3]) {
                            for (int k = quant - 1; k > q; k--) {
                                i[k][0] = i[k - 1][0];
                                i[k][1] = i[k - 1][1];
                                i[k][2] = i[k - 1][2];
                                i[k][3] = i[k - 1][3];
                            }
                            i[q][0] = r;
                            i[q][1] = g;
                            i[q][2] = b;
                            i[q][3] = m[r][g][b];
                            m[r][g][b] = 0;
                            break;
                        }
                    }
                }
            }
        }
        for (int k = 0; k < quant; k++) {
            System.out.println("Cor" + k + "\t R:" + i[k][0] + "\tG:" + i[k][1] + "\tB:" + i[k][2]);
            double r = i[k][0] / 255.0;
            double g = i[k][1] / 255.0;
            double b = i[k][2] / 255.0;
            c[k] = new Color(r, g, b, 1);

//            System.out.println("COR " + k);
//            System.out.println("R:" + i[k][0]);
//            System.out.println("G:" + i[k][1]);
//            System.out.println("B:" + i[k][2] + "\n");
        }
        return c;
    }

    public boolean maisProPreto(Color c) {
        double R = c.getRed();
        double G = c.getGreen();
        double B = c.getBlue();

        if (((R + B + G) / 3) < 0.5) {
            return true;
        }
        return false;
    }

    @FXML
    public void pretoEBranco() {
        if (img.getImage() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "IMAGEM NÃO SELECIONADA");
            a.show();
        } else {
            WritableImage wi = img.snapshot(null, null);
            for (int i = 0; i < wi.getWidth(); i++) {
                for (int j = 0; j < wi.getHeight(); j++) {
                    if (maisProPreto(wi.getPixelReader().getColor(i, j))) {
                        wi.getPixelWriter().setColor(i, j, Color.BLACK);
                    } else {
                        wi.getPixelWriter().setColor(i, j, Color.WHITE);
                    }
                }
            }
            img.setImage(wi);
        }
    }

    public Color sePareceMaisCom(Color[] conjunto, Color origem) {
        double[] dist = new double[conjunto.length];
        for (int i = 0; i < conjunto.length; i++) {
            dist[i] = Math.sqrt(Math.pow(conjunto[i].getRed() - origem.getRed(), 2)
                    + Math.pow(conjunto[i].getGreen() - origem.getGreen(), 2)
                    + Math.pow(conjunto[i].getBlue() - origem.getBlue(), 2));
        }
        int pos = 0;
        for (int i = 1; i < conjunto.length; i++) {
            if (dist[i] < dist[pos]) {
                pos = i;
            }
        }
        return conjunto[pos];
    }

    @FXML
    public void reducaoDeCores() {
        if (img.getImage() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "IMAGEM NÃO SELECIONADA");
            a.show();
        } else {
            try {
                int quant = Integer.parseInt(quantCores.getText());
                Color[] c = coresMaisOcorrencias(img.snapshot(null, null), quant);
                WritableImage wi = img.snapshot(null, null);
                for (int i = 0; i < wi.getWidth(); i++) {
                    for (int j = 0; j < wi.getHeight(); j++) {
                        wi.getPixelWriter().setColor(i, j, sePareceMaisCom(c, wi.getPixelReader().getColor(i, j)));
                    }
                }
                img.setImage(wi);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "NÚMERO INVÁLIDO");
                a.show();
            }

        }
    }

    public double[] calcRotacaoPonto(double x, double y, double radians) {
        double seno = Math.sin(radians);
        double cosseno = Math.cos(radians);
        double p[] = new double[2];
        p[0] = (x * cosseno - y * seno);
        p[1] = (x * seno + y * cosseno);
        return p;
    }

    @FXML
    public void rotacao() {
        if (img.getImage() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "IMAGEM NÃO SELECIONADA");
            a.show();
        } else {
            try {
                WritableImage wi = img.snapshot(null, null);
                int angulo = Integer.parseInt(grausRotac.getText());
                double radiano = (2 * Math.PI * angulo) / 360;
                double seno = Math.sin(radiano);
                double cosseno = Math.cos(radiano);

                double[] p1 = calcRotacaoPonto(wi.getWidth(), 0, radiano);
                double[] p2 = calcRotacaoPonto(wi.getWidth(), wi.getHeight(), radiano);
                double[] p3 = calcRotacaoPonto(0, wi.getHeight(), radiano);
                int largura = (int) Math.ceil(Math.abs(Double.max(p1[0], Double.max(p2[0], p3[0])) - Double.min(p1[0], Double.min(p2[0], p3[0]))));
                int altura = (int) Math.ceil(Math.abs(Double.max(p1[1], Double.max(p2[1], p3[1])) - Double.min(p1[1], Double.min(p2[1], p3[1]))));
                System.out.println("Largura: " + largura + "\nAltura: " + altura);
                int correcaoX = (int) Math.abs(0 - Double.min(p1[0], Double.min(p2[0], p3[0])));
                //teste
                int correcaoY;
                if (Double.min(p1[1], Double.min(p2[1], p3[1])) < 0) {
                    correcaoY = (int) Math.abs(0 - Double.min(p1[1], Double.min(p2[1], p3[1])));
                } else {
                    correcaoY = 0;
                }

                WritableImage nova = new WritableImage(5 * largura, 5 * altura);

                for (int i = 0; i < wi.getWidth(); i++) {
                    for (int j = 0; j < wi.getHeight(); j++) {
                        double p[] = calcRotacaoPonto(i, j, radiano);
                        nova.getPixelWriter().setColor((int) p[0] + correcaoX, (int) p[1] + correcaoY, wi.getPixelReader().getColor(i, j));
                    }
                }
                img.setImage(nova);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "GRAU INVALIDO");
                a.show();
            }
        }
    }

    public double[] calcEscala(double x, double y, double Sx, double Sy) {
        double p[] = new double[2];
        p[0] = x * Sx;
        p[1] = y * Sy;
        return p;
    }

    @FXML
    public void escala() {
        if (img.getImage() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "IMAGEM NÃO SELECIONADA");
            a.show();
        } else {
            try {
                WritableImage wi = img.snapshot(null, null);
                double Sx = Double.parseDouble(sx.getText());
                double Sy = Double.parseDouble(sy.getText());

                double[] p1 = calcEscala(wi.getWidth(), 0, Sx, Sy);
                double[] p2 = calcEscala(wi.getWidth(), wi.getHeight(), Sx, Sy);
                double[] p3 = calcEscala(0, wi.getHeight(), Sx, Sy);
                int largura = (int) Math.ceil(Math.abs(Double.max(p1[0], Double.max(p2[0], p3[0])) - Double.min(p1[0], Double.min(p2[0], p3[0]))));
                int altura = (int) Math.ceil(Math.abs(Double.max(p1[1], Double.max(p2[1], p3[1])) - Double.min(p1[1], Double.min(p2[1], p3[1]))));
                System.out.println("Largura: " + largura + "\nAltura: " + altura);
                int correcaoX = (int) Math.abs(0 - Double.min(p1[0], Double.min(p2[0], p3[0])));
                //teste
                int correcaoY;
                if (Double.min(p1[1], Double.min(p2[1], p3[1])) < 0) {
                    correcaoY = (int) Math.abs(0 - Double.min(p1[1], Double.min(p2[1], p3[1])));
                } else {
                    correcaoY = 0;
                }

                WritableImage nova = new WritableImage(5 * largura, 5 * altura);

                for (int i = 0; i < wi.getWidth(); i++) {
                    for (int j = 0; j < wi.getHeight(); j++) {
                        double p[] = calcEscala(i, j, Sx, Sy);
                        nova.getPixelWriter().setColor((int) p[0] + correcaoX, (int) p[1] + correcaoY, wi.getPixelReader().getColor(i, j));
                    }
                }
                img.setImage(nova);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "VALORES INVALIDOS");
                a.show();
            }

        }
    }

    public double[] calcTranslacao(double x, double y, double Tx, double Ty) {
        double p[] = new double[2];
        p[0] = x + Tx;
        p[1] = y + Ty;
        return p;
    }

    @FXML
    public void translacao() {
        if (img.getImage() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "IMAGEM NÃO SELECIONADA");
            a.show();
        } else {
            try {
                WritableImage wi = img.snapshot(null, null);
                double Tx = Double.parseDouble(tx.getText());
                double Ty = Double.parseDouble(ty.getText());
                if (Tx < 0 || Ty < 0) {
                    throw new NumberFormatException();
                }
                WritableImage nova = new WritableImage((int) (wi.getWidth() + Tx), (int) (wi.getHeight() + Ty));

                for (int i = 0; i < wi.getWidth(); i++) {
                    for (int j = 0; j < wi.getHeight(); j++) {
                        double p[] = calcTranslacao(i, j, Tx, Ty);
                        nova.getPixelWriter().setColor((int) p[0], (int) p[1], wi.getPixelReader().getColor(i, j));
                    }
                }
                img.setImage(nova);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "VALORES INVALIDOS");
                a.show();
            }

        }
    }

    public double[] calcReflexao(double x, double y, int horizontal, int vertical) {
        double p[] = new double[2];
        p[0] = (x * horizontal);
        p[1] = (y * vertical);
        return p;
    }

    @FXML
    public void reflexao() {
        if (img.getImage() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "IMAGEM NÃO SELECIONADA");
            a.show();
        } else {
            try {
                WritableImage wi = img.snapshot(null, null);

                int vertical = 1, horizontal = 1;
                if (this.vertical.isSelected()) {
                    vertical = -1;
                }
                if (this.horizontal.isSelected()) {
                    horizontal = -1;
                }

                double[] p1 = calcReflexao(wi.getWidth(), 0, horizontal, vertical);
                double[] p2 = calcReflexao(wi.getWidth(), wi.getHeight(), horizontal, vertical);
                double[] p3 = calcReflexao(0, wi.getHeight(), horizontal, vertical);
                int largura = (int) Math.ceil(Math.abs(Double.max(p1[0], Double.max(p2[0], p3[0])) - Double.min(p1[0], Double.min(p2[0], p3[0]))));
                int altura = (int) Math.ceil(Math.abs(Double.max(p1[1], Double.max(p2[1], p3[1])) - Double.min(p1[1], Double.min(p2[1], p3[1]))));
                System.out.println("Largura: " + largura + "\nAltura: " + altura);
                int correcaoX = (int) Math.abs(0 - Double.min(p1[0], Double.min(p2[0], p3[0])));
                //teste
                int correcaoY;
                if (Double.min(p1[1], Double.min(p2[1], p3[1])) < 0) {
                    correcaoY = (int) Math.abs(0 - Double.min(p1[1], Double.min(p2[1], p3[1])));
                } else {
                    correcaoY = 0;
                }

                WritableImage nova = new WritableImage(5 * largura, 5 * altura);

                for (int i = 0; i < wi.getWidth(); i++) {
                    for (int j = 0; j < wi.getHeight(); j++) {
                        double p[] = calcReflexao(i, j, horizontal, vertical);
                        nova.getPixelWriter().setColor((int) p[0] + correcaoX, (int) p[1] + correcaoY, wi.getPixelReader().getColor(i, j));
                    }
                }
                img.setImage(nova);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "GRAU INVALIDO");
                a.show();
            }
        }
    }

    public double[] calcCisalhamento(double x, double y, double horizontal, double vertical) {
        double p[] = new double[2];
        if (horizontal > 0) {
            p[0] = (x + y * horizontal);
            p[1] = (y);
        } else {
            p[0] = (x);
            p[1] = (y + x * vertical);
        }
        return p;
    }

    @FXML
    public void cisalhamento() {
        if (img.getImage() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "IMAGEM NÃO SELECIONADA");
            a.show();
        } else {
            try {
                WritableImage wi = img.snapshot(null, null);

                double vertical = 0, horizontal = 0;
                if (Cv.getText().compareTo("") != 0) {
                    vertical = Double.parseDouble(Cv.getText());
                }
                if (Ch.getText().compareTo("") != 0) {
                    horizontal = Double.parseDouble(Ch.getText());
                }

                double[] p1 = calcCisalhamento(wi.getWidth(), 0, horizontal, vertical);
                double[] p2 = calcCisalhamento(wi.getWidth(), wi.getHeight(), horizontal, vertical);
                double[] p3 = calcCisalhamento(0, wi.getHeight(), horizontal, vertical);
                int largura = (int) Math.ceil(Math.abs(Double.max(p1[0], Double.max(p2[0], p3[0])) - Double.min(p1[0], Double.min(p2[0], p3[0]))));
                int altura = (int) Math.ceil(Math.abs(Double.max(p1[1], Double.max(p2[1], p3[1])) - Double.min(p1[1], Double.min(p2[1], p3[1]))));
                System.out.println("Largura: " + largura + "\nAltura: " + altura);
                int correcaoX = (int) Math.abs(0 - Double.min(p1[0], Double.min(p2[0], p3[0])));
                //teste
                int correcaoY;
                if (Double.min(p1[1], Double.min(p2[1], p3[1])) < 0) {
                    correcaoY = (int) Math.abs(0 - Double.min(p1[1], Double.min(p2[1], p3[1])));
                } else {
                    correcaoY = 0;
                }

                WritableImage nova = new WritableImage(5 * largura, 5 * altura);

                for (int i = 0; i < wi.getWidth(); i++) {
                    for (int j = 0; j < wi.getHeight(); j++) {
                        double p[] = calcCisalhamento(i, j, horizontal, vertical);
                        nova.getPixelWriter().setColor((int) p[0] + correcaoX, (int) p[1] + correcaoY, wi.getPixelReader().getColor(i, j));
                    }
                }
                img.setImage(nova);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "VALOR INVALIDO");
                a.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selecionada = false;
    }

}
