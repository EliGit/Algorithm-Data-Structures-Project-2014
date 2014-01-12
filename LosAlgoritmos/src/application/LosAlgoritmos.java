/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import algorithms.Astar;
import algorithms.JPS;
import algorithms.Tools;
import datastructures.Stack;
import datastructures.Vertex;

/**
 * Functionality for managing the different routing algorithms.
 * The routing algorithms save all necessary information to the VertexMatrix,
 * each vertex knows if it is on the shortest path and what was the distance to it.
 * @author Elias Nygren
 */
public class LosAlgoritmos {
    public final static int NO_HEURISTIC = 0;
    public final static int MANHATTAN = 1;
    public final static int DIAGONAL_EQUAL_COST = 2;
    public final static int DIAGONAL = 3;
    public final static int EUCLIDEAN = 4;
    
    public final static int DIJKSTRA = 10;
    public final static int ASTAR = 11;
    public final static int JPS = 12;
    
    
    private Vertex[][] vertexMatrix;
    private boolean clearmap;
    private int[] start;
    private int[] goal;
    private Stack<Vertex> bestroute;
    private Tools Tools;

    public LosAlgoritmos() {
        Tools = new Tools();
    }
    
    
    
    /**
     * Routing with specified settings, returns the shortest route.
     * 
     * @param start start coordinates y,x of the route.
     * @param goal end coordinates y,x of the route.
     * @param algo chosen algorithm, algorithms provided in LosAlgoritmos as final static integers.
     * @param heuristic chosen heuristic, heuristics provided in LosAlgoritmos as final static integers.
     * @param diagonalMovement true -> diagonal movement allowed. Always allowed for JPS.
     * @throws Exception if any input is invalid.
     */
   
    public Stack<Vertex> route(int[] start, int[] goal, int algo, int heuristic, boolean diagonalMovement) throws Exception{
        if(vertexMatrix==null) throw new Exception("Invalid map!");
        if(!Tools.valid(start[0], start[1], vertexMatrix) || !Tools.valid(goal[0], goal[1], vertexMatrix)) throw new Exception("Invalid coordinate!");
        if(algo<10 || algo > 12) throw new Exception("Invalid algorithm!");
        if(heuristic<0 || heuristic>4) throw new Exception("Invalid heuristic!");
        if(algo!=DIJKSTRA && heuristic==NO_HEURISTIC) heuristic = MANHATTAN;
        
        this.start=start;
        this.goal=goal;
        
        //clear map only if it has been used in routing.
        if(clearmap) clearMap();
        clearmap=true;
        
        if(algo==DIJKSTRA){
            Astar A = new Astar(vertexMatrix, start, goal, NO_HEURISTIC, diagonalMovement);
            return bestroute=A.run();
        } 
        else if(algo==ASTAR){
            Astar A = new Astar(vertexMatrix, start, goal, heuristic, diagonalMovement);
            return bestroute=A.run();
        } 
        else if(algo==JPS){
            JPS J = new JPS(vertexMatrix, start, goal, heuristic, true);
            return bestroute=J.run();
        }
        return bestroute=null;
    }
    
    /**
     * Return the distance of the shortest route found by the last run algorithm.
     * @return distance from start to goal.
     */
    public double getDistance(){
        return this.vertexMatrix[goal[0]][goal[1]].getDistance();
    }
    
    /**
     * Loads the desired map to be used by the routing algorithms.
     * @param charM 
     */

    public void loadCharMatrix(char[][] charM){
        vertexMatrix = new Vertex[charM.length][charM[0].length];
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                vertexMatrix[i][j]=new Vertex(j, i, charM[i][j]);
            }
        }
        clearmap=false;
    }
    
    private void clearMap(){
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                vertexMatrix[i][j]=new Vertex(j, i, vertexMatrix[i][j].getKey());
            }
        }
    }
    
    /**
     * Return the number of comparisons done by the routing algorithm that was last run.
     * @return comparisons.
     */
    public int comparisons(){
        int comps=0;
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                if(vertexMatrix[i][j].getDistance() != -1) comps++;
            }
        }
        return comps;
    }
    
 
    


    public Vertex[][] getVertexMatrix() {
        return vertexMatrix;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getGoal() {
        return goal;
    }
    
    public Stack<Vertex> getBestroute() {
        return bestroute;
    }    
    

    
    
    
    /**
     * Prints the map as chars and the found route (all vertices with .isOnPath == true) as distance values to the console.
     * For debugging.
     */
    public void printRouteDistances(){     
        System.out.println("");
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                Vertex v = vertexMatrix[i][j];
                if(v.isOnPath()){
                    System.out.print(v.getDistance() + "");
                } else {
                    System.out.print(v.getKey());
                }
            }
            System.out.println("");
        }
    }
    
    /**
     * Prints the map as the distance values of the vertices to the console.
     * For debugging.
     */
    
    public void printAllDistances(){        
        System.out.println("");
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                Vertex v = vertexMatrix[i][j];
                int a = (int) Math.round(v.getDistance());
                if(a==-1){
                    System.out.print("X  ");
                } else if(a<10){
                    System.out.print(a + "  ");
                } else if(a<100){
                    System.out.print(a + " ");
                } else {
                    System.out.print(a + " ");
                }  
            }
            System.out.println("");
        }
    }
    
    
    public void printAllToGoals(){        
        System.out.println("");
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                Vertex v = vertexMatrix[i][j];
                int a = (int) Math.round(v.getToGoal());
                if(a==-1){
                    System.out.print("X  ");
                } else if(a<10){
                    System.out.print(a + "  ");
                } else if(a<100){
                    System.out.print(a + " ");
                } else {
                    System.out.print(a + " ");
                }  
            }
            System.out.println("");
        }
    }
    
     /**
     * Prints the map and the found route (all vertices with .isOnPath == true) to the console.
     * For debugging.
     */
    
    public void printMapWithRoute(){      
        System.out.println("");
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                Vertex v = vertexMatrix[i][j];
                if(v.isOnPath()){
                    System.out.print('*');
                } else {
                    System.out.print(v.getKey());
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    
}
