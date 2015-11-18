package Application;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Classe principale de l'application, point d'entrée principal. 
 * C'est cette classe qui lira le fichier de configuration config.cfg et instanciera une des deux implémentations disponibles
 * @author Benjamin Vianey & Cindy Bodet 
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
            
            System.out.println("Implementation: " + imp);
            System.out.println("Nombre co maximum : " + maxCo);
            System.out.println("Temps maximum d'inactivite: " + maxIdle+" ms, "+ maxIdle/1000 +" s");
            System.out.println("Port d'ecoute: " + port);
            
            
            if(imp!=null)
            {
            	if(imp.equals("1"))
            	{
            		ImplemBasLevel.Server llServer = new ImplemBasLevel.Server(maxCo, port, maxIdle);
            		llServer.runServer();
            	}
            	if(imp.equals("2"))
            	{
            		//lancer l'implementation haut level
            		ImplemHautLevel.Server hlServer = new ImplemHautLevel.Server(maxCo, port, maxIdle);
            		hlServer.runServer();
            	}
            }
            else
            {
            	defautServer();
            }
           
 
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite avec le fichier de configuration.");
            defautServer();
        }
    }
    
    
    public static void defautServer(){
    	System.out.println("Fichier de configuration absent ou parametre(s) incorrect(s).");
    	System.out.println("En l'absence d'un fichier de configuration valide, une configuration par défaut va se lancer");
    	System.out.println("Implementation: 1 (Bas niveau)");
        System.out.println("Nombre co maximum : 50" );
        System.out.println("Temps maximum d'inactivite: 10000 ms,  10 s");
        System.out.println("Port d'ecoute: 5555");
    	ImplemBasLevel.Server llServerDef = new ImplemBasLevel.Server(50, 5555, 10000);
    	llServerDef.runServer();
    }

}
