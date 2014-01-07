/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

import datastructures.Vertex;
import java.util.ArrayList;

/**
 * Functionality for managing the different routing algorithms.
 * !!! THIS CLASS COULD BE REMOVED WITH SOME REFACTORING, PROBABLY WILL BE AT SOME POINT !!!
 * The routing algorithms save all necessary information to the VertexMatrix,
 * each vertex knows if it is on the shortest path and what was the distance to it.
 * @author EliAir
 */
public class LosAlgoritmos {
    private Vertex[][] vertexMatrix;
    private int[] start;
    private int[] goal;
    private ArrayList<Vertex> bestroute;
    
    
    /**
     * Loads the desired map to be used by the routing algorithms.
     * @param charM 
     */

    public void loadMap(char[][] charM){
        vertexMatrix = new Vertex[charM.length][charM[0].length];
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                vertexMatrix[i][j]=new Vertex(j, i, charM[i][j]);
            }
        }
    }
    
    /**
     * Loads the starting point of the route int[] {y, x}.
     * @param y y coordinate
     * @param x x coordinate
     */
    
    public void loadStart(int y, int x) {
        this.start = new int[] {y, x};
        vertexMatrix[y][x].setOnPath(true);
    }
    
    /**
     * Loads the ending point of the route int[] {y, x}.
     * @param y y coordinate
     * @param x x coordinate
     */

    public void loadGoal(int y, int x) {
        this.goal = new int[] {y, x};
        vertexMatrix[y][x].setOnPath(true);
    }
    
    /**
     * Run A* with the specified settings.
     *
     */
    public void astar(int heuristics, boolean diagonalMovement){        
        Astar A; 
        if(heuristics==Tools.MANHATTAN) A = new Astar(vertexMatrix, start, goal, Tools.MANHATTAN, diagonalMovement);
        else if(heuristics==Tools.DIAGONAL_EQUAL_COST) A = new Astar(vertexMatrix, start, goal, Tools.DIAGONAL_EQUAL_COST, diagonalMovement);
        else if(heuristics==Tools.DIAGONAL) A = new Astar(vertexMatrix, start, goal, Tools.DIAGONAL, diagonalMovement);
        else if(heuristics==Tools.EUCLIDEAN) A = new Astar(vertexMatrix, start, goal, Tools.EUCLIDEAN, diagonalMovement);
        else A = new Astar(vertexMatrix, start, goal, Tools.NO_HEURISTIC, diagonalMovement);        
        bestroute = A.run();
    }
    
    public void JPS(){
        JPS jps = new JPS(vertexMatrix, start, goal, Tools.MANHATTAN, true);
        jps.run();
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
    
    public ArrayList<Vertex> getBestroute() {
        return bestroute;
    }    
    
    public int comparisons(){
        int comps=0;
        for (int i = 0; i < vertexMatrix.length; i++) {
            for (int j = 0; j < vertexMatrix[0].length; j++) {
                if(vertexMatrix[i][j].getDistance() != -1) comps++;
            }
        }
        return comps;
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
