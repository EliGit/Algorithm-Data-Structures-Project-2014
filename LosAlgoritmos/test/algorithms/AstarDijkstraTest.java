/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import algorithms.Tools;
import algorithms.Astar;
import application.LosAlgoritmos;
import datastructures.Vertex;
import application.Cartographer;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the A* algorithm.
 * @author EliAir
 */
public class AstarDijkstraTest {
    private Astar A;
    private LosAlgoritmos la;
    private Cartographer c;
    private int[] t1;
    private int[] t2;
    
    public AstarDijkstraTest() {
    }
    
    /**
     * Sets up Astarm LosAlgoritmos and Cartographer classes that are needed in testing.
     * Cartographer loaded with test4.map.
     */
    
    @Before
    public void setUp() throws FileNotFoundException, Exception {
        la = new LosAlgoritmos();
        c = new Cartographer(new File("./maps/test4.map"));
        t1 = new int[] {0,0};
        t2 = new int[] {2, 2};
        A = new Astar(createVertexMatrix(), t1, t2, Tools.NO_HEURISTIC, false);
    }
    
    /**
     * Constructor should set up all necessary data structures, map, start and goal.
     */

    @Test
    public void testConstructor() {
//        assertNotNull(A.getHeap());
        assertNotNull(A.getMap());
        assertNotNull(A.getPath());
        assertNotNull(A.getS());
        assertNotNull(A.getT());
        assertEquals(t1[0], A.getS().getY());
        assertEquals(t1[1], A.getS().getX());
        assertEquals(t2[0], A.getT().getX());
        assertEquals(t2[1], A.getT().getX());        
    }
    

    /**
     * A* should set vertex.isOnPath() correctly.
     */
    
    @Test
    public void runFindsCorrectRoute(){
        A.run();
        assertTrue(A.getMap()[0][0].isOnPath());
        assertTrue(A.getMap()[0][1].isOnPath());
        assertTrue(A.getMap()[1][1].isOnPath());
        assertTrue(A.getMap()[2][1].isOnPath());
        assertTrue(A.getMap()[2][2].isOnPath());
        assertFalse(A.getMap()[1][0].isOnPath());
        assertFalse(A.getMap()[2][0].isOnPath());
        assertFalse(A.getMap()[1][0].isOnPath());
    }
    
    /**
     * A* should set vertex.distance correctly.
     * All non passable coordinates should have Integer.MAX_VALUE, others a valid distance.
     */
    
    @Test
    public void runSetsDistancesCorrectly(){        
        A.run();
        assertEquals(0, A.getMap()[0][0].getDistance(), 0.002);
        assertEquals(1, A.getMap()[0][1].getDistance(), 0.002);
        assertEquals(2, A.getMap()[1][1].getDistance(), 0.002);
        assertEquals(3, A.getMap()[2][1].getDistance(), 0.002);
        assertEquals(4, A.getMap()[2][2].getDistance(), 0.002);
        assertEquals(-1, A.getMap()[1][0].getDistance(), 0.002);
        assertEquals(-1, A.getMap()[2][0].getDistance(), 0.002);
        assertEquals(-1, A.getMap()[0][2].getDistance(), 0.002);
    }
    
    
    public Vertex[][] createVertexMatrix() throws Exception{
        char[][] charM = c.toCharMatrix();
        Vertex[][] vertexM = new Vertex[charM.length][charM[0].length];
        for (int i = 0; i < vertexM.length; i++) {
            for (int j = 0; j < vertexM[0].length; j++) {
                vertexM[i][j]=new Vertex(j, i, charM[i][j]);
            }
        }
        return vertexM;
    }    
}