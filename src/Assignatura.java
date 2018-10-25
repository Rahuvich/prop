import java.util.ArrayList;

public class Assignatura
{
    private String nomAssig;
    private Bloc bloc;
    private ArrayList<Grup> grupsAssig;

    ///CREADORES///
    public Assignatura(String nomAssig, String codiBloc)
    {
        this.nomAssig = nomAssig;
        this.bloc = new Bloc(codiBloc);
    }

    public Assignatura(String nomAssig, String codiBloc, ArrayList<Integer> grups )
    {
        this.nomAssig = nomAssig;
        this.bloc = new Bloc(codiBloc);
        grupsAssig = new ArrayList<Grup>();
        for(int i = 0; i < grups.size(); ++i){
            grupsAssig.add(new Grup(grups.get(i), this));
        }
    }

    ///CONSULTORES///

    public String getnomassig()
    {
        return this.nomAssig;
    }

    public String getCodiBloc() {
        return bloc.getCodi();
    }


}