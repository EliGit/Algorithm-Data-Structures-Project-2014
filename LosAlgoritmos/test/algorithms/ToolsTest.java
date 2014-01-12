/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import application.LosAlgoritmos;
import application.Cartographer;
import datastructures.Queue;
import datastructures.Stack;
import datastructures.Vertex;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tools tests.
 * @author EliAir
 */
public class ToolsTest {
    private Vertex v;
    private Queue<Vertex> list;
    private LosAlgoritmos la;
    private Cartographer c;
    private Vertex[][] map;
    private Tools Tools;
    
    public ToolsTest() {        
    }
    
    @Before
    public void setUp() {
        v = new Vertex(5,5, '.');
        Tools = new Tools();
    }
    

    /**
     * Heuristics return correct values.
     */
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
    
    /**
     * GetNeighbors should return all valid neighbors.
     * @throws FileNotFoundException
     * @throws Exception 
     */
    
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
        assertEquals(2, list.getNumberOfElements());
        
        map[1][0].setKey('.');
        list = Tools.getNeighbors(map, v, "12345678");
        assertTrue(list.contains(map[0][1]));        
        assertTrue(list.contains(map[1][1]));
        assertTrue(list.contains(map[1][0]));
        assertEquals(3, list.getNumberOfElements());
    }
    
    /**
     * getAllNeighbors should return all neighbors that are on the map.
     * @throws Exception 
     */
    @Test
    public void getAllNeighbors() throws Exception{
        customSetup();
        v.setX(1); v.setY(1);
        
        list = Tools.getAllNeighbors(map, v);
        assertTrue(list.contains(map[0][0]));
        assertTrue(list.contains(map[0][1]));
        assertTrue(list.contains(map[2][1]));
        assertTrue(list.contains(map[2][2]));
        assertTrue(list.contains(map[2][0]));
        assertTrue(list.contains(map[1][0]));
        assertTrue(list.contains(map[0][2]));
        assertTrue(list.contains(map[1][2]));
        assertEquals(8, list.getNumberOfElements());
        
        
        
        v.setX(0); v.setY(0);
        list = Tools.getAllNeighbors(map, v);
        assertTrue(list.contains(map[0][1]));        
        assertTrue(list.contains(map[1][1]));
        assertTrue(list.contains(map[1][0]));
        assertEquals(3, list.getNumberOfElements());
        
        
    }
    
    /**
     * shortestRoute should return the shortest route in a stack.
     * @throws Exception 
     */
    @Test
    public void shortestRoute() throws Exception{
        customSetup();
        
        map[2][2].setPath(map[2][1]);
        map[2][1].setPath(map[1][1]);
        map[1][1].setPath(map[0][1]);
        map[0][1].setPath(map[0][0]);
        
        Stack s;
        s = Tools.shortestPath(map[2][2], map[0][0]);
        
        assertTrue(s.contains(map[2][2]));
        assertTrue(s.contains(map[2][1]));
        assertTrue(s.contains(map[1][1]));
        assertTrue(s.contains(map[0][1]));
        assertTrue(s.contains(map[0][0]));
        
    }
    
    /**
     * closestValid should find the closest valid coordinate with Dijkstra.
     * If all is normal, no exceptions should be thrown.
     * @throws Exception 
     */
    @Test
    public void closestValid() throws Exception{
        la = new LosAlgoritmos();
        c = new Cartographer(new File("./maps/combat.map"));
        la.loadCharMatrix(c.toCharMatrix());
        Vertex[][] map = la.getVertexMatrix();
        assertNotNull(map);
        
       
        
        int[] coord = new int[2];
        int[] newcoord = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                coord[0]=i; coord[1]=j;
                newcoord = Tools.closestValidCoordinate(map, coord);
                assertTrue(Tools.valid(newcoord[0], newcoord[1], map));
            }
        }
    }
    
    /**
     * Running closest valid must not change the state of the map.
     * @throws Exception 
     */
    @Test
    public void closestValidUndoesChanges() throws Exception{
        la = new LosAlgoritmos();
        c = new Cartographer(new File("./maps/combat.map"));
        la.loadCharMatrix(c.toCharMatrix());
        Vertex[][] map = la.getVertexMatrix();
        assertNotNull(map);
        
        int[] coord = new int[] {100, 10000};
        Tools.closestValidCoordinate(map, coord);
        
        coord = new int[] {14, 15};
        Tools.closestValidCoordinate(map, coord);
        
        for (int i = 0; i < map.length; i++) 
            for (int j = 0; j < map[0].length; j++) {
                Vertex v = map[i][j];
                assertFalse(v.isClosed());
                assertFalse(v.isOnPath());
                assertFalse(v.isOpened());
                assertEquals(-1, v.getDistance(), 0.002);
                assertEquals(-1, v.getToGoal(), 0.002);
            }
        
    }
    
    private void customSetup() throws Exception{
        c = new Cartographer(new File("./maps/test4.map"));
        la = new LosAlgoritmos();
        la.loadCharMatrix(c.toCharMatrix());
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        map = la.getVertexMatrix();
    }
    
}