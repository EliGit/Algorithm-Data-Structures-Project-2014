                                               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import application.Cartographer;
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
    private Scanner s;
    private LosAlgoritmos la;
    

    public textUI() throws Exception {
        this.c = new Cartographer(new File("./maps/isound1.map"));
        this.map = this.c.toCharMatrix();
        this.la = new LosAlgoritmos();
        this.s = new Scanner(System.in);
    }
        
    public void run(){
        System.out.println("---------- TiraLabra 2013: A* vs. JPS ----------");
        System.out.println("Input 'd' for default map and route (quickplay)");
        System.out.println("Input nothing (enter) to select map and route");
        while(true){
            String input = s.nextLine();
            
            if(input.equals("d")){
                System.out.println("Default map: ");            
                la.loadMap(map);
                la.loadStart(1, 37);
                la.loadGoal(33, 33);
                la.printMapWithRoute();                
                la.astar();
                la.printMapWithRoute();
                la.printAllDistances();
                
                
            } else if (input == ""){
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
                
                la.loadMap(map);
                la.loadStart(Integer.parseInt(lahtoi), Integer.parseInt(lahtoj));
                la.loadGoal(Integer.parseInt(maalii), Integer.parseInt(maalij));
                la.printMapWithRoute();
                la.astar();                
                la.printMapWithRoute();
            }
        }
               
            
            
        }
    
    
//    public void printMap(){
//        System.out.println("Map: ");
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[0].length; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println("");
//        }
//        System.out.println("");
//    }
}
