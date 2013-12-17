/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EliAir
 */
public class CartographerTest {
    private Cartographer c;
    private File f1;
    private File f2;    
    private File f3;
    private File f4;    
    //contents of f1:
//    private static char[][] testCharArr1= {{'T','T'},
//                                            {'@','@'}};
    //conents of f2:
//    private static char[][] testCharArr2= {{'@','@'},
//                                            {'T','T'}};
    
    public CartographerTest() {
    }
    
    @Before
    public void setUp() {      
        f1 = new File("./maps/test1.map");
        f2 = new File("./maps/test2.map");
        f3 = new File("./maps/test3.map");
        f4 = new File("asdasdasd"); //does not exist
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructorThrowsExceptionCorrectly() {        
        try {
            c = new Cartographer(f1);            
        } catch (Exception e){
            assert false;
        }
        
        try {
            c = new Cartographer(f4);
            assert false;
        } catch (Exception e){
            assert true;
        }        
    }
    
    @Test
    public void loadMapThrowsExceptionCorrectly() {
        try{
            c = new Cartographer(f1);
            assertTrue(c.loadMap(f2));
            assertFalse(c.loadMap(f4));
        } catch (Exception e) {
            assert false;
        }                
    }
    
    @Test
    public void toCharMatrixThrowsExceptionCorrectly(){
        try{
            c = new Cartographer(f3);
            c.toCharMatrix();
            assert false;
        } catch (Exception e){
            assert true;
        }
    }
    
    @Test
    public void toCharMatrixWorksCorrectly(){
        try {
            c = new Cartographer(f1);
            char[][] a = c.toCharMatrix();
            assertTrue(a[0][0]=='T');
            assertTrue(a[1][0]=='@');
            
            c.loadMap(f2);            
            a = c.toCharMatrix();
            assertTrue(a[0][0]=='@');
            assertTrue(a[0][3]=='T');
            assertTrue(a[1][0]=='T');                        
            assertTrue(a[2][3]=='T');                        
        } catch (Exception ex) {
            assert false;
        }
        
        
    }
    
    
}