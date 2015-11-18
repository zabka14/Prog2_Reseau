package Application;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Classe principale de l'application
 * @author Benjamin Vianey
 *
 */
public class Application {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader("config.cfg"));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            String imp = (String) jsonObject.get("Implementation");
            int maxCo = Integer.parseInt((String) jsonObject.get("MaxThread"));
            int maxIdle = Integer.parseInt((String) jsonObject.get("MaxIdleTime"));
            int port =  Integer.parseInt((String) jsonObject.get("Port"));
            
            
            
 
            System.out.println("Implémentation: " + imp);
            System.out.println("Nombre co maximum : " + maxCo);
            System.out.println("Temps maximum d'inactivité: " + maxIdle+" ms, "+ maxIdle/1000 +" s");
            System.out.println("Port d'écoute: " + port);
            
            
            if(imp!=null)
            {
            	if(imp.equals("1"))
            	{
            		ImplemBasLevel.Server llServer = new ImplemBasLevel.Server(maxCo, port, maxIdle);
            		llServer.runServer();
            	}
            	if(imp.equals("1"))
            	{
            		//lancer l'implémentation haut level
            	}
            }
            else
            {
            	System.out.println("Fichier de configuration absent ou paramètre(s) incorrect(s).");
            }
           
 
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite avec le fichier de configuration.");
        }
    }

}
