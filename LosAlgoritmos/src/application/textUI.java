/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.util.Scanner;
import losalgoritmos.LosAlgoritmos;

/**
 *
 * @author EliAir
 */
public class textUI {
    private Cartographer c;
    private char[][] map;
    private boolean mode;
    private Scanner s;

    public textUI(boolean mode) throws Exception {
        this.c = new Cartographer(new File("./maps/isound1.map"));
        this.map = this.c.toCharMatrix();
        this.mode = mode;
        s = new Scanner(System.in);
    }
        
    public void run(){
        System.out.println("---------- TiraLabra 2013: reititysalgoritmeja ----------");
        System.out.println("Map: ");
//        printMap();
        if(mode){
            // default
            LosAlgoritmos la = new LosAlgoritmos();
            la.loadMap(map);
            la.astar();
            
        } else {
            System.out.print("Lähtöpiste i: ");
            String lahtoi = s.nextLine();
            System.out.println("");
            
            System.out.print("Lähtöpiste j: ");
            String lahtoj = s.nextLine();
            System.out.println("");
            
            System.out.print("Maali i: ");
            String maalii = s.nextLine();
            System.out.println("");
            
            System.out.print("Maali j: ");
            String maalij = s.nextLine();
            System.out.println("");
            
        }
    }
    
    public void printMap(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
