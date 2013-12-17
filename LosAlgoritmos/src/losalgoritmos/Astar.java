/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

import java.util.PriorityQueue;

/**
 * Implementation of the A* algorithm.
 * @author EliAir
 */
public class Astar {
    private int[][] distance;
    private int[][] path;
    public static PriorityQueue<int[]> heap;
    private char[][] map;
    
    public Astar(char[][] map){
        this.map = map;
        this.distance = new int[map.length][map[0].length];
        this.path = new int[map.length][map[0].length];
        heap = new PriorityQueue<int[]>();
    }
     
 
    public void AstarAlgo (char[][] map) {                            
        //Init
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                this.distance[i][j]=Integer.MAX_VALUE;
                this.path[i][j]=-1;
            }
        }
                                   
         
        //for all to heap
        
        
        //A*
        //while heap not empty
        while(!heap.isEmpty()){
            //u = Heap-del-min 
            int[] u = heap.poll();
            //for all naapurit v
//            for () {
//                if(valid(v[0], v[1])){
//                    relax(u,v);                     
//                }
//            }            
        }
    }
}
