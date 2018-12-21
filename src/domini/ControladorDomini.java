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
        try {
        vaules = Fabrica.carregaAules("/src/dades/miniAules.json");
        vassig = Fabrica.carregaAssig("/src/dades/miniAssig.json");
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

    public  void deleteRestFranjaHoraria(String iniHora, String fiHora, String d) {
        int horaIni = Integer.parseInt(iniHora);
        int horaFi = Integer.parseInt(fiHora);
        int dia = getDiaFromName(d);
        for (int i = 0; i < vassig.size(); i++)
        {
            String nomAssig = vassig.get(i).getNomAssig();
            if(horari.restAssig.containsKey(nomAssig)){
                for (Iterator<Restriccions> it = horari.restAssig.get(nomAssig).iterator(); it.hasNext(); ) {
                    Restriccions aux = it.next();
                    if(aux instanceof  RestFranjaHoraria){
                       if( (((RestFranjaHoraria) aux).getDia() == dia)
                           && (((RestFranjaHoraria) aux).getHoraIni() == horaIni)
                                && (((RestFranjaHoraria) aux).getHoraFi() == horaFi) ){
                            it.remove();
                            if(horari.restAssig.get(nomAssig).isEmpty())
                                horari.restAssig.remove(nomAssig);

                        }
                    }
                }
            }
        }

    }

    public static void deleteRestSeparat(String nomAssig){
        if(horari.restAssig.containsKey(nomAssig)){
            for (Iterator<Restriccions> it = horari.restAssig.get(nomAssig).iterator(); it.hasNext(); ) {
                Restriccions aux = it.next();
                if(aux instanceof  RestSeparat){
                        it.remove();
                        if(horari.restAssig.get(nomAssig).isEmpty())
                            horari.restAssig.remove(nomAssig);

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
     * Cada Array conte nom de la dia i l'horaINi i horaFi
     * @return
     */
    public static ArrayList<String[]> getAllRestFranjaHoraria(){

        ArrayList<String[]> result = new ArrayList<>();
            for (Restriccions rest : horari.getAllRestAssig()) {
                if(rest instanceof RestFranjaHoraria){

                    String[] restString = new String[3];
                    restString[0] = (Integer.toString(((RestFranjaHoraria) rest).getHoraIni()));
                    restString[1] = (Integer.toString(((RestFranjaHoraria) rest).getHoraFi()));
                    restString[2] = Integer.toString(((RestFranjaHoraria) rest).getDia());
                    boolean found = false;
                    for(int i = 0; i < result.size() && !found; i++)
                    {
                        String[] antString = result.get(i);
                        if((antString[0].equals(restString[0]))
                                &&(antString[1].equals(restString[1]))
                                &&(antString[2].equals(restString[2])))
                            found = true;

                    }

                    if(!found) result.add(restString);

                }
            }
        return result;
    }
    public static ArrayList<String[]> getAllRestSeparat(){
        ArrayList<String[]> result = new ArrayList<>();
        for (Restriccions rest : horari.getAllRestAssig()) {
            if(rest instanceof RestSeparat){
                String[] restString = new String[1];
                restString[0] = ((RestSeparat) rest).getAssig().getNomAssig();
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
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void swap(String[] origin, String hora, String dia) {
        horari.moveClasse(origin, Integer.parseInt(hora), getIndexDia(dia));
        horari.printHorari();
    }

    public HashMap<String, ArrayList<String[]>> getAllRest() {
        HashMap<String, ArrayList<String[]>> rests = new HashMap<>();


        rests.put("RestTornAssig", getAllRestTornAssig());

        rests.put("RestTornGrup", getAllRestTornGrup());

        rests.put("RestHoraAssig", getAllRestHoraAssig());

        rests.put("RestHoraGrup", getAllRestHoraGrup());

        rests.put("RestFranjaHoraria", getAllRestFranjaHoraria());

        rests.put("RestSeparat", getAllRestSeparat());




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
    public String getStringDia(int i)
    {
        return Dia.values()[i].toString();
    }

    public void createRestTornAssig (String assig, String torn) {
        boolean x = getBoolFromTorn(torn);
        Assignatura aux = getAssigFromName(assig);
        RestTornAssig res = new RestTornAssig(aux, x);
        horari.afegirRestriccio(res);
    }

    public void createRestTornGrup (String assig, String grup, String torn) {
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
        for(int i = 0; i < vassig.size(); ++i)
        {
                Assignatura assig= vassig.get(i);
                RestFranjaHoraria res = new RestFranjaHoraria(assig, horaIni, horaFi, dia);
                horari.afegirRestriccio(res);
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

}
