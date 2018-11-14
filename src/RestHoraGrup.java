public class RestHoraGrup extends ResGrup{
	
	//Hora en la que NO vols que es faci classe
	
    private int hora;
    
// @Override a sobre de la funcio que vull sobreescriure

    public RestHoraGrup(Grup grup, int hora) {
        this.g = grup;
        this.hora = hora;
    }
    
    @Override
    public boolean esCompleix(Classe classe)
	{
	    System.out.println("Soc el net: restHoraGrup");
	    boolean ans = false;
	    if (classe.getHoraIni()!=hora) ans=true;
		return ans;
	}
    
}