/************************************[KEY]***********************************
TEMP = added for convenience, fun, or practice, please ignore when grading!
TEST = used to test, left as comments when not in use. 
???? = questions
NOTE = reminders or explanations
*************************************[KEY]***********************************/

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import java.io.IOException;

public class Image {
	private int width, height;
	private int[][] pixels;
   
	public Image(String filename) throws Exception { 
		this.read(filename); 
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
 
	public void read(String filename) throws Exception {
		File fileImage = new File(filename);
      
      //NOTE: ImageIO.read() method takes in File object and returns BufferedImage object
      BufferedImage bufImage = ImageIO.read(fileImage);
      
      //NOTE: this refers to the calling object, which will be an Image object and sets
      //      width & height of the Image object to that of the BufferedImage object
		this.width = bufImage.getWidth();
		this.height = bufImage.getHeight();
      
/*
TEST: check width/height for bufImage and Image object
      this Image object has the height and width of the buffered animals.jpg image
NOTE: this.height = 182     
      this.width = 276
          
      System.out.println("bufImage.getWidth() = " + bufImage.getWidth());
      System.out.println("bufImage.getHeight() = " + bufImage.getHeight());
      System.out.println("this.getWidth() = " + this.getWidth());
      System.out.println("this.getHeight() = " + this.getHeight());
*/

      
		// Complete the remainder of this method 
      this.pixels = new int[this.height][this.width];
      for (int r = 0; r < this.height; r++) {
         for (int c = 0; c < this.width; c++) {
            this.pixels[r][c] = bufImage.getRGB(c, r);
         }
      }
      
        
   }

//NOTE: returns BufferedImage object. Invoke via imageObject.createBufferedImage()
   private BufferedImage createBufferedImage() { 
         //this = imageObject that invoked method
      	BufferedImage bufImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
  
      	for (int row = 0; row < this.height; row++) {
         	for (int col = 0; col < this.width; col++) {
         		bufImage.setRGB(col, row, this.pixels[row][col]);
         	}
         }
      	return bufImage;
	}
  
  
   public void write(String filename) throws Exception {
     File fileImage = new File(filename);
//NOTE: filename.substring(int x); creates new String object by cutting out "filename" starting from index "x"
     String ext = filename.substring(filename.indexOf('.') + 1);
     BufferedImage bufImage = createBufferedImage();
     ImageIO.write(bufImage, ext, fileImage);
   }
   
   
	public void draw(Graphics gc, int x, int y) {
      BufferedImage bufImage = createBufferedImage();
   	gc.drawImage(bufImage, x, y, null);
	}


   public void flipY() {
      int temp;
      for (int r = 0; r < this.height; r++) {
         for (int c = 0; c < Math.floor(this.width / 2); c++) {
           
            temp = this.pixels[r][c];
            this.pixels[r][c] = this.pixels[r][ (this.width - 1) - c ];
            this.pixels[r][ (this.width - 1) - c ] = temp;

         }
      }
   }
   
   public void grayscale() {
      for (int r = 0; r < this.height; r++) {
         for (int c = 0; c < this.width; c++) {
            Color pixColor = new Color(this.pixels[r][c]);
            int red = pixColor.getRed();
            int green = pixColor.getGreen();
            int blue = pixColor.getBlue();
            Color pixGray = new Color( (red+green+blue)/3, (red+green+blue)/3, (red+green+blue)/3 );
            this.pixels[r][c] = pixGray.getRGB();
         }
      }      
   }
   
   
   public void saveAsJPG(String filename) throws Exception {
      BufferedImage img = this.createBufferedImage();
      File outFilename = new File(filename + ".jpg");
      ImageIO.write(img, "jpg", outFilename);
   }   
   
   public void rotate180() {
      int temp;
      for (int r = 0; r < Math.floor(this.height / 2); r++) {
         for (int c = 0; c < this.width; c++) {
           
            temp = this.pixels[r][c];
            this.pixels[r][c] = this.pixels[(this.height - 1) - r][ (this.width - 1) - c ];
            this.pixels[(this.height - 1) - r][ (this.width - 1) - c ] = temp;

         }
      }
   }
   
} 
/*
NOTE: rotate180 plan
  0 1 2 3  
0 x x x        [0][0] ; [3][3]   [r][c] ; [height-1-r][width-1-c]
1              [0][1] ; [3][2]
2              [0][2] ; [3][1]
3       x      [0][3] ; [3][0]
               [1][0] ; [2][3]
*/