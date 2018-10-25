public class Classe
{
    private Aula aula;
    private Grup grup;
    private Dia dia;
    private Hora horaIni;

    ///CONTRUCTORA///
    public Classe(String aulari, char pis, int numAula, int capacitat,
                  Dia dia, int horaIni, int numGrup, String nomAssig, int numBloc )
    {
        this.aula = new Aula(aulari, pis, numAula, capacitat);
        this.grup = new Grup(numGrup, nomAssig, numBloc);
        this.dia = dia;
        this.horaIni = new Hora(horaIni);
    }

    ///

}
