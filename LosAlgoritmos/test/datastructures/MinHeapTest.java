/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EliAir
 */
public class MinHeapTest {
    MinHeap heap;
    Random r;
    
    public MinHeapTest() {
    }
    
    @Before
    public void setUp() {
        heap = new MinHeap(Integer.class, 10);         
        r = new Random();
        for (int i = 0; i < 100; i++) {
            heap.add(r.nextInt());
        }
        heap.add(Integer.MIN_VALUE);
        assertEquals(101, heap.size());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void valuesInCorrectOrder() {        
        Comparable[] arr = heap.getHeap();
        
        for (int i = 1; i < 100; i++) {
            if(2*i>100) break;
            assertTrue(arr[i].compareTo(arr[2*i])<0);
            assertTrue(arr[i].compareTo(arr[2*i+1])<0);
        }
        
    }
    
    @Test
    public void correctMinAndHeapSizeDecreases(){
        assertEquals(Integer.MIN_VALUE, heap.poll());
        assertEquals(100, heap.size());
    }
    
    @Test
    public void update(){
        heap = new MinHeap(Vertex.class, 10);
        Vertex a = new Vertex(0,0,'.');
        Vertex b = new Vertex(0,0,'.');
        Vertex c = new Vertex(0,0,'.');
        Vertex d = new Vertex(0,0,'.');
        a.setDistance(1);
        b.setDistance(3);
        c.setDistance(5);
        d.setDistance(7);
        heap.add(a);
        heap.add(b);
        heap.add(c);
        heap.add(d);
        
        assertEquals(a, heap.poll());
        a.setDistance(2);
        heap.add(a);
        d.setDistance(1);
        heap.update(d);
        assertEquals(d, heap.poll());
        
        
        
    }

}