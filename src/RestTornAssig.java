public class RestTornAssig extends Restriccions{
	
    private String assig;
    private boolean mati;
    
// @Override a sobre de la funcio que vull sobreescriure

    public RestTornAssig(String assig, boolean mati) {
        this.assig = assig;
        this.mati = mati;
    }
    
    @Override
    public boolean es_compleix()
	{
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