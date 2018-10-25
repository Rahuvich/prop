
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
    public Aula (String aula, String capacitat)
    {
        //char caracter = cadena.charAt(0); //Solo primer caracter
        StringBuilder sb = new StringBuilder();
        sb.append(aula.charAt(0));
        sb.append(aula.charAt(1));
        this.aulari = sb.toString();

        this.pis = aula.charAt(2);

        sb = new StringBuilder();
        sb.append(aula.charAt(3));
        sb.append(aula.charAt(4));
        sb.append(aula.charAt(5));
        this.numero = Integer.parseInt(sb.toString() );
        //int entero = Integer.parseInt(cadena);

        this.capacitat = Integer.parseInt(capacitat);



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

