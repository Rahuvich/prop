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
	    System.out.println("Soc el net grup");
    	/*
    	if(mati)
    		if(hi ha lloc pel de matins) return true;
    		else return false;
    	else
    		if(hi ha lloc de tardes) return true;
    		else return false;
    	*/
		return false;
	}
    
}