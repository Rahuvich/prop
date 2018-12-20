package domini;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class ControladorDomini {

    private static ArrayList<Assignatura> vassig = new ArrayList<>();
    private static ArrayList<Aula> vaules = new ArrayList<>();

    private static Scanner reader = new Scanner(System.in);
    
    private static Horari horari;

    private static int horaIni, horaFi;

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

	public void creaHorari(int ini, int fi) {

    	horari = new Horari(ini, fi, vassig, vaules);
    	
    	System.out.println("El dia lectiu comenca a les " + horari.getHoraIni() + " i acaba a les " + horari.getHoraFi());
    }

    private static void llistaRestriccions() {
        System.out.println("Escolleix restriccio");
        System.out.println("1. Restringir el torn mati/tarda d'una assignatura");
        System.out.println("2. Restringir el torn mati/tarda d'un grup");
        System.out.println("3. Restringir l'hora d'inici d'un grup");
        System.out.println("4. Restringir l'hora d'inici d'una assignatura");
        System.out.println("5. Restringir el dia en que s'imparteix un grup");
        System.out.println("6. Restringir la franja horaria no lectiva");
        System.out.println("7. Restringir que es reparteixi en el maxim de dies possibles una assignatura");
        System.out.println("8. Torna enrere");

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
                crearRestFranjaHoraria();
                llistaRestriccions();
            case 7:
                crearRestSeparat();
                llistaRestriccions();
            case 8:

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
        for (int i = 0; i< 5; ++i){
            System.out.println(i+1 + ". " + Dia.values()[i]);
        }

        int dia = readInput();
        if(dia < 1 || dia > 5)
        {
            System.out.println("Dia invalid");
            llistaRestriccions();
            return;
        }

        System.out.println("El grup " + vassig.get(assigIndex-1).getGrups().get(grup-1).getNumero() +
                " de l'assignatura " + vassig.get(assigIndex-1).getNomAssig() + " tindra clase els " + Dia.values()[dia-1]);

        RestDiaGrup res = new RestDiaGrup(vassig.get(assigIndex-1).getGrups().get(grup-1), dia-1);
        horari.afegirRestriccio(res);
    }

    public static void crearRestFranjaHoraria()
    {
        System.out.println("Selecciona quin dia vols aplicar la Franja horaria");
        for (int i = 0; i< 5; ++i){
            System.out.println(i+1 + ". " + Dia.values()[i]);
        }
        int dia = readInput();

        if(dia < 1 || dia > 5)
        {
            System.out.println("Dia invalid");
            llistaRestriccions();
            return;
        }
        System.out.println("Insereix l'hora en que vulguis que comencin les hores no lectives del " + Dia.values()[dia-1]);
        int horaInici = readInput();

        if(horaInici < horaIni || horaInici > horaFi)
        {
            System.out.println("Hora d'inici de les hores no lectives INVALIDA");
            llistaRestriccions();
            return;
        }
        System.out.println("Insereix l'hora en que vulguis que acabin les hores no lectives del " + Dia.values()[dia-1]);
        int horaFin = readInput();

        if(horaFin < horaIni || horaFin > horaFi)
        {
            System.out.println("Hora de fi de les hores no lectives INVALIDA\"");
            llistaRestriccions();
            return;
        }

        System.out.println("El " + Dia.values()[dia-1]+
                " de les " + horaInici + " a les " + horaFin + " no hi haura classes");

        for (int i = 0; i<vassig.size(); ++i){
            for (int j = 0; j<vassig.get(i).getGrups().size(); ++j)
            {
                //System.out.println("Creando restriccion al grup " + vassig.get(i).getGrups().get(j).getNumero() +  " de l'assignatura " + vassig.get(i).getNomAssig());
                RestFranjaHoraria res = new RestFranjaHoraria(vassig.get(i).getGrups().get(j), horaInici, horaFin, dia-1);
                horari.afegirRestriccio(res);
            }
        }
    }

    private static void crearRestSeparat()
    {
        System.out.println("Escolleix assignatura");
        for (int i = 0; i<vassig.size(); ++i){
            System.out.println(i+1 + ". " + vassig.get(i).getNomAssig());
        }

        int assigIndex = readInput();

        System.out.println("L'assignatura " + vassig.get(assigIndex-1).getNomAssig() + " es separarà en el màxim de dies possibles");



        RestSeparat res = new RestSeparat(vassig.get(assigIndex-1));
        horari.afegirRestriccio(res);
    }

    private static boolean getBoolFromTorn(String m) {
        if (m == "Mati") return true;
        else return false;
    }
    private static String getTornFromBool(boolean b) {
        if (b == true) return "Mati";
        else return "Tarda";
    }

    /**
     * Borra la restriccio
     * @param nomAssig Nom de l'assignatura
     * @param h Hora entre HoraIni i HoraFi
     */
    public static void deleteRestHoraAssig(String nomAssig, String h){
        int hora = Integer.parseInt(h);

        if(horari.restAssig.containsKey(nomAssig)){
            for (Iterator<Restriccions> it = horari.restAssig.get(nomAssig).iterator(); it.hasNext(); ) {
                Restriccions aux = it.next();
                if(aux instanceof  RestHoraAssig){
                    if(((RestHoraAssig) aux).getHora() == hora){
                        it.remove();
                        if(horari.restAssig.get(nomAssig).isEmpty())
                            horari.restAssig.remove(nomAssig);

                    }
                }
            }
        }
    }

    /**
     * Borra la restriccio
     * @param nomAssig Nom de l'assignatura
     * @param grup String del numero del grup (10, 11, 20, 21)
     * @param h Hora entre HoraIni i HoraFi
     */
    public static void deleteRestHoraGrup(String nomAssig, String grup, String h){
        int hora = Integer.parseInt(h);
        int indexAssig = -1;
        for (int i = 0; i < vassig.size(); i++) {
            if(nomAssig.equals(vassig.get(i).getNomAssig())) indexAssig = i;
        }

        int indexGrup = getIndexGrup(vassig.get(indexAssig), grup);

        if(horari.restGrups.containsKey(vassig.get(indexAssig).getGrups().get(indexGrup))){
            for (Iterator<Restriccions> it =
                 horari.restGrups.get(vassig.get(indexAssig).getGrups().get(indexGrup)).iterator(); it.hasNext(); ) {
                Restriccions aux = it.next();
                if(aux instanceof  RestHoraGrup){
                    if(((RestHoraGrup) aux).getHora() == hora){
                        it.remove();
                        if(horari.restAssig.get(vassig.get(indexAssig).getGrups().get(indexGrup)).isEmpty())
                            horari.restAssig.remove(vassig.get(indexAssig).getGrups().get(indexGrup));

                    }
                }
            }
        }
    }

    /**
     * Borra la restriccio
     * @param nomAssig Nom de l'assignatura
     * @param grup String del numero del grup (10, 11, 20, 21)
     * @param m true == mati, false == tarda
     */
    public static void deleteRestTornGrup(String nomAssig, String grup, String m){
        boolean mati = Boolean.parseBoolean(m);
        int indexAssig = -1;
        for (int i = 0; i < vassig.size(); i++) {
            if(nomAssig.equals(vassig.get(i).getNomAssig())) indexAssig = i;
        }

        int indexGrup = getIndexGrup(vassig.get(indexAssig), grup);

        if(horari.restGrups.containsKey(vassig.get(indexAssig).getGrups().get(indexGrup))){
            for (Iterator<Restriccions> it =
                 horari.restGrups.get(vassig.get(indexAssig).getGrups().get(indexGrup)).iterator(); it.hasNext(); ) {
                Restriccions aux = it.next();
                if(aux instanceof  RestTornGrup){
                    if(((RestTornGrup) aux).getMati() == mati){
                        it.remove();
                        if(horari.restAssig.get(vassig.get(indexAssig).getGrups().get(indexGrup)).isEmpty())
                            horari.restAssig.remove(vassig.get(indexAssig).getGrups().get(indexGrup));

                    }
                }
            }
        }
    }

    /**
     * Borra la restriccio
     * @param nomAssig Nom de l'assignatura
     * @param m String that == Mati or == tarda
     */
    public static void deleteRestTornAssig(String nomAssig, String m){
        boolean mati = getBoolFromTorn(m);
        if(horari.restAssig.containsKey(nomAssig)){
            for (Iterator<Restriccions> it = horari.restAssig.get(nomAssig).iterator(); it.hasNext(); ) {
                Restriccions aux = it.next();
                if(aux instanceof  RestTornAssig){
                    if(((RestTornAssig) aux).getMati() == mati){
                        it.remove();
                        if(horari.restAssig.get(nomAssig).isEmpty())
                            horari.restAssig.remove(nomAssig);

                    }
                }
            }
        }
    }

    /**
     * Borra la restriccio
     * @param nomAssig Nom de l'assignatura
     * @param grup String del numero del grup (10, 11, 20, 21)
     * @param dia Between 0 and 4
     */
    public static void deleteRestDiaGrup(String nomAssig, String grup, int dia) {
        int indexAssig = -1;
        for (int i = 0; i < vassig.size(); i++) {
            if(nomAssig.equals(vassig.get(i).getNomAssig())) indexAssig = i;
        }

        int indexGrup = getIndexGrup(vassig.get(indexAssig), grup);

        if(horari.restGrups.containsKey(vassig.get(indexAssig).getGrups().get(indexGrup))){
            for (Restriccions aux : horari.restGrups.get(vassig.get(indexAssig).getGrups().get(indexGrup))) {
                if(aux instanceof  RestDiaGrup){
                    if(((RestDiaGrup) aux).getDia() == dia)
                        horari.restGrups.remove(vassig.get(indexAssig).getGrups().get(indexGrup), aux);
                }
            }
        }
    }

    public  void deleteRestFranjaHoraria(String iniHora, String fiHora, String d) {
        System.out.println("delete en CD");

        int horaIni = Integer.parseInt(iniHora);
        int horaFi = Integer.parseInt(fiHora);
        int dia = getDiaFromName(d);
System.out.println("antes del for");
        for (int i = 0; i < vassig.size(); i++)
        {
            for(int j = 0; j < vassig.get(i).getGrups().size(); ++j)
            {

                Grup g = vassig.get(i).getGrups().get(j);
                System.out.println("bucle mas interno " +  g.getNomAssig() + " "+ g.getNumero() );
                if(horari.restGrups.containsKey(g)){
                    System.out.println("contiene g");
                    for (Restriccions aux : horari.restGrups.get(g)) {
                        if(aux instanceof  RestFranjaHoraria){
                           if( (((RestFranjaHoraria) aux).getDia() == dia) && (((RestFranjaHoraria) aux).getHoraIni() == horaIni) && (((RestFranjaHoraria) aux).getHoraFi() == horaFi) )
                               System.out.println("lo borro ");
                               horari.restGrups.remove(g, aux);
                        }
                    }
                }
            }
        }
        System.out.println("despues del for");

    }

    public static void deleteRestSeparat(String nomAssig){
        int indexAssig = -1;
        for (int i = 0; i < vassig.size(); i++) {
            if(nomAssig.equals(vassig.get(i).getNomAssig())) indexAssig = i;
        }

        if(horari.restAssig.containsKey(nomAssig)){
            for (Restriccions aux : horari.restAssig.get(nomAssig)) {
                if(aux instanceof  RestSeparat){
                    if(((RestSeparat) aux).assig.getNomAssig() == nomAssig){
                        horari.restAssig.remove(nomAssig, aux);
                        System.out.println("He eliminat una restriccio");
                    }
                }
            }
        }
    }



    private static int getIndexGrup(Assignatura assig, String grup){
        for (int i = 0; i < assig.getGrups().size(); i++) {
            if(grup.equals(String.valueOf(assig.getGrups().get(i).getNumero()))) return i;
        }
        return -1;
    }

    public static String[][][][] getHorari(){
        return horari.getHorari();
    }


    /**
     * Cada Array conte nom de la assignatura i l'hora
     * @return
     */
    public static ArrayList<String[]> getAllRestHoraAssig(){
        ArrayList<String[]> result = new ArrayList<>();
        for (Restriccions rest : horari.getAllRestAssig()) {
            if(rest instanceof RestHoraAssig){
                String[] restString = new String[2];
                restString[0] = (((RestHoraAssig) rest).getAssig().getNomAssig());
                restString[1] = (String.valueOf(((RestHoraAssig) rest).getHora()));
                result.add(restString);
            }
        }
        return result;
    }

    /**
     * Cada Array conte nom de la assignatura, numero del grup (10, 11, 20, 21) i l'hora
     * @return
     */
    public static ArrayList<String[]> getAllRestHoraGrup(){
        ArrayList<String[]> result = new ArrayList<>();
        for (Restriccions rest : horari.getAllRestGrup()) {
            if(rest instanceof RestHoraGrup){
                String[] restString = new String[3];
                restString[0] = (((RestHoraGrup) rest).getGrup().getNomAssig());
                restString[1] = (String.valueOf(((RestHoraGrup) rest).getGrup().getNumero()));
                restString[2] = (String.valueOf(((RestHoraGrup) rest).getHora()));
                result.add(restString);
            }
        }
        return result;
    }

    /**
     * Cada Array conte nom de la assignatura i true si es mati, false si es tarda
     * @return
     */
    public static ArrayList<String[]> getAllRestTornAssig(){
        ArrayList<String[]> result = new ArrayList<>();
        for (Restriccions rest : horari.getAllRestAssig()) {
            if(rest instanceof RestTornAssig){
                String[] restString = new String[2];
                restString[0] = ((RestTornAssig) rest).getAssig().getNomAssig();
                restString[1] = (getTornFromBool(((RestTornAssig) rest).getMati()));
                result.add(restString);
            }
        }
        return result;
    }

    /**
     * Cada Array conte nom de la assignatura, el numero del grup (10, 11, 20, 21) i true si es mati, false si es tarda
     * @return
     */
    public static ArrayList<String[]> getAllRestTornGrup(){
        ArrayList<String[]> result = new ArrayList<>();
        for (Restriccions rest : horari.getAllRestGrup()) {
            if(rest instanceof RestTornGrup){
                String[] restString = new String[3];
                restString[0] = (((RestTornGrup) rest).getGrup().getNomAssig());
                restString[1] = (String.valueOf(((RestTornGrup) rest).getGrup().getNumero()));
                restString[2] = (getTornFromBool(((RestTornGrup) rest).getMati()));
                result.add(restString);
            }
        }
        return result;
    }

    /**
     * Cada Array conte nom de la assignatura, el numero del grup (10, 11, 20, 21) i dia (0-4)
     * @return
     */
    public static ArrayList<String[]> getAllRestDiaGrup(){
        ArrayList<String[]> result = new ArrayList<>();
        for (Restriccions rest : horari.getAllRestGrup()) {
            if(rest instanceof RestDiaGrup){
                String[] restString = new String[3];
                restString[0] = (((RestDiaGrup) rest).getGrup().getNomAssig());
                restString[1] = (String.valueOf(((RestDiaGrup) rest).getGrup().getNumero()));
                restString[2] = Dia.values()[((RestDiaGrup) rest).getDia()].toString();
                result.add(restString);
            }
        }
        return result;
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

    public void swap(String[] origin, String hora, String dia) {
        System.out.println("calling swap from td");
        horari.moveClasse(origin, Integer.parseInt(hora), getIndexDia(dia));
        System.out.println("swap completed from td, calling print horari");
        horari.printHorari();
    }

    public HashMap<String, ArrayList<String[]>> getAllRest() {
        HashMap<String, ArrayList<String[]>> rests = new HashMap<>();


        rests.put("RestTornAssig", getAllRestTornAssig());

        rests.put("RestTornGrup", getAllRestTornGrup());

        rests.put("RestHoraAssig", getAllRestHoraAssig());

        rests.put("RestHoraGrup", getAllRestHoraGrup());

        rests.put("RestDiaGrup", getAllRestDiaGrup());


        return rests;
    }

    public ArrayList<String> getUnitatsDocents() {
        ArrayList<String> uds = new ArrayList<>();
        uds.add("FIB");
        uds.add("miniFIB");
        uds.add("microFIB");
        return uds;

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


    public ArrayList<String> getGrups(String assig){
        int index = -1;
        Assignatura ass = null;
        for(int i = 0; (i < vassig.size()) && (index == -1); i++) {
            if (vassig.get(i).getNomAssig() == assig) index = i;
            ass = vassig.get(i);
        }
        ArrayList<String> listgrups = new ArrayList<>();
        ArrayList<Grup> listnumeros = ass.getGrups();
        for(int i = 0; i < listnumeros.size(); i++ ) {
            listgrups.add(Integer.toString(listnumeros.get(i).getNumero()));
        }
        return listgrups;
    }

    public ArrayList<String> getHores(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = horari.getHoraIni(); i < horari.getHoraFi(); ++i) {
            list.add(Integer.toString(i));
        }
    return list;
    }

    public ArrayList<String> getHoresFi(String iniHora){
        ArrayList<String> list = new ArrayList<>();
        int horaIni = Integer.parseInt(iniHora);
        for (int i = horaIni; i <= horari.getHoraFi(); ++i) {
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

    public int getIndexDia(String dia){
        for (int i = 0; i < 5; ++i) {
            if (Dia.values()[i].toString().equals(dia)) return i;
        }
    return -1;
    }

    public void createRestTornAssig (String assig, String torn) {
        boolean x = getBoolFromTorn(torn);
        Assignatura aux = getAssigFromName(assig);
        System.out.println("despres getassig");

        if(aux == null) System.out.println("Assig no trobat, null");
        else System.out.println("Treballem amb assig " + aux.getNomAssig());

        RestTornAssig res = new RestTornAssig(aux, x);
        horari.afegirRestriccio(res);
        System.out.println("after createRestTornAssig from td");
    }

    public void createRestTornGrup (String assig, String grup, String torn) {
        System.out.println("createRestTornGrup Cd");
        boolean x = getBoolFromTorn(torn);
        Assignatura aux = getAssigFromName(assig);
        Grup g = getGrupFromAssig(aux, grup);
        RestTornGrup res = new RestTornGrup(g, x);
        horari.afegirRestriccio(res);
    }



    public void createRestHoraAssig (String assig, String h) {
        int hora = Integer.parseInt(h);
        Assignatura aux = getAssigFromName(assig);
        RestHoraAssig res = new RestHoraAssig(aux, hora);
        horari.afegirRestriccio(res);

    }

    public void createRestHoraGrup (String assig,String grup, String h) {
        int hora = Integer.parseInt(h);
        Assignatura aux = getAssigFromName(assig);
        Grup g = getGrupFromAssig(aux, grup);
        RestHoraGrup res = new RestHoraGrup(g, hora);
        horari.afegirRestriccio(res);

    }

    public void createRestFranjaHoraria (String iniHora,String fiHora, String d) {
        int horaIni = Integer.parseInt(iniHora);
        int horaFi = Integer.parseInt(fiHora);
        int dia = getDiaFromName(d);
        System.out.println("el string de dia es " + d + " s'ha convertit en" + dia );
        for(int i = 0; i < vassig.size(); ++i)
        {
            for(int j = 0; j < vassig.get(i).getGrups().size(); ++j )
            {
                Grup g = vassig.get(i).getGrups().get(j);
                RestFranjaHoraria res = new RestFranjaHoraria(g, horaIni, horaFi, dia);
                horari.afegirRestriccio(res);
            }
        }
    }

    public void createRestSeparat (String assig) {
        Assignatura aux = getAssigFromName(assig);
        RestSeparat res = new RestSeparat(aux);
        horari.afegirRestriccio(res);
    }

    private Integer getDiaFromName(String d)
    {
        int dia = 0;
        switch (d) {
            case "DILLUNS":
                dia = 0;
                break;
            case "DIMARTS":
                dia = 1;
                break;
            case "DIMECRES":
                dia = 2;
                break;
            case "DIJOUS":
                dia = 3;
                break;
            case "DIVENDRES":
                dia = 4;
                break;
        }
        return dia;
    }
    private Assignatura getAssigFromName(String assig) {
        for (int i = 0; i < vassig.size(); ++i) {
            if (vassig.get(i).getNomAssig()==assig) return vassig.get(i);
        }
        return null;
    }

    private Grup getGrupFromAssig(Assignatura aux, String grup)
    {
        ArrayList<Grup> vgrups = new ArrayList<Grup>();
        vgrups = aux.getGrups();
        Grup g = null;
        for(int i = 0; i < vgrups.size(); i++) { if(Integer.parseInt(grup) == vgrups.get(i).getNumero()) g = vgrups.get(i); }
        return g;
    }

    private static int readInput(){
        String aux = reader.nextLine();

        int x = Integer.parseInt(aux);
        return x;
    }
    
    private void printAssignatura() {};
}
