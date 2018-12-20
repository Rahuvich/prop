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

    //Atributs RestHoraGrup
    @FXML
    private Button afegirRestHoraGrup;
    @FXML
    private Button borrarRestHoraGrup;
    @FXML
    private ComboBox<String> grupRestHoraGrup;
    @FXML
    private ComboBox<String> assigRestHoraGrup;
    @FXML
    private ComboBox<String> horaRestHoraGrup;
    @FXML
    private ListView<String> listRestHoraGrup;

    //Atributs RestFranjaHoraria
    @FXML
    private Button afegirRestFranjaHoraria;
    @FXML
    private Button borrarRestFranjaHoraria;
    @FXML
    private ComboBox<String> horaIniRestFranjaHoraria;
    @FXML
    private ComboBox<String> horaFiRestFranjaHoraria;
    @FXML
    private ComboBox<String> diaRestFranjaHoraria;
    @FXML
    private ListView<String> listRestFranjaHoraria;

    //Atributs RestSeparat
    @FXML
    private Button afegirRestSeparat;
    @FXML
    private Button borrarRestSeparat;
    @FXML
    private ComboBox<String> assigRestSeparat;
    @FXML
    private ListView<String> listRestSeparat;


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
        assigRestHoraGrup.setItems(observableListAssigs);
        assigRestSeparat.setItems(observableListAssigs);
    }

    public void setGrups (ArrayList<String> grups) {
        ObservableList<String> observableListGrups = FXCollections.observableList(grups);
        grupRestTornGrup.setItems(observableListGrups);
        grupRestHoraGrup.setItems(observableListGrups);
    }


    public void setHores (ArrayList<String> hores) {
        ObservableList<String> observableListHores = FXCollections.observableList(hores);
        horaRestHoraAssig.setItems(observableListHores);
        horaRestHoraGrup.setItems(observableListHores);
        horaIniRestFranjaHoraria.setItems(observableListHores);
        horaFiRestFranjaHoraria.setItems(observableListHores);
    }

    public void setHoresFi (ArrayList<String> hores) {
        ObservableList<String> observableListGrups = FXCollections.observableList(hores);
        horaFiRestFranjaHoraria.setItems(observableListGrups);
    }

    public void setDies (ArrayList<String> dies) {
        ObservableList<String> observableListDies = FXCollections.observableList(dies);
        diaRestFranjaHoraria.setItems(observableListDies);
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
                case "RestFranjaHoraria":
                    if(aux.isEmpty()) System.out.println("esta vacia la lista en el case");
                    else System.out.println("en la lista hay " + aux.size() + "componentes en el case");
                    setRestFranjaHoraria(aux);
                    break;
                case "RestSeparat":
                    setRestSeparat(aux);
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
    public void setRestFranjaHoraria(ArrayList<String[]> list) {
        if(list.isEmpty()) System.out.println("esta vacia la lista");
        else System.out.println("en la lista hay " + list.size() + "componentes");
        for (int i=0; i<list.size(); ++i) {
            String[] aux = list.get(i);
            int d = Integer.parseInt(aux[2]);
            aux[2] = getStringFromdia(d);
            listRestFranjaHoraria.getItems().add("De " + aux[0] + " a " + aux[1] +" els " + aux[2] + " será horari no lectiu");

        }
    }
    public void setRestSeparat (ArrayList<String[]> list) {
        for (int i=0; i<list.size(); ++i) {
            String[] aux = list.get(i);
            listRestSeparat.getItems().add("L'assignatura " + aux[0] + " es realitzara en el maxim de dies posibles ");

        }
    }

    private String getStringFromdia(int d)
    {
        String dia = " ";
        switch (d) {
            case 0:
                dia = "DILLUNS";
                break;
            case 1:
                dia = "DIMARTS";
                break;
            case 2:
                dia = "DIMECRES";
                break;
            case 3:
                dia = "DIJOUS";
                break;
            case 4:
                dia = "DIVENDRES";
                break;
        }
        return dia;
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

        /*
         ** RESTHORAASSIG
         */


        afegirRestHoraAssig.setOnAction((event) -> {

            String assig = assigRestHoraAssig.getValue();
            String h = horaRestHoraAssig.getValue();
            cP.createRestHoraAssig(assig, h);
            if (!rests.containsKey("RestHoraAssig")) {
                rests.put("RestHoraAssig", null);
            }

            ArrayList<String[]> aux = rests.get("RestHoraAssig");
            if (aux==null){
                aux = new ArrayList<>();
            }
            String[] actual = new String[] {assig, h};
            aux.add(actual);

            rests.put("RestHoraAssig", aux);

            listRestHoraAssig.getItems().add("L'assignatura " + assig +" no fara classe a les " + h);


        });

        borrarRestHoraAssig.setOnAction((event) -> {
            int selected = listRestHoraAssig.getSelectionModel().getSelectedIndex();
            String[] aux = rests.get("RestHoraAssig").get(selected);
            System.out.println("from viewrestcontroller vull elminiar rest torn assig de lassig " + aux[0] + " a les " + aux[1]);
            cP.deleteRestHoraAssig(aux[0], aux[1]);
            System.out.println("from viewrestcontroller he fet el delete");
            listRestHoraAssig.getItems().remove(selected);

        });
        /*
         ** RESTHORAGRUP
         */
        assigRestHoraGrup.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    setGrups(cP.getGrups(newValue));
                }
        );

        afegirRestHoraGrup.setOnAction((event) -> {
            String assig = assigRestHoraGrup.getValue();
            String grup = grupRestHoraGrup.getValue();
            String hora = horaRestHoraGrup.getValue();
            cP.createRestHoraGrup(assig, grup, hora);

            if (!rests.containsKey("RestHoraGrup")) {
                rests.put("RestHoraGrup", null);
            }

            ArrayList<String[]> aux = rests.get("RestHoraGrup");
            if (aux==null){
                aux = new ArrayList<>();
            }
            String[] actual = new String[] {assig, grup, hora};
            aux.add(actual);

            rests.put("RestHoraGrup", aux);

            listRestHoraGrup.getItems().add("L'assignatura " + assig + " del grup " + grup +" no fara classe a les " + hora);
        });

        borrarRestHoraGrup.setOnAction((event) -> {
            int selected = listRestHoraGrup.getSelectionModel().getSelectedIndex();
            String[] aux = rests.get("RestHoraGrup").get(selected);
            System.out.println("from viewrestcontroller vull elminiar rest torn assig de lassig " + aux[0] + " del grup " + aux[1] +" al torn " + aux[2]);
            cP.deleteRestHoraGrup(aux[0], aux[1], aux[2]);
            System.out.println("from viewrestcontroller he fet el delete");
            listRestHoraGrup.getItems().remove(selected);

        });
        /*
         ** RESTFRANJAHORARIA
         */
        horaIniRestFranjaHoraria.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    setHoresFi(cP.getHoresFi(newValue));
                }
        );

        afegirRestFranjaHoraria.setOnAction((event) -> {
            String horaIni = horaIniRestFranjaHoraria.getValue();
            String horaFi = horaFiRestFranjaHoraria.getValue();
            String dia = diaRestFranjaHoraria.getValue();
            cP.createRestFranjaHoraria(horaIni, horaFi, dia);

            if (!rests.containsKey("RestFranjaHoraria")) {
                rests.put("RestFranjaHoraria", null);
            }

            ArrayList<String[]> aux = rests.get("RestFranjaHoraria");
            if (aux==null){
                aux = new ArrayList<>();
            }
            String[] actual = new String[] {horaIni, horaFi, dia};
            aux.add(actual);

            rests.put("RestFranjaHoraria", aux);

            listRestFranjaHoraria.getItems().add("De " + horaIni + " a " + horaFi +" els " + dia + " será horari no lectiu");
        });

        borrarRestFranjaHoraria.setOnAction((event) -> {
            int selected = listRestFranjaHoraria.getSelectionModel().getSelectedIndex();
            String[] aux = rests.get("RestFranjaHoraria").get(selected);
            System.out.println("from viewrestcontroller vull elminiar rest torn assig de lassig " + aux[0] + " del grup " + aux[1] +" al torn " + aux[2]);
            cP.deleteRestFranjaHoraria(aux[0], aux[1], aux[2]);
            System.out.println("from viewrestcontroller he fet el delete");
            listRestFranjaHoraria.getItems().remove(selected);

        });


        /*
         ** RESTSEPARAT
         */
        afegirRestSeparat.setOnAction((event) -> {
            String assig = assigRestSeparat.getValue();
            cP.createRestSeparat(assig);

            if (!rests.containsKey("RestSeparat")) {
                rests.put("RestSeparat", null);
            }

            ArrayList<String[]> aux = rests.get("RestSeparat");
            if (aux==null){
                aux = new ArrayList<>();
            }
            String[] actual = new String[] {assig};
            aux.add(actual);

            rests.put("RestSeparat", aux);


            listRestSeparat.getItems().add("L'assignatura " + assig + " es realitzara en el maxim de dies posibles ");
        });

        borrarRestSeparat.setOnAction((event) -> {
            System.out.println("entro en borrarRestSeparat");
            int selected = listRestSeparat.getSelectionModel().getSelectedIndex();
            System.out.println("abans string");
            if(rests.containsKey("RestSeparat")) System.out.println("la conte");
            else System.out.println("no la conte");
            String[] aux = rests.get("RestSeparat").get(selected);
            System.out.println(aux[0]);
            System.out.println("from viewrestcontroller vull elminiar rest torn assig de lassig " + aux[0]);
            cP.deleteRestSeparat(aux[0]);
            System.out.println("from viewrestcontroller he fet el delete");
            listRestSeparat.getItems().remove(selected);

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
