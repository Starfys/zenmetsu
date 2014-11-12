package com.mtsu.zenmetsu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MainMenu implements Screen{
	private Stage stage = new Stage();
	private Skin skin = new Skin( Gdx.files.internal( "skins/menuSkin.json" ) );
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render( float delta )
	{
		// TODO Auto-generated method stub
		Gdx.gl20.glClearColor( 0 , 0 , 0 , 1 );
		Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT );
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
