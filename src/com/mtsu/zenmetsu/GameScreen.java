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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import android.util.Log;

public class GameScreen implements Screen{
	//Game asset variables
	private Game gameContext;
    private Stage gameStage; //Stage to	handle input
    private Sound tapSound;  //Sound played when circle is tapped
	private Music gameMusic; //BGM music
	private OrthographicCamera gameCamera; //Camera for the screen
	private SpriteBatch gameSprites; //Used in the case sprites are needed
	private ShapeRenderer gameShapeRenderer; //Renders the circle
	private float gameWidth;  //Width of screen
	private float gameHeight; //Height of screen
	private Random gameRandGen;	//Generates random numbers. One object so the same seed is kept
	//Objects
	private Circle gameCircle; //Stores the circle data
    private int score;          //Stores the score
	//Bounds for the circle spawn
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;
    //Font Stuff
    BitmapFont gameFont;
	
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
        //Initialize stage
        gameStage = new Stage();
		//Initialize renderer
		gameShapeRenderer = new ShapeRenderer();
		//Initialize randerer
		gameRandGen = new Random();
		//Initialize circle
		gameCircle = new Circle();
		gameCircle.setPosition( gameWidth / 2 , gameHeight / 2 );
		gameCircle.setRadius( 100 );
        //Add listener to stage
        gameStage.addListener( new InputListener(){
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            if( gameCircle.contains( x , y ) )
            {
                spawnCircle();
                score+=1;
                Log.i("Zenmetsu" , Integer.toString( score ) );
                tapSound.play();
            }
            return true;
            
        }
                                 
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

        }

        } 
        );
		//Set bounds for the spawning
		xmin = (int) ( 0.10f * gameWidth );
		xmax = (int) ( gameWidth - xmin );
        xmin += 0.05f * gameWidth; //Add some extra to the top for a score bar
		ymin = xmin;
		ymax = (int) ( gameHeight - ymin );
	    //Configure font
       	FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Aller_Bd.ttf"));
		FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
		fontParameter.size = 20;
		gameFont = fontGenerator.generateFont( fontParameter ); // font size 12 pixels
        //Initialize sound
        //https://www.freesound.org/people/bubaproducer/sounds/151022/
        tapSound = Gdx.audio.newSound( Gdx.files.internal( "sounds/laser.wav" ) );
        //https://www.freesound.org/people/LS/sounds/60048/
        gameMusic = Gdx.audio.newMusic( Gdx.files.internal( "sounds/music.wav" ) );
        gameMusic.setLooping(true);
        gameMusic.play();
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
	public void render( float delta ) {
		//Clear Screen
		Gdx.gl.glClearColor( 0 , 0 , 0 , 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		//Update camera
		gameCamera.update();
		//Set camera on renderer
		gameShapeRenderer.setProjectionMatrix( gameCamera.combined );
        //Update circle
        gameCircle.radius += delta * 15; //Circle grows at a rate of 15 pixel radius per second
        if( gameCircle.radius >= 100 )
        {
            spawnCircle();
        }
		//Draw the circle
		gameShapeRenderer.begin( ShapeType.Filled );
		gameShapeRenderer.setColor( 1,0,0,1);
		gameShapeRenderer.circle( gameCircle.x , gameCircle.y , gameCircle.radius );
		gameShapeRenderer.end();
	    gameSprites.begin();	
        gameFont.draw( gameSprites , "Score " + Integer.toString( score ) , 10f , gameHeight - 10 );
        gameSprites.end();
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
		//Initialize input
       	Gdx.input.setInputProcessor( gameStage );	
	}
	
	public void spawnCircle()
	{
		 float xCoord = gameRandGen.nextInt( ( xmax - xmin ) + 1 ) + xmin;
		 float yCoord = gameRandGen.nextInt( ( ymax - ymin ) + 1 ) + ymin;
		 gameCircle.setPosition( xCoord , yCoord );
		 gameCircle.setRadius( 40 );
	}

}


