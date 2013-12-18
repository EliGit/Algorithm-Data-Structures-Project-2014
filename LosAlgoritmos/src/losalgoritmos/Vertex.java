/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

/**
 *
 * @author EliAir
 */
public class Vertex implements Comparable<Vertex>{
    private int x;
    private int y;
    private int toGoal;
    private int distance;
    private char key;
    private boolean onPath;

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
