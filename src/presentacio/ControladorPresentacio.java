package presentacio;

import domini.ControladorDomini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import presentacio.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPresentacio extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ControladorDomini td = new ControladorDomini();

    private HashMap<String, Color> colorAssig;

    private boolean viewRestAssig = false;


    public static void main(String[] args) {

        //RAUL SI VOLS EXECUTAR EN FORMATO TERMINAL DESCOMENTA LO DE tESTdRIVER.EXECUTE()

//        ControladorDomini.execute();

        launch(args);
    }

    public void creaHorari_showInfoOrRest(String uD, int horaIni, int horaFi) {
        //Cal cridar al controladorModel pq ens crei un horari amb horaIni i horaFi i cargui les assignatures de la
        //FIB si el bool Fib es true i minifib si es fals. Tambe podem posar la microfib pero no esta implementat
        td.loader(uD);
        td.creaHorari(horaIni, horaFi);
        showInfoOrRest();
    }

    public void devMode_viewInfo() {
        td.devMode();
        showViewRest();
    }

    public void generaHorari() {
        td.generaHorari();
        showViewHorari();
    }

    public void swap(String[] origin, String hora, String dia ) {


        td.swap(origin, hora, dia);
        showViewHorari();


    }


    public ArrayList<String> getAules() {
        return td.getAules();
    }

    public ArrayList<String> getAssigs() {
        return td.getAssigs();
    }

    public ArrayList<String> getGrups(String assig) {

        return td.getGrups(assig);

    }

    public ArrayList<String> getHores() {
        return td.getHores();
    }

    public ArrayList<String> getHoresFi(String iniHora) {
        return td.getHoresFi(iniHora);
    }

    public ArrayList<String> getDies() {
        return td.getDies();
    }

    public void createRestTornAssig (String assig, String torn) {
        td.createRestTornAssig(assig, torn);
    }

    public void createRestTornGrup (String assig, String grup, String torn) {
        td.createRestTornGrup(assig,grup, torn);
    }

    public void createRestHoraAssig (String assig, String h) {
        td.createRestHoraAssig(assig, h);
    }

    public void createRestHoraGrup (String assig, String grup, String hora) { td.createRestHoraGrup(assig, grup, hora);
    }
    public void createRestFranjaHoraria (String horaIni, String horaFi, String dia) { td.createRestFranjaHoraria(horaIni,horaFi,dia);
    }

    public void deleteRestTornAssig(String nomAssig, String mati) {
        td.deleteRestTornAssig(nomAssig, mati);
    }

    public void deleteRestTornGrup(String nomAssig, String nomGrup, String mati) {
        td.deleteRestTornGrup(nomAssig, nomGrup, mati);
    }
    public void deleteRestHoraAssig(String nomAssig, String h) {
        td.deleteRestHoraAssig(nomAssig, h);
    }
    public void deleteRestHoraGrup(String nomAssig, String nomGrup, String hora) {
        td.deleteRestHoraGrup(nomAssig, nomGrup, hora);
    }
    public void deleteRestFranjaHoraria(String horaIni, String horaFi, String dia) {
        td.deleteRestFranjaHoraria(horaIni, horaFi, dia);
    }




    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Schedule creator RAP");


        iniRootLayout();

        showStartScreen();
    }

    private void iniRootLayout() {
        try {
            rootLayout = FXMLLoader.load(getClass().getResource("view/0Base.fxml"));

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showStartScreen() {
        try {
            // Load scene 1StartScreen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/1StartScreen.fxml"));
            AnchorPane startScreen = loader.load();
            StartScreenController controller = loader.getController();
            controller.setUnitatDocent(td.getUnitatsDocents());
            controller.setMainApp(this);
            rootLayout.setCenter(startScreen);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfoOrRest() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/2InfoOrRest.fxml"));
            AnchorPane InfoOrRest = loader.load();

            rootLayout.setCenter(InfoOrRest);

            InfoOrRestController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showViewInfo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/3ViewInfo.fxml"));

            AnchorPane ViewInfo = loader.load();

            ViewInfoController controller = loader.getController();
            controller.setAssigs(td.getAssigs());
            controller.setAules(td.getAules());
            controller.setMainApp(this);

            rootLayout.setCenter(ViewInfo);

//            ViewInfoController controller = loader.getController();
//            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showViewRest() {
        try {

            if(!viewRestAssig) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("view/3ViewRest.fxml"));
                AnchorPane ViewRest = loader.load();

                ViewRestController controller = loader.getController();
                controller.setAssigs(getAssigs());
                controller.setHores(getHores());
                controller.setDies(getDies());
                controller.setTorns();
                controller.setRestsEx(td.getAllRest());

                controller.setMainApp(this);

                rootLayout.setCenter(ViewRest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showViewHorari() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/4ViewHorari.fxml"));
            AnchorPane ViewHorari = loader.load();
            ViewHorariController controller = loader.getController();
            controller.setClassOrigin();
            String[][][][] horari = td.getHorari();

            assignColors(horari);

            controller.setHorari(horari, td.getDies(), td.getHores(), colorAssig);
            controller.setMainApp(this);

            rootLayout.setCenter(ViewHorari);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void assignColors(String[][][][] hs) {
        if (colorAssig == null) {
            colorAssig = new HashMap<>();
            for (int i = 0; i < hs.length; ++i) {
                for (int j = 0; j < hs[0].length; ++j) {
                    for (int k = 0; k < hs[0][0].length; ++k) {
                        if (hs[i][j][k][0]!=null) {
                            if (!colorAssig.containsKey(hs[i][j][k][1])) {
                                double c1, c2, c3;
                                c1 = Math.random();
                                c2 = Math.random();
                                c3 = Math.random();
                                if ((c1 + c2 + c3) > 2.5) {
                                    if ((c1 > c2) && (c1 > c3)) c1 = c1 / 2;
                                    else if ((c2 > c1) && (c2 > c3)) c2 = c2 / 2;
                                    else c3 = c3 / 2;
                                    //if ((c3>c1) && (c3>c2))
                                }
                                colorAssig.put(hs[i][j][k][1], Color.color(c1, c2, c3));

                                System.out.println("a " + hs[i][j][k][1] + " hi ha el color " + colorAssig.get(hs[i][j][k][1]));
                            }
                        }
                    }
                }
            }
        }

    }
}
