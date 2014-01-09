/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import application.LosAlgoritmos;
import application.Cartographer;
import datastructures.Vertex;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Heavy testing for all routing algorithms. 
 * Main idea: results of all three algorithms should be the same, always.
 * Warning: important to reload the map after every search, as the algorithms change the change of the VertexMatrix (the map)
 * @author EliAir
 */
public class RoutingAlgorithmTest {
    private final static double EPSILON = 0.002;
    private Tools Tools;
    private LosAlgoritmos la;
    private Cartographer c;
    private int[] t1;
    private int[] t2;
    private ArrayList<Vertex> list;
    private char[][] charM;
    
    @Before
    public void setUp() throws FileNotFoundException, Exception {
        Random r = new Random();
        la = new LosAlgoritmos();
        c = new Cartographer(new File("./maps/combat.map"));
        charM = c.toCharMatrix();
        la.loadCharMatrix(charM);      
        Tools = new Tools();
    }
    
    
    /**
     * Compares the results of all routing algorithms using the same heuristic.
     * Uses EPSILON in comparing the double values. 50 tests per each heuristic.
     * Manhattan is not tested as it is not consistent when diagonal is allowed.
     * @throws Exception 
     */
    @Test
    public void sameHeuristicRoutingTest() throws Exception{
        int heuristic=LosAlgoritmos.DIAGONAL;
        for (int i = 0; i < 1000; i++) {
            t1 = Tools.randomPoint(la.getVertexMatrix());
            t2 = Tools.randomPoint(la.getVertexMatrix());
            la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, true);
            double dijkstra = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.ASTAR, heuristic, true);
            double astar = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.JPS, heuristic, true);
            double jps = la.getDistance();
            if(Math.abs(dijkstra-astar)>EPSILON || Math.abs(dijkstra-jps)>EPSILON){
                System.out.println(dijkstra);
                System.out.println(astar);
                System.out.println(jps);
                System.out.println(""+Arrays.toString(t1)+","+Arrays.toString(t2));    
//                    System.out.println(""+Arrays.toString(tt1)+","+Arrays.toString(tt2)); 
//                    System.out.println(Arrays.toString(la.closestValidCoordinate(tt1)) + " , " + Arrays.toString(la.closestValidCoordinate(tt2)));
            }
            assertEquals(dijkstra, astar, EPSILON);                
            assertEquals(dijkstra, jps, EPSILON);
            if(i%100==0) heuristic++;
            if(heuristic>LosAlgoritmos.EUCLIDEAN) break;   
            la.loadCharMatrix(charM);
        }        
    }
    
    /**
     * Compares the results of all routing algorithms using different heuristic in each.
     * Uses EPSILON in comparing the double values. 50 tests per each heuristic.
     * Manhattan is not tested as it is not consistent when diagonal is allowed.
     * @throws Exception 
     */
    @Test
    public void differentHeuristicRoutingTest() throws Exception{
        int heuristic1=LosAlgoritmos.DIAGONAL;
        int heuristic2=LosAlgoritmos.DIAGONAL_EQUAL_COST;
        for (int i = 0; i < 1000; i++) {
            t1 = Tools.randomPoint(la.getVertexMatrix());
            t2 = Tools.randomPoint(la.getVertexMatrix());

            la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, true);
            double dijkstra = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.ASTAR, heuristic1, true);
            double astar = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.JPS, heuristic2, true);
            double jps = la.getDistance();        

            if(Math.abs(dijkstra-astar)>EPSILON || Math.abs(dijkstra-jps)>EPSILON){
                System.out.println(dijkstra);
                System.out.println(astar);
                System.out.println(jps);
                System.out.println(""+Arrays.toString(t1)+","+Arrays.toString(t2));                        
            } 
//                else {
                assertEquals(dijkstra, astar, EPSILON);                
                assertEquals(dijkstra, jps, EPSILON);
//                }


            if(i%100==0) {
                heuristic1++;
                heuristic2++;
            }
            if(heuristic1>LosAlgoritmos.EUCLIDEAN) break;
            if(heuristic2>LosAlgoritmos.EUCLIDEAN) heuristic2=LosAlgoritmos.DIAGONAL;
            la.loadCharMatrix(charM);
        }        
    }
    
    /**
     * Dijkstra and Astar should have same MANHATTAN scores when diagonal not allowed.
     * JPS is not tested as it always uses 8 directions, MANHATTAN is consistent with only 4.
     * @throws Exception 
     */
    @Test
    public void manhattanTests() throws Exception{
        for (int i = 0; i < 50; i++) {
            t1 = Tools.randomPoint(la.getVertexMatrix());
            t2 = Tools.randomPoint(la.getVertexMatrix());
            
            la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, false);
            double dijkstra = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.ASTAR, LosAlgoritmos.MANHATTAN, false);
            double astar = la.getDistance();
                                
            if(Math.abs(dijkstra-astar)>EPSILON ){
                System.out.println(dijkstra);
                System.out.println(astar);
                System.out.println(""+Arrays.toString(t1)+","+Arrays.toString(t2));    
            } 
                assertEquals(dijkstra, astar, EPSILON);     
                la.loadCharMatrix(charM);
        }        
    }
    
    
//    @Test
//    public void dijkstraTests() throws Exception{
//        
//        for (int i = 0; i < 100; i++) {
//            t1 = Tools.randomPoint(la.getVertexMatrix());
//            t2 = Tools.randomPoint(la.getVertexMatrix());
//            la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, true);
////            double dijkstra = la.getDistance();          
//        }        
//        
//    }
//    
//    @Test
//    public void astarTests() throws Exception{
//        for (int i = 0; i < 100; i++) {
//            t1 = Tools.randomPoint(la.getVertexMatrix());
//            t2 = Tools.randomPoint(la.getVertexMatrix());
//            la.route(t1, t2, LosAlgoritmos.ASTAR, LosAlgoritmos.DIAGONAL_EQUAL_COST, true);
////            double astar = la.getDistance();
//        }  
//    }
//    
//    @Test
//    public void JPSTests() throws Exception{
//        for (int i = 0; i < 100; i++) {
//            t1 = Tools.randomPoint(la.getVertexMatrix());
//            t2 = Tools.randomPoint(la.getVertexMatrix());
//            la.route(t1, t2, LosAlgoritmos.JPS, LosAlgoritmos.DIAGONAL_EQUAL_COST, true);
////            double jps = la.getDistance();        
//        }  
//    }
    
    
}
