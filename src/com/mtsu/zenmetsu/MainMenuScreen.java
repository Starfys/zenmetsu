package com.mtsu.zenmetsu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MainMenuScreen implements Screen{
	Texture texture;
	TextureRegion christmasTree;
	TextureRegion titlesprite;
	//OrthographicCamera camera;
	BitmapFont font;
	Sprite sprite;
	private TextButton menubutton;

	private Skin menuSkin;
	private Stage menuStage;
	private SpriteBatch gameSprites;
	private Game gameContext;
	final int BUTTON_WIDTH = 200;
	final int BUTTON_HEIGHT = 100;
	public MainMenuScreen( Game initGame ){
		create();
		gameContext = initGame;
	}

	public MainMenuScreen(){
		create();
		// setup the dimensions of the menu buttons
	   
	}
	
	public void create()
	{
		//Initialize sprites
		gameSprites = new SpriteBatch();
		//Initialize stage
		menuStage = new Stage();
		//Initialize input
		Gdx.input.setInputProcessor( menuStage );

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
		parameter.size = 12;
		BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
		//font12.scale( 2 );
		menuSkin.add( "defaultFont" , font12 );
		
		menuSkin.add( "blackColor", new Texture( pixmap ) );
		
		//Store the default font
		BitmapFont bfont = new BitmapFont();
		bfont.scale( 1 );
		menuSkin.add( "defaultFont" , bfont );
		
	
		//Design a button
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = menuSkin.newDrawable("blackColor", Color.DARK_GRAY);
		textButtonStyle.down = menuSkin.newDrawable("blackColor", Color.DARK_GRAY);
		textButtonStyle.checked = menuSkin.newDrawable("blackColor", Color.BLUE);
		textButtonStyle.over = menuSkin.newDrawable("blackColor", Color.LIGHT_GRAY);
		textButtonStyle.font = menuSkin.getFont("defaultFont");
		
		//Add to the skin
		menuSkin.add("defaultButton" , textButtonStyle);
		
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton textButton= new TextButton("PLAY",textButtonStyle);
		textButton.setPosition(80, 200);
		textButton.setWidth(BUTTON_WIDTH);
		textButton.setHeight(BUTTON_HEIGHT);
		
		menubutton = new TextButton("Zenmetsu",textButtonStyle);
		menubutton.setPosition(80, 700);
		menubutton.setWidth(BUTTON_WIDTH);
		menubutton.setHeight(BUTTON_HEIGHT);
		menuStage.addActor(textButton);
		menuStage.addActor(menubutton);
		
	
		
		 
       // sprite = new Sprite(texture2);
		/*	//create table for different resolutions
		Table table=new Table();
		table.setSize(800,480);

		TextButton startGame=new TextButton("PLAY",textButtonStyle);
		//table.add(startGame).width(200).height(50);
		//table.row();

		TextButton options=new TextButton("options",textButtonStyle);
		table.add(options).width(150).padTop(10).padBottom(3);
		table.row();

		TextButton credits=new TextButton("credits",skin);
		table.add(credits).width(150);
		table.row();

		TextButton quit=new TextButton("quit",skin);
		table.add(quit).width(100).padTop(10);

		stage.addActor(table);
		
		*/
		
	textButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
			System.out.println("Clicked! Is checked: " + textButton.isChecked());
			textButton.setText("Starting new game");
			//gameContext.setScreen( new GameScreen());
			 
			}
	});
		
		textButtonStyle.up = menuSkin.newDrawable("blackColor", Color.DARK_GRAY);
		textButtonStyle.down = menuSkin.newDrawable("blackColor", Color.DARK_GRAY);
		textButtonStyle.checked = menuSkin.newDrawable("blackColor", Color.BLUE);
		textButtonStyle.over = menuSkin.newDrawable("blackColor", Color.LIGHT_GRAY);
		textButtonStyle.font = menuSkin.getFont("defaultFont");
		//Add to the skin
		menuSkin.add("defaultButton" , textButtonStyle);
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		menubutton=new TextButton("PLAY",textButtonStyle);
		textButton.setPosition(200, 200);
		menuStage.addActor(textButton);
		menuStage.addActor(textButton);
		menuStage.addActor(textButton);
		
	}
	
	@Override
	public void dispose(){
		// TODO Auto-generated method stub
		menuStage.dispose();
		menuSkin.dispose();
		//tree.dispose();
		menuStage.dispose();
	}

	@Override
	public void hide(){
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void pause(){	
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render( float delta ){
		Gdx.gl20.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	
		//batch.setProjectionMatrix(camera.combined);
		gameSprites.begin();
		gameSprites.draw(christmasTree,0,0,700/(700.0f/700.0f),700);
	
		 
		//sprite.draw(batch);
		gameSprites.end();
		menuStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		menuStage.draw();
	}

	@Override
	public void resize(int arg0, int arg1){
		// TODO Auto-generated method stub
		//stage.setViewport(width, height, false);
	}

	@Override
	public void resume(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show(){
		// TODO Auto-generated method stub
		
	}

}
