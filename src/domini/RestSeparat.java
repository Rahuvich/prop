package domini;

public class RestSeparat extends ResAssig
{

// @Override a sobre de la funcio que vull sobreescriure

    public RestSeparat(Assignatura assig) {
        this.assig = assig;
    }

    @Override
    public boolean esCompleix(Classe classe)
    {

        boolean ans = false;
        int grup = classe.getGrup().getNumero();
        int dia = classe.getDia();
        if(dia == grup%5)
        {
            ans = true;
        }
        return ans;
    }
}
