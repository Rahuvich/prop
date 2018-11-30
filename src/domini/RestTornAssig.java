package domini;

public class RestTornAssig extends ResAssig{
	
    private boolean mati;
    
// @Override a sobre de la funcio que vull sobreescriure

    public RestTornAssig(Assignatura assig, boolean mati) {
        this.assig = assig;
        this.mati = mati;
    }
    
    @Override
    public boolean esCompleix(Classe classe)
	{
	    boolean ans = false;
	    if (mati) {
	    	if (classe.getHoraIni()<14) ans=true;
	    }
	    else {
	    	if (classe.getHoraIni()>13) ans=true;
	    }
		return ans;
	}
    
}