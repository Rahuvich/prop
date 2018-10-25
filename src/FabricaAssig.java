import org.json.*;

import java.util.ArrayList;

public class FabricaAssig {

    public static void readAssig(String archivo){
        JSONObject assig = new JSONObject(archivo);
        JSONArray lista = assig.getJSONArray("Assignatures");

        for(int i = 0; i < assig.length(); ++i){
            String nomAssig = lista.getJSONObject(i).getString("codiAssig");
            String numBloc = lista.getJSONObject(i).getString("bloc");

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
