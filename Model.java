// Luke James
// 14 October 2022
// Assignment 4 - Model.java
// Function: Holds the model of the world of the game

import java.util.ArrayList;
import java.util.Iterator;

class Model
{
	ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
	Mario mario;

	Model()
	{
		pipeList = new ArrayList<Pipe>();
		mario = new Mario(100,100);
	}

	public void addPipe(int mx, int my)
	{
		boolean foundPipe = false;
		for(int i = 0; i < pipeList.size(); i++)
		{
			if(pipeList.get(i).clickingOnPreExisting(mx,my) == true) //there's a pipe where mouse clicks
			{
				foundPipe = true;
				pipeList.remove(i);
			}

		}
		if(!foundPipe)
			pipeList.add(new Pipe(mx,my));
		
	}

	//Checks if Mario is not "not colliding" 
	boolean checkCollision(Mario m, Pipe p)
	{
		//MARIO'S RIGHT is less than PIPE'S LEFT
		if ((m.x+m.w) < p.x) 
			return false; //He is NOT colliding
		
		//MARIO'S LEFT is greater than PIPE'S RIGHT
		if (m.x > (p.x+p.w)) 
			return false; 
		
		//MARIO'S BOTTOM is greater than PIPE'S TOP
		if ((m.y+m.h) < p.y) 
			return false;
		
		//MARIO'S TOP is less than PIPE'S BOTTOM
		if (m.y > (p.y+p.h)) 
			return false;
		
		return true; //He IS colliding
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("pipeList", tmpList);
		for(int i = 0; i < pipeList.size(); i++)
			tmpList.add(pipeList.get(i).marshal());
			return ob;
	}

	void unmarshal(Json ob)
	{
		pipeList = new ArrayList<Pipe>();
		Json tmpList = ob.get("pipeList");
		for(int i = 0; i < tmpList.size(); i++)
			pipeList.add(new Pipe(tmpList.get(i)));
	}

	public void update()
	{
		mario.update();
		Iterator<Pipe> it = pipeList.iterator();

		while(it.hasNext())
		{
			Pipe currentPipe = it.next();

			//Check for collision
			boolean check = checkCollision(mario, currentPipe);

			if(check)
				mario.getOutOfPipe(currentPipe);
		}
	}
}