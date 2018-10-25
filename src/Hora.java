public class Hora {
    ///ATRIBUTS///
    private int hora;

    ///CREADORA///
    public Hora(int hora)
    {
        if(hora < 8 && hora > 20) throw new ArithmeticException("La hora no es valida");
        else this.hora = hora;
    }
}