/*
 * To change this license header, choose License Headers htmlReader Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template htmlReader the editor.
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
public class URLCounter {

    public static final String TAG = URLCounter.class.getSimpleName();

    public static final Logger LOG = Logger.getLogger(TAG);


    //public static final String THE_URL = "http://www.gutenberg.org/cache/epub/29640/";
   public static final String THE_URL = "https://www.unison.mx";

    public static void main(String[] args) {

        URL webPage = null;
        try {
            webPage = new URL(THE_URL);
        } catch (MalformedURLException ex) {
            LOG.severe(ex.getMessage());
        }

        // Crear un flujo para leer datos del URL
        BufferedReader htmlReader = null;
        try {
            htmlReader = new BufferedReader(
                    new InputStreamReader( webPage.openStream() ));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }

        String htmlLine = null;

        try {
            while ((htmlLine = htmlReader.readLine()) != null) {
                if (htmlLine.toLowerCase().contains("<img")) {
                    System.out.println( htmlLine.trim() );
                }
            }
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        } finally {
            try {
                if (htmlReader != null) {
                    htmlReader.close();
                }
            } catch (IOException ex) {
                LOG.severe(ex.getMessage());
            }
        }

    }
}
