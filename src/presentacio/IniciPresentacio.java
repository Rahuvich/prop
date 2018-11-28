package presentacio;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.paint.Color.BLUE;

public class IniciPresentacio {
    private static IniciPresentacio ourInstance = new IniciPresentacio();

    public static IniciPresentacio getInstance() {
        return ourInstance;
    }

    private IniciPresentacio() {
    }

    public BorderPane getRoot() {

        BorderPane border = new BorderPane();

        border.setCenter(centerGridPane());
        border.setBottom(bottom());
        return border;

    }

    private HBox bottom() {
        //Creating Buttons
        Button bCancel = new Button("Cancel");
        Button bNext = new Button("Next");

        bCancel.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        bNext.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        EventHandler<MouseEvent> eventHandler =
                new EventHandler<javafx.scene.input.MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {
                        bCancel.setStyle("-fx-background-color: #ff0000; ");
                    }
                };
//Adding the event handler
        bCancel.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);

        HBox nextOrQuit = new HBox(bCancel, bNext);

        nextOrQuit.setAlignment(Pos.CENTER_RIGHT);

        return nextOrQuit;

    }

    private GridPane centerGridPane() {
        //creating label email
        Text queVols = new Text("Que vols crear?");
        Text hI = new Text("Hora Inici");
        Text hF = new Text("Hora Fi");

        //Creating Text Filed for HoraIni
        TextField textFieldHI = new TextField();
        TextField textFieldHF = new TextField();


        final ToggleGroup group = new ToggleGroup();
        RadioButton miniFib = new RadioButton("Mini Fib");
        RadioButton fib = new RadioButton("Fib");
        miniFib.setToggleGroup(group);
        fib.setToggleGroup(group);


        //Creating a Grid Pane
        GridPane centerGrid = new GridPane();

        //Setting size for the pane
        centerGrid.setMinSize(400,200);

        //Setting the padding
        centerGrid.setPadding(new

                Insets(10,10,10,10));

        //Setting the vertical and horizontal gaps between the columns
        centerGrid.setVgap(5);
        centerGrid.setHgap(5);

        //Setting the Grid alignment
        centerGrid.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        //Question
        centerGrid.add(queVols,0,0);

        //Adding radiobuttons
        centerGrid.add(miniFib,0,1);
        centerGrid.add(fib,0,2);

        //Adding hores dinici i fi
        centerGrid.add(hI,0,3);
        centerGrid.add(textFieldHI,1,3);
        centerGrid.add(hF,0,4);
        centerGrid.add(textFieldHF,1,4);

        //Styling nodes

        queVols.setFont(Font.font("verdana", FontWeight.BOLD,20));
        hI.setFont(Font.font("verdana",14));
        hF.setFont(Font.font("verdana",14));
//                    centerGrid.setStyle("-fx-background-color: BEIGE;");
        return centerGrid;

    }
}
