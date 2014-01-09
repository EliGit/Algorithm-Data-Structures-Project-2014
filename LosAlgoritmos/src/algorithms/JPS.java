/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.MinHeap;
import datastructures.Vertex;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author EliAir
 */
public class JPS {
    public final static int NO_HEURISTIC = 0;
    public final static int MANHATTAN = 1;
    public final static int DIAGONAL_EQUAL_COST = 2;
    public final static int DIAGONAL = 3;
    public final static int EUCLIDEAN = 4;
    
    private Vertex[][] path;
//    private PriorityQueue<Vertex> heap;
    private MinHeap<Vertex> heap;
    private Vertex[][] map;
    private Vertex s;
    private Vertex t;
    private String directions;
    private int heuristics;
    private Tools Tools;
    
    
    public JPS(Vertex[][] map, int[] start, int[] goal, int heuristics, boolean diagonalMovement){
        Tools = new Tools();
        this.map = map;
        this.path = new Vertex[map.length][map[0].length];
//        this.heap = new PriorityQueue<>(map.length*map[0].length);
        this.heap = new MinHeap<Vertex>(Vertex.class, map.length*map[0].length);
        this.s = map[start[0]][start[1]];
        this.t = map[goal[0]][goal[1]];
        this.heuristics=heuristics;
        
        s.setOnPath(true);
        t.setOnPath(true);
        
        if(diagonalMovement) directions = "12345678";
        else directions = "1357";
        
    }
    
    public ArrayList<Vertex> run() {
        //init
        s.setDistance(0);        
        heap.add(s);        
        s.setOpened(true);
        
        Vertex vertex;
        ArrayList<Vertex> ngbrs;
        
        while(!heap.isEmpty()){
            vertex = heap.poll();
            //vertex is closed when the algorithm has dealt with it
            vertex.setClosed(true);            
            //if v == target, stop algo, find the route from path matrix
            if(vertex.equals(t)) return Tools.shortestPath(path, t, s);
//            System.out.println(vertex);
            

            //IDENTIFY SUCCESSORS:
            
            //for all neighbours
            ngbrs = getNeighbors(vertex);
            for(Vertex ngbr : ngbrs){                
                //find next jumpPoint
                int[] jumpPoint = jump(ngbr.getX(), ngbr.getY(), vertex.getX(), vertex.getY());
                
                if(jumpPoint!=null){
                    Vertex jumpNode = map[jumpPoint[1]][jumpPoint[0]];
                    
                    //no need to process a vertex that has already been dealt with
                    if(jumpNode.isClosed()) continue;
                                        
                    //distance == distance of parent and from parent to jumpPoint                                        
                    double distance = Tools.heuristics(jumpNode.getY(), jumpNode.getX(), EUCLIDEAN, vertex) + vertex.getDistance();
                    
                    //relax IF vertex is not opened (not placed to heap yet) OR shorter distance to it has been found
                    if(!ngbr.isOpened() || jumpNode.getDistance()>distance){
                        jumpNode.setDistance(distance);
                        //use appropriate heuristic if necessary, -1 is the default value of distance to goal
                        
                        if(jumpNode.getToGoal() == -1) jumpNode.setToGoal(Tools.heuristics(jumpNode.getY(), jumpNode.getX(), this.heuristics, t));                    
                        
                        path[jumpNode.getY()][jumpNode.getX()]=vertex;

                        //if vertex was not yet opened, open it and place to heap. Else update its position in heap.
                        if(!jumpNode.isOpened()){                            
                            heap.add(jumpNode);
                            jumpNode.setOpened(true);
                        } else {
                            heap.update(jumpNode);
//                            boolean wasremoved = heap.remove(jumpNode);
//                            if(wasremoved) heap.add(jumpNode);
                        }                    
                    }
                }                            
            }
        }
        return null;
    }
    
    
    /**
     * Recursively determine next jump point, null if pruned.
     * @param y neighbor y.
     * @param x neighbor x.
     * @param py current y.
     * @param px current x.
     * @return jump point y, x in array
     */
    private int[] jump(int x, int y, int px, int py){

        if (!valid(x, y)) {
            return null;
        }    
        
        Vertex child = map[y][x];
        Vertex parent = map[py][px]; 
        
        if(child.equals(t)) {
            return new int[] {x, y};
        }
        int dx = x - px; int dy = y - py;
        
        // check for forced neighbors
        // along the diagonal
        if (dx != 0 && dy != 0){
            if((valid(x-dx,y+dy) && !valid(x-dx,y))||
               (valid(x+dx,y-dy) && !valid(x,y-dy))){
                return new int[] {x, y};
            }
        } else { // horizontally/vertically
            if( dx != 0 ) { // moving along x
                if((valid(x+dx,y+1) && !valid(x,y+1))||
                   (valid(x+dx,y-1) && !valid(x,y-1))){
                    return new int[] {x, y};
                }
            } else {
                if((valid(x+1,y+dy) && !valid(x+1,y))||
                   (valid(x-1,y+dy) && !valid(x-1,y))){
                    return new int[] {x, y};
             }
            }
        }
            
        // when moving diagonally, must check for vertical/horizontal jump points
        if (dx != 0 && dy != 0) {
            int[] jx = jump(x + dx, y, x, y);
            int[] jy = jump(x, y + dy, x, y);
            if (jx!=null || jy!=null) {
                return new int[] {x, y};
            }
        }

        // moving diagonally, must make sure one of the vertical/horizontal
        // neighbors is open to allow the path
        if(valid(x+dx,y)|| valid(x,y+dy)){
            return jump(x+dx, y+dy, x, y);
        } else {
            return null;
        }
    }
    
    
    
    

    
    /**
    * Find the neighbors for the given node. If the node has a parent,
    * prune the neighbors based on the jump point search algorithm, otherwise
    * return all available neighbors.
    * @return {Array.<[number, number]>} The neighbors found.
    */
    
    public ArrayList<Vertex> getNeighbors(Vertex u){
        ArrayList<Vertex> ngbrs = new ArrayList<Vertex>();
        Vertex parent = path[u.getY()][u.getX()];
        
        if(parent!=null){ //prune
            //directions
            int dy = (u.getY() - parent.getY()) / Math.max(Math.abs(u.getY() - parent.getY()), 1);
            int dx = (u.getX() - parent.getX()) / Math.max(Math.abs(u.getX() - parent.getX()), 1);
            int y = u.getY();
            int x = u.getX();
            
            //diagonally
            if(dx!=0 && dy!=0){                
                if (valid(x,y + dy)) {
                    ngbrs.add(map[y+dy][x]);
                }
                if(valid(x+dx,y)){
                    ngbrs.add(map[y][x+dx]);
                }
                if(valid(x,y+dy) || valid(x+dx,y)){
                    if(valid(x+dx, y+dy)) {
                        ngbrs.add(map[y+dy][x+dx]);
//                        System.out.println("dy: " +dy+ " dx: "+dx+ " y: "+y+ " x: "+x+ " ");
                    }                    
                }
                if(!valid(x-dx,y) && valid(x,y+dy)){
                    ngbrs.add(map[y+dy][x-dx]);
                }
                if(!valid(x,y-dy) && valid(x+dx,y)){
                    ngbrs.add(map[y-dy][x+dx]);
                }
            } else {//horizontally                
                if(dx==0){
                    if (valid(x,y + dy)) {
                        if (valid(x,y + dy)) {
                            ngbrs.add(map[y+dy][x]);
                        }
                        if (!valid(x+1,y)) {
                            ngbrs.add(map[y+dy][x+1]);
                        }
                        if (!valid(x-1,y)) {
                            ngbrs.add(map[y+dy][x-1]);
                        }
                    }
                } else {//vertically
                    if (valid(x + dx,y)) {
                        if (valid(x+dx,y)) {
                            ngbrs.add(map[y][x+dx]);
                        }
                        if (!valid(x,y+1)) {
                            ngbrs.add(map[y+1][x+dx]);
                        }
                        if (!valid(x,y-1)) {
                            ngbrs.add(map[y-1][x+dx]);
                        }
                    }
                }
                
            }
        //no pruning - get all ngbrs normally    
        } else {
            ngbrs = Tools.getNeighbors(map, u, directions);
        }
        return ngbrs;
    }
    
    private boolean valid(int x, int y){
        if(x<0 || y < 0 ||x>=map[0].length || y>=map.length){
            return false;
        }
        if(map[y][x].getKey()=='.') return true;
        return false;
    }
    
    
    //getters for testing:
    public Vertex[][] getMap() {
        return map;
    }

    public Vertex[][] getPath() {
        return path;
    }


    public Vertex getS() {
        return s;
    }

    public Vertex getT() {
        return t;
    }
}
