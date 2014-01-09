/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import application.LosAlgoritmos;
import java.util.ArrayDeque;
import java.util.ArrayList;
import datastructures.Vertex;
import java.util.Random;

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
    public double heuristics(int y, int x, int heuristic, Vertex whereTo){
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
    public ArrayList<Vertex> shortestPath(Vertex[][] path, Vertex goal,  Vertex start){
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
    public ArrayList<Vertex> getNeighbors(Vertex[][] map, Vertex u, String directions){
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
     * Gets the all neighbors of a given vertex on the map.
     * @param u the vertex whose neighbors are desired.
     * @return a list of the neighbors
     */
    
    public ArrayList<Vertex> getAllNeighbors(Vertex[][] map, Vertex u){
        ArrayList<Vertex> ngbrs = new ArrayList<Vertex>();
        for(char c : "12345678".toCharArray()){
            Vertex v = getNeighbor(map, u, c);   
            if(v!=null) ngbrs.add(v);
        }
        return ngbrs; 
    }
    
    /**
     * Gets the neighbor in the specified direction - left, up, right, down, 
     * @param u the vertex whose neighbor is desired
     * @param c the direction from which the neighbor is desired, format: L/U/R/D
     * @return null if the direction is out of map, otherwise the neighbor vertex.
     */
    private Vertex getNeighbor(Vertex[][] map, Vertex u, char c){
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
    
    
    /**
     * True if coordinate is walkable and within the map, false otherwise.
     * @param y y coordinate.
     * @param x x coordinate.
     * @param map Vertex[][] map.
     * @return boolean valid.
     */
    public boolean valid(int y, int x, Vertex[][] map){        
        if(x<0 || y < 0 ||x>=map[0].length || y>=map.length){
            return false;
        }
        if(map[y][x].getKey()=='.') return true;
        return false;
    }
    
    
    /**
     * Returns a random, valid, point on the map.
     * @param map
     * @return 
     */
    public int[] randomPoint(Vertex[][] map){
        Random r = new Random();
        int x = r.nextInt(map[0].length);
        int y = r.nextInt(map.length);
//        LosAlgoritmos la = new LosAlgoritmos();
        return closestValidCoordinate(map, new int[] {y,x});
    }
    
    
    /**
     * Checks that coordinate is valid, if not, returns the closest valid coordinate.
     * Moves coordinate to within the map and uses Dijkstra(Astar, NO_HEURISTIC) with utilitymode.
     * @param coord coordinate to be validated.
     * @return closest valid coordinate.
     */
    public int[] closestValidCoordinate(Vertex[][] vertexMatrix, int[] coord){
        if(vertexMatrix==null) return null;
        if(valid(coord[0], coord[1], vertexMatrix)) return coord;                
        
        int y = coord[0];
        int x = coord[1];
        
        
        if(y<0) y=0;
        if(y>=vertexMatrix.length) y=vertexMatrix.length-1;
        if(x<0) x=0;
        if(x>=vertexMatrix[0].length) x=vertexMatrix[0].length-1;
        int[] newcoord = new int[] {y,x};
//        System.out.println(Arrays.toString(newcoord));
        
//        System.out.print("Invalid coordinate, finding closest valid coordinate: ");
        
        Astar A = new Astar(vertexMatrix, newcoord, newcoord, NO_HEURISTIC, true, true);
        ArrayList<Vertex> list = A.run();
        y = list.get(0).getY();
        x = list.get(0).getX();
        
        //undo any changes
        ArrayList<Vertex> ulist = A.getUtilityList();
        for (Vertex vertex : ulist) {
            vertex.setClosed(false);
            vertex.setOnPath(false);
            vertex.setDistance(-1);
            vertex.setToGoal(-1);
            vertex.setOpened(false);
        }
//        System.out.println(" {y: " + y + " x: " + x+ "}");
        return new int[] {y, x};
        
    }
}
