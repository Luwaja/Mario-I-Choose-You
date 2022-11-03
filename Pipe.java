// Luke James
// 14 October 2022
// Assignment 4 - Pipe.java
// Function: Holds the info for the individual pipes

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//import javax.swing.plaf.ScrollBarUI;

public class Pipe 
{
     int x, y, w, h;
     static BufferedImage image;

     public Pipe(int x, int y)
     {
          this.x = x;
          this.y = y;
          this.w = 55;
          this.h = 500;
          
          //Load image unless it has already been loaded
          if (image == null)
               image = View.loadImage("pipe.png");
     } 

     
     boolean clickingOnPreExisting(int mousex, int mousey)
     {
          if(mousex >= x && mousex <= x+w && mousey >= y && mousey <= y+h)
               return true; //True if mouse click is within the bounds of the pipe image
          else
               return false; //False otherwise
     }

     public Pipe(Json ob)
     {
          x = (int)ob.getLong("x");
          y = (int)ob.getLong("y");
          w = 55;
          h = 500;

          if (image == null)
               image = View.loadImage("pipe.png");
     }
     
     Json marshal()
     {
          Json ob = Json.newObject();
          ob.add("x", x);
          ob.add("y", y);
          return ob;
     }

     public void drawYourself(Graphics g, int scrollPos)
     {
          g.drawImage(image, x-scrollPos, y, w, h, null);
     }

     @Override
     public String toString()
     {
          return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
     }
}
