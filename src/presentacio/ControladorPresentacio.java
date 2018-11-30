package presentacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentacio.view.InfoOrRestController;
import presentacio.view.StartScreenController;
import presentacio.view.ViewInfoController;

import java.io.IOException;

public class ControladorPresentacio extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {

        //TestDriver.execute();
        launch(args);
    }

    //Crida a generaHorari del ControladorModel
    public void generaHorari() {}

    public void creaHorari_showInfoOrRest(boolean volFib, int horaIni, int horaFi) {
        //Cal cridar al controladorModel pq ens crei un horari amb horaIni i horaFi i cargui les assignatures de la
        //FIB si el bool Fib es true i minifib si es fals. Tambe podem posar la microfib pero no esta implementat
        System.out.println("crea horari i doncs show info or rest");
        showInfoOrRest();
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

            rootLayout.setCenter(startScreen);

            StartScreenController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfoOrRest() {
        try {
            System.out.println("Load scene 2InfoOrRest");
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
            System.out.println("viewinfo from cp");
            loader.setLocation(getClass().getResource("view/3ViewInfo.fxml"));
            AnchorPane ViewInfo = loader.load();
            System.out.println("after loading info from cp");

            rootLayout.setCenter(ViewInfo);
            System.out.println("after setting viewinfo from cp");

            ViewInfoController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showViewRest() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/3ViewInfo.fxml"));
            AnchorPane ViewRest = loader.load();

            rootLayout.setCenter(ViewRest);

//            StartScreenController controller = loader.getController();
//            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
