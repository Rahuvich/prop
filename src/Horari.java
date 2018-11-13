import java.util.ArrayList;
import java.util.HashMap;

public class Horari {
	
	///ATRIBUTS///
	private int horaIniDia;
	private int horaFiDia;
	
	private ArrayList[][][] horari;
	
	private HashMap<Grup, ArrayList<Restriccions>> restGrups;
	private HashMap<Assignatura, ArrayList<Restriccions>> restAssig;
	
	///CONSTRUCTORA///
	public Horari(int horaIniDia, int horaFiDia) {
		this.horaIniDia = horaIniDia;
		this.horaFiDia = horaFiDia;
		horari = new ArrayList[5][horaFiDia-horaIniDia][];
		restGrups = new HashMap<>();
		restAssig = new HashMap<>();
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
		ArrayList<Restriccions> vres = new ArrayList<Restriccions>();
		if(restGrups.containsKey(g)) 
		{
			vres = restGrups.get(g);
			//crear instancia aleatoria classe
			Restriccions res = new Restriccions();
			for(int i = 0; i < vres.size(); ++i)
			{
				res.esCompleix();//Hem de pasar la classe y comprobat si funciona

			}
		}
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

