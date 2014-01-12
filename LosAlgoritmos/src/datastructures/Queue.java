/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 * Lightweight standard queue implementation.
 * Fixed size: Queue fits only size-1 items.
 * @author Elias Nygren
 */
public class Queue<E> {    
    private final static int DEFAULT_SIZE = 9;
    private Object[] data;
    private int head;
    private int tail;
    
    public Queue(){
        data = new Object[DEFAULT_SIZE];      
        head=0;
        tail=0;
    }
    
    public Queue(int size){
        data = new Object[size];
        head=0;
        tail=0;
    }
    
    /**
     * Enqueue element.
     * @param e element.
     */
    public boolean enQ(E e){        
        if(full()) return false;
        data[tail++]=e;
        if(tail==data.length) tail = 0;
        return true;
    }
    
    /**
     * Dequeue.
     */
    public E deQ(){
        E e = (E) data[head];
        data[head++]=null;
        if(head==data.length) head = 0;
        return e;        
    }
    
    /**
     * Checks whether queue is empty.
     * @return true if empty.
     */
    public boolean isEmpty(){
        return head==tail;
    }
    
    /**
     * Checks whether queue is full.
     * @return true if full.
     */
    private boolean full(){
        int tailnext = tail+1;
        if(tailnext==data.length) tailnext = 0;
        return tailnext==head;
    }
    
    /**
     * Naive search of the queue for jUnit tests.
     * @return true if queue contains item.
     */
    public boolean contains(E e){
        for (int i = 0; i < data.length; i++) {
            if(data[i]==null) continue;
            if(data[i].equals(e)) return true;            
        }
        return false;
    }
    
    /**
     * Naive count for jUnit tests.
     * @return number of items in queue.
     */
    public int getNumberOfElements(){
        int count=0;
        for (int i = 0; i < data.length; i++) {
            if(data[i]!=null) count++;
        }
        return count;
    }
    
    
    
}
