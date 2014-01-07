/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import datastructures.Vertex;

/**
 *
 * @author EliAir
 */
public class Tools {
    public final static int NO_HEURISTIC = 0;
    public final static int MANHATTAN = 1;
    public final static int DIAGONAL_EQUAL_COST = 2;
    public final static int DIAGONAL = 3;
    public final static int EUCLIDEAN = 4;
    
    
    
     /**
     * Implementations of A* heuristics.
     * When Dijkstra (no heuristics) selected, returns -1
     * 
     * @param y y coordinate of current vertex
     * @param x x coordinate of current vertex
     * @param heuristic selected heuristic
     * @return estimate distance to goal
     */
    public static double heuristics(int y, int x, int heuristic, Vertex whereTo){
        int dx = Math.abs(y - whereTo.getY());
        int dy = Math.abs(x - whereTo.getX());
        if(heuristic==MANHATTAN) return dy + dx;        
        if(heuristic==DIAGONAL_EQUAL_COST) return Math.max(dx, dy);
        if(heuristic==DIAGONAL) return (dx + dy) + (Math.sqrt(2) - 2) * Math.min(dx, dy);
        if(heuristic==EUCLIDEAN) return Math.sqrt(dx * dx + dy * dy);
        return -1;
    }
    
    
    /**
     * Finds the shortest path from Vertex[][] path.
     * Used by the run() method after finding the goal.
     * @param goal The vertex at the goal (end point).
     * @param start The vertex at the start.
     * @return the best route as an ArrayList of vertices.
     */
    public static ArrayList<Vertex> shortestPath(Vertex[][] path, Vertex goal,  Vertex start){
        ArrayDeque<Vertex> pino = new ArrayDeque<>();
        ArrayList<Vertex> list = new ArrayList<>();
        pino.push(goal);
        Vertex u = path[goal.getY()][goal.getX()];
        while(!u.equals(start)){                
            pino.push(u);
            u = path[u.getY()][u.getX()];
        }         
        pino.push(u);
        start.setOnPath(true);
        while(!pino.isEmpty()){                
            u=pino.pop();             
            u.setOnPath(true);
            list.add(u);
        }
        return list;
    }
    
    
    /**
     * Gets the neighbors of a given vertex on the map.
     * @param u the vertex whose neighbors are desired.
     * @return a list of the neighbors
     */
    public static ArrayList<Vertex> getNeighbors(Vertex[][] map, Vertex u, String directions){
        ArrayList<Vertex> ngbrs = new ArrayList<Vertex>();
        for(char c : directions.toCharArray()){
            Vertex v = getNeighbor(map, u, c);    
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
    private static Vertex getNeighbor(Vertex[][] map, Vertex u, char c){
        int i = 0; int j=0;        
        if(c=='1'){ --j;} //left
        else if(c=='2'){ --j; --i;} 
        else if(c=='3'){ --i;} //up
        else if(c=='4'){ --i; ++j;} 
        else if(c=='5'){ ++j; } //right
        else if(c=='6'){ ++i; ++j;} 
        else if(c=='7'){ ++i; } //down
        else if(c=='8'){ ++i; --j;} 

        //easy, lazy way to check if within bounds of the matrix
        try{ 
            return map[u.getY()+i][u.getX()+j];
        } catch (ArrayIndexOutOfBoundsException e){ 
            return null;
        }        
    }
}
