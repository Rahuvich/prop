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
}
