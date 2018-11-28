package presentacio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorPresentacio extends Application {
    private int estat = 0;

    @Override
    public void start(Stage stage) {

        BorderPane border = IniciPresentacio.getInstance().getRoot();
        Scene scene = new Scene(border, 800, 500);

        //Setting title to the Stage
        stage.setTitle("My Schedule Creator");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]){
            launch(args);
        }
    }
