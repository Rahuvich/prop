import org.json.simple.parser.ParseException;

import java.io.IOException;

public class program {
    public static void main(String args[]) {

        // Generar Assignatures
        try {
            Fabrica.carregaAssig("/src/dades/assig.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Generar Aules
        try {
            Fabrica.carregaAules("/src/dades/aules.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // Generar horaris
    }
}
