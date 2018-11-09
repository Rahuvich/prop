public class Classe
{
    private Aula aula;
    private Grup grup;
    private Dia dia;
    private Hora horaIni;
    private int duracio;

    ///CONTRUCTORA///
    public Classe(Aula a, Grup g, Dia d, Hora h, int duracio)
    {
        this.aula = a;
        this.grup = g;
        this.dia = dia;
        this.horaIni = h;
        this.duracio = duracio;
    }

    public String getAssig() {
    	return grup.getnomAssig();
    }

}
