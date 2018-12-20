package domini;

public class RestFranjaHoraria extends ResAssig
{
    private int horaIni;
    private int horaFi;
    private int dia;

    public RestFranjaHoraria(Assignatura assig, int horaIni, int horaFi, int dia)
    {
        this.assig = assig;
        this.horaIni = horaIni;
        this.horaFi = horaFi;
        this.dia = dia;
    }
    // @Override a sobre de la funcio que vull sobreescriure
    @Override
    public boolean esCompleix(Classe classe)
    {   boolean ans = true;
        if (classe.getDia() == dia && classe.getHoraIni() < horaFi && classe.getHoraIni() >= horaIni ) ans=false;
        return ans;
    }

    public int getHoraFi() {
        return horaFi;
    }

    public int getDia() {
        return dia;
    }

    public int getHoraIni() {
        return horaIni;
    }
}
