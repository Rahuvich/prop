package domini;

public class Aula
{
    ///ATRIBUTS///
    private String aulari;
    private char pis;
    private int numero;
    private int capacitat;

    ///CREADORES///
    public Aula(String aulari, char pis, int numero, int capacitat)
    {
        this.aulari = aulari;
        this.pis = pis;
        this.numero = numero;
        this.capacitat = capacitat;
    }

    ///CONSULTORES///
    public String getAulari()
    {
        return this.aulari;
    }


    public char getPis()
    {
        return this.pis;
    }

    public int getNumero()
    {
        return this.numero;
    }

    public String getAula(){
        return aulari + pis + String.format("%02d", numero);
    }

    public int getCapacitat()
    {
        return this.capacitat;
    }

    public boolean esGran(){
        return capacitat > 50;
    }

}

