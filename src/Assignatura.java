

public class Assignatura
{
    private String nomAssig;
    private Bloc bloc;

    ///CREADORES///
    public Assignatura(String nomAssig, int num)
    {
        this.nomAssig = nomAssig;
        this.bloc = new Bloc(num);
    }

    ///CONSULTORES///

    public String getnomassig()
    {
        return this.nomAssig;
    }

    public int getnumbloc() {
        return bloc.getnumero();
    }
}