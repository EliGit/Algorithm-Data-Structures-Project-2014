/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import algorithms.Tools;
import application.LosAlgoritmos;
import application.Cartographer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Heavy testing for all routing algorithms. 
 * Main idea: results of all three algorithms should be the same, always.
 * Warning: important to reload the map after every search, as the algorithms change the state of the VertexMatrix (the map)
 * @author EliAir
 */
public class CorrectPathsTest {
    private final static double EPSILON = 0.5;
    private Tools Tools;
    private LosAlgoritmos la;
    private Cartographer c;
    private int[] t1;
    private int[] t2;
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
    public void diagonalRoutingTest() throws Exception{
        int heuristic=LosAlgoritmos.DIAGONAL-1; //-1 because heuristic++ first line of loop
        
        int i = 0;
        while(true){
            //change heuristic, there are 2 of them in this test (diagonal equal cost, euclidean)
            if(i%100==0) heuristic++;
            if(heuristic>LosAlgoritmos.EUCLIDEAN) break;
            
            //two random valid points on the map
            t1 = Tools.randomPoint(la.getVertexMatrix());
            t2 = Tools.randomPoint(la.getVertexMatrix());
            
            //LosAlgoritmos.route clears all changes on the map so no need to reload it in between.
            la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, true);
            double dijkstra = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.ASTAR, heuristic, true);
            double astar = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.JPS, heuristic, true);
            double jps = la.getDistance();
            
            //if not dijkstra==astar==jps -> log error
            if(Math.abs(dijkstra-astar)>EPSILON || Math.abs(dijkstra-jps)>EPSILON){                
                System.out.println(dijkstra);
                System.out.println(astar);
                System.out.println(jps);
                System.out.println(""+Arrays.toString(t1)+","+Arrays.toString(t2));
                System.out.print("heuristic: ");
                if(heuristic==LosAlgoritmos.DIAGONAL) System.out.println(LosAlgoritmos.DIAGONAL);
                if(heuristic==LosAlgoritmos.DIAGONAL_EQUAL_COST) System.out.println(LosAlgoritmos.DIAGONAL_EQUAL_COST);
                if(heuristic==LosAlgoritmos.EUCLIDEAN) System.out.println(LosAlgoritmos.EUCLIDEAN);
            }
            
            assertEquals(dijkstra, astar, EPSILON);                
            assertEquals(dijkstra, jps, EPSILON);
            
            //this undoes all changes to the vertices left from running JPS so Tools.randomPoint works
            la.loadCharMatrix(charM);
            i++;
        }        
    }
    
    
    
    /**
     * Dijkstra and Astar should have same MANHATTAN scores when diagonal not allowed.
     * JPS is not tested as it always uses 8 directions, MANHATTAN is consistent with only 4.
     * @throws Exception 
     */
    @Test
    public void manhattanTests() throws Exception{
        for (int i = 0; i < 100; i++) {
            //two random valid points on the map
            t1 = Tools.randomPoint(la.getVertexMatrix());
            t2 = Tools.randomPoint(la.getVertexMatrix());
            
            la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, false);
            double dijkstra = la.getDistance();
            la.route(t1, t2, LosAlgoritmos.ASTAR, LosAlgoritmos.MANHATTAN, false);
            double astar = la.getDistance();
                  
            //if not dijkstra==astar -> log error
            if(Math.abs(dijkstra-astar)>EPSILON ){
                System.out.println(dijkstra);
                System.out.println(astar);
                System.out.println(""+Arrays.toString(t1)+","+Arrays.toString(t2));    
            } 
            
            assertEquals(dijkstra, astar, EPSILON);     
            
            //this undoes all changes to the vertices left from running JPS so Tools.randomPoint works
            la.loadCharMatrix(charM);
        }        
    }
}
