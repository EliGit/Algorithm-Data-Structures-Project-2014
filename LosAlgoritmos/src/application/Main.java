/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import losalgoritmos.LosAlgoritmos;

/**
 * Main.
 * @author EliAir
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        textUI UI = new textUI();
        UI.run();
        
        
        //debugging
//        Cartographer c = new Cartographer(new File("./maps/isound1.map"));
//        LosAlgoritmos la = new LosAlgoritmos();
//        la.loadMap(c.toCharMatrix());
////        la.printMapWithRoute();
//        la.astarDefault();
//        la.printMapWithRoute();

    }
}


