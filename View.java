// Luke James
// 14 October 2022
// Assignment 4 - View.java
// Function: Holds the view of the game

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;


class View extends JPanel
{
	Model model;
	int scrollPos;
	Image ground_image;

	View(Controller c, Model m)
	{
		c.setView(this);
		model = m;
		scrollPos = 0;
		ground_image = loadImage("grassblock.png");

	}

	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		
		try
		{
			im = ImageIO.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}

		System.out.println("Successfully loaded " + filename + " image.");
		return im;
	}

	public void paintComponent(Graphics g)
	{
		scrollPos = model.mario.x - 100;
		g.setColor(new Color(150, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Drawing in the ground
		g.drawImage(ground_image, 0, 400, 500, 100, null);

		//Drawing in pipes
		for (int i = 0; i < model.pipeList.size(); i++)
			model.pipeList.get(i).drawYourself(g, scrollPos);

		//Drawing in Mario
		if(model.mario.rightFacing) //Facing right
			g.drawImage(Mario.marioImages[model.mario.currentImage], model.mario.x - scrollPos, model.mario.y, model.mario.w, model.mario.h, null);
		else //Facing left
			g.drawImage(Mario.marioImages[model.mario.currentImage], (model.mario.x - scrollPos + model.mario.w), model.mario.y, (-model.mario.w), model.mario.h, null);
	}
}