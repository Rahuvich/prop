public class Classe
{
    private Aula aula;
    private Grup grup;
    private Dia dia;

    private int horaIni;
    private int duracio;

    private boolean empty;

    ///CONTRUCTORA///
    public Classe(Aula a, Grup g, Dia d, int h, int duracio)
    {
        this.aula = a;
        this.grup = g;
        this.dia = dia;
        this.horaIni = h;
        this.duracio = duracio;
        empty = false;
    }

    public Classe()
    {
        empty = true;
    }


    public String getAssig() {
    	return grup.getNomAssig();
    }

    public boolean isEmpty(){return empty;}

    public void setEmpty(){
        empty = true;
    }

}
