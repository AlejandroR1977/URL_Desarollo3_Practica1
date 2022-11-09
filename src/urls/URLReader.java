/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rnavarro
 */
public class URLReader {
    // https://people.sc.fsu.edu/~jburkardt/data/csv/csv.html
    // https://people.sc.fsu.edu/~jburkardt/data/csv/cities.csv
    // https://www.gutenberg.org/ebooks/search/?query=Pablo
    // https://people.sc.fsu.edu/~jburkardt/data/xml/ell_65.xml

    public static final String TAG = URLReader.class.getSimpleName();

    public static final Logger LOG = Logger.getLogger(TAG);

    public static void main(String[] args) {

        URL webPage = null;
        try {
           // webPage = new URL("https://www.unison.mx");
           webPage = new URL("https://people.sc.fsu.edu/~jburkardt/data/csv/cities.csv");
        } catch (MalformedURLException ex) {
            LOG.severe(ex.getMessage());
        }

        // Crear un flujo para leer datos del URL
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader( webPage.openStream() ));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }

        String inputLine;

        try {
            while ((inputLine = in.readLine()) != null)
                if(inputLine.contains(args[1]))
                System.out.println(inputLine);

        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }
        try {
            in.close();
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }
    }

}
