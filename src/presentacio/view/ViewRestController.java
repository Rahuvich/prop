package presentacio.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import presentacio.ControladorPresentacio;

import java.util.ArrayList;

public class ViewRestController {

    @FXML
    private Button genera;
    @FXML
    private Button quit;
    @FXML
    private Button info;
    @FXML
    private Button ini;
    @FXML
    private Button afegirRest1;
    @FXML
    private Button borrarRest1;
    @FXML
    private ComboBox<String> assig1;
    @FXML
    private ComboBox<String> torn1;
    @FXML
    private ComboBox<String> dia1;



    private ControladorPresentacio cP;

    public ViewRestController() {};

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    @FXML
    public void initialize() {

        ini.setOnAction((event) -> {

            ObservableList<String> observableListAssigs = FXCollections.observableList(cP.getAssigs());
            assig1.setItems(observableListAssigs);


            ObservableList<String> observableListDies = FXCollections.observableList(cP.getDies());
            dia1.setItems(observableListDies);

            ArrayList<String> listTorn = new ArrayList<>();
            listTorn.add("Mati");
            listTorn.add("Tarda");
            ObservableList<String> observableListTorn = FXCollections.observableList(listTorn);
            torn1.setItems(observableListTorn);


//            ObservableList<String> observableListHores = FXCollections.observableList(cP.getHores());
//            hora2.setItems(observableListHores);


        });

        afegirRest1.setOnAction((event) -> {

            String assig = assig1.getValue();
            String torn = torn1.getValue();
            String dia = dia1.getValue();

            cP.createRest1(assig, torn, dia);
        });

        info.setOnAction((event) -> {
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
