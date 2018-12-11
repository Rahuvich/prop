package domini;

public class RestDiaGrup extends ResGrup
{
    private int dia;
    public RestDiaGrup(Grup g, int dia)
    {
        this.g = g;
        this.dia = dia;
        System.out.println("El grup " + g.getNumero() + " de l'assignatura " + g.getNomAssig() + " tindra clase els " + dia);

    }
                // @Override a sobre de la funcio que vull sobreescriure
    @Override
    public boolean esCompleix(Classe classe)
    {
        boolean ans = false;
        if ((classe.getDia())== dia) ans=true;
        System.out.println("classe.getDia()=" + classe.getDia());
        return ans;
    }
}
