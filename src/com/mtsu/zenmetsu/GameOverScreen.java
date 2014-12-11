package com.mtsu.zenmetsu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameOverScreen implements Screen{

	private Skin menuSkin;
	private Stage menuStage;
	private GameController gameContext;
	private final int BUTTON_WIDTH = 600;
	private final int BUTTON_HEIGHT = 200;
	private float menuWidth;  //Width of screen
	private float menuHeight; //Height of screen
	private OrthographicCamera gameCamera; //Camera for the screen
	private TextButtonStyle textButtonStyle;
	private TextButton scoreButton;
	public GameOverScreen( GameController initGame ){
		create();
		gameContext = initGame;	
	}

	public void create(){
		
		//Initialize stage
		menuStage = new Stage();
					


		//Set game coordinates
		menuWidth = Gdx.graphics.getWidth();
		menuHeight = Gdx.graphics.getHeight();
				
		//Initialize camera
		gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho( false , menuWidth , menuHeight );
		
		//Custom skins can be made
		//Just generating a simple one here
		menuSkin = new Skin();
		Pixmap pixmap = new Pixmap( 100 , 100 , Format.RGBA8888 );
		pixmap.setColor( Color.BLACK );
		pixmap.fill();
		menuSkin.add( "blackColor", new Texture( pixmap ) );
		
		//Store the default font
		//BitmapFont bfont = new BitmapFont();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Aller_Bd.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 100;
		BitmapFont bfont = generator.generateFont(parameter); // font size 12 pixels
		
		//font12.scale( 2 );
		menuSkin.add( "defaultFont" , bfont );
		menuSkin.add( "blackColor", new Texture( pixmap ) );
		menuSkin.add( "defaultFont" , bfont );
		
		//Design a button
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = menuSkin.getFont("defaultFont");
		
		//Add to the skin
		menuSkin.add("defaultButton" , textButtonStyle);
		
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		TextButton textButton= new TextButton("GAME OVER",textButtonStyle);
		textButton.setPosition( menuWidth * 0.5f - 300 , menuHeight * 0.4f );
		textButton.setWidth(BUTTON_WIDTH);
		textButton.setHeight(BUTTON_HEIGHT);
		menuStage.addActor(textButton);
		scoreButton= new TextButton("",textButtonStyle);
		scoreButton.setPosition( menuWidth * 0.5f - 300 , menuHeight * 0.2f );
		scoreButton.setWidth(BUTTON_WIDTH);
		scoreButton.setHeight(BUTTON_HEIGHT);
		menuStage.addActor(scoreButton);

		
		
		//listener for play button
		textButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
			gameContext.setScreen( gameContext.mainMenuScreen );
			 
			}
	});
		

		
	}
	
	@Override
	public void dispose(){
		// TODO Auto-generated method stub
		menuStage.dispose();
	}

	@Override
	public void hide(){
		// TODO Auto-generated method stub
		scoreButton.setText("");
	}

	@Override
	public void pause(){	
		// TODO Auto-generated method stub
	}

	@Override
	public void render( float delta ){
		Gdx.gl20.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		menuStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		menuStage.draw();
	}

	@Override
	public void resize(int arg0, int arg1){
		// TODO Auto-generated method stub
	}

	@Override
	public void resume(){
		// TODO Auto-generated method stub
	}

	@Override
	public void show(){
		// TODO Auto-generated method stub
		//Initialize input
		Gdx.input.setInputProcessor( menuStage );
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		scoreButton.setText("Score: "+Integer.toString( gameContext.gameScreen.score ));
	}

}
