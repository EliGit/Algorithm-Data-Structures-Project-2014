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
    public final static int NO_HEURISTIC = 0;
    public final static int MANHATTAN = 1;
    public final static int DIAGONAL_EQUAL_COST = 2;
    public final static int DIAGONAL = 3;
    public final static int EUCLIDEAN = 4;
    

    private Vertex[][] path;
    private PriorityQueue<Vertex> heap;
    private Vertex[][] map;
    private Vertex s;
    private Vertex t;
    private String directions;
    private int heuristics;
    
    /**
     * Initializes the Astar so it is ready to run.
     * @param map The charmatrix representation of the map used by this routing algorithm
     * @param start The starting point of the route, supplied in {y, x} format coordinate array
     * @param goal The end point of the route, supplied in {y, x} format coordinate array
     * @param heuristics desired heuristic
     * @param diagonalMovement true -> diagonalMovement is allowed, false -> denied
     */
    
    public Astar(Vertex[][] map, int[] start, int[] goal, int heuristics, boolean diagonalMovement){
        this.map = map;
        this.path = new Vertex[map.length][map[0].length];
        this.heap = new PriorityQueue<>(map.length*map[0].length);
        this.s = map[start[0]][start[1]];
        this.t = map[goal[0]][goal[1]];
        s.setOnPath(true);
        t.setOnPath(true);
        if(diagonalMovement) directions = "12345678";
        else directions = "1357";
        this.heuristics=heuristics;
    }
    
    /**
     * Runs the Astar algorithm with the provided map, starting point and the goal.
     * Saves all information to the Vertex objects on the map.
     * @return the best route as an ArrayList of vertices.
     */
    
    public ArrayList<Vertex> run() {
        s.setDistance(0);
        
        heap.add(s);

        Vertex vertex;
        ArrayList<Vertex> ngbrs;
        
        while(!heap.isEmpty()){
            vertex = heap.poll();
            vertex.setClosed(true);
//            System.out.println("vertex y: " + vertex.getY() + " x: " + vertex.getX()+ " d: " + vertex.getDistance() + " toGoal: " + vertex.getToGoal() + " f: " + (vertex.getDistance()+vertex.getToGoal()) );
            
            //if v == target, stop algo, find the route
            if(vertex.equals(t)) return shortestPath(t=vertex);
            
            ngbrs = getNeighbors(vertex);
            for(Vertex ngbr : ngbrs){                                   
                if(ngbr.isClosed()) continue;
                
                
                double distance = vertex.getDistance() + ((ngbr.getX() - vertex.getX() == 0 || ngbr.getY() - vertex.getY() == 0) ? 1 : Math.sqrt(2));                
                //relax
                if(!ngbr.isOpened() || ngbr.getDistance()>distance){
                    ngbr.setDistance(distance);
                    if(ngbr.getToGoal() == -1) ngbr.setToGoal(heuristics(ngbr.getY(), ngbr.getX(), this.heuristics));
                    
                    path[ngbr.getY()][ngbr.getX()]=vertex;
                    
                    if(!ngbr.isOpened()){
                        heap.add(ngbr);
                        ngbr.setOpened(true);
                    } else {
                        boolean wasremoved = heap.remove(ngbr);
                        if(wasremoved) heap.add(ngbr);
                    }                    
                }                                
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
        ArrayDeque<Vertex> pino = new ArrayDeque<>();
        ArrayList<Vertex> list = new ArrayList<>();
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
//            System.out.println("x: " + u.getX() + " y: " + u.getY());
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
        for(char c : directions.toCharArray()){
            Vertex v = getNeighbor(u, c);    
            //if v valid (within the map)
            if(v!=null){
                if(v.getKey()=='.') ngbrs.add(v);
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
     * Implementations of A* heuristics.
     * When Dijkstra (no heuristics) selected, returns -1
     * 
     * @param i y coordinate of current vertex
     * @param j x coordinate of current vertex
     * @param heuristic selected heuristic
     * @return estimate distance to goal
     */
    private double heuristics(int i, int j, int heuristic){
        int dx = Math.abs(i - t.getY());
        int dy = Math.abs(j - t.getY());
        if(heuristic==MANHATTAN) return dy + dx;        
        if(heuristic==DIAGONAL_EQUAL_COST) return Math.max(dx, dy);
        if(heuristic==DIAGONAL) return (dx + dy) + (Math.sqrt(2) - 2) * Math.min(dx, dy);
        if(heuristic==EUCLIDEAN) return Math.sqrt(dx * dx + dy * dy);
        return -1;
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

    
