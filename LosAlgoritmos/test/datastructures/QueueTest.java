/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EliAir
 */
public class QueueTest {
    private Queue<Vertex> Q;
    private Vertex a;
    private Vertex b;
    private Vertex c;
    private Vertex d;
    
    public QueueTest() {
    }
    
    @Before
    public void setUp() {
        Q = new Queue();
        a=new Vertex(0,0, 'a');
        b=new Vertex(0,0, 'b');
        c=new Vertex(0,0, 'c');
        d=new Vertex(0,0, 'd');    
    }
    
    /**
     * Checks that empty queue is created.
     */
    @Test
    public void constructorTest() {
        assertTrue(Q.isEmpty());
    }
    

    
    /**
     * Queue not empty after enQ.
     */
    @Test
    public void enQTest(){
        assertTrue(Q.isEmpty());
        Q.enQ(a);
        assertFalse(Q.isEmpty());
    }
    
    /**
     * Queue empty when last item popped.
     */
    @Test
    public void deQTest(){        
        Q.enQ(a);
        assertFalse(Q.isEmpty());
        Q.deQ();
        assertTrue(Q.isEmpty());
    }
    
    /**
     * Queue returns values in correct order.
     */
    @Test
    public void correctOrderTest(){
        Q.enQ(a);
        Q.enQ(b);
        Q.enQ(c);
        Q.enQ(d);
        assertEquals('a', Q.deQ().getKey());
        assertEquals('b', Q.deQ().getKey());
        assertEquals('c', Q.deQ().getKey());
        assertEquals('d', Q.deQ().getKey());
    }
    
    /**
     * Queue works with mixed order of enQs and deQs.
     */
    @Test
    public void correctMixedOrderTest(){
        Q.enQ(a);
        Q.enQ(b);
        Q.enQ(c);
        assertEquals('a', Q.deQ().getKey());
        Q.enQ(a);
        assertEquals('b', Q.deQ().getKey());
        Q.deQ();
        Q.deQ();
        assertTrue(Q.isEmpty());
        Q.enQ(a);
        Q.enQ(b);
        Q.deQ();
        Q.enQ(c);
        Q.deQ();
        assertEquals('c', Q.deQ().getKey());
        
    }
    
    /**
     * enQ returns false when full (8 items in queue).
     */
    
    @Test
    public void queueFullTest(){
        assertTrue(Q.isEmpty());
        Q.enQ(a);
        Q.enQ(b);
        Q.enQ(c);
        Q.enQ(d);
        
        Q.enQ(a);
        Q.enQ(b);
        Q.enQ(c);
        Q.enQ(d);
        
        assertFalse(Q.enQ(a));
        assertFalse(Q.enQ(a));
        assertFalse(Q.enQ(a));
        Q.deQ();
        assertTrue(Q.enQ(a));
        assertFalse(Q.enQ(a));
    }
    
    /**
     * Contains should find values in the queue.
     * Also: should not find values dequeued from the queue.
     */
    @Test
    public void containsTest(){
        Q.enQ(a);
        Q.enQ(b);
        Q.enQ(c);
        Q.enQ(d);
        assertTrue(Q.contains(a));
        assertTrue(Q.contains(b));
        assertTrue(Q.contains(c));
        assertTrue(Q.contains(d));
        Q.deQ();
        assertFalse(Q.contains(a));
    }
    
    /**
     * Should return the correct number of elements in queue.
     */
    @Test
    public void getNumberOfElements(){
        Q.enQ(a);
        Q.enQ(b);
        Q.enQ(c);
        Q.enQ(d);
        assertEquals(4, Q.getNumberOfElements());
        Q.deQ();
        Q.deQ();
        assertEquals(2, Q.getNumberOfElements());
    }

}