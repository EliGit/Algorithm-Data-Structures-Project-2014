package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import losalgoritmos.Vertex;

/**
 * From 
 * Functionality to write and save .map files.
 * @author http://elsewhat.com/2006/08/17/converting-a-two-dimensional-array-of-ints-to-jpg-image-in-java/
 */

public class ImageExport {
    private BufferedImage bf;
    /**
     * Export an image to a JPG file
     * 
     * @param fileName The filename to export to
     * @param image The image to write to file
     * @throws IOException If problems occur during writing of file
     */
    public void exportImageToFile(String fileName, RenderedImage image)throws IOException{
        File file = new File(fileName);
        //to export to png, change 2 parameter to "png"
        ImageIO.write(image, "jpg", file);
    }
    /**
     * Convert a two dimensional array of ints to a BufferedImage.
     * Each int represents a pixel of a certain colour.
     * The ints are expected to be calculated using
     * int color = (255 << 24 ) | (red << 16 ) | (green <<  8) | blue;
     * where red,green and blue are values in [0-255]
     * 
     * @param rgbValue The two dimensional int array representing the pixels
     * @return A BufferedImage with all the pixels drawn
     */
    public BufferedImage convertRGBImage(int[][] rgbValue){
        int height = rgbValue.length;
        int width = rgbValue[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //we either have to loop through all values, or convert to 1-d array
        for(int y=0; y< height; y++){
            for(int x=0; x< width; x++){
                bufferedImage.setRGB(x,y,rgbValue[y][x]);  
            }
        }
        return bufferedImage;  
    }
    /**
     * Convert a two dimensional array of ints to a BufferedImage.
     * Each int represents a pixel of a certain colour.
     * The ints are expected to be calculated using
     * int color = (255 << 24 ) | (red << 16 ) | (green <<  8) | blue;
     * where red,green and blue are values in [0-255]
     * 
     * In addition this also draws a header text in white colour on a back background.
     * This increases the height of the image.
     * 
     * @param rgbValue The two dimensional int array representing the pixels
     * @param strHeader The text to draw at the top of the image
     * @return A BufferedImage with all the pixels drawn
     */
    public BufferedImage convertRGBImageWithHeader(int[][] rgbValue,String strHeader){
        //We add extra pixels on top of the image for the strHeader
        int headerHeight=13;
        int height = rgbValue.length+headerHeight;
        int width = rgbValue[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //we either have to loop through all values, or convert to 1-d array
        for(int y=headerHeight; y< height; y++){
            for(int x=0; x< width; x++){
                bufferedImage.setRGB(x,y,rgbValue[y][x]);  
            }
        }
        //Draw the text
//        Graphics2D g=bufferedImage.createGraphics();
//        g.setFont(new Font("Monospaced", Font.BOLD, 14) );
//        g.setColor(Color.white);
//        g.drawString(strHeader,0,10);

        return bufferedImage;  
    }
    
    public BufferedImage call(char[][] map, int bfwidth,int bfheight, int[] start, int[] goal){
        //setup int array
        int height=map.length;
        int width=map[0].length;            
        int[][] pixel = new int [height][width];

        int r = 0;
        int g = 0;
        int b = 0;

        //generate some pattern            
        for(int y=0; y< height; y++){
            for(int x=0; x< width; x++){
                if((y==start[0] && x==start[1]) || (y==goal[0] && x==goal[1])){
                    r=255;
                    g=0;
                    b=0;
                }
                else if(map[y][x]=='.'){
                    r=0;
                    g=255;
                    b=0;
                } else {//black
                    r=0;
                    g=0;
                    b=0;
                }
                int color = new Color(r, g, b).getRGB();
                pixel[y][x]=color;
            }
        }
        
        bf = convertRGBImage(pixel);
        return resize(bf, bfwidth, bfheight);            
    }
    
    public BufferedImage update(ArrayList<Vertex> route, int bfwidth,int bfheight){        
        BufferedImage bufferedImage = new BufferedImage(bf.getWidth(), bf.getHeight(), BufferedImage.TYPE_INT_RGB);        
        for (int i =0; i<route.size()-1; i++) {
            Vertex vertex = route.get(i);
            int rgb = new Color(162,162,162).getRGB();
            bufferedImage.setRGB(vertex.getX(), vertex.getY(), rgb);
        }
                
        for(int y=0; y< bf.getHeight(); y++){
            for(int x=0; x< bf.getWidth(); x++){
                if(bufferedImage.getRGB(x, y)!=new Color(162,162,162).getRGB()){
                    bufferedImage.setRGB(x,y,bf.getRGB(x, y));
                }
                
            }
        }

        return resize(bufferedImage, bfwidth, bfheight);
        
    }
    
    private BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
    return bi;
}
}


