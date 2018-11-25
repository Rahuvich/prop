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

    public boolean esMesPetitaQue(int auxDia, int auxHora, int auxAula){
        if(dia > auxDia) return false;
        if(dia >= auxDia && hora > auxHora) return false;
        if(dia >= auxDia && hora >= auxHora && aula > auxAula) return false;
        return true;
    }

    public boolean equalsClasse(int auxDia, int auxHora, int auxAula) {
        return dia == auxDia && hora == auxHora && aula == auxAula;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vector5)) return false;
        else {
            Vector5 vec = new Vector5((Vector5) obj);
            return dia == vec.dia && hora == vec.hora && aula == vec.aula;
        }
    }
}
