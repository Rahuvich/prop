import java.util.ArrayList;

public class Horari {
	
	///ATRIBUTS///
	private Classe[] vclasses;
	private ArrayList<Restriccions> vrest;
	
	///CONSTRUCTORA///
	public Horari() {
		vrest = new ArrayList<>();
	}
	
	///CREADORES///
	public void generartot() 
	{
		System.out.println("GENERA TOT");
	}
	public void generarAssig(String nomAssig) 
	{
		System.out.println("GENERA ASSIG");
	}
	public void generarGrup(int num, String nomAssig) 
	{
		System.out.println("GENERA GRUP");
		
		
	}
	
	public void afegirRestriccio(Restriccions res) {
		vrest.add(res);
	}
	

}

