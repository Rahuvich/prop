package domini;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TestDriver {

    private static ArrayList<Assignatura> vassig = new ArrayList<>();
    private static ArrayList<Aula> vaules = new ArrayList<>();

    private static Scanner reader = new Scanner(System.in);
    
    private static Horari horari;

    private static int horaIni, horaFi;

    public void execute(){
    	chooseMode();
    }
    
    private void chooseMode() {
    	System.out.println("Qui ets?");
        System.out.println("1. Dev team (cargara automaticament la miniFIB amb horaIni 8 i horaFi 20, es saltara els testers i anira directament a restriccions");
        System.out.println("2. Horacio");

        switch (readInput()){
        case 1:
            devMode();

            crearRestriccions();

            reader.close();
            break;
        case 2:
        default:

            String uD = getUnitatDocent();

        	loader(uD);
        	
        	System.out.println("Insereix l'hora en que vulguis que comencin les classes");
        	horaIni = readInput();

        	System.out.println("Insereix l'hora en que vulguis que acabin les classes");
        	horaFi = readInput();
        	
        	creaHorari(horaIni, horaFi);

            tester();

            crearRestriccions();
            reader.close();
            break;
        }
        generaHorari();
    }

    public void generaHorari() {
        horari.generaTot();
    }

    public void devMode() {
        System.out.println("before try of devmode");
        try {
        vaules = Fabrica.carregaAules("/src/dades/miniAules.json");
        vassig = Fabrica.carregaAssig("/src/dades/miniAssig.json");
        System.out.println("Assignatures y aules de la miniFIB creades");
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
        horaIni = 8;
        horaFi = 20;
        creaHorari(8, 20);
    }
    
    private String getUnitatDocent () {
        System.out.println("Que vols crear?");
        System.out.println("1. microFIB?");
        System.out.println("2. miniFIB?");
        System.out.println("3. FIB (beta)?");
        switch (readInput()){
            case 1:
                return "microFIB";
            case 2:
                return "miniFIB";
            case 3:
                return "FIB";
            default:
                return "nothing";
        }
    }

	public void creaHorari(int ini, int fi) {

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
        System.out.println("3. Restringir l'hora d'inici d'un grup");
        System.out.println("4. Restringir l'hora d'inici d'una assignatura");
        System.out.println("5. Restringir el dia en que s'imparteix un grup");
        System.out.println("6. Torna enrere");

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
                crearRestHoraGrup();
                llistaRestriccions();
                break;
            case 4:
                crearRestHoraAssig();
                llistaRestriccions();
            case 5:
                crearRestDiaGrup();
                llistaRestriccions();
            case 6:

            default:
        }
    }

    private static void crearRestHoraAssig() {
        System.out.println("Escolleix assignatura");
        for (int i = 0; i<vassig.size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(i).getNomAssig());
        }

        int assigIndex = readInput();

        System.out.println("Escolleix hora entre " + horaIni + " y " + horaFi);
        int hora = readInput();
        if(hora < horaIni || hora > horaFi){
            System.out.println("Hora invalida");
            llistaRestriccions();
            return;
        }

        System.out.println("L'assignatura " + vassig.get(assigIndex-1).getNomAssig() + " no podra ser a les " + hora);



        RestHoraAssig res = new RestHoraAssig(vassig.get(assigIndex-1), hora);
        horari.afegirRestriccio(res);
    }

    private static void crearRestHoraGrup() {
        System.out.println("Escolleix assignatura");
        for (int i = 0; i<vassig.size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(i).getNomAssig());
        }
        int assigIndex = readInput();
        for (int i = 0; i<vassig.get(assigIndex-1).getGrups().size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(assigIndex-1).getGrups().get(i).getNumero());
        }
        int grup = readInput();

        System.out.println("Escolleix hora entre " + horaIni + " y " + horaFi);
        int hora = readInput();
        if(hora < horaIni || hora > horaFi){
            System.out.println("Hora invalida");
            llistaRestriccions();
            return;
        }

        System.out.println("El grup " + vassig.get(assigIndex-1).getGrups().get(grup-1).getNumero() +
                " de l'assignatura " + vassig.get(assigIndex-1).getNomAssig() + " no podra ser a les " + hora);

        RestHoraGrup res = new RestHoraGrup(vassig.get(assigIndex-1).getGrups().get(grup-1), hora);
        horari.afegirRestriccio(res);
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

    public static void crearRestDiaGrup()
    {
        System.out.println("Escolleix assignatura");
        for (int i = 0; i<vassig.size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(i).getNomAssig());
        }
        int assigIndex = readInput();
        for (int i = 0; i<vassig.get(assigIndex-1).getGrups().size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(assigIndex-1).getGrups().get(i).getNumero());
        }
        int grup = readInput();
        System.out.println("Escolleix dia:");
        System.out.println(1 + ". DILLUNS");
        System.out.println(2 + ". DIMARTS");
        System.out.println(3 + ". DIMECRES");
        System.out.println(4 + ". DIJOUS");
        System.out.println(5 + ". DIVENDRES");
        int dia = readInput();
        String nomdia;
        if(dia == 1) nomdia = "dilluns";
        else if(dia == 2) nomdia = "dimarts";
        else if(dia == 3) nomdia = "dimecres";
        else if(dia == 4) nomdia = "dijous";
        else if(dia == 5) nomdia = "divendres";
        else
        {
            System.out.println("Dia invalid");
            llistaRestriccions();
            return;
        }

        System.out.println("El grup " + vassig.get(assigIndex-1).getGrups().get(grup-1).getNumero() +
                " de l'assignatura " + vassig.get(assigIndex-1).getNomAssig() + " tindra clase els " + nomdia);

        RestDiaGrup res = new RestDiaGrup(vassig.get(assigIndex-1).getGrups().get(grup-1), dia-1);
        horari.afegirRestriccio(res);
    }

    public static void loader(String unitatD){

        switch (unitatD){
            case "microFIB":
                try {
                    vaules = Fabrica.carregaAules("/src/dades/microAules.json");
                    vassig = Fabrica.carregaAssig("/src/dades/microAssig.json");
                    System.out.println("Assignatures y aules de la miniFIB creades");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "miniFIB":
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
            case "FIB":
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

    public ArrayList<String> getAules(){
        ArrayList<String> listAules = new ArrayList<>();
        for (Aula aula: vaules) {
            listAules.add(aula.getAula());
        }
    return listAules;
    }

    public ArrayList<String> getAssigs(){
        ArrayList<String> listAssigs = new ArrayList<>();
        for (Assignatura assig: vassig) {
            listAssigs.add(assig.getNomAssig());
        }
    return listAssigs;
    }
    public ArrayList<String> getHores(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = horari.getHoraIni(); i < horari.getHoraFi(); ++i) {
            list.add(Integer.toString(i));
        }
    return list;
    }

    public ArrayList<String> getDies(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            list.add(Dia.values()[i].toString());
        }
    return list;
    }

    public void createRestTornAssig (String assig, String torn) {
        System.out.println("before createRestTornAssig from td");
        boolean x = false;
        if (torn=="Mati") x = true;
        Assignatura aux = getAssigFromName(assig);
        RestTornAssig res = new RestTornAssig(aux, x);
        horari.afegirRestriccio(res);
        System.out.println("after createRestTornAssig from td");
    }

    public void createRestHoraAssig (String assig, int hora) {
        Assignatura aux = getAssigFromName(assig);
        RestHoraAssig res = new RestHoraAssig(aux, hora);
        horari.afegirRestriccio(res);

    }

    private Assignatura getAssigFromName(String assig) {
        for (int i = 0; i < vassig.size(); ++i) {
            if (vassig.get(i).getNomAssig()==assig) return vassig.get(i);
        }
        return null;
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
