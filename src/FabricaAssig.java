import org.json.*;

import java.util.ArrayList;

public class FabricaAssig {

    public static void carregaAssig(String archivo){
        JSONObject assigFile = new JSONObject(archivo);
        JSONArray llista = assigFile.getJSONArray("Assignatures");

        for(int i = 0; i < assigFile.length(); ++i){
            String nomAssig = llista.getJSONObject(i).getString("codiAssig");
            String codiBloc = llista.getJSONObject(i).getString("bloc");
            JSONArray llistaGrups = llista.getJSONObject(i).getJSONArray("grups");
            ArrayList<Integer> grups = new ArrayList<>();
            for(int j = 0; j < llistaGrups.length(); ++j) grups.add(llistaGrups.getInt(j));
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
