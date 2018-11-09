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
		horari = new ArrayList[5][horaFiDia-horaIniDia][];
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
		
		
		
		
	}
	
	public void afegirRestriccio(Restriccions res) {
		
		vrest.add(res);
	}
	

}

