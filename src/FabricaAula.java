import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FabricaAula {


    public static void Fabricar_aulas(String archivo) throws FileNotFoundException, IOException {
        String aula;
        String capacitat;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while( ((aula = b.readLine())!=null) && ((capacitat = b.readLine())!=null) )
        {
            Aula aula1 = new Aula(aula, capacitat);
        }
	       /* BufferedReader b = new BufferedReader(f);
	        while((cadena = b.readLine())!=null) {
	            System.out.println(cadena);
	        }*/
    }

    public static void main(String[] args) throws IOException {
        Fabricar_aulas("C:/Users/usuario/Documents/GitHub/core"); // Si hi ha un error de lectura a aquest ficher donara error
    }

}