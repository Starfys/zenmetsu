package com.mtsu.zenmetsu;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen{
	//Game asset variables
	private Game gameContext;
	private Sound tapSound;
	private Music gameMusic;
	private OrthographicCamera gameCamera;
	private SpriteBatch gameSprites;
	private ShapeRenderer gameShapeRenderer;
	private float gameWidth;
	private float gameHeight;
	private Random gameRandGen;	//Generates random numbers. One object so the same seed is kept
	//Objects
	private Circle gameCircle;
	private Actor circleActor;
	//Bounds for the circle spawn
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;
	
	public GameScreen( GameController initGame )
	{
		//Initialize context
		gameContext = initGame;
		//Set game coordinates
		gameWidth = Gdx.graphics.getWidth();
		gameHeight = Gdx.graphics.getHeight();
		//Initialize camera
		gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho( false , gameWidth , gameHeight );
		//Initialize sprites
		gameSprites = new SpriteBatch();
		//Initialize renderer
		gameShapeRenderer = new ShapeRenderer();
		//Initialize randerer
		gameRandGen = new Random();
		//Initialize circle
		gameCircle = new Circle();
		gameCircle.setPosition( gameWidth / 2 , gameHeight / 2 );
		gameCircle.setRadius( 100 );
		//Initialize circle actor
		
		//Set bounds for the spawning
		int xmin = (int) ( 0.10f * gameWidth );
		int xmax = (int) ( gameWidth - xmin );
		int ymin = xmin;
		int ymax = (int) ( gameHeight - ymin );
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		//Clear Screen
		Gdx.gl.glClearColor( 0 , 0 , 0 , 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		//Update camera
		gameCamera.update();
		//Set camera on renderer
		gameShapeRenderer.setProjectionMatrix( gameCamera.combined );
		//Draw the circle
		gameShapeRenderer.begin( ShapeType.Filled );
		gameShapeRenderer.setColor( 1,0,0,1);
		gameShapeRenderer.circle( gameCircle.x , gameCircle.y , gameCircle.radius );
		gameShapeRenderer.end();
	}

	@Override
	public void resize(int arg0, int arg1)
	{
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
	
	public void spawnCircle()
	{
		 float xCoord = gameRandGen.nextInt( ( xmax - xmin ) + 1 ) + xmin;
		 float yCoord = gameRandGen.nextInt( ( ymax - ymin ) + 1 ) + ymin;
		 gameCircle.setPosition( xCoord , yCoord );
		 gameCircle.setRadius( 40 );
	}

}


