public class RestTornGrup extends Restriccions{
	
    private String assig;
    private int grup;
    private boolean mati;
    
// @Override a sobre de la funció que vull sobreescriure

    public RestTornGrup(String assig, boolean mati) {
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