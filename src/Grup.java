public class Grup
{
    private int numero;
    private Assignatura assig;

    ///CREADORES///

    public Grup(int numGrup, Assignatura assig)
    {
        this.numero = numGrup;
        this.assig = assig;
    }

    ///CONSULTORES///

    public int getnumero()
    {
        return this.numero;
    }


    public String getnomAssig()
    {
        return this.assig.getnomassig();
    }

    public String getCodiBloc()
    {
        return this.assig.getCodiBloc();
    }
}
