package domini;

public class RestDiaGrup extends ResGrup
{
    private int dia;
    public RestDiaGrup(Grup g, int dia)
    {
        this.g = g;
        this.dia = dia;
    }
                // @Override a sobre de la funcio que vull sobreescriure
    @Override
    public boolean esCompleix(Classe classe)
    {
        return Dia.values()[classe.getDia()].equals(Dia.values()[dia]);
    }

    public int getDia(){return dia;}
}
