import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Horari {
	
	///ATRIBUTS///
	private int horaIniDia;
	private int horaFiDia;

	private Classe[][][] horari;
	
	private HashMap<Grup, ArrayList<Restriccions>> restGrups;
	private HashMap<Assignatura, ArrayList<Restriccions>> restAssig;

	private ArrayList<Assignatura> vassigs;
	private ArrayList<Aula> vaules;
	
	///CONSTRUCTORA///
	public Horari(int horaIniDia, int horaFiDia, ArrayList<Assignatura> vassigs, ArrayList<Aula> vaules) {
		this.horaIniDia = horaIniDia;
		this.horaFiDia = horaFiDia;
		//horari = new ArrayList[5][horaFiDia-horaIniDia][vaules.size()][];
		//horari = new ArrayList<Dia>(5, new ArrayList<>(horaFiDia - horaIniDia, new ArrayList<>(vaules.size())));
		horari = new Classe[5][horaFiDia][vaules.size()];

		for (int i = 0; i < 5; ++i){
			for (int j = horaIniDia; j < horaFiDia; ++j){
				for (int k = 0; k < vaules.size(); ++k){
					horari[i][j][k] = new Classe();
				}
			}
		}

		restGrups = new HashMap<>();
		restAssig = new HashMap<>();
		this.vassigs = vassigs;
		this.vaules = vaules;

		printHorari();
	}
	///FUNCIONS AUXILIAR DE LES CREADORES///
	
	//
	void backtrackingClasseGrup(Assignatura assig, int i, ArrayList<Grup> grupsAssig, boolean solucio[])
	{
		if(i == grupsAssig.size() && solucio[i-1]) printHorari();
		else if(i == grupsAssig.size() && !solucio[i-1]);//Si no encontramos solucion
		else
		{
			//Miramos si podemos generar el grupo
			if(generarClasseGrup(grupsAssig.get(i)) && !solucio[i])
			{
				solucio[i] = true;
				backtrackingClasseGrup(assig, i+1, grupsAssig ,solucio);	
			}
			//Si no podemos generar el grupo, hemos de volver para atras
			solucio[i] =false;
			backtrackingClasseGrup(assig, i, grupsAssig,solucio);
		}
	}

	private boolean comprovarRestriccio(Classe aux) {
		// Buscar dintre del vector de grup o assignatura si te alguna restriccio y cridar res.esCompleix()
		return true;
	}
	/**
	 * Printeja un horari
	 */
	public void printHorari() {

		System.out.printf("%2s", " ");
		for(int i = 0; i < 5; ++i) {
			System.out.printf("%20s", Dia.values()[i].toString());
		}
		System.out.println();
		for(int i = horaIniDia; i < horaFiDia; ++i) {
			System.out.printf("%2d", i);
			for (int j = 0; j<5;++j){
				/**
				 * CANVIAR EL OUTPUT UN COP TINGUEM FET EL BACKTRACKING
				 */
				System.out.printf("%20s", "10 PRO2");
			}
			System.out.println();
		}
	}
	
	///CREADORES///
	public void generarTot()
	{
		System.out.println("GENERA TOT");
    }
	
	public void generarClassesAssig(Assignatura assig) 
	{
		System.out.println("GENERA ASSIG");
		ArrayList<Grup> grupsAssig = assig.getGrups();
		boolean solucio[] = new boolean[grupsAssig.size()]; 
		Arrays.fill(solucio, false);
		backtrackingClasseGrup(assig,0,grupsAssig, solucio);
		
		
	}
	
	public boolean generarClasseGrup(Grup g) 
	{
		System.out.println("GENERA GRUP");
		boolean found = false;

		for (int i = 0; i < 5 && !found; ++i){
			for (int j = horaIniDia; j < horaFiDia && !found; ++j){
				for (int k = 0; k < vaules.size() && !found; ++k){
					if(horari[i][j][k].isEmpty()) { // Cal afegir la segona condicio del if: && vaules.get(k).getCapacitat() >= g.getNumeroAlumnes()
						Classe aux = new Classe(vaules.get(k), g, Dia.values()[i], j, 2);
						if(comprovarRestriccio(aux)){
							found = true;
							horari[i][j][k] = aux;
							System.out.println("Classe creada");
						}
					}
				}
			}
		}
		return found;
	}
	
	
	
	/** 
	 * 
	 * De moment es pot crear la mateixa restriccio de la mateixa assig/grup
	 * diferents cops i les segueix guardant.
	 */
	public void afegirRestriccio(Restriccions res) {
		ArrayList<Restriccions> auxV = new ArrayList<>();
		if(res instanceof ResGrup) {
			Grup g = ((ResGrup) res).getGrup();
			restGrups.putIfAbsent(g, new ArrayList<>());
			auxV = restGrups.get(g);
			auxV.add(res);
			restGrups.put(g, auxV);
		}
		else if(res instanceof ResAssig)
		{
			Assignatura assig = ((ResAssig) res).getAssig();
			restAssig.putIfAbsent(assig, new ArrayList<>());
			auxV = restAssig.get(assig);
			auxV.add(res);
			restAssig.put(assig, auxV);
		}
	}
	
	public int getHoraIni () {
		return this.horaIniDia;
	}
	
	public int getHoraFi () {
		return this.horaFiDia;
	}

}

