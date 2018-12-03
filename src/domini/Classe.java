package domini;

public class Classe
{
    private Aula aula;
    private Grup grup;
    private Dia dia;

    private int horaIni;
    private int duracio;

    private boolean empty;

    ///CONTRUCTORA///
    public Classe(Aula a, Grup g, Dia dia, int h, int duracio)
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

    public Grup getGrup(){
        return grup;
    }

    public String getCodiQuatri(){
        return grup.getCodiQuatri();
    }

    public String getAula(){ return aula.getAula();}

    public void setEmpty(boolean b){
        empty = b;
    }

    public int getDia(){return dia.ordinal();}
    
    public void printClasse() {
    	System.out.println("El " + dia.toString() + " a les " + horaIni + " tindra classe el grup " + grup.getNumero() + " de " + grup.getNomAssig()
         + " a l'aula " + aula.getAula());
    }

    public int getDuracio(){return duracio;}

}
