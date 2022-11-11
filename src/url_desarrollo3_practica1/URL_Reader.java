package url_desarrollo3_practica1;

import java.net.*;
import java.util.*;
import java.util.logging.*;
import java.io.*;

public class URL_Reader {

    public static final String TAG = URL_Reader.class.getSimpleName();

    public static final Logger LOG = Logger.getLogger(TAG);

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        URL webPage = null;
        URLConnection connection = null;
        int counter = 0, position = 0;
        System.out.println("Introduce un link: ");
        args[0] = lector.nextLine();
        System.out.println("Introduce una palabra: ");
        args[1] = lector.nextLine();
        ArrayList<Character> palabraList = new ArrayList<>();
        for (char ch : args[1].toCharArray()) {
            palabraList.add(ch);
        }
        try {;
            webPage = new URL(args[0]);

            // abrir conexion
            connection = webPage.openConnection();

            String tipoContenido = connection.getContentType();
            long size = connection.getContentLengthLong();

            System.out.println("Tipo: " + tipoContenido);
            System.out.println("Tamaño en bytes: " + size);
            System.out.println("");

        } catch (MalformedURLException ex) {
            LOG.severe(ex.getMessage());
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }
        // Obtener encabezados que regresa el web server
        Map<String, List<String>> headers = connection.getHeaderFields();

        // Obtener nombres de encabezados que regresa el web server
        Set<String> headersName = headers.keySet();

        for (String name : headersName) {
            System.out.printf("%s = %s\n", name, headers.get(name));
        }

        System.out.println("\nContedido:\n");

        // Crear un flujo para leer datos del URL
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }

        String inputLine = null;
        try {
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.toLowerCase().contains(args[1])) {
                    position = 0;
                    counter++;
                    ArrayList<Character> lineList = new ArrayList<>();
                    for (char ch : inputLine.toCharArray()) {
                        lineList.add(ch);
                    }
                    System.out.println(lineList);
                    for (int i = 0; i < lineList.size(); i++) {
                        char c = Character.toLowerCase(lineList.get(i));
                        if (Objects.equals(palabraList.get(0), c)) {
                            int matched = 1;
                            if (palabraList.size() > 1) {
                                int j = 1;
                                int k = i + 1;
                                c = Character.toLowerCase(lineList.get(k));
                                while (palabraList.get(j) == c) {
                                    j++;
                                    k++;
                                    c = Character.toLowerCase(lineList.get(k));
                                    matched++;
                                    if (matched == palabraList.size()) {
                                        position = i;
                                        break;
                                    }
                                }
                            } else {
                                position = i;
                            }
                        }
                    }
                    System.out.println("La posicion de ocurrencia numero " + counter + " es: " + position);
                }
            }
            System.out.println("");
            System.out.println("La palabra " + args[1] + " aprece esta cantidad de veces: " + counter);
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                LOG.severe(ex.getMessage());
            }
        }
    }
}
