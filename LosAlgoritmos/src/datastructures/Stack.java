/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Arrays;

/**
 * Lightweight standard stack datastructure implementation with array.
 * Array size is doubled during the push operation if needed.
 * Thus, push is amortized O(1) while worst case is O(n).
 * @author Elias Nygren
 */
public class Stack<E> {
    private int stackSize;
    private final static int DEFAULT_SIZE=150;
    private Object[] data;
    
    
    /**
     * Initialize stack with given size.
     * @param size initial size of the stack.
     */
    public Stack(int size){
        data = new Object[size];
    }
    
    /**
     * Initialize stack with default size.
     */
    public Stack(){
        data = new Object[DEFAULT_SIZE];
    }
    
    /**
     * Push element to stack.
     * @param e element to be pushed.
     */
    public void push(E e){
        if(stackSize==data.length) resize();
        data[stackSize++]=e;
    }
    
    /**
     * Pop element from stack.
     * @return popped element.
     */
    
    public E pop(){
        E e = (E) data[--stackSize];
        data[stackSize]=null;
        return e;
    }
    
    /**
     * Checks if stack is empty.
     * @return true if empty.
     */
    public boolean isEmpty(){
        return stackSize==0;
    }
    
    
    /**
     * Double the stack size.
     */
    private void resize(){
        data = Arrays.copyOf(data, data.length * 2);
    }
    
    /**
    * Naive search of the stack for jUnit tests.
    * @return true if stack contains item.
    */
    public boolean contains(E e){
        for (int i = 0; i < data.length; i++) {
            if(data[i]==null) continue;
            if(data[i].equals(e)) return true;            
        }
        return false;
    }
}
