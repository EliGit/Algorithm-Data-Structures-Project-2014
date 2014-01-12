/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
 
import java.io.File;
import javax.swing.AbstractListModel;
 
/**
 * ListModel for the mapList JList in GUI.
 * Finds all files in ./maps folder and loads them into the program.
 * @author Elias Nygren
 */
public class MapListModel extends AbstractListModel{
    private File[] list;
   
    public MapListModel(){
        fillTheList();
    }
   
    private void fillTheList(){
        File[] files = new File("./maps").listFiles();
        int nulls=0;
        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().contains("test")){
//                files[i]=null;
//                nulls++;
            }
        }
        list = new File[files.length-nulls];
       
        int index=0;
        for (int i = 0; i < files.length; i++) {
            if(files[i]!=null) list[index++]=files[i];
        }
    }
 
    @Override
    public int getSize() {
        return list.length;
    }
 
    @Override
    public Object getElementAt(int index) {
        return list[index];
    }
   
}