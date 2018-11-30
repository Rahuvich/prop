package domini;

public class RestTornGrup extends ResGrup{
	
    private boolean mati;
    
// @Override a sobre de la funcio que vull sobreescriure

    public RestTornGrup(Grup g, boolean mati) {
        this.g = g;
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