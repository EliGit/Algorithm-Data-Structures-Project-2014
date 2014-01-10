
package datastructures;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Binary MinHeap implementation.
 * Uses an array as the underlying data structure.
 * @author EliAir
 */
public class MinHeap<T extends Comparable> {

    private T[] heap;
    private int length;
    private int heapSize;
    private HashMap<T, Integer> map;
    
    /**
     * Initializes MinHeap with the given initial size.
     * @param size 
     */
    
    public MinHeap(Class<T> c, int size) {
        length = size + 1;
        heapSize = 0;
        heap = (T[]) Array.newInstance(c, length);           
        map = new HashMap<T, Integer>();
        
    }

    /**
     * Insert value i to the heap.
     * @param v value to be added.
     */
    public void add(T v) {
        heapSize++;
        if (length-1 < heapSize) {
            resizeHeap();
        }
        heap[heapSize] = v;
        map.put(v, heapSize);
                
        int index = heapSize;

        while (hasParent(index) && heap[index].compareTo(heap[parent(index)]) == -1) {
            swap(index, parent(index));
            index = parent(index);
        }   
    }

    /**
     * Remove the min value of the heap.
     * @return min value.
     */
    public T poll(){
        T r = heap[1];
        map.remove(r);
        heap[1] = heap[heapSize--];
        heapify(1);
        return r;
    }
    
    /**
     * Size of the heap.
     * @return heap size.
     */
    
    public int size(){
        return heapSize;
    }
    
    /**
     * True if heap is empty.
     * @return heapSize==0.
     */
    public boolean isEmpty(){
        return heapSize == 0;
    }
    
    /**
     * Update the position of the given value in the heap.
     * @param v the value whose position is to be updated.
     */
    
    public void update(T v){                
        if(!map.containsKey(v)){
            
            add(v);
        }
        int index = map.get(v);

        while (hasParent(index) && heap[index].compareTo(heap[parent(index)]) == -1) {
            swap(index, parent(index));
            index = parent(index);
        }   
    }
    
    
    private void swap(int a, int b){
        T tmp = heap[a];
        heap[a] = heap[b];        
        heap[b] = tmp;        
        map.put(heap[a], a);
        map.put(tmp, b);
    }
    
    private void heapify(int i){
        int l = left(i);
        int r = right(i);
        if(r <= heapSize){
            int largest = r;
            if(heap[l].compareTo(heap[r]) == -1) largest = l;
            if(heap[i].compareTo(heap[largest]) == 1){
                swap(i, largest);
                heapify(largest);
            }
        }else if(l == heapSize && heap[i].compareTo(heap[l])==1) swap(i,l);
    }
    

    private void resizeHeap() {
        heap = Arrays.copyOf(heap, heap.length * 2);
        length = heap.length;
    }


    
    private int left(int i) {
        return 2*i;
    }

    private int right(int i) {
        return 2*i + 1;
    }

    private int parent(int i) {
        return i/2;
    }
    
    private boolean hasParent(int i){
        return i>1;
    }

    /**
     * For testing.
     * Returns the underlying array data stucture.
     * @return 
     */
    public T[] getHeap() {
        return heap;
    }
    
    
}
