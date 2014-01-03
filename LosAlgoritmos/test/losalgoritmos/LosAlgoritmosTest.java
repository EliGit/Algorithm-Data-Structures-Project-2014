/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

import application.Cartographer;
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
    
    public LosAlgoritmosTest() {
    }
    
    /**
     * Setup runs LosAlgoritmos and Cartographer constructors.
     * Cartographer is used in some of the tests and it is initialized with test1.map.
     * @throws FileNotFoundException 
     */
    @Before
    public void setUp() throws FileNotFoundException {
        la = new LosAlgoritmos();
        c = new Cartographer(new File("./maps/test1.map"));
    }
    
    /**
     * Tests that VertexMap, Goal and Start are all null after running the constructor.
     */
    
    @Test
    public void testConstructor() {
        assertEquals(null, la.getGoal());
        assertEquals(null, la.getStart());
        assertEquals(null, la.getVertexMatrix());
    }
    
    /**
     * Checks that the vertex matrix does not have null item at [0][0] and that it has the correct size.
     * LoadMap should create a matrix full of vertex objects.
     * @throws Exception 
     */
    
    @Test
    public void testLoadMap() throws Exception{
        la.loadMap(c.toCharMatrix());
        assertNotNull(la.getVertexMatrix()[0][0]);
        assertEquals(2, la.getVertexMatrix().length);
        assertEquals(1, la.getVertexMatrix()[0][1].getX());
        assertEquals(0, la.getVertexMatrix()[0][1].getY());
    }
    
    /**
     * Tests that start coordinates are set correctly and that the right vertex is marked correctly.
     * @throws Exception 
     */
    @Test
    public void testLoadStart() throws Exception{
        int[] t1 = new int[] {1,1};
        la.loadMap(c.toCharMatrix());
        la.loadStart(1, 1);
        assertEquals(t1[0], la.getStart()[0]);
        assertEquals(t1[1], la.getStart()[1]); 
        assertTrue(la.getVertexMatrix()[1][1].isOnPath());
    }
    
    /**
     * Tests that goal coordinates are set correctly and that the right vertex is marked correctly.
     * @throws Exception 
     */
    @Test
    public void testLoadGoal() throws Exception{
        int[] t1 = new int[] {1,1};
        la.loadMap(c.toCharMatrix());
        la.loadGoal(1, 1);
        assertEquals(t1[0], la.getGoal()[0]);
        assertEquals(t1[1], la.getGoal()[1]); 
        assertTrue(la.getVertexMatrix()[1][1].isOnPath());
    }
    
    
}