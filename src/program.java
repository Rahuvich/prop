import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

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

        Scanner reader = new Scanner(System.in);
        System.out.println("Vols afegir una restricció? (si/no)");

        String ans = reader.nextLine();
        if(ans.equals("si")){
            System.out.println("Assig X només de mati/tarda");
            System.out.println("Escriu quina assignatura: (per exemple F)");
            String assig = reader.nextLine();
            System.out.println("Escriu preferencia mati/tarda: (per exemple mati)");
            String pref = reader.nextLine();
            boolean mati;
            if(pref.equals("mati")) mati = true;
            else mati = false;
            new Restriccio(assig, mati);
        }



        reader.close();

        // Generar horaris
    }
}
