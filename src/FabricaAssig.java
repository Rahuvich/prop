
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FabricaAssig {

    public static void carregaAssig(String archivo) throws IOException, ParseException {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader(archivo));
        // typecasting obj to JSONObject
        JSONObject assigFile = (JSONObject) obj;

        JSONArray llista = (JSONArray) assigFile.get("Assignatures");

        for(int i = 0; i < llista.size(); ++i){
            String nomAssig = (String) ((JSONObject) llista.get(i)).get("codiAssig");
            String codiBloc = (String) ((JSONObject) llista.get(i)).get("bloc");
            System.out.println(nomAssig + " pertany a bloc " + codiBloc);
            JSONArray llistaGrups = (JSONArray) ((JSONObject) llista.get(i)).get("grups");
            ArrayList<Integer> grups = new ArrayList<>();
            for(int j = 0; j < llistaGrups.size(); ++j) {
                grups.add(((Long) llistaGrups.get(j)).intValue());
                System.out.print(" " + ((Long) llistaGrups.get(j)).intValue());
            }
            System.out.println();
            Assignatura assig = new Assignatura(nomAssig, codiBloc, grups);
        }
    }

    /*

    JSON FILE:

    {
   "pageInfo": {
         "pageName": "abc",
         "pagePic": "http://example.com/content.jpg"
    }
    "posts": [
         {
              "post_id": "123456789012_123456789012",
              "actor_id": "1234567890",
              "picOfPersonWhoPosted": "http://example.com/photo.jpg",
              "nameOfPersonWhoPosted": "Jane Doe",
              "message": "Sounds cool. Can't wait to see it!",
              "likesCount": "2",
              "comments": [],
              "timeOfPost": "1234567890"
         }
    ]
}


    JAVA FILE:

    JSONObject obj = new JSONObject(" .... ");
    String pageName = obj.getJSONObject("pageInfo").getString("pageName");

    JSONArray arr = obj.getJSONArray("posts");
    for (int i = 0; i < arr.length(); i++)
    {
        String post_id = arr.getJSONObject(i).getString("post_id");
    }*/
}
