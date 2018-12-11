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

    //Atributs RestHoraAssig
    @FXML
    private Button afegirRestHoraAssig;
    @FXML
    private Button borrarRestHoraAssig;
    @FXML
    private ComboBox<String> assigRestHoraAssig;
    @FXML
    private ComboBox<String> horaRestHoraAssig;

    @FXML
    private Button afegirRestTornAssig;
    @FXML
    private Button borrarRestTornAssig;
    @FXML
    private ComboBox<String> assigRestTornAssig;
    @FXML
    private ComboBox<String> tornRestTornAssig;

    @FXML
    private Button afegirRestTornGrup;
    @FXML
    private Button borrarRestTornGrup;


    private ControladorPresentacio cP;



    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    public void setAssigs (ArrayList<String> assigs) {
        ObservableList<String> observableListAssigs = FXCollections.observableList(assigs);
        assigRestHoraAssig.setItems(observableListAssigs);
        assigRestTornAssig.setItems(observableListAssigs);
    }

    public void setHores (ArrayList<String> hores) {
        ObservableList<String> observableListHores = FXCollections.observableList(hores);
        horaRestHoraAssig.setItems(observableListHores);
    }

    public void setTorns() {
        ArrayList<String> listTorn = new ArrayList<>();
        listTorn.add("Mati");
        listTorn.add("Tarda");
        ObservableList<String> observableListTorn = FXCollections.observableList(listTorn);
        tornRestTornAssig.setItems(observableListTorn);
    }

    @FXML
    public void initialize() {

        afegirRestTornAssig.setOnAction((event) -> {

            String assig = assigRestTornAssig.getValue();
            String torn = tornRestTornAssig.getValue();
            cP.createRestTornAssig(assig, torn);

        });

        afegirRestHoraAssig.setOnAction((event) -> {

            String assig = assigRestTornAssig.getValue();
            String torn = tornRestTornAssig.getValue();
            cP.createRestTornAssig(assig, torn);

        });

        info.setOnAction((event) -> {
            cP.showViewInfo();
        });
        //Button next
        genera.setOnAction((event) -> {

            //Aqui sha de cridar al genera horari del controladorPresentacio
            cP.generaHorari();
        });

        //Quit button
        quit.setOnAction((event) -> {
            System.exit(0);
        });
    }
}
