import java.util.ArrayList;
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

		horari = new Classe[5][horaFiDia-horaIniDia][vaules.size()];

		for (int i = 0; i < 5; ++i){
			for (int j = 0; j < horaFiDia - horaIniDia; ++j){
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

	/**
	 * Printeja un horari
	 */
	private void printHorari() {
		System.out.printf("%2s", " ");
		for(int i = 0; i < 5; ++i) {
			System.out.printf("%20s", Dia.values()[i].toString());
		}
		System.out.println();
		boolean row = false;
		for(int j = 0; j < horaFiDia - horaIniDia; j++) {
			System.out.printf("%2d", j+horaIniDia);
			for (int k = 0; k < vaules.size(); ++k) {
				for (int i = 0; i<5;++i){
					if(!horari[i][j][k].isEmpty()){
						row = true;
						String s = String.valueOf(horari[i][j][k].getGrup().getNumero());
						s = s.concat(horari[i][j][k].getAssig());
						s = s.concat(" ");
						s = s.concat(horari[i][j][k].getAula());
						System.out.printf("%20s", s);
					}
				}
				if(row){
					System.out.println();
					System.out.printf("%2s", "");
					row = false;
				}
			}
			System.out.println();
		}
	}

	public void generaTot()
	{
		System.out.println("Starting algorithm");
		ArrayList<String> quatriFet = new ArrayList<>();

		for(int i = 0; i < vassigs.size(); ++i){
			if(quatriFet.contains(vassigs.get(i).getCodiQuatri())) continue;
			quatriFet.add(vassigs.get(i).getCodiQuatri());
			generaQuatri(vassigs.get(i).getCodiQuatri());
		}

		printHorari();
    }

    private void generaQuatri (String codiQ){
		//System.out.println("	Algorithm: Quatrimestre " + codiQ);
		for(int i = 0; i < vassigs.size(); ++i){
			if(vassigs.get(i).getCodiQuatri().equals(codiQ)) generaAssig(vassigs.get(i));
		}
	}
	
	private void generaAssig(Assignatura assig)
	{
		//System.out.println("		Algorithm: Assignatura " + assig.getNomAssig());
		for(int i = 0; i < assig.getGrups().size(); ++i){
			generaGrup(assig.getGrups().get(i));
		}
	}
	
	private void generaGrup(Grup g)
	{
		if(!backtrackingDia(0, g)) {
			System.out.println("No s'ha pogut generar el grup " + g.getNumero() + "" + g.getNomAssig());
			System.exit(0);
		}
	}

	private boolean backtrackingDia(int dia, Grup g){
		if(dia == 5) return false;
		else{
			if(backtrackingHora(dia, 0, g)) return true;
			else return backtrackingDia(dia+1, g);
		}
	}

	private boolean backtrackingHora(int dia, int hora, Grup g){
		if(hora == horaFiDia-horaIniDia) return false;
		else{
			if(backtrackingAula(dia, hora, 0, g)) return true;
			else return backtrackingHora(dia, hora+1, g);
		}
	}

	private boolean backtrackingAula(int dia, int hora, int aula, Grup g){
		if(aula == vaules.size()) return false;
		else{
			if(comprobarAssignacio(dia, hora, aula, g)) {
				horari[dia][hora][aula] = new Classe(vaules.get(aula), g, Dia.values()[dia], hora+horaIniDia, 2);
				return true;
			} else return backtrackingAula(dia, hora, aula+1, g);
		}
	}

	private boolean comprobarAssignacio(int dia, int hora, int aula, Grup g){
		// RESTRICCIONS COMUNS
		if(vaules.get(aula).getCapacitat() < g.getNumeroAlumnes()) return false;

		// RESTRICCIONS INDIVIDUALS
		if(!g.esSubgrup()) return grupPosible(dia, hora, aula, g);
		else return subgrupPosible(dia, hora, aula, g);
	}

	private boolean subgrupPosible(int dia, int hora, int aula, Grup g) {
		if(vaules.get(aula).esGran()) return false; // No hauria pero si es necesari hauriem de fer que es fiqui

		for(int i = 0; i < vaules.size(); ++i){
			if(!horari[dia][hora][i].isEmpty()) {
				if(aula == i) return false;
				if(horari[dia][hora][i].getGrup().etsMeuPare(g)) return false;
				if(horari[dia][hora][i].getGrup().etsFamilia(g)) return false;
				if(horari[dia][hora][i].getCodiQuatri().equals(g.getCodiQuatri()) &&
						horari[dia][hora][i].getGrup().getNumero() == g.getNumero()) return false;
				//f(horari[dia][hora][i].getCodiQuatri().equals(g.getCodiQuatri()) && aula == i) return false;
			}
		}
		return true;
	}

	private boolean grupPosible(int dia, int hora, int aula, Grup g) {
		if(!vaules.get(aula).esGran()) return false;

		for(int i = 0; i < vaules.size(); ++i){
			if(!horari[dia][hora][i].isEmpty()) {
				if(aula == i) return false;
				if(!horari[dia][hora][i].getGrup().esSubgrup()){
					if(horari[dia][hora][i].getAssig().equals(g.getNomAssig())) return false;
					if(horari[dia][hora][i].getCodiQuatri().equals(g.getCodiQuatri()) &&
							horari[dia][hora][i].getGrup().getNumero() == g.getNumero()) return false;
					//if(horari[dia][hora][i].getCodiQuatri().equals(g.getCodiQuatri()) && aula == i) return false;
				}
			}
		}
		return true;
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

