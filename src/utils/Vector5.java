package utils;

public class Vector5 {
    public int dia;
    public int hora;
    public int aula;
    public int grup;
    public int assig;


    public Vector5(int dia, int hora, int aula, int grup, int assig) {
        this.dia = dia;
        this.hora = hora;
        this.aula = aula;
        this.grup = grup;
        this.assig = assig;
    }

    public Vector5(Vector5 vec) {
        this.dia = vec.dia;
        this.hora = vec.hora;
        this.aula = vec.aula;
        this.grup = vec.grup;
        this.assig = vec.assig;
    }
}
