package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * From 
 * Functionality to write and save .map files.
 * @author http://elsewhat.com/2006/08/17/converting-a-two-dimensional-array-of-ints-to-jpg-image-in-java/
 */

public class ImageExport {

    /**
     * Export an image to a JPG file
     * 
     * @param fileName The filename to export to
     * @param image The image to write to file
     * @throws IOException If problems occur during writing of file
     */
    public static void exportImageToFile(String fileName, RenderedImage image)throws IOException{
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
    public static BufferedImage convertRGBImage(int[][] rgbValue){
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
    public static BufferedImage convertRGBImageWithHeader(int[][] rgbValue,String strHeader){
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
    
    public static void call(int[][][] map) {
        //setup int array
            int height=map.length;
            int width=map[0].length;            
            int[][] pixel = new int [height][width];

        //generate some pattern            
            for(int y=0; y< height; y++){
                for(int x=0; x< width; x++){
                    int r = map[y][x][0];
                    int g = map[y][x][1];
                    int b = map[y][x][2];
//                    System.out.println("r: " + r);
//                    System.out.println("g: " + g);
//                    System.out.println("b: " + b);
                    int color = new Color(r, g, b).getRGB();
//                    int color = (255 << 24 ) | (red << 16 ) | (green <<  8) | blue;
                    pixel[y][x]=color;
                                        
                }
            }
            //Create image
            BufferedImage image = ImageExport.convertRGBImage(pixel);
        try {
            //Write it to file
            ImageExport.exportImageToFile("test1.jpg",image);        
        } catch (IOException ex) {
            Logger.getLogger(ImageExport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


