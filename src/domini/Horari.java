package domini;

import java.util.ArrayList;
import java.util.HashMap;


public class Horari {

	///ATRIBUTS///
	private int horaIniDia;
	private int horaFiDia;

    private Classe[][][] horari;

	private HashMap<Grup, Boolean> grupTried;

	public HashMap<Grup, ArrayList<Restriccions>> restGrups;
	public HashMap<String, ArrayList<Restriccions>> restAssig;


	private ArrayList<Assignatura> vassigs;
	private ArrayList<Aula> vaules;

	long startAlgorithm, endAlgorithm;

	///CONSTRUCTORA///
	public Horari(int horaIniDia, int horaFiDia, ArrayList<Assignatura> vassigs, ArrayList<Aula> vaules) {
		this.horaIniDia = horaIniDia;
		this.horaFiDia = horaFiDia;

		System.out.println("			--------EEEEEEEEE-------");

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

		grupTried = new HashMap<>();
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
		for(int j = 0; j < horaFiDia - horaIniDia; j++) {
			System.out.printf("%2d", j+horaIniDia);
			for (int k = 0; k < vaules.size(); ++k) {
				for (int i = 0; i<5;++i){
				    String s = "-";
					if(!horari[i][j][k].isEmpty()){
						s = String.valueOf(horari[i][j][k].getGrup().getNumero());
						s = s.concat(horari[i][j][k].getAssig());
						s = s.concat(" ");
						s = s.concat(horari[i][j][k].getAula());
					}
                    System.out.printf("%20s", s);
				}
				System.out.println();
				System.out.printf("%2s", "");

			}
			System.out.println();
		}
	}

	public void generaTot()
	{
		System.out.println("Starting algorithm");
		startAlgorithm = System.nanoTime();

		if(!backtrackingGrup(0,0))
			System.out.println("No ha sigut possible");
		else printHorari();

		endAlgorithm = System.nanoTime();
		System.out.println("Ha tardat " + ((endAlgorithm - startAlgorithm) / 1000000) + "ms");
    }

    private boolean backtrackingGrup(int g, int assig){
		if(assig == vassigs.size()) return true;
		if(g == vassigs.get(assig).getGrups().size()) return backtrackingGrup(0, assig+1);
		if(grupTried.containsKey(vassigs.get(assig).getGrups().get(g))) return backtrackingGrup(g+1, assig);

		if(((System.nanoTime() - startAlgorithm) / 1000000) > 30000) System.out.println("Pot ser que hi hagi alguna inconsistencia (30s)");

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < horaFiDia - horaIniDia; j++) {
				for (int k = 0; k < vaules.size(); k++) {
					if(comprobarAssignacio(i, j, k, vassigs.get(assig).getGrups().get(g))){
						horari[i][j][k] = new Classe(vaules.get(k), vassigs.get(assig).getGrups().get(g), Dia.values()[i], j+horaIniDia, 2);

						if(backtrackingGrup(g+1 , assig)) return true;

						horari[i][j][k].setEmpty(true);
					}
				}
			}
		}
		return false;
	}

	private boolean comprobarAssignacio(int dia, int hora, int aula, Grup g){
		// RESTRICCIONS COMUNS
		boolean b = true;
		if(vaules.get(aula).getCapacitat() < g.getNumeroAlumnes() || !horari[dia][hora][aula].isEmpty()) return false;

		// RESTRICCIONS INDIVIDUALS
		if(!g.esSubgrup()) b = grupPosible(dia, hora, aula, g);
		else b = subgrupPosible(dia, hora, aula, g);

		if(!b) return false;
		return compleixRestriccions(dia, hora, aula, g);
	}

	private boolean compleixRestriccions(int dia, int hora, int aula, Grup g) {
		if (restGrups.containsKey(g)){
			// CAL CANVIAR:
			for(int i = 0; i < restGrups.get(g).size(); ++i) {
				if(!restGrups.get(g).get(i).esCompleix(new Classe(vaules.get(aula), g, Dia.values()[dia], hora+horaIniDia, 2)))
					return false;

			}
		}
		if (restAssig.containsKey(g.getNomAssig())){
			// CAL CANVIAR:
			for(int i = 0; i < restAssig.get(g.getNomAssig()).size(); ++i) {
				if(!restAssig.get(g.getNomAssig()).get(i).esCompleix(new Classe(vaules.get(aula), g, Dia.values()[dia], hora+horaIniDia, 2)))
					return false;

			}
		}
		return true;
	}

	private boolean subgrupPosible(int dia, int hora, int aula, Grup g) {
		if(vaules.get(aula).esGran()) return false; // No hauria pero si es necesari hauriem de fer que es fiqui

		for(int i = 0; i < vaules.size(); ++i){
			if(!horari[dia][hora][i].isEmpty()) {
				if(horari[dia][hora][i].getGrup().etsMeuPare(g)) return false;
				if(horari[dia][hora][i].getGrup().etsFamilia(g)) return false;
				if(horari[dia][hora][i].getCodiQuatri().equals(g.getCodiQuatri()) &&
						horari[dia][hora][i].getGrup().getNumero() == g.getNumero()) return false;
			}
		}
		return true;
	}

	private boolean grupPosible(int dia, int hora, int aula, Grup g) {
		if(!vaules.get(aula).esGran()) return false;

		for(int i = 0; i < vaules.size(); ++i){
			if(!horari[dia][hora][i].isEmpty()) {
				if(!horari[dia][hora][i].getGrup().esSubgrup()){
					if(horari[dia][hora][i].getAssig().equals(g.getNomAssig())) return false;
					if(horari[dia][hora][i].getCodiQuatri().equals(g.getCodiQuatri()) &&
							horari[dia][hora][i].getGrup().getNumero() == g.getNumero()) return false;
				} else{
					if(g.etsFamilia(horari[dia][hora][i].getGrup())) return false; // Si hi ha un subgrup de la teva familia aborta
				}
			}
		}
		return true;
	}


	private Classe getClassFromString (String[] classe) {
		for (int i = 0; i < horari.length; ++i) {
			for (int j = 0; j < horari[0].length; ++j) {
				for (int k = 0; k < horari[0][0].length; ++k) {
					if (!horari[i][j][k].isEmpty()) {
						//System.out.println("class from string " + horari[i][j][k].getGrup().getNumero());
						if ((horari[i][j][k].getGrup().getNumero()==Integer.parseInt(classe[0])) &&
								(horari[i][j][k].getGrup().getNomAssig().equals(classe[1]))) return horari[i][j][k];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Mou la classe a la hora.
	 * @param c String[] on c[0] = numGrup c[1] = nomAssig c[2] = aula
	 * @param hora Hora lectiva (ha de ser entre horaIni i horaFi)
	 * @param dia Dia de la setmana (0-4)
	 * @return return false si no s'ha pogut fer l'operacio, else return true
	 */
	public boolean moveClasse(String[] c, int hora, int dia){
		Classe classe = getClassFromString(c);

		int auxDia = classe.getDia();
		int auxHora = classe.getHoraIni() - horaIniDia;
		int auxAula = -1;
		for (int i = 0; i < vaules.size(); i++) {
			if(classe.getAula().equals(vaules.get(i).getAula())) auxAula = i;
		}
		if(auxAula < 0) return false;

		hora -= horaIniDia;

		for (int i = 0; i < vaules.size(); i++) {
			if(comprobarAssignacio(dia, hora, i, classe.getGrup())){
				classe.setEmpty(true);
				horari[dia][hora][i] = new Classe(vaules.get(i), classe.getGrup(), Dia.values()[i], hora+horaIniDia, classe.getDuracio());
				return true;
			}
		}

		// Si la classe esta ocupada intentara fer el backtracking amb la classe alla.
		boolean possible = false;
		Classe[][][] auxHorari = new Classe[5][horaFiDia-horaIniDia][vaules.size()];

		for (int i = 0; i < 5; ++i){
			for (int j = 0; j < horaFiDia - horaIniDia; ++j){
				for (int k = 0; k < vaules.size(); ++k){
					auxHorari[i][j][k] = horari[i][j][k];
					horari[i][j][k].setEmpty(true);
				}
			}
		}

		for (int i = 0; i < vaules.size(); i++) {
			if(comprobarAssignacio(dia, hora, i, classe.getGrup())){
				horari[dia][hora][i] = new Classe(vaules.get(i), classe.getGrup(), Dia.values()[i], hora+horaIniDia, classe.getDuracio());
				grupTried.put(classe.getGrup(), true);
				possible = true;
				break;
			}
		}

		startAlgorithm = System.nanoTime();
		if(!possible || !backtrackingGrup(0,0)){
			System.out.println("						--No ha sigut possible el swap--");
			for (int i = 0; i < 5; ++i){
				for (int j = 0; j < horaFiDia - horaIniDia; ++j){
					for (int k = 0; k < vaules.size(); ++k){
						horari[i][j][k] = auxHorari[i][j][k];
					}
				}
			}
			return false;
		} else return true;

	}

	/**
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
			restAssig.putIfAbsent(assig.getNomAssig(), new ArrayList<>());
			auxV = restAssig.get(assig.getNomAssig());
			auxV.add(res);
			restAssig.put(assig.getNomAssig(), auxV);
		}
	}

	public int 	getHoraIni () {
		return this.horaIniDia;
	}

	public int getHoraFi () {
		return this.horaFiDia;
	}






	public String[][][][] getHorari() {
		String[][][][] hs = new String[5][horaFiDia-horaIniDia][vaules.size()][3];
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < horaFiDia - horaIniDia; ++j) {
				for (int k = 0; k < vaules.size(); ++k) {
					if (!horari[i][j][k].isEmpty()) {
						hs[i][j][k][0] = Integer.toString(horari[i][j][k].getGrup().getNumero());
						hs[i][j][k][1] = horari[i][j][k].getAssig();
						hs[i][j][k][2] = horari[i][j][k].getAula();
						System.out.println(hs[i][j][k][0] + " " + hs[i][j][k][1] + " " + hs[i][j][k][2]);
					}
				}
			}
		}
		return hs;
	}

	public ArrayList<Restriccions> getAllRestAssig() {
		ArrayList<Restriccions> result = new ArrayList<>();
		for (ArrayList<Restriccions> rests: restAssig.values()){
			result.addAll(rests);
		}
		return result;
	}

	public ArrayList<Restriccions> getAllRestGrup() {
		ArrayList<Restriccions> result = new ArrayList<>();
		for (ArrayList<Restriccions> rests: restGrups.values()){
			result.addAll(rests);
		}
		return result;
	}

}

