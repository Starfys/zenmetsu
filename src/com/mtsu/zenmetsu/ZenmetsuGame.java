package com.mtsu.zenmetsu;

import com.badlogic.gdx.Game;

public class ZenmetsuGame extends Game{
	@Override
	public void create() {
		// TODO Auto-generated method stub
		setScreen( new MainMenu( ) );
	}
	
}
