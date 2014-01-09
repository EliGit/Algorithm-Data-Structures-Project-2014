/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import application.LosAlgoritmos;
import algorithms.JPS;
import datastructures.Vertex;
import application.Cartographer;
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
public class JPSTest {
    private JPS jps;    
    private LosAlgoritmos la;
    private Cartographer c;
    private int[] t1;
    private int[] t2;
    private Vertex s;
    private Vertex g;
    
    public JPSTest() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, Exception {
        c = new Cartographer(new File("./maps/testpruningAC.map"));
        customSetup(new int[] {1,0}, new int[] {1, 1}, new File("./maps/testpruningAC.map"));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPruningA() {
        ArrayList<Vertex> list = new ArrayList<>();
        list = jps.getNeighbors(g);   
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if(i==1 && j==2) assertTrue(list.contains(jps.getMap()[i][j]));
                else assertFalse(list.contains(jps.getMap()[i][j]));       
    }
    
    @Test
    public void testPruningB() throws Exception{
        customSetup(new int[] {1,0}, new int[] {1, 1}, new File("./maps/testpruningB.map"));
        ArrayList<Vertex> list = new ArrayList<>();
        list = jps.getNeighbors(g);    
        for (int i = 0; i < 3; i++) 
           for (int j = 0; j < 3; j++) 
               if(i==1 && j==2) assertTrue(list.contains(jps.getMap()[1][2]));
               else if(i==0 && j==2) assertTrue(list.contains(jps.getMap()[0][2]));
               else assertFalse(list.contains(jps.getMap()[i][j]));
    }
    
    @Test
    public void testPruningC() throws Exception{
        customSetup(new int[] {2,0}, new int[] {1, 1}, new File("./maps/testpruningAC.map"));
        ArrayList<Vertex> list = new ArrayList<>();
        list = jps.getNeighbors(g);
        for (int i = 0; i < 3; i++) 
           for (int j = 0; j < 3; j++) 
               if(i==0 && j==1) assertTrue(list.contains(jps.getMap()[i][j]));
               else if(i==0 && j==2) assertTrue(list.contains(jps.getMap()[i][j]));               
               else if(i==1 && j==2) assertTrue(list.contains(jps.getMap()[i][j]));
               else assertFalse(list.contains(jps.getMap()[i][j]));        
    }
    
    @Test
    public void testPruningD() throws Exception{
        customSetup(new int[] {2,0}, new int[] {1, 1}, new File("./maps/testpruningD.map"));
        ArrayList<Vertex> list = new ArrayList<>();
        list = jps.getNeighbors(g);    
        for (int i = 0; i < 3; i++) 
           for (int j = 0; j < 3; j++) 
               if(i==0 && j==0) assertTrue(list.contains(jps.getMap()[i][j]));
               else if(i==0 && j==1) assertTrue(list.contains(jps.getMap()[i][j]));               
               else if(i==0 && j==2) assertTrue(list.contains(jps.getMap()[i][j]));
               else if(i==1 && j==2) assertTrue(list.contains(jps.getMap()[i][j]));
               else assertFalse(list.contains(jps.getMap()[i][j]));
    }
    
    
    
    public void customSetup(int[] start, int[] goal, File newmap) throws Exception{
        c.loadMap(newmap);
        t1 = start;
        t2 = goal;
        jps = new JPS(createVertexMatrix(), t1, t2, JPS.EUCLIDEAN, true);
        s = jps.getMap()[t1[0]][t1[1]];
        g = jps.getMap()[t2[0]][t2[1]];
        jps.getPath()[t2[0]][t2[1]]=jps.getMap()[t1[0]][t1[1]];
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