// Luke James
// 14 October 2022
// Assignment 4 - Game.java
// Function: Set default elements and run game

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		
		this.setTitle("A4 - Mario I Choose You!");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
	}

	public void run()
	{
		//load file
		Json j = Json.load("pipemap.json");
		model.unmarshal(j);
		System.out.println("File is loaded.");
		
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 50 milliseconds
			try
			{
				Thread.sleep(20);
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
