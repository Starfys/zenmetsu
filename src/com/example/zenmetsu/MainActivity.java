package com.example.zenmetsu;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity
{
	//Stores the view containing the OpenGLES content
	private GLSurfaceView gameGLView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//Call super function
		super.onCreate(savedInstanceState);
		
		//Set fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
			    			  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        gameGLView = new GameGLSurfaceView( this );
        setContentView( gameGLView );

	}
	
	//Default android stuff, we are not using a menu so ignore
	@Override
	public boolean onCreateOptionsMenu( Menu menu ){return false;}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {return false;}
	
	@Override
	//Resume function
	protected void onResume()
	{
		super.onResume();
		gameGLView.onResume();
	}
	@Override
	//Pause function
	protected void onPause()
	{
		super.onPause();
		gameGLView.onPause();
	}
}