/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.Stack;
import datastructures.Vertex;

/**
 * Interface for routing algorithms, for easier handling in performance testing.
 * @author Elias Nygren
 */
public interface Algo {        
    public Stack<Vertex> run();
}
