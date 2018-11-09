public class RestTornAssig extends ResAssig{
	
    private boolean mati;
    
// @Override a sobre de la funcio que vull sobreescriure

    public RestTornAssig(Assignatura assig, boolean mati) {
        this.assig = assig;
        this.mati = mati;
    }
    
    @Override
    public boolean esCompleix()
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