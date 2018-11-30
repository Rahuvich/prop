package domini;

public class Grup
{
    private int numero;
    private Assignatura assig;
    private int alumnes;

    ///CREADORES///

    public Grup(int numGrup, Assignatura assig, int alumnes)
    {
        this.numero = numGrup;
        this.assig = assig;
        this.alumnes = alumnes;
    }

    ///CONSULTORES///

    public int getNumero()
    {
        return this.numero;
    }

    public int getNumeroAlumnes()
    {
        return this.alumnes;
    }


    public String getNomAssig()
    {
        return this.assig.getNomAssig();
    }

    public String getCodiQuatri()
    {
        return this.assig.getCodiQuatri();
    }

    public boolean esSubgrup() {
        return !(numero%10 == 0);
    }

    public boolean etsMeuPare(Grup g){
        if(g.getNomAssig() != assig.getNomAssig()) return false;
        if(this.esSubgrup()) return false;
        return String.valueOf(Math.abs((long)g.getNumero())).charAt(0) == String.valueOf(Math.abs((long)numero)).charAt(0);
    }

    public boolean etsFamilia(Grup g){
        if(g.getNomAssig() != assig.getNomAssig()) return false;
        return String.valueOf(Math.abs((long)g.getNumero())).charAt(0) == String.valueOf(Math.abs((long)numero)).charAt(0);
    }
}
