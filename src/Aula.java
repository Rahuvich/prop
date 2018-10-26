
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
    public String getaulari()
    {
        return this.aulari;
    }


    public char getpis()
    {
        return this.pis;
    }

    public int getnumero()
    {
        return this.numero;
    }

    public int getcapacitat()
    {
        return this.capacitat;
    }

}

