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
    private Button getinfo;
    @FXML
    private ListView<String> aules;
    @FXML
    private ListView<String> assigs;


    private ControladorPresentacio cP;

    public ViewInfoController() {};

    public void setMainApp(ControladorPresentacio contPres) {
        System.out.println("sets main app");
        this.cP = contPres;}

    @FXML
    public void initialize() {


        getinfo.setOnAction((event) -> {
            ArrayList<String> listAules = new ArrayList<>();
            listAules.addAll(cP.getAules());
            ObservableList<String> observableListAules = FXCollections.observableList(listAules);
            aules.setItems(observableListAules);

            ArrayList<String> listAssigs = new ArrayList<>();
            listAssigs.addAll(cP.getAssigs());
            ObservableList<String> observableListAssigs = FXCollections.observableList(listAssigs);
            assigs.setItems(observableListAssigs);
        });

        rest.setOnAction((event) -> {
            //Aqui
            System.out.println("rest button works");
            if(cP == null) System.out.println("nulll");
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
