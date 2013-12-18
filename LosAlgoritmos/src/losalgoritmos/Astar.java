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
    private Vertex[][] path;
    private PriorityQueue<Vertex> heap;
    private Vertex[][] map;
    private Vertex s;
    private Vertex t;
    private static final String suunnat = "VYOA";
    
    public Astar(Vertex[][] map, int[] start, int[] goal){
        this.map = map;
        this.path = new Vertex[map.length][map[0].length];
        this.heap = new PriorityQueue<Vertex>(map.length*map[0].length);
        this.s = map[start[0]][start[1]];
        this.t = map[goal[0]][goal[1]];
        init();
        s.setOnPath(true);
        t.setOnPath(true);
    }
    
    public void run() {
        print();
        //for all to heap
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                heap.add(map[i][j]);
            }
        }
        
        Vertex u;
        ArrayList<Vertex> ngbrs;
        
        //while heap not empty
        boolean found = false;
        while(!heap.isEmpty()){
            //u = heap-del-min
            u = heap.poll();
            //for all neighbours v
            ngbrs = getNaapurit(u);
            for(Vertex v : ngbrs){                                
                //relax
                if(v.getDistance()>u.getDistance()){
                    heap.remove(v);
                    v.setDistance(u.getDistance() + 1);
                    path[v.getY()][v.getX()]=u;
                    heap.add(v);
                }                
                //if v == target, stop algo, find the route
                if(v.equals(t)){    
                    shortestPath(v);
                    found=true;
                    break;
                }
            }
            if(found){
                break;
            }
            
        }
    }
    
    private void shortestPath(Vertex v){
        ArrayDeque<Vertex> pino = new ArrayDeque<Vertex>();
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
        }
        print();
//        print2();
    }
    

    
    private ArrayList<Vertex> getNaapurit(Vertex u){
        ArrayList<Vertex> ngbrs = new ArrayList<Vertex>();
        for(char c : suunnat.toCharArray()){
            Vertex v = getNaapuri(u, c);    
            //if v valid (within the map)
            if(v!=null){
                if(v.getKey()=='.'){
                    ngbrs.add(v);
                }                
            }            
        }
        return ngbrs;                
    }
    
    private Vertex getNaapuri(Vertex u, char c){
        int i = 0; int j=0;        
        if(c=='V'){ j=-1;} 
        else if(c=='Y'){ i=-1;} 
        else if(c=='O'){ j=1; } 
        else if(c=='A'){ i=1; }        
        try{ //v.getX()>=0 && v.getY()>=0 && v.getX()<map[0].length && v.getY()<map.length            
            return map[u.getY()+i][u.getX()+j];
        } catch (ArrayIndexOutOfBoundsException e){ 
            return null;
        }        
    }
    
    private void init(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j].setDistance(Integer.MAX_VALUE);
                this.path[i][j]=null;
            }
        }
        s.setDistance(0);        
    }
    
    
    private void print(){        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Vertex v = map[i][j];
                if(v.isOnPath()){
                    System.out.print('*');
                } else {
                    System.out.print(v.getKey());
                }
            }
            System.out.println("");
        }
    }
    
    private void print2(){        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Vertex v = map[i][j];
                if(v.isOnPath()){
                    System.out.print(v.getDistance() + "");
                } else {
                    System.out.print(v.getKey());
                }
            }
            System.out.println("");
        }
    }
    
    private void printDist(){        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Vertex v = map[i][j];
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
}

    
