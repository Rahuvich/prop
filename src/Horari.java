import utils.Vector5;

import java.util.ArrayList;
import java.util.HashMap;


public class Horari {
	
	///ATRIBUTS///
	private int horaIniDia;
	private int horaFiDia;

	private Classe[][][] horari;

	private ArrayList<Vector5> fullClasses;
	private Vector5 firstEmptyClass;

	private ArrayList<Vector5> alreadyTried;

	private HashMap<Grup, ArrayList<Restriccions>> restGrups;
	private HashMap<String, ArrayList<Restriccions>> restAssig;


	private ArrayList<Assignatura> vassigs;
	private ArrayList<Aula> vaules;

	long startAlgorithm, endAlgorithm;
	
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

		fullClasses = new ArrayList<>();
		firstEmptyClass = new Vector5(0,0,0,0,0);
		alreadyTried = new ArrayList<>();
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
		startAlgorithm = System.nanoTime();

		if(!backtracking(0,0,0,0,0))
			System.out.println("No ha sigut possible");
		else printHorari();

		endAlgorithm = System.nanoTime();
		System.out.println("Ha tardat " + ((endAlgorithm - startAlgorithm) / 1000000) + "ms");

    }
	
	private boolean backtracking(int dia, int hora, int aula, int g, int assig)
	{
		if(assig == vassigs.size()) return true;
		else if(g == vassigs.get(assig).getGrups().size()) return backtracking(firstEmptyClass.dia,firstEmptyClass.hora,firstEmptyClass.aula,0, assig+1);
		else if(aula == vaules.size()) return backtracking(dia, hora+1, 0, g, assig);
		else if(hora == horaFiDia-horaIniDia) return backtracking(dia+1, 0, 0, g, assig);
		else if(dia == 5) {
			if(!fullClasses.isEmpty()){   // TORNA ENRERE
				Vector5 aux = new Vector5(getLastOneAndRemove());
				return backtracking(aux.dia, aux.hora, aux.aula+1,aux.grup, aux.assig);
			} else return false;   // No es possible generar horari
		}
		else{ // Els parametres son correctes
			Vector5 vec = new Vector5(dia, hora, aula, g, assig);
			if(comprobarAssignacio(dia, hora, aula, vassigs.get(assig).getGrups().get(g))) {
				horari[dia][hora][aula] = new Classe(vaules.get(aula), vassigs.get(assig).getGrups().get(g), Dia.values()[dia], hora+horaIniDia, 2);
				fullClasses.add(vec);
				alreadyTried.add(vec);
				if(firstEmptyClass.equalsClasse(dia, hora, aula)) updateFirstEmptyClass(dia, hora, aula);
				System.out.println("He ficat: " + vassigs.get(assig).getGrups().get(g).getNumero() + "" +
						vassigs.get(assig).getNomAssig() + " a la posicio " +
						dia + "" +
						hora + "" +
						aula);
				return backtracking(firstEmptyClass.dia,firstEmptyClass.hora,firstEmptyClass.aula,g+1,assig);
			} else if(horari[dia][hora][aula].isEmpty() && !firstEmptyClass.esMesPetitaQue(dia,hora,aula)){
				System.out.println("First empty class updated: " + dia + " " + hora + " " + aula);
				firstEmptyClass = new Vector5(dia, hora, aula, 0,0);
				return backtracking(dia, hora, aula+1, g, assig);
			}
			else return backtracking(dia, hora, aula+1, g, assig);
		}
		
		
		/*if( ((System.nanoTime() - startAlgorithm) / 1000000) > 3000 ){
			System.out.println("Es possible que hi hagi alguna inconsistencia (3s)");
			System.exit(0);
		}*/
	}

	private Vector5 getLastOneAndRemove(){
		int auxDia = fullClasses.get(fullClasses.size()-1).dia;
		int auxHora = fullClasses.get(fullClasses.size()-1).hora;
		int auxAula = fullClasses.get(fullClasses.size()-1).aula;
		horari[auxDia][auxHora][auxAula].setEmpty();
		if(!firstEmptyClass.esMesPetitaQue(auxDia,auxHora,auxAula)){
			System.out.println("First empty class updated: " + auxDia + " " + auxHora + " " + auxAula);
			firstEmptyClass = new Vector5(auxDia, auxHora, auxAula, 0,0);
		}
		System.out.println("Estic treient: " +
				vassigs.get(fullClasses.get(fullClasses.size()-1).assig).getGrups().get(fullClasses.get(fullClasses.size()-1).grup).getNumero() + "" +
				vassigs.get(fullClasses.get(fullClasses.size()-1).assig).getNomAssig());
		return fullClasses.remove(fullClasses.size()-1);
	}

	private void updateFirstEmptyClass(int dia, int hora, int aula){
		for (int i = dia; i < 5; ++i){
			for (int j = hora; j < horaFiDia-horaIniDia; j++) {
				for (int k = aula; k < vaules.size(); k++) {
					if(horari[i][j][k].isEmpty()) {
						System.out.println("First empty class updated: " + i + " " + j + " " + k);
						firstEmptyClass = new Vector5(i, j, k, 0, 0);
						return;
					}
				}
			}
		}
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
				}
			}
		}
		return true;
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
	
	public int getHoraIni () {
		return this.horaIniDia;
	}
	
	public int getHoraFi () {
		return this.horaFiDia;
	}

}

