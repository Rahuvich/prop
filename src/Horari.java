import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


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
	}
	///FUNCIONS AUXILIAR DE LES CREADORES///
	
/*	void backtrackingClasseGrup(Assignatura assig, int i, ArrayList<Grup> grupsAssig, boolean solucio[])
	{
		if(i == grupsAssig.size() && solucio[i-1]) printHorari();
		else if(i == grupsAssig.size() && !solucio[i-1]);//Si no encontramos solucion
		else
		{
			//Miramos si podemos generar el grupo
			boolean found = false;
			int fila = 0;
			generarClasseGrup(grupsAssig.get(i), mirades,fila,found);
			if(!found && !solucio[i]) //Quiere decir que no ha encontrado solucion
			{
				vuelta_atras();
			}
			else//Si se ha encontrado solución seguimos
			solucio[i] =false;
			backtrackingClasseGrup(assig, i, grupsAssig,solucio);
		}
	}
*/
	void backtrackingClasseAssig(Assignatura assig, int i, ArrayList<Grup> grupsAssig, boolean solucio[], boolean foundAssig, boolean marcadesAnt[][][],int filaAnt, int colAnt, int aulaAnt, boolean vueltaAtras)
	{
		if(i == grupsAssig.size() && solucio[i-1] && !vueltaAtras) foundAssig = true;
		else if(i == grupsAssig.size() && !solucio[i-1] && !vueltaAtras) foundAssig = false;//Si no encontramos solucion
		else if(i == -1 && vueltaAtras) foundAssig = false; // Quiere decir que hemos seguido tirando para atras pero no hay solucion de esta assignatura  			
		else
		{
			//Miramos si podemos generar el grupo
			int fila = 0;
			boolean found = false; // diu si el grup te solucio o no, found diu 
			boolean[][][] marcades;
			marcades = new boolean[5][horaFiDia][vaules.size()];
			Arrays.fill(marcades, false); // todas empiezana false
			int col = 0; // Queremos saber la ultima posicion que fue valida
			int aula =0;
			
			if(!vueltaAtras)
			{
				backtrackinggenerarClasseGrup(grupsAssig.get(i), marcades,fila,found,col, aula);
				// Si no hemos encontrado solucion volvemos para atràs
				if(!found) backtrackingClasseAssig(assig, i-1,grupsAssig,solucio, foundAssig, marcadesAnt, filaAnt,colAnt,aulaAnt, true);
				else//Si se ha encontrado solución seguimos, hemos podido colocar el grupo
				{
						solucio[i] =true;
						backtrackingClasseAssig(assig, i+1, grupsAssig,solucio, foundAssig, marcades,fila,col,aula,vueltaAtras);
				}
			}
			else // Si es vueltaAtras
			{
				solucio[i] = false;
				marcadesAnt[filaAnt][colAnt][aulaAnt] = true;
				foundAssig = false;
				backtrackinggenerarClasseGrup(grupsAssig.get(i), marcadesAnt,filaAnt,found,colAnt, aulaAnt);
				if(found) // Si el grup a trobat una solució
				{
					solucio[i] = true;
					backtrackingClasseAssig(assig, i+1,grupsAssig,solucio, foundAssig, marcadesAnt, filaAnt,colAnt,aulaAnt, false);
				}
				else // Si no ha trobat solució hem de tornar enrere
				{
					backtrackingClasseAssig(assig, i-1,grupsAssig,solucio, foundAssig, marcadesAnt, filaAnt,colAnt,aulaAnt, true);
				}
				
			}
	}
}

	private boolean comprovarRestriccio(Classe aux, Grup g) {
		// Buscar dintre del vector de grup o assignatura si te alguna restriccio y cridar res.esCompleix()
		//Restriccio restGrup[] = restGrups.get(g);
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
		boolean foundAssig = false;
		boolean marcades[][][] = new boolean[5][horaFiDia][vaules.size()];
		Arrays.fill(marcades, false);
		backtrackingClasseAssig(assig, 0,grupsAssig,solucio, foundAssig, marcades, 0,0,0, false);
		
		
	}
	
	/*public boolean generarClasseGrup(Grup g, boolean[][][] mirades) 
	{
		System.out.println("GENERA GRUP");

		for (int i = 0; i < 5 && !found; ++i){
			for (int j = horaIniDia; j < horaFiDia && !found; ++j){
				for (int k = 0; k < vaules.size() && !found; ++k){
					if(!mirades[i][j][k] && horari[i][j][k].isEmpty()) { // Cal afegir la segona condicio del if: && vaules.get(k).getCapacitat() >= g.getNumeroAlumnes()
						mirades[i][j][k] = true;
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
	*/
	public void backtrackinggenerarClasseGrup(Grup g, boolean[][][] marcadas, int i,boolean found, int j, int k) 
	{
		System.out.println("GENERA GRUP");
		if(i == 5 ) found = false;
		else
		{
			for (j = horaIniDia; j < horaFiDia && !found; ++j){
				for (k = 0; k < vaules.size() && !found; ++k){
					if(!marcadas[i][j][k] && horari[i][j][k].isEmpty()) { // Cal afegir la segona condicio del if: && vaules.get(k).getCapacitat() >= g.getNumeroAlumnes()
						marcadas[i][j][k] = true;
						Classe aux = new Classe(vaules.get(k), g, Dia.values()[i], j, 2);
						if(comprovarRestriccio(aux, g)){
							found = true;
							horari[i][j][k] = aux;
							System.out.println("Classe creada");
						}
					}
				}
			}
			if(!found) backtrackinggenerarClasseGrup(g, marcadas,i+1,found, 0,0);
		}
	}
	
	public void generarClasseGrup(Grup g)
	{
		boolean marcadas[][][] = new boolean[5][horaFiDia][vaules.size()];
		backtrackinggenerarClasseGrup(g, marcadas,0,false, 0,0);
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

