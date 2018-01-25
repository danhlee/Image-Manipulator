/************************************[KEY]***********************************
TEMP = added for convenience, fun, or practice, please ignore when grading!
TEST = used to test, left as comments when submitting just in case
???? = questions
NOTE = reminders or explanations
*************************************[KEY]***********************************/

import java.awt.*; //???? why didn't this import also include awt.event.KeyEvent?
import javax.imageio.*;
import java.awt.event.KeyEvent;
import java.util.*;


class TestImage {
	public static void main(String args[]) throws Exception {
      Scanner input = new Scanner(System.in);
      //NOTE: robot used for auto alt-tabbing after displaying images
      Robot robot = new Robot();
      
    	Frame frame = new Frame("My Images");
   	frame.setSize(1024,768);
   	frame.setVisible(true);
   	Graphics gc = frame.getGraphics();
      
   	try {
         Image testImg = new Image("animals.jpg");
         testImg.draw(gc, 10, 40);
//TEMP: to alt-tab back to jgrasp so you can press "any key" right away
//      instead of alt-tabbing manually or clicking on jgrasp to type into the RUN I/O pane    
         robot.keyPress(KeyEvent.VK_ALT);
         robot.keyPress(KeyEvent.VK_TAB);
         robot.keyRelease(KeyEvent.VK_ALT);
         robot.keyRelease(KeyEvent.VK_TAB);
         
         System.out.println("**********************************");
         System.out.print("1 = mirror image\n2 = upside down image\n3 = grayscale image" +
                            "\n**********************************\nHow would you like to alter your image?: ");
         
//NOTE: Image effect options
         boolean inRange = true;
         while (inRange) {
            int imageEffectNumber = Integer.parseInt(input.nextLine());
            if(imageEffectNumber == 1) {
               //NOTE: mirror image
               testImg.flipY();
               inRange = false;
            }
            else if (imageEffectNumber == 2) {
               testImg.rotate180();
               inRange = false;
            }
            else if (imageEffectNumber == 3) {
               testImg.grayscale();
               inRange = false;
            }
            else {
               System.out.println("**********************************");
               System.out.print("1 = mirror image\n2 = upside down image\n3 = grayscale image" +
                                "\n**********************************\nYour choice was invalid. Please try again: ");
            }
         }
         //NOTE: draw altered image
         //(10 + 276) = original image starting x position + width of image + 1 more pixel to start next image
         System.out.println("The altered image is to the right of the original!");
         testImg.draw(gc, (10 + 276) + 1 , 40);


//NOTE: Save file options
         System.out.println("**********************************");
         System.out.print("1 = save and exit\n2 = exit\n**********************************" +
                          "\nWould you like to save your new image?: ");
         inRange = true;
         while (inRange) {
            int saveNum = Integer.parseInt(input.nextLine());
            if(saveNum == 1) {
               //???? How do I allow for filenames with spaces? input.nextLine() threw an exception error...
               System.out.print("Enter filename(no spaces): ");
               String outputName = input.next();
               testImg.saveAsJPG(outputName);
               //TEMP: added to terminate program
               System.exit(0);
            }
            else if (saveNum == 2) {
               //TEMP: added to terminate program
               System.exit(0);  
            }
            else {
               System.out.println("**********************************");
               System.out.print("1 = save and exit\n2 = exit\n**********************************" + 
                                "\nInvalid choice! Would you like to save your new image?: ");
            }
         }

      } 
      catch (Exception e) {
         e.printStackTrace();
      }

   }   
}