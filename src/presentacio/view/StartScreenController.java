package presentacio.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import presentacio.ControladorPresentacio;

public class StartScreenController {

    @FXML
    private RadioButton miniFib;
    @FXML
    private RadioButton fib;
    @FXML
    private Button next;
    @FXML
    private Button quit;
    @FXML
    private TextField hI;
    @FXML
    private TextField hF;

    private ControladorPresentacio cP;

    public StartScreenController() {};

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    @FXML
    public void initialize() {

        //Button next
        next.setOnAction((event) -> {

            //You can also use getText() to get the text of the name of the radiobutton if we want to have more options or something
            cP.creaHorari_showInfoOrRest(fib.isSelected(), Integer.parseInt(hI.getText()), Integer.parseInt(hF.getText()));

        });

        //Quit button
        quit.setOnAction((event) -> {
            System.out.println("About to exit");
            System.exit(0);

        });
    }



}
