package presentacio.view;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.geometry.Insets;
        import javafx.scene.control.*;
        import javafx.scene.layout.BorderPane;
        import javafx.scene.layout.ColumnConstraints;
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


    @FXML
    private Label classOrigin;
    @FXML
    private Label classDestination;

    private Button[][][] classes;

    private boolean classSelected = false;

//    public Classe(Aula a, Grup g, Dia dia, int h, int duracio)
//    {
//        this.aula = a;
//        this.grup = g;
//        this.dia = dia;
//        this.horaIni = h;
//        this.duracio = duracio;
//        empty = false;
//    }


    private ControladorPresentacio cP;

    public void setMainApp(ControladorPresentacio contPres) {this.cP = contPres;}

    public void setClassOrigin() {
        classOrigin.setText("No sha seleccionat res");
    }

    //hs[dia][hora][aula]

    public void setHorari (String[][][][] hs, ArrayList<String> dies, ArrayList<String> hores) {


        /*
        Idea1:
        array per guardar si hi ha boto de classe disponible
        array per guardar valors importants per tornar a fer referencia al domini

        Idea2:
        label selected,  2 buttons, select class, select hueco

        Idea3: depends on what label is selected, it knows automatically

        */
        GridPane horariGrid = new GridPane();
        ColumnConstraints c0 = new ColumnConstraints();
        c0.setPercentWidth(3);
        horariGrid.getColumnConstraints().addAll(c0);
        horariGrid.setGridLinesVisible(true);
        horariGrid.setPadding(new Insets(10, 10, 10, 10));
        //set days
        for (int i = 1; i <= hs.length; ++i) horariGrid.add(new Label(dies.get(i-1)), i, 0);
        //set hours
        for (int i = 1; i <= hs[0].length; ++i) horariGrid.add(new Label(hores.get(i-1)), 0, i);


        ScrollPane horariScroll = new ScrollPane(horariGrid);

        horariScroll.setFitToWidth(true);


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
                        text = dies.get(i) + " a les " + hores.get(j);
                    }

                    Button auxB = new Button(text);



                    //Assings button even

                    auxB.setOnAction(new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent e) {
                            if (!classSelected) {
                                classSelected=true;
                                classOrigin.setText(auxB.getText());
                            }
                            else {
                                classSelected=false;
                                //getrowindex and col no van
                                classDestination.setText(auxB.getText());
                                cP.swap(classOrigin.getText(), classDestination.getText());
                            }

                        }
                    });

                    classes[i][j][k] = auxB;

                    //Assigns button to list
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
