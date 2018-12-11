package presentacio.view;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import presentacio.ControladorPresentacio;

        import java.util.ArrayList;

public class ViewHorariController {

    @FXML
    private Button rest;
    @FXML
    private Button quit;


    private ControladorPresentacio cP;

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    public void setHorari (ArrayList<String> horariGran) {

    }

    @FXML
    public void initialize() {

        rest.setOnAction((event) -> { 
            cP.showViewRest();
        });

        quit.setOnAction((event) -> {
            System.exit(0);
        });
    }
}
