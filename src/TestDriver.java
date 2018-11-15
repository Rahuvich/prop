import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestDriver {

    private static ArrayList<Assignatura> vassig = new ArrayList<>();
    private static ArrayList<Aula> vaules = new ArrayList<>();

    private static Scanner reader = new Scanner(System.in);
    
    private static Horari horari;

    public static void execute(){
    	chooseMode();
    	
/*        loader();

    	creaHorari();

        tester();

        crearRestriccions();
        
        
        
        //horari.generarClasseGrup(null);
        
        //horari.printHorari();

        reader.close();
*/    }
    
    private static void chooseMode() {
    	System.out.println("Qui ets?");
        System.out.println("1. Dev team (cargara automaticament la miniFIB amb horaIni 8 i horaFi 20, es saltara els testers i anira directament a restriccions");
        System.out.println("2. Horacio");

        switch (readInput()){
        case 1:
            try {
                vaules = Fabrica.carregaAules("/src/dades/miniAules.json");
                vassig = Fabrica.carregaAssig("/src/dades/miniAssig.json");
                System.out.println("Assignatures y aules de la miniFIB creades");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        	creaHorari(8, 20);

            crearRestriccions();
            
            //horari.generarClasseAssig(Assignatura assig, int i, ArrayList<Grup> grupsAssig, boolean solucio[], boolean foundAssig, boolean marcadesAnt[][][],int filaAnt, int colAnt, int aulaAnt, boolean vueltaAtras);
            
            //horari.printHorari();

            reader.close();
            break;
        case 2:
        default:
        	loader();
        	
        	System.out.println("Insereix l'hora en que vulguis que comencin les classes");
        	int horaIni = readInput();

        	System.out.println("Insereix l'hora en que vulguis que acabin les classes");
        	int horaFi = readInput();
        	
        	creaHorari(horaIni, horaFi);

            tester();

            crearRestriccions();
            reader.close();
            break;
        }

        horari.generaTot();
    }
    
 

	private static void creaHorari(int ini, int fi) {

    	horari = new Horari(ini, fi, vassig, vaules);
    	
    	System.out.println("El dia lectiu comenca a les " + horari.getHoraIni() + " i acaba a les " + horari.getHoraFi());
    }

    private static void crearRestriccions(){
        System.out.println("Vols crear alguna restriccio?");
        System.out.println("1. Si");
        System.out.println("2. No");

        switch (readInput()){
            case 1:
                llistaRestriccions();
            case 2:
            default:
        }
    }

    private static void llistaRestriccions() {
        System.out.println("Escolleix restriccio");
        System.out.println("1. Restringir el torn mati/tarda d'una assignatura");
        System.out.println("2. Restringir el torn mati/tarda d'un grup");
        System.out.println("3. Torna enrere");

        switch (readInput()){
            case 1:
                crearRestTornAssig();
                llistaRestriccions();
                break;
            case 2:
                crearRestTornGrup();
                llistaRestriccions();
                break;
            case 3:
            default:
        }
    }

    private static void crearRestTornGrup() {
        System.out.println("Escolleix assignatura");
        for (int i = 0; i<vassig.size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(i).getNomAssig());
        }
        int assigIndex = readInput();
        for (int i = 0; i<vassig.get(assigIndex-1).getGrups().size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(assigIndex-1).getGrups().get(i).getNumero());
        }
        int grup = readInput();

        boolean mati = false;
        System.out.println("Escolleix mati o tarda");
        System.out.println("1. Mati");
        System.out.println("2. Tarda");
        switch (readInput()){
            case 1:
                mati = true;
                break;
            case 2:
                mati = false;
                break;
            case 3:
            default:
        }

        System.out.print("El grup " + vassig.get(assigIndex-1).getGrups().get(grup-1).getNumero() +  " de l'assignatura " + vassig.get(assigIndex-1).getNomAssig() + " nomes podra ser de ");
        if(mati) System.out.print("mati");
        else System.out.print("tarda");
        System.out.println();
        
        RestTornGrup res = new RestTornGrup(vassig.get(assigIndex-1).getGrups().get(grup-1), mati);
        horari.afegirRestriccio(res);
    }

    private static void crearRestTornAssig() {
        System.out.println("Escolleix assignatura");
        for (int i = 0; i<vassig.size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(i).getNomAssig());
        }

        int assigIndex = readInput();

        boolean mati = false;
        System.out.println("Escolleix mati o tarda");
        System.out.println("1. Mati");
        System.out.println("2. Tarda");
        switch (readInput()){
            case 1:
                mati = true;
                break;
            case 2:
                mati = false;
                break;
            case 3:
            default:
        }

        System.out.print("L'assignatura " + vassig.get(assigIndex-1).getNomAssig() + " nomes podra ser de ");
        if(mati) System.out.print("mati");
        else System.out.print("tarda");
        System.out.println();
        
        
        
        RestTornAssig res = new RestTornAssig(vassig.get(assigIndex-1), mati);
        horari.afegirRestriccio(res);
    }

    private static void loader(){
        System.out.println("Que vols crear?");
        System.out.println("1. miniFIB?");
        System.out.println("2. FIB (beta)?");

        switch (readInput()){
            case 1:
                try {
                    vaules = Fabrica.carregaAules("/src/dades/miniAules.json");
                    vassig = Fabrica.carregaAssig("/src/dades/miniAssig.json");
                    System.out.println("Assignatures y aules de la miniFIB creades");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
            default:
                try {
                    vaules = Fabrica.carregaAules("/src/dades/fibAules.json");
                    vassig = Fabrica.carregaAssig("/src/dades/fibAssig.json");
                    System.out.println("Assignatures y aules de la FIB creades");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private static void tester(){
        System.out.println("Sobre que vols informar-te?");
        System.out.println("1. Aules?");
        System.out.println("2. Assignatures?");
        System.out.println("3. Res");

        switch (readInput()){
            case 1:
                testAula();
                break;
            case 2:
                testAssig();
                break;
            default:
        }
    }

    private static void testAula() {
        System.out.println("Que vols testejar?");
        System.out.println("1. getAulari()");
        System.out.println("2. getPis()");
        System.out.println("3. getNum()");
        System.out.println("4. getCapacity()");
        System.out.println("5. getAula()");
        System.out.println("6. Torna enrere");

        switch (readInput()){
            case 1:
                for (Aula aula: vaules) System.out.println(aula.getAulari());
                testAula();
                break;
            case 2:
                for (Aula aula: vaules) System.out.println(aula.getPis());
                testAula();
                break;
            case 3:
                for (Aula aula: vaules) System.out.println(aula.getNumero());
                testAula();
                break;
            case 4:
                for (Aula aula: vaules) System.out.println(aula.getCapacitat());
                testAula();
                break;
            case 5:
                for (Aula aula: vaules) System.out.println(aula.getAula());
                testAula();
                break;
            case 6:
                tester();
                break;
            default:
        }
    }

    private static void testAssig() {
        System.out.println("Que vols testejar?");
        System.out.println("1. getNom()");
        System.out.println("2. getGrups()");
        System.out.println("3. getQuatri()");
        System.out.println("4. Torna enrere");

        switch (readInput()){
            case 1:
                for (Assignatura assig: vassig) System.out.println(assig.getNomAssig());
                testAssig();
                break;
            case 2:
                for (Assignatura assig: vassig) {
                    System.out.println(assig.getNomAssig());
                    for (Grup grup: assig.getGrups()){
                        System.out.print(" ");
                        System.out.print(grup.getNumero());
                    }
                    System.out.println();
                }
                testAssig();
                break;
            case 3:
                    for (Assignatura assig: vassig) System.out.println(assig.getNomAssig() + ": " + assig.getCodiQuatri());
                testAssig();
                break;
            case 4:
                tester();
                break;
            default:
        }
    }

    private static int readInput(){
        String aux = reader.nextLine();

        int x = Integer.parseInt(aux);
        return x;
    }
    
    private void printAssignatura() {};
}
