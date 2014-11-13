package com.mtsu.zenmetsu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenuScreen implements Screen{

	Skin skin;
	Stage stage;
	SpriteBatch batch;
	Game gameContext;
	public MainMenuScreen( Game initGame ){
		create();
		gameContext = initGame;
	}
 
	public MainMenuScreen(){
		create();
	}
	public void create()
	{
		//Initialize sprites
		batch = new SpriteBatch();
		//Initialize stage
		stage = new Stage();
		//Initialize input
		Gdx.input.setInputProcessor( stage );

		//Custom skins can be made
		//Just generating a simple one here
		skin = new Skin();
		Pixmap pixmap = new Pixmap( 100 , 100 , Format.RGBA8888 );
		pixmap.setColor( Color.BLACK );
		pixmap.fill();
		
		skin.add( "blackColor", new Texture( pixmap ) );
		
		//Store the default font
		BitmapFont bfont = new BitmapFont();
		bfont.scale( 1 );
		skin.add( "defaultFont" , bfont );
		
		//Design a button
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("blackColor", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("blackColor", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("blackColor", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("blackColor", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("defaultFont");
		//Add to the skin
		skin.add("defaultButton" , textButtonStyle);
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton textButton=new TextButton("PLAY",textButtonStyle);
		textButton.setPosition(200, 200);
		stage.addActor(textButton);
		stage.addActor(textButton);
		stage.addActor(textButton);
		
	}
	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render( float delta )
	{
		Gdx.gl20.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show()
	{
		// TODO Auto-generated method stub
		
	}

}
