package com.mtsu.zenmetsu;

import com.badlogic.gdx.Game;

public class GameController extends Game{
	 
	    @Override
	    public void create()
	    {
	        setScreen(new MainMenuScreen());
	   }
}