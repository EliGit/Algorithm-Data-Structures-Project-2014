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
public class StackTest {
    private Stack<Vertex> s;
    private Vertex a;
    private Vertex b;
    private Vertex c;
    private Vertex d;
    
    public StackTest() {
    }
    
    @Before
    public void setUp() {
        s=new Stack();
        a=new Vertex(0,0, 'a');
        b=new Vertex(0,0, 'b');
        c=new Vertex(0,0, 'c');
        d=new Vertex(0,0, 'd');
    }
 
    /**
     * Checks that empty stack is created.
     */
    @Test
    public void constructorTest() {
        assertTrue(s.isEmpty());        
    }
    
    /**
     * Stack not empty after push.
     */
    @Test
    public void pushTest(){
        assertTrue(s.isEmpty());
        s.push(a);
        assertFalse(s.isEmpty());
    }
    
    /**
     * Stack empty when last item popped.
     */
    @Test
    public void popTest(){        
        s.push(a);
        s.pop();
        assertTrue(s.isEmpty());
    }
    
    /**
     * Pop returns values in correct order.
     */
    @Test
    public void correctOrderTest(){
        s.push(a);
        s.push(b);
        s.push(c);
        s.push(d);
        assertEquals('d', s.pop().getKey());
        assertEquals('c', s.pop().getKey());
        assertEquals('b', s.pop().getKey());
        assertEquals('a', s.pop().getKey());
    }
    
    /**
     * Stack works with mixed order of pushes and pops.
     */
    @Test
    public void correctMixedOrderTest(){
        s.push(a);
        s.push(b);
        s.push(c);
        assertEquals('c', s.pop().getKey());
        s.push(a);
        assertEquals('a', s.pop().getKey());
        s.pop();
        s.pop();
        assertTrue(s.isEmpty());
        s.push(a);
        s.push(b);
        s.pop();
        s.push(c);
        s.pop();
        assertEquals('a', s.pop().getKey());
        
    }
    
    /**
     * Stack works when it has to be resized (doubled).
     */
    
    @Test
    public void resizeTest(){
        s = new Stack(1);
        s.push(a);
        s.push(b);
        s.push(c);
        s.push(d);
        assertEquals('d', s.pop().getKey());
        assertEquals('c', s.pop().getKey());
        assertEquals('b', s.pop().getKey());
        assertEquals('a', s.pop().getKey());
    }
    
    /**
     * Stack works with a large input of pushes and pops.
     */
    @Test
    public void largeAmountOfElementsTest(){
        Stack<Integer> stack = new Stack(1);
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }
        for (int i = 999; i >= 0; i--) {
            assertEquals(0, stack.pop().compareTo(i));
        }
    }
    
    /**
     * Contains should find values in the queue.
     * Also: should not find values dequeued from the queue.
     */
    @Test
    public void containsTest(){
        s.push(a);
        s.push(b);
        s.push(c);
        s.push(d);
        assertTrue(s.contains(a));
        assertTrue(s.contains(b));
        assertTrue(s.contains(c));
        assertTrue(s.contains(d));
        s.pop();
        assertFalse(s.contains(d));
    }
}