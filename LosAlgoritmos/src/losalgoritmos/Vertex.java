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
    private double toGoal;
    private double distance;
    private char key;
    private boolean onPath;
    private boolean closed;
    private boolean opened;
    
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
        this.closed = false;
        this.opened = false;
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

    public double getToGoal() {
        return toGoal;
    }

    public void setToGoal(double toGoal) {
        this.toGoal = toGoal;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
    
    

    /**
     * Natural order based on the distance values.
     * Formula: distance + toGoal. Default for toGoal is -1 
     * (not changing this means that the A* is actually Dijkstra)
     * @param o to what this object is compared to.
     * @return  -1, 0 or 1.
     */
    @Override
    public int compareTo(Vertex that) {
        double thisdist;
        double thatdist;
        
        
        thisdist = this.toGoal==-1 ? this.distance : this.distance + this.toGoal;
        thatdist = that.toGoal==-1 ? that.distance : that.distance + that.toGoal;
        
//        thisdist = this.distance + this.toGoal;
//        thatdist = that.distance + that.toGoal;
        
//        if(this.distance==that.distance){
//            thisdist=this.toGoal;
//            thatdist=that.toGoal;
//        } else {
//            if(this.distance==Integer.MAX_VALUE) thisdist = Integer.MAX_VALUE;
//            else thisdist = this.distance + this.toGoal;
//                
//            if(that.distance==Integer.MAX_VALUE) thatdist = Integer.MAX_VALUE;
//            else thatdist = that.distance + that.toGoal;
//        }

        if(thisdist < thatdist){
            return -1;
        } 
        if(thisdist == thatdist){
            return 0;            
        }
        return 1;
        
    }
    
    
}
