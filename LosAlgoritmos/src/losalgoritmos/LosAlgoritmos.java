/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

/**
 * Functionality for managing the different routing algorithms.
 * @author EliAir
 */
public class LosAlgoritmos {
//    private char[][] charM;
    private Vertex[][] vertexM;
    private int[] start;
    private int[] goal;
    
    
    /**
     * Loads the desired map to be used by the routing algorithms.
     * @param charM 
     */

    public void loadMap(char[][] charM){
        vertexM = new Vertex[charM.length][charM[0].length];
        for (int i = 0; i < vertexM.length; i++) {
            for (int j = 0; j < vertexM[0].length; j++) {
                vertexM[i][j]=new Vertex(j, i, charM[i][j]);
            }
        }
    }
    
    /**
     * Run A* with the default settings. For debugging.
     */
    public void astarDefault(){
//        Astar A = new Astar(vertexM, new int[] {1,1}, new int[] {vertexM.length-3,vertexM[0].length-3});
        Astar A = new Astar(vertexM, new int[] {1,37}, new int[] {33,33});
        A.run();
    }
    
    /**
     * Run A* with the specified settings.
     * @param start Starting point coordinates, {y, x} array
     * @param goal Ending point coordinates, {y, x} array
     */
    public void astar(){
        Astar A = new Astar(vertexM, start, goal);
        A.run();
    }
    
    /**
     * Prints the map and the found route (all vertices with .isOnPath == true).
     */
    
    public void printMapWithRoute(){      
        System.out.println("");
        for (int i = 0; i < vertexM.length; i++) {
            for (int j = 0; j < vertexM[0].length; j++) {
                Vertex v = vertexM[i][j];
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
    
    /**
     * Prints the map as chars and the found route (all vertices with .isOnPath == true) as distance values.
     */
    public void printRouteDistances(){     
        System.out.println("");
        for (int i = 0; i < vertexM.length; i++) {
            for (int j = 0; j < vertexM[0].length; j++) {
                Vertex v = vertexM[i][j];
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
     * Prints the map as the distance values of the vertices.
     */
    
    public void printAllDistances(){        
        System.out.println("");
        for (int i = 0; i < vertexM.length; i++) {
            for (int j = 0; j < vertexM[0].length; j++) {
                Vertex v = vertexM[i][j];
                if(v.getDistance()>10000){
                    System.out.print("X  ");
                } else if(v.getDistance()<10){
                    System.out.print(v.getDistance() + "  ");
                } else if(v.getDistance()<100){
                    System.out.print(v.getDistance() + " ");
                } else {
                    System.out.print(v.getDistance()-100 + " ");
                }  
            }
            System.out.println("");
        }
    }

    /**
     * Loads the starting point of the route int[] {y, x}.
     * @param y y coordinate
     * @param x x coordinate
     */
    
    public void loadStart(int y, int x) {
        this.start = new int[] {y, x};
        vertexM[y][x].setOnPath(true);
    }
    
    /**
     * Loads the ending point of the route int[] {y, x}.
     * @param y y coordinate
     * @param x x coordinate
     */

    public void loadGoal(int y, int x) {
        this.goal = new int[] {y, x};
        vertexM[y][x].setOnPath(true);
    }

    public Vertex[][] getVertexM() {
        return vertexM;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getGoal() {
        return goal;
    }
    
    
    
    

    
}
