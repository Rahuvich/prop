package presentacio.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import presentacio.ControladorPresentacio;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewInfoController {

    @FXML
    private Button genera;
    @FXML
    private Button quit;
    @FXML
    private Button rest;
    @FXML
    private ListView<String> aules;
    @FXML
    private ListView<String> assigs;


    private ControladorPresentacio cP;

    public ViewInfoController() {};

    public void setMainApp(ControladorPresentacio contPres) {
        this.cP = contPres;
    }

    public void setAssigs(ArrayList<String> ass) {
        ObservableList<String> observableListAssigs = FXCollections.observableList(ass);
        assigs.setItems(observableListAssigs);
    }
    public void setAules(ArrayList<String> aul) {
        ObservableList<String> observableListAules = FXCollections.observableList(aul);
        aules.setItems(observableListAules);
    }

    @FXML
    public void initialize() {

        rest.setOnAction((event) -> {

            cP.showViewRest();
        });
        //Button next
        genera.setOnAction((event) -> {
            //Aqui sha de cridar al genera horari del controladorPresentacio
            System.out.println("genera horari from view info");
            cP.generaHorari();
        });

        //Quit button
        quit.setOnAction((event) -> {
            System.exit(0);
        });
    }
}
