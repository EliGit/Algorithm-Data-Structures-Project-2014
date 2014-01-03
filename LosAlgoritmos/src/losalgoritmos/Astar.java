/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Implementation of the A* algorithm. Currently just Dijkstra.
 * @author EliAir
 */
public class Astar {
    private Vertex[][] path;
    private PriorityQueue<Vertex> heap;
    private Vertex[][] map;
    private Vertex s;
    private Vertex t;
    private static final String suunnat = "12345678"; //Left, Up, Right, Down
    
    /**
     * Initializes the Astar so it is ready to run.
     * @param map The charmatrix representation of the map used by this routing algorithm
     * @param start The starting point of the route, supplied in {y, x} format coordinate array
     * @param goal The end point of the route, supplied in {y, x} format coordinate array
     * @param astar true -> use manhattan heuristic, false -> dijkstra
     */
    
    public Astar(Vertex[][] map, int[] start, int[] goal, boolean astar){
        this.map = map;
        this.path = new Vertex[map.length][map[0].length];
        this.heap = new PriorityQueue<Vertex>(map.length*map[0].length);
        this.s = map[start[0]][start[1]];
        this.t = map[goal[0]][goal[1]];
        init(astar);
        s.setOnPath(true);
        t.setOnPath(true);
    }
    
    /**
     * Runs the Astar algorithm with the provided map, starting point and the goal.
     * Saves all information to the Vertex objects on the map.
     * @return the best route as an ArrayList of vertices.
     */
    
    public ArrayList<Vertex> run() {
        //for all to heap
        for (int i = 0; i < map.length; i++) 
            for (int j = 0; j < map[0].length; j++) 
                heap.add(map[i][j]);

        
        Vertex u;
        ArrayList<Vertex> ngbrs;
        
        int count=0;
        //while heap not empty
        while(!heap.isEmpty()){
            //u = heap-del-min
            u = heap.poll();
            
            count++;
//            System.out.println("count: " + count + " heapsize: " + heap.size() + " count+ heap" + (count+heap.size()));
//            System.out.println("--x: " + u.getX() + " y: " + u.getY() + " dist: " + u.getDistance() + " togoal: " + u.getToGoal());
            
            //for all neighbours v
            ngbrs = getNeighbors(u);
            for(Vertex v : ngbrs){       
                //if v == target, stop algo, find the route
                
                if(!heap.contains(v)) continue;
                
                //relax
                if(v.getDistance()>u.getDistance()){
                    boolean wasremoved = heap.remove(v);                    
                    v.setDistance(u.getDistance() + 1);
                    path[v.getY()][v.getX()]=u;
                    if(wasremoved) heap.add(v);
                }
                if(v.equals(t)) return shortestPath(v);
                
            }
        }
        return null;
    }
    
    /**
     * Finds the shortest path from Vertex[][] path created by running the Astar algorithm.
     * Used by the run() method after finding the goal.
     * @param v The vertex at the goal (end point).
     * @return the best route as an ArrayList of vertices.
     */
    private ArrayList<Vertex> shortestPath(Vertex v){
        ArrayDeque<Vertex> pino = new ArrayDeque<Vertex>();
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        pino.push(v);
        Vertex u = path[v.getY()][v.getX()];
        while(!u.equals(s)){                
            pino.push(u);
            u = path[u.getY()][u.getX()];
        }                      
        s.setOnPath(true);
        while(!pino.isEmpty()){                
            u=pino.pop();             
            u.setOnPath(true);
            list.add(u);
            System.out.println("x: " + u.getX() + " y: " + u.getY());
        }
        return list;
    }
    

    /**
     * Gets the neighbors of a given vertex on the map.
     * @param u the vertex whose neighbors are desired.
     * @return a list of the neighbors
     */
    private ArrayList<Vertex> getNeighbors(Vertex u){
        ArrayList<Vertex> ngbrs = new ArrayList<Vertex>();
        for(char c : suunnat.toCharArray()){
            Vertex v = getNeighbor(u, c);    
            //if v valid (within the map)
            if(v!=null){
                if(v.getKey()=='.'){
                    ngbrs.add(v);
                }                
            }            
        }
        return ngbrs;                
    }
    
    /**
     * Gets the neighbor in the specified direction - left, up, right, down, 
     * @param u the vertex whose neighbor is desired
     * @param c the direction from which the neighbor is desired, format: L/U/R/D
     * @return null if the direction is out of map, otherwise the neighbor vertex.
     */
    private Vertex getNeighbor(Vertex u, char c){
        int i = 0; int j=0;        
        if(c=='1'){ --j;} //left
        else if(c=='2'){ --j; --i;} 
        else if(c=='3'){ --i;} //up
        else if(c=='4'){ --i; ++j;} 
        else if(c=='5'){ ++j; } //right
        else if(c=='6'){ ++i; ++j;} 
        else if(c=='7'){ ++i; } //down
        else if(c=='8'){ ++i; --j;} 

        try{ 
            return map[u.getY()+i][u.getX()+j];
        } catch (ArrayIndexOutOfBoundsException e){ 
            return null;
        }        
    }
    
    /**
     * Helper for the constructor, initializes the vertices on the map and the path matrix.
     */
    private void init(boolean astar){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j].setDistance(Integer.MAX_VALUE);
                if(astar) map[i][j].setToGoal(manhattan(i, j));
                this.path[i][j]=null;
            }
        }
        s.setDistance(0);        
    }
    
    private int manhattan(int i, int j){
        return Math.abs((i - t.getY()) + (j-t.getX()));
    }
    

    public Vertex[][] getMap() {
        return map;
    }

    public Vertex[][] getPath() {
        return path;
    }

    public PriorityQueue<Vertex> getHeap() {
        return heap;
    }

    public Vertex getS() {
        return s;
    }

    public Vertex getT() {
        return t;
    }
    
    
    
}

    
