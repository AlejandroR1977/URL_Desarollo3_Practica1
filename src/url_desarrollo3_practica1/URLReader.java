package url_desarrollo3_practica1;

import urls.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author rnavarro
 */
public class URLReader {
     public static final String TAG = URLReader.class.getSimpleName();
    
    public static final Logger LOG = Logger.getLogger(TAG);
    
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        URL webPage = null;
        URLConnection connection = null;
        int counter = 0;
        System.out.println("Introduce un link: ");
        args[0] = lector.nextLine();        
        System.out.println("Introduce una palabra: ");
        args[1] = lector.nextLine();
        try {;
            webPage = new URL(args[0]);
            
            // abrir conexion
            connection = webPage.openConnection();
            
            String tipoContenido = connection.getContentType();
            long size = connection.getContentLengthLong();
            
            System.out.println("Tipo: " + tipoContenido );   
            System.out.println("Tamaño en bytes: " + size ); 
            System.out.println("");
           
        } catch (MalformedURLException ex) {
            LOG.severe(ex.getMessage());
        } catch (IOException ex) {
             LOG.severe(ex.getMessage());
        }
        // Obtener encabezados que regresa el web server
        Map<String,List<String>> headers = connection.getHeaderFields();  
        
        // Obtener nombres de encabezados que regresa el web server
        Set<String> headersName = headers.keySet();
        
        for(String name: headersName) {
            System.out.printf("%s = %s\n",name, headers.get(name) );
        }
        
        System.out.println("\nContedido:\n");
        
        // Crear un flujo para leer datos del URL
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader( connection.getInputStream() ));
        } catch (IOException ex) {
             LOG.severe(ex.getMessage());
        }

        String inputLine = null;
        
        try {
            while ((inputLine = in.readLine()) != null){
                if(inputLine.toLowerCase().contains(args[1])){
                    counter++;
                }
                System.out.println(inputLine);
                }
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }
        try {
            in.close();
        } catch (IOException ex) {
             LOG.severe(ex.getMessage());
        }
        System.out.println("");
        System.out.println("La palabra " + args[1] + " aprece esta cantidad de veces: " + counter);
    }
}
