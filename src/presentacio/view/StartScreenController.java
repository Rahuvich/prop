package presentacio.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentacio.ControladorPresentacio;

import java.util.ArrayList;

public class StartScreenController {

    @FXML
    private RadioButton miniFib;
    @FXML
    private RadioButton fib;
    @FXML
    private ComboBox<String> unitatDocent;

    @FXML
    private ToggleGroup g1;
    @FXML
    private Button next;
    @FXML
    private Button quit;
    @FXML
    private Button devMode;
    @FXML
    private TextField hI;
    @FXML
    private TextField hF;

    private ControladorPresentacio cP;

    public StartScreenController() {};

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    public void setUnitatDocent (ArrayList<String> unitatDocent) {
        ObservableList<String> observableListunitatDocent = FXCollections.observableList(unitatDocent);
        this.unitatDocent.setItems(observableListunitatDocent);
        this.unitatDocent.getSelectionModel().selectFirst();

    }

    @FXML
    public void initialize() {



        //Button next
        next.setOnAction((event) -> {

            //You can also use getText() to get the text of the name of the radiobutton if we want to have more options or something
            cP.creaHorari_showInfoOrRest(unitatDocent.getSelectionModel().getSelectedItem(), Integer.parseInt(hI.getText()), Integer.parseInt(hF.getText()));

        });

        //Quit button
        quit.setOnAction((event) -> {
            System.out.println("About to exit");
            System.exit(0);

        });
        devMode.setOnAction((event) -> {
            cP.devMode_viewInfo();

        });
    }



}
