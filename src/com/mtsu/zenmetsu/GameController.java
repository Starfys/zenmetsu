package com.mtsu.zenmetsu;

import com.badlogic.gdx.Game;

public class GameController extends Game{
	 	
		public MainMenuScreen mainMenuScreen;
		public GameScreen gameScreen;
	    @Override
	    public void create()
	    {
	    	mainMenuScreen = new MainMenuScreen( this );
	    	gameScreen = new GameScreen( this );
	        setScreen( mainMenuScreen );
	   }
}