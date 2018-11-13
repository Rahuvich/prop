import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Horari {
	
	///ATRIBUTS///
	private int horaIniDia;
	private int horaFiDia;
	
	//private ArrayList[][][][] horari;
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
		horari = new Classe[5][horaFiDia-horaIniDia][vaules.size()];

		for (int i = 0; i < 5; ++i){
			for (int j = 0; j < horaFiDia-horaIniDia; ++j){
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
	
	///CREADORES///
	public void generarTot()
	{
		System.out.println("GENERA TOT");
    }
	
	public void generarClassesAssig(Assignatura assig) 
	{
		System.out.println("GENERA ASSIG");
	}
	
	public void generarClasseGrup(Grup g) 
	{
		System.out.println("GENERA GRUP");
		boolean found = false;

		for (int i = 0; i < 5 && !found; ++i){
			for (int j = 0; j < horaFiDia-horaIniDia && !found; ++j){
				for (int k = 0; k < vaules.size() && !found; ++k){
					if(horari[i][j][k].isEmpty() && vaules.get(k).getCapacitat() >= g.getNumeroAlumnes()) { // He afegit la segona condicio del if
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
	}

	private boolean comprovarRestriccio(Classe aux) {
		// Buscar dintre del vector de grup o assignatura si te alguna restriccio y cridar res.esCompleix()
		return true;
	}

	public void printHorari() {
		System.out.println("--- HORARI ---");
		for(int i = 0; i < 5; ++i) {
			System.out.println("- DIA "+ (i+1) +" -");
			for(int j = horaIniDia ; j < horaFiDia; ++j) {
				System.out.println("- HORA "+ j +" -");
				System.out.println("CLASSE");
				
			}
		}
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

