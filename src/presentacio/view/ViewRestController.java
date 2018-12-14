package presentacio.view;


import com.google.common.collect.Multimap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import presentacio.ControladorPresentacio;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private ListView listRestHoraAssig;

    @FXML
    private Button afegirRestTornAssig;
    @FXML
    private Button borrarRestTornAssig;
    @FXML
    private ComboBox<String> assigRestTornAssig;
    @FXML
    private ComboBox<String> tornRestTornAssig;
    @FXML
    private ListView<String> listRestTornAssig;

    @FXML
    private Button afegirRestTornGrup;
    @FXML
    private Button borrarRestTornGrup;


    HashMap<String, ArrayList<String[]>> rests = new HashMap<>();

    private ControladorPresentacio cP;



    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

//    public void setRestriccionsEx(Map<String, ArrayList<String>> restEx) {
//
//
//
//    }

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

            if (!rests.containsKey("RestTornAssig")) {
                rests.put("RestTornAssig", null);
            }

            ArrayList<String[]> aux = rests.get("RestTornAssig");
            if (aux==null){
                aux = new ArrayList<>();
            }
            String[] actual = new String[] {assig, torn};
            aux.add(actual);

            rests.put("RestTornAssig", aux);


            listRestTornAssig.getItems().add("L'assignatura " + assig + " fara classe durant " + torn);
        });

        borrarRestTornAssig.setOnAction((event) -> {
            int selected = listRestTornAssig.getSelectionModel().getSelectedIndex();
            String[] aux = rests.get("RestTornAssig").get(selected);
            cP.deleteRestTornAssig(aux[0], aux[1]);

            listRestTornAssig.getItems().remove(selected);

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
