/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Performance;

import algorithms.Algo;
import algorithms.Astar;
import algorithms.JPS;
import algorithms.Tools;
import application.Cartographer;
import application.LosAlgoritmos;
import datastructures.Vertex;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Performance testing.
 * @author Elias Nygren
 */
public class Performance {    
    private Tools Tools;
    private LosAlgoritmos la;
    private Cartographer c;
    
    private char[][] charM;
    private Vertex[][] map;
    
    private int[] t1;
    private int[] t2;
    
    
    /**
     * Initialization.
     * @throws Exception 
     */
    public Performance() throws Exception{
        la = new LosAlgoritmos();
//        c = new Cartographer(new File("./maps/combat.map"));
        c = new Cartographer(new File("./maps/AR0011SR.map"));
        Tools = new Tools();
        charM = c.toCharMatrix();        
    }
    
    /**
     * Compare Dijkstra, A* and JPS  or measure all algorithms with self-made and built-in data structures.
     */
    public void measure(){
        Scanner s = new Scanner(System.in);
        System.out.println("Measure or Compare? M/C");
        String in = s.nextLine();
        
        int heuristic = LosAlgoritmos.DIAGONAL;
        
        if(in.equals("M")){
            System.out.println("Hit enter to measure.");
            while(true){
                prep();
                in = s.nextLine();                
                if(in.isEmpty()){
                    System.out.println("Dijkstra, " + algo(LosAlgoritmos.DIJKSTRA, 0) + ",    " + map[t2[0]][t2[1]].getDistance());   
                    System.out.println("A*      , " + algo(LosAlgoritmos.ASTAR, heuristic)+ ",    " + map[t2[0]][t2[1]].getDistance());   
                    System.out.println("JPS     , " + algo(LosAlgoritmos.JPS, heuristic)+ ",    " + map[t2[0]][t2[1]].getDistance());   
                
                } else break;
            }
        }        
        if(in.equals("C")){
            System.out.println("How many ? ");
            in = s.nextLine();
            int howmany = Integer.parseInt(in);
            int i =0;
            while(i<howmany){
                prep();      
//                System.out.println(Arrays.toString(t1) + ", " + Arrays.toString(t2));
                System.out.println("D EN   , " + algo(LosAlgoritmos.DIJKSTRA, 0) + ",    " + map[t2[0]][t2[1]].getDistance());   
                System.out.println("D JAVA , " + algoJava(LosAlgoritmos.DIJKSTRA, 0) + ",    " + map[t2[0]][t2[1]].getDistance());   
    //                    System.out.println("");
                System.out.println("A EN   , " + algo(LosAlgoritmos.ASTAR, heuristic) + ",    " + map[t2[0]][t2[1]].getDistance());   
                System.out.println("A JAVA , " + algoJava(LosAlgoritmos.ASTAR, heuristic) + ",    " + map[t2[0]][t2[1]].getDistance());   
    //                    System.out.println("");
                System.out.println("J EN   , " + algo(LosAlgoritmos.JPS, heuristic) + ",    " + map[t2[0]][t2[1]].getDistance());   
                System.out.println("J JAVA , " + algoJava(LosAlgoritmos.JPS, heuristic) + ",    " + map[t2[0]][t2[1]].getDistance());   
    //                    System.out.println("----------------------------------------");
                if(map[t2[0]][t2[1]].getDistance()!=-1.0) i++;
            }
            
        }
        
        
        
    }
    
    /**
     * Return two different, valid coordinates.
     */
    private void prep(){
        la.loadCharMatrix(charM);
        map = la.getVertexMatrix();
        while(true){
            t1 = Tools.randomPoint(map);
            t2 = Tools.randomPoint(map);
            if(!(t1[0]==t2[0] && t1[1]==t2[1])) break;    
        }
        
    }
    
    /**
     * Measure performance of specified algo & heuristic.
     * @param algo
     * @param heuristic
     * @return duration
     */
    public long algo(int algo, int heuristic){        
        la.loadCharMatrix(charM);
        map = la.getVertexMatrix();
        
        Algo A = null;        
        if(algo==LosAlgoritmos.DIJKSTRA) A = new Astar(map, t1, t2, LosAlgoritmos.NO_HEURISTIC, true);
        else if(algo==LosAlgoritmos.ASTAR) A = new Astar(map, t1, t2, heuristic, true);
        else if(algo==LosAlgoritmos.JPS) A = new JPS(map, t1, t2, heuristic, true);
        
        long startTime = System.nanoTime();
        A.run();
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        return duration;
    }
    
    /**
     * Measure performance of specified algo & heuristic using Java built in data structures.
     * @param algo
     * @param heuristic
     * @return duration
     */
    public long algoJava(int algo, int heuristic){
        la.loadCharMatrix(charM);
        map = la.getVertexMatrix();
        
        AlgoJava A = null;
        
        if(algo==LosAlgoritmos.DIJKSTRA) A = new AstarJavaDataStructures(map, t1, t2, LosAlgoritmos.NO_HEURISTIC, true);
        else if(algo==LosAlgoritmos.ASTAR) A = new AstarJavaDataStructures(map, t1, t2, heuristic, true);
        else if(algo==LosAlgoritmos.JPS) A = new JPSJavaDataStructures(map, t1, t2, heuristic, true);
        
        long startTime = System.nanoTime();
        A.run();
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        return duration;
    }
    
    
}
