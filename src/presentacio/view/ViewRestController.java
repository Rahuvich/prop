package presentacio.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentacio.ControladorPresentacio;

public class ViewRestController {

    @FXML
    private Button genera;
    @FXML
    private Button quit;
    @FXML
    private Button info;


    private ControladorPresentacio cP;

    public ViewRestController() {};

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    @FXML
    public void initialize() {

        info.setOnAction((event) -> {
            //Aqui
            System.out.println("rest button works");
            cP.showViewInfo();
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
