/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.FileNotFoundException;

/**
 * Main.
 * @author EliAir
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        textUI UI = new textUI(true);
        UI.run();

    }
}


