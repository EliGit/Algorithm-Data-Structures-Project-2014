/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

import application.Cartographer;
import datastructures.Vertex;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EliAir
 */
public class ToolsTest {
    private Vertex v;
    private ArrayList<Vertex> list;
    private LosAlgoritmos la;
    private Cartographer c;
    private Vertex[][] map;
    
    public ToolsTest() {        
    }
    
    @Before
    public void setUp() {
        v = new Vertex(5,5, '.');
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void heuristics(){
        for (int i = 1; i < 5; i++) {
            assertEquals(0, Tools.heuristics(5,5,i, v), 0.002);
        }
        for (int i = 1; i < 5; i++) {
            assertEquals(5, Tools.heuristics(0,5,i, v), 0.002);
        }
        for (int i = 1; i < 5; i++) {
            assertEquals(5, Tools.heuristics(5,0,i, v), 0.002);
        }
        for (int i = 1; i < 5; i++) {
            assertEquals(5, Tools.heuristics(10,5,i, v), 0.002);
        }
        for (int i = 1; i < 5; i++) {
            assertEquals(5, Tools.heuristics(5,10,i, v), 0.002);
        }                
    }
    
    @Test
    public void getNeighbors() throws FileNotFoundException, Exception{
        customSetup();
        v.setX(1); v.setY(1);
        
        list = Tools.getNeighbors(map, v, "12345678");
        
        assertTrue(list.contains(map[0][0]));
        assertTrue(list.contains(map[0][1]));
        assertTrue(list.contains(map[2][1]));
        assertTrue(list.contains(map[2][2]));
        assertFalse(list.contains(map[2][0]));
        assertFalse(list.contains(map[1][0]));
        assertFalse(list.contains(map[0][2]));
        assertFalse(list.contains(map[1][2]));
        
        v.setX(0); v.setY(0);
        list = Tools.getNeighbors(map, v, "12345678");
        assertTrue(list.contains(map[0][1]));        
        assertTrue(list.contains(map[1][1]));
        assertFalse(list.contains(map[1][0]));
        assertEquals(2, list.size());
        
        map[1][0].setKey('.');
        list = Tools.getNeighbors(map, v, "12345678");
        assertTrue(list.contains(map[0][1]));        
        assertTrue(list.contains(map[1][1]));
        assertTrue(list.contains(map[1][0]));
        assertEquals(3, list.size());
    }
    
    @Test
    public void shortestRoute() throws Exception{
        customSetup();
        Vertex[][] path = new Vertex[map.length][map[0].length];
        path[2][2]=map[2][1];
        path[2][1]=map[1][1];
        path[1][1]=map[0][1];
        path[0][1]=map[0][0];
        
        list = Tools.shortestPath(path, map[2][2], map[0][0]);
        
        assertTrue(list.contains(map[2][2]));
        assertTrue(list.contains(map[2][1]));
        assertTrue(list.contains(map[1][1]));
        assertTrue(list.contains(map[0][1]));
        assertTrue(list.contains(map[0][0]));
        
    }
    
    public void customSetup() throws Exception{
        c = new Cartographer(new File("./maps/test4.map"));
        la = new LosAlgoritmos();
        la.loadMap(c.toCharMatrix());
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        map = la.getVertexMatrix();
    }
}