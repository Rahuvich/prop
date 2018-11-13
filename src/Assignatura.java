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

    public Assignatura(String nomAssig, String codiBloc, int alumnes)
    {
        this.nomAssig = nomAssig;
        this.bloc = new Bloc(codiBloc);
        grupsAssig = new ArrayList<Grup>();
        assignarAlumnes(alumnes);
    }

    private void assignarAlumnes(int alumnes){
        grupsAssig = new ArrayList<Grup>();
        int quantitatTeories = alumnes - alumnes%100;

        quantitatTeories = String.valueOf(Math.abs((long)quantitatTeories)).charAt(0) - '0';
        quantitatTeories += 2;

        int quantitatSubgrups;
        if(alumnes < 100) quantitatSubgrups = 2;
        else if(alumnes > 300) quantitatSubgrups = 4;
        else quantitatSubgrups = 3;

        for (int i = 0; i < quantitatTeories; ++i){
            grupsAssig.add(new Grup((i+1) *10, this, alumnes/quantitatTeories));
            for(int j = 0; j < quantitatSubgrups; ++j){
                int subgrup = (i+1)*10;
                subgrup += j+1;
                grupsAssig.add(new Grup(subgrup, this, alumnes/quantitatTeories/quantitatSubgrups));
            }
        }
    }

    ///CONSULTORES///

    public String getNomAssig()
    {
        return this.nomAssig;
    }

    public String getCodiBloc() {
        return bloc.getCodi();
    }
    
    public ArrayList<Grup> getGrups(){
    	return grupsAssig;
    }


}