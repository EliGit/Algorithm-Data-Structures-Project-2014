/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losalgoritmos;

/**
 *
 * @author EliAir
 */
public class LosAlgoritmos {
//    private char[][] charM;
    private Vertex[][] vertexM;
    
    
    

    public void loadMap(char[][] charM){
        vertexM = new Vertex[charM.length][charM[0].length];
        for (int i = 0; i < vertexM.length; i++) {
            for (int j = 0; j < vertexM[0].length; j++) {
                vertexM[i][j]=new Vertex(j, i, charM[i][j]);
            }
        }
    }
    
    public void astar(){
//        Astar A = new Astar(vertexM, new int[] {1,1}, new int[] {vertexM.length-3,vertexM[0].length-3});
        Astar A = new Astar(vertexM, new int[] {1,1}, new int[] {15,15});
        A.run();
    }
    
    
    
    

    
}
