//This class mediates between android and OpenGLES
//It receives data from the renderer and sends it to the screen
//It also receives touch data from the android api and sends it to the renderer

package com.example.zenmetsu;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameGLSurfaceView extends GLSurfaceView
{

	private final GameGLRenderer gameRenderer;
	
	//Constructor
	public GameGLSurfaceView( Context context )
	{
		//Call super constructor
		super( context );
		
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);
        
        // Set the Renderer for drawing on the GLSurfaceView
        gameRenderer = new GameGLRenderer( context );
        setRenderer( gameRenderer );
        
        // Set to render continuously
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        
	}
	
    @Override
    //Pause function
    public void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        gameRenderer.onPause();
        
    }
 
    @Override
    //Resume function
    public void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        gameRenderer.onResume();
        
    }
}
