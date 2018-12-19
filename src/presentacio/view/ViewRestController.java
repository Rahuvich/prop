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


//Atributs RestTornAssig
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
    private ListView<String> listRestHoraAssig;

    //Atributs RestTornGrup
    @FXML
    private Button afegirRestTornGrup;
    @FXML
    private Button borrarRestTornGrup;
    @FXML
    private ComboBox<String> grupRestTornGrup;
    @FXML
    private ComboBox<String> assigRestTornGrup;
    @FXML
    private ComboBox<String> tornRestTornGrup;
    @FXML
    private ListView<String> listRestTornGrup;

    //Atributs RestHoraGrup
    @FXML
    private Button afegirRestHoraGrup;
    @FXML
    private Button borrarRestHoraGrup;
    @FXML
    private ComboBox<String> assigRestHoraGrup;
    @FXML
    private ComboBox<String> tornRestHoraGrup;
    @FXML
    private ListView<String> listRestHoraGrup;


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
        assigRestTornGrup.setItems(observableListAssigs);
    }

    public void setGrups (ArrayList<String> grups) {
        ObservableList<String> observableListGrups = FXCollections.observableList(grups);
        grupRestTornGrup.setItems(observableListGrups);
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
        tornRestTornGrup.setItems(observableListTorn);
    }

    public void setRestsEx (HashMap<String, ArrayList<String[]>> r) {

        rests = r;

        for (Map.Entry<String, ArrayList<String[]>> entry : r.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            ArrayList<String[]> aux = entry.getValue();
            switch (entry.getKey()) {
                case "RestTornAssig":
                    setRestTornAssig(aux);
                    break;
                case "RestHoraAssig":
                    setRestHoraAssig(aux);
                    break;
                case "RestHoraGrup":
                    setRestHoraGrup(aux);
                    break;
                case "RestTornGrup":
                    setRestTornGrup(aux);
                    break;

            }

        }


    }

    public void setRestTornAssig (ArrayList<String[]> list) {
        for (int i=0; i<list.size(); ++i) {
            String[] aux = list.get(i);
            listRestTornAssig.getItems().add("L'assignatura " + aux[0] + " fara classe durant " + aux[1]);

        }
    }
    public void setRestHoraAssig (ArrayList<String[]> list) {
        for (int i=0; i<list.size(); ++i) {
            String[] aux = list.get(i);
            listRestHoraAssig.getItems().add("L'assignatura " + aux[0] + " no fara classe durant les" + aux[1]);

        }
    }
    public void setRestTornGrup (ArrayList<String[]> list) {
        for (int i=0; i<list.size(); ++i) {
            String[] aux = list.get(i);
            listRestTornGrup.getItems().add("El grup " + aux[1] + " de l'assignatura " + aux[0] + " no fara classe durant " + aux[2]);
        }
    }
    public void setRestHoraGrup (ArrayList<String[]> list) {
        for (int i=0; i<list.size(); ++i) {
            String[] aux = list.get(i);
            listRestHoraGrup.getItems().add("El grup " + aux[1] + " de l'assignatura " + aux[0] + " no fara classe durant les" + aux[2]);
        }
    }

    public String getAssig()
    {
        String assig = assigRestTornGrup.getValue();
        System.out.println("String de assigRestTornGrup= " + assig);
        return assig;
        //assigRestTornGrup.

    }



    @FXML
    public void initialize() {


        /*
         ** RESTTORNASSIG
         */
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
            System.out.println("from viewrestcontroller vull elminiar rest torn assig de lassig " + aux[0] + " al torn " + aux[1]);
            cP.deleteRestTornAssig(aux[0], aux[1]);

            listRestTornAssig.getItems().remove(selected);

        });

        /*
        ** RESTTORNGRUP
         */
        assigRestTornGrup.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            setGrups(cP.getGrups(newValue));
        }
        );

        afegirRestTornGrup.setOnAction((event) -> {
            String assig = assigRestTornGrup.getValue();
            String grup = grupRestTornGrup.getValue();
            String torn = tornRestTornGrup.getValue();
            cP.createRestTornGrup(assig, grup, torn);

            if (!rests.containsKey("RestTornGrup")) {
                rests.put("RestTornGrup", null);
            }

            ArrayList<String[]> aux = rests.get("RestTornGrup");
            if (aux==null){
                aux = new ArrayList<>();
            }
            String[] actual = new String[] {assig, grup, torn};
            aux.add(actual);

            rests.put("RestTornGrup", aux);

            listRestTornGrup.getItems().add("L'assignatura " + assig + " del grup " + grup +" fara classe de " + torn);
        });

        borrarRestTornGrup.setOnAction((event) -> {
            int selected = listRestTornGrup.getSelectionModel().getSelectedIndex();
            String[] aux = rests.get("RestTornGrup").get(selected);
            System.out.println("from viewrestcontroller vull elminiar rest torn assig de lassig " + aux[0] + " del grup " + aux[1] +" al torn " + aux[2]);
            cP.deleteRestTornGrup(aux[0], aux[1], aux[2]);
            System.out.println("from viewrestcontroller he fet el delete");
            listRestTornGrup.getItems().remove(selected);

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
