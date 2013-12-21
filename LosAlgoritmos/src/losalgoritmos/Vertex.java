/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

/**
 * The class for the graph vertices. Provides functionality for storing information related to that specific vertex.
 * @author EliAir
 */
public class Vertex implements Comparable<Vertex>{
    private int x;
    private int y;
    private int toGoal;
    private int distance;
    private char key;
    private boolean onPath;
    
    /**
     * Initializes values.
     * @param x coordinate
     * @param y coordinate
     * @param key type of coordinate 
     */

    public Vertex(int x, int y, char key) {
        this.x = x;
        this.y = y;
        this.key = key;
        this.toGoal = -1;
        this.distance = -1;
        this.onPath = false;
    }

    public boolean isOnPath() {
        return onPath;
    }

    public void setOnPath(boolean onPath) {
        this.onPath = onPath;
    }

    /**
     * Checks if coordinates match.
     * @param v to what this is compared.
     * @return  true if coordinates match, false otherwise.
     */
    public boolean equals(Vertex v){
        if(this.x==v.getX() && this.y==v.getY()){
//            System.out.println("dasd");
            return true;
        } 
        return false;
    }
    
    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public int getToGoal() {
        return toGoal;
    }

    public void setToGoal(int toGoal) {
        this.toGoal = toGoal;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Natural order based on the distance values.
     * @param o to what this object is compared to.
     * @return  -1, 0 or 1.
     */
    @Override
    public int compareTo(Vertex o) {
        if(this.distance<o.distance){
            return -1;
        } 
        if(this.distance == o.distance){
            return 0;            
        }
        return 1;
        
    }
    
    
}
