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
    
    public int getHoraIni() {return this.horaIni;}

    public boolean isEmpty(){return empty;}

    public int getGrup(){
        return grup.getNumero();
    }

    public String getAula(){ return aula.getAula();}

    public void setEmpty(){
        empty = true;
    }
    
    public void printClasse() {
    	System.out.println("El " + dia + " a les " + horaIni + "tindra classe el grup " + grup.getNumero() + " de " + grup.getNomAssig());
    }

}
