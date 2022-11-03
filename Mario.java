// Luke James
// 14 October 2022
// Assignment 4 - Mario.java
// Function: Hold info for Mario

import java.awt.image.BufferedImage;

public class Mario 
{
     int x, y, w, h, prevx, prevy;
     double vert_velocity = 1.2;
     int currentImage;
     static BufferedImage[] marioImages;
     boolean rightFacing = true;
     int numFramesInAir = 0;
     
     //Constructor
     public Mario(int x, int y)
     {
          //Set variables
          this.x = x;
          this.y = y;
          currentImage = 0;

          //Load all 5 Mario images
          marioImages = new BufferedImage[5];
          for(int i = 0; i < marioImages.length; i++)
          {
               if (marioImages[i] == null)
                    marioImages[i] = View.loadImage("mario" + (i+1) + ".png");
          }

          //Now that the images are loaded, get width and height
          this.w = marioImages[0].getWidth();
          this.h = marioImages[0].getHeight();
     } 

     // Mario update
     void update()
     {
          vert_velocity += 4; //this is gravity
          y += vert_velocity;  //updates postion
          numFramesInAir++;

          //Stop Mario from falling
          if (y > 400-h)
          {
               vert_velocity = 0;
               y=400-h; //Snap back to ground
               numFramesInAir = 0;
          }
     }

     //Reverts Mario back to previous position outside of pipe
     public void getOutOfPipe(Pipe pipe)
     {
          
          //Mario coming from top
          if(prevy + h < pipe.y)
          {
               y = pipe.y - h - 1;

               //Allow Mario to jump off pipe
               numFramesInAir = 0;
               vert_velocity = 0;
          }
          //Mario coming from bottom
          else if(prevy > (pipe.y+pipe.h))
          {    
               y = pipe.y + pipe.h;
          }
          else
          {
               //Mario coming from left
               if(prevx < pipe.x)
               {
                    x = pipe.x - w - 1;
               }
               //Mario coming from right
               if(prevx > pipe.x)
               {
                    x = pipe.x + pipe.w + 1;
               }
          }
     }
     
     //Sets Mario's current position to previous position
     public void setPreviousPosition()
     {
          prevx = x;
          prevy = y;
     }

     //Cylces through Mario's image states
     void changeImageState()
     {
          currentImage++;
          if(currentImage > 4)
               currentImage = 0;
          //System.out.println("current image = " + currentImage);

     }

     @Override
     public String toString()
     {
          return "Mario (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
     }

}
