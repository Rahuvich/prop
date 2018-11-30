package presentacio.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentacio.ControladorPresentacio;

public class ViewInfoController {

    @FXML
    private Button genera;
    @FXML
    private Button quit;
    @FXML
    private Button rest;


    private ControladorPresentacio cP;

    public ViewInfoController() {};

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    @FXML
    public void initialize() {

        rest.setOnAction((event) -> {
            //Aqui
            System.out.println("rest button works");
            cP.showViewRest();
        });
        //Button next
        genera.setOnAction((event) -> {
            //Aqui sha de cridar al genera horari del controladorPresentacio
            //cP.generaHorari;
        });

        //Quit button
        quit.setOnAction((event) -> {
            System.exit(0);
        });
    }
}
