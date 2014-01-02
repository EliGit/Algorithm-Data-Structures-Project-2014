/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractListModel;

/**
 * ListModel for the mapList JList in GUI.
 * Finds all files in ./maps folder and loads them into the program.
 * @author EliAir
 */
public class MapListModel extends AbstractListModel{
    private ArrayList<File> list;
    
    public MapListModel(){
//        map = new HashMap<String, File>();
        list = new ArrayList<File>();
        fillTheList();
    }
    
    private void fillTheList(){
        File[] files = new File("./maps").listFiles();
        for (File file : files) {
            if(!file.getName().contains("test")) list.add(file);
        }
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }
    
}
