package presentacio.view;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.layout.BorderPane;
        import javafx.scene.layout.GridPane;
        import presentacio.ControladorPresentacio;

        import java.lang.reflect.Array;
        import java.util.ArrayList;

public class ViewHorariController {

    @FXML
    private Button rest;
    @FXML
    private Button quit;
    @FXML
    private BorderPane borderPaneViewHorari;


    private ControladorPresentacio cP;

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    //hs[dia][hora][aula]

    public void setHorari (String[][][][] hs, ArrayList<String> dies, ArrayList<String> hores) {

        GridPane horariGrid = new GridPane();
        //set days
        for (int i = 1; i <= hs.length; ++i) horariGrid.add(new Label(dies.get(i-1)), i, 0);
        //set hours
        for (int i = 1; i <= hs[0].length; ++i) horariGrid.add(new Label(hores.get(i-1)), 0, i);


        ScrollPane horariScroll = new ScrollPane(horariGrid);


        Button[][][] classes = new Button[hs.length][hs[0].length][hs[0][0].length];

        for (int i = 0; i < hs.length; ++i) {
            for (int j = 0; j < hs[0].length; ++j) {
                ListView<Button> listHora = new ListView<>();
                for (int k = 0; k < hs[0][0].length; ++k) {

                    //Declares button text
                    String text;
                    if (hs[i][j][k][0] != null) {
                        text = hs[i][j][k][0] + " " + hs[i][j][k][1] + " " + hs[i][j][k][2];
                    } else {
                        text = "Classe disponible";
                    }

                    //Assings button event

                    //Assigns button to list
                    classes[i][j][k] = new Button(text);
                    listHora.getItems().add(classes[i][j][k]);


                }
                horariGrid.add(listHora, i+1, j+1);
            }
        }

        borderPaneViewHorari.setCenter(horariScroll);
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
