package UI;

import datastructures.Stack;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import datastructures.Vertex;

/**
 * From 
 * Functionality to write and save .map files.
 * exportImageToFile and convertRGBImage modified from: http://elsewhat.com/2006/08/17/converting-a-two-dimensional-array-of-ints-to-jpg-image-in-java/
 * 
 * @author 
 */

public class ImageBuilder {
    private static final int PATH = new Color(0,0,255).getRGB();
    private static final int POINT = new Color(255,0,0).getRGB();
    private static final int TERRAIN = new Color(255,255,255).getRGB();
    private static final int WALL = new Color(0,0,0).getRGB();
    private static final int CALCULATED = new Color(162,162,162).getRGB();
    
    private BufferedImage bf;
    
    /**
     * Export an image to a JPG file.
     * !!! THIS METHOD NOT IN USE NOW !!!
     * @param fileName The filename to export to
     * @param image The image to write to file
     * @throws IOException If problems occur during writing of file
     */
    public void exportImageToFile(String fileName, RenderedImage image)throws IOException{
        File file = new File(fileName);
        //png -> second param "png"
        ImageIO.write(image, "jpg", file);
    }
    /**
     * Convert rgb matrix to BufferedImage.     
     * 
     * @param rgbValue The two dimensional int array representing the pixels
     * @return A BufferedImage with all the pixels drawn
     */
    private BufferedImage convertRGBImage(int[][] rgbValue){
        int height = rgbValue.length;
        int width = rgbValue[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int y=0; y< height; y++)
            for(int x=0; x< width; x++)
                bufferedImage.setRGB(x,y,rgbValue[y][x]);  
        return bufferedImage;  
    }
    
    /**
     * Builds a BufferedImage from the charMatrix of the desired width and height (resizing used) with the specified start and goal locations.
     * 
     * @param map charMatrix of the map.
     * @param bfwidth desired width of the BufferedImage.
     * @param bfheight desired height of the BufferedImage.
     * @param start the starting point of the path.
     * @param goal the end point of the path.
     * @return the built BufferedImage, ready for display.
     */
    public BufferedImage buildImage(char[][] map, int bfwidth,int bfheight, int[] start, int[] goal){
        int[][] RGBmatrix = new int [map.length][map[0].length];
        
        for(int y=0; y< map.length; y++)
            for(int x=0; x< map[0].length; x++){
                if((y==start[0] && x==start[1]) || (y==goal[0] && x==goal[1])) RGBmatrix[y][x] = POINT;
                else if(map[y][x] == '.') RGBmatrix[y][x] = TERRAIN;
                else RGBmatrix[y][x] = WALL;
            }        

        bf = convertRGBImage(RGBmatrix);
        return resize(bf, bfwidth, bfheight);            
    }
    
    /**
     * Updates the last built BufferedImage with the provided route, returns an image of the desired size.
     * 
     * @param route the best route in an ArrayList<Vertex> format, order does not matter.
     * @param bfwidth desired width of the BufferedImage.
     * @param bfheight desired height of the BufferedImage.
     * @return the updated BufferedImage.
     */
    
    public BufferedImage updateImageWithPath(Stack<Vertex> route, int bfwidth,int bfheight){        
        BufferedImage bufferedImage = new BufferedImage(bf.getWidth(), bf.getHeight(), BufferedImage.TYPE_INT_RGB);        
        while(!route.isEmpty()){
            Vertex v = route.pop();        
            bufferedImage.setRGB(v.getX(), v.getY(), PATH);
        }
        
            
        
        for(int y=0; y< bf.getHeight(); y++)
            for(int x=0; x< bf.getWidth(); x++)
                if(bufferedImage.getRGB(x, y)!=PATH)
                    bufferedImage.setRGB(x,y,bf.getRGB(x, y));
        
        return resize(bufferedImage, bfwidth, bfheight);  
    }
    
    /**
     * Updates the last built BufferedImage with the provided vertexMatrix, returns an image of the desired size.
     * 
     * @param route the best route in an ArrayList<Vertex> format, order does not matter.
     * @param bfwidth desired width of the BufferedImage.
     * @param bfheight desired height of the BufferedImage.
     * @return the updated BufferedImage.
     */
    
    public BufferedImage updateImageWithPathAndComparisons(Vertex[][] vertexMatrix, int bfwidth,int bfheight){
        BufferedImage bufferedImage = new BufferedImage(bf.getWidth(), bf.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < bf.getHeight(); y++) 
            for (int x = 0; x < bf.getWidth(); x++) {
                if(vertexMatrix[y][x].isOnPath()) bufferedImage.setRGB(x,y, PATH);
                else if (vertexMatrix[y][x].getDistance()!=-1) bufferedImage.setRGB(x,y, CALCULATED);
                else bufferedImage.setRGB(x,y,bf.getRGB(x, y));
            }                
        return resize(bufferedImage, bfwidth, bfheight);
    }
    
    
    private BufferedImage resize(BufferedImage image, int width, int height) {
        int type;
        type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height,type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}


