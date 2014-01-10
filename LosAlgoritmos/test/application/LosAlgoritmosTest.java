/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import datastructures.Stack;
import datastructures.Vertex;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the LosAlgoritmos class.
 * @author EliAir
 */
public class LosAlgoritmosTest {
    private LosAlgoritmos la;
    private Cartographer c;
    private int[] t1;
    private int[] t2;
    private Stack<Vertex> list;
    
    public LosAlgoritmosTest() {
    }
    
    /**
     * Setup runs LosAlgoritmos and Cartographer constructors.
     * Cartographer is used in some of the tests and it is initialized with test1.map.
     * @throws FileNotFoundException 
     */
    @Before
    public void setUp() throws FileNotFoundException, Exception {
        la = new LosAlgoritmos();
        c = new Cartographer(new File("./maps/test4.map"));
        la.loadCharMatrix(c.toCharMatrix());
        t1 = new int[] {0,0};
        t2 = new int[] {2, 2};
    }
    

    
    /**
     * Checks that the vertex matrix does not have null item at [0][0] and that it has the correct size.
     * LoadMap should create a matrix full of vertex objects.
     * @throws Exception 
     */
    
    @Test
    public void testLoadCharMatrix() throws Exception{        
        assertNotNull(la.getVertexMatrix()[0][0]);
        assertEquals(3, la.getVertexMatrix().length);
        assertEquals(1, la.getVertexMatrix()[0][1].getX());
        assertEquals(0, la.getVertexMatrix()[0][1].getY());
    }
    
    /**
     * Route returns the correct routeBasic and errors.
     */
    
    @Test
    public void routeBasic() {        
        try {
            list = la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, true);
            assertTrue(list.contains(la.getVertexMatrix()[0][0]));
            assertTrue(list.contains(la.getVertexMatrix()[1][1]));
            assertTrue(list.contains(la.getVertexMatrix()[2][2]));
            assertFalse(list.contains(la.getVertexMatrix()[2][0]));
            

            
        } catch (Exception ex) {
            System.out.println(ex);
            assert false;
        }
        
        try{
            la.route(t1, t2, -1, LosAlgoritmos.NO_HEURISTIC, true);
        } catch (Exception ex) {
            assertEquals("java.lang.Exception: Invalid algorithm!", ex.toString());
        }
        
        try{
            la.route(t1, t2, LosAlgoritmos.DIJKSTRA, LosAlgoritmos.NO_HEURISTIC, true);
        } catch (Exception ex) {
            assertEquals("java.lang.Exception: Invalid heuristic!", ex.toString());
        }
    }
        
        
    }
    

    
    
