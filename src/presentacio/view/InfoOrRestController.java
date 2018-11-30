package presentacio.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import presentacio.ControladorPresentacio;

public class InfoOrRestController {

    @FXML
    private Button genera;
    @FXML
    private Button quit;
    @FXML
    private Button info;
    @FXML
    private Button rest;


    private ControladorPresentacio cP;

    public InfoOrRestController() {};

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    @FXML
    public void initialize() {

        info.setOnAction((event) -> {
            //Aqui
            System.out.println("info button works");
            cP.showViewInfo();
        });
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
            System.out.println("About to exit");
            System.exit(0);
//            cP.showInfoOrRest();
//            outputTextArea.appendText("Button Action\n");
        });
    }




}
