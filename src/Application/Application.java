package Application;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Application {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader("config.txt"));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            String imp = (String) jsonObject.get("Implementation");
            String maxCo = (String) jsonObject.get("MaxThread");
            String maxIdle = (String) jsonObject.get("MaxIdleTime");
            String port = (String) jsonObject.get("Port");
            
 
            System.out.println("Implémentation: " + imp);
            System.out.println("Nombre co maximum : " + maxCo);
            System.out.println("Temps maximum d'inactivité: " + maxIdle);
            System.out.println("Port d'écoute: " + port);
           
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
