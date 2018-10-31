import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class program {
    public static void main(String args[]) {

        // Generar Assignatures
    	ArrayList<Assignatura> vassig = new ArrayList<>();
    	ArrayList<Aula> vaules = new ArrayList<>();
    	
        try {
            vassig = Fabrica.carregaAssig("/src/dades/assig.json");
            for(int i = 0; i < vassig.size(); i++)
            {
            	System.out.println(vassig.get(i).getNomAssig());
            }
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

        //Crear clase horari
        Horari horari = new Horari();
        
        Scanner reader = new Scanner(System.in);
        System.out.println("Vols afegir una restriccio? (si/no)");

        String ans = reader.nextLine();
        if(ans.equals("si")){
            System.out.println("Assig X nomes de mati/tarda");
            System.out.println("Escriu quina assignatura: (per exemple F)");
            String assig = reader.nextLine();
            
            System.out.println("Escriu preferencia mati/tarda: (per exemple mati)");
            String pref = reader.nextLine();
            boolean mati;
            if(pref.equals("mati")) mati = true;
            else mati = false;
            horari.afegirRestriccio(new RestTornAssig(assig, mati));	
        }



        reader.close();

        // Generar horaris
    }
}
