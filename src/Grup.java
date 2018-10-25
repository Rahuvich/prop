public class Grup
{
    private int numero;
    private Assignatura assig;

    ///CREADORES///
    public Grup(int numGrup, String nomAssig, int numbloc)
    {
        this.numero = numGrup;
        this.assig = new Assignatura(nomAssig, numbloc);
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

    public int getnumbloc()
    {
        return this.assig.getnumbloc();
    }
}
