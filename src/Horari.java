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
	public void printHorari() {

		horari[0][0][0] = new Classe(vaules.get(0), vassigs.get(0).getGrups().get(0), Dia.values()[0], 2, 2);
		horari[0][0][1] = new Classe(vaules.get(1), vassigs.get(0).getGrups().get(1), Dia.values()[0], 2, 2);
		horari[0][0][2] = new Classe(vaules.get(2), vassigs.get(0).getGrups().get(2), Dia.values()[0], 2, 2);


		horari[1][0][1] = new Classe(vaules.get(1), vassigs.get(1).getGrups().get(1), Dia.values()[1], 2, 2);
		horari[1][0][2] = new Classe(vaules.get(2), vassigs.get(1).getGrups().get(2), Dia.values()[1], 2, 2);

		System.out.printf("%2s", " ");
		for(int i = 0; i < 5; ++i) {
			System.out.printf("%20s", Dia.values()[i].toString());
		}
		System.out.println();
		for(int j = 0; j < horaFiDia - horaIniDia; j++) {
			System.out.printf("%2d", j+horaIniDia);
			for (int i = 0; i<5;++i){
				for (int k = 0; k < vaules.size(); ++k){
					if(!horari[i][j][k].isEmpty()){
						String s = String.valueOf(horari[i][j][k].getGrup());
						s = s.concat(horari[i][j][k].getAssig());
						s = s.concat(" ");
						s = s.concat(horari[i][j][k].getAula());
						System.out.printf("%20s", s);
					}
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

    public void generaQuatri (String codiQ){
		System.out.println("	Algorithm: Quatrimestre " + codiQ);
		for(int i = 0; i < vassigs.size(); ++i){
			if(vassigs.get(i).getCodiQuatri().equals(codiQ)) generaAssig(vassigs.get(i));
		}
	}
	
	public void generaAssig(Assignatura assig)
	{
		System.out.println("		Algorithm: Assignatura " + assig.getNomAssig());
		for(int i = 0; i < assig.getGrups().size(); ++i){
			generaGrup(assig.getGrups().get(i));
		}
	}
	
	public void generaGrup(Grup g)
	{
		System.out.println("			Algorithm: Grup " + g.getNumero());
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

