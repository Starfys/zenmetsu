//This class does the rendering work

package com.example.zenmetsu;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class GameGLRenderer implements GLSurfaceView.Renderer
{
    //Used to convert the 3-d plane onto the screen
		//Converts 3-d to 2-d, All graphics are 2d, but still necessary in OpenGLES
    	private final float[] projectionMatrix = new float[ 16 ];
    	//Used to normalize proportions on all screens through a "camera"
    	private final float[] viewMatrix = new float[ 16 ];
    	//Used to combine the view and projection matrices
    	private final float[] viewProjectionMatrix = new float[ 16 ];
    	
	//Geometric variables
		//Used to store the vertices of the object rendered
    	private static float vertices[];
		//Used to store indices?
		//TODO: Remove
    	private static short indices[];
		//Stores vertices in raw form
		public FloatBuffer vertexBuffer;
		//Stores indices in raw form
		//TODO: remove
		private ShortBuffer drawListBuffer;
	
	//Shaders
		//Vertex shader for solid color
		//Also helps to transform 3d coordinate to 2d screen
	    private static final String solidColorVertexShader =
	            "uniform    mat4        uMVPMatrix;" +
	            "attribute  vec4        vPosition;" +
	            "void main() {" +
	            "  gl_Position = uMVPMatrix * vPosition;" +
	            "}";
	    //Fragment shader for solid color
	    //Computes color for pixels
	    private static final String solidColorFragmentShader =
	            "precision mediump float;" +
	            "void main() {" +
	            "  gl_FragColor = vec4(0.5,0,0,1);" +
	            "}";
	    //Does something with shaders
	    private static int solidColorShaderProgram;
	    
	//Resolution
	    private float mScreenWidth = 1280;
		private float mScreenHeight = 720;
		
	//Miscellaneous useful variables
		//Context of app
		private Context mContext;
		//Last time updated
		private long mLastTime;
		//Not sure what this is for
		private int mProgram;
		
		
	

    //Constructor
    public GameGLRenderer( Context c )
    {
    	//Store the context in a private variable
    	mContext = c;
    	//Store the time, adjusted to balance for startup
    	mLastTime = System.currentTimeMillis()+100;
    }
    
    //Pause function
    public void onPause()
    {
        /* Do stuff to pause the renderer */
    }
 
    //Resume function
    public void onResume()
    {
        /* Do stuff to resume the renderer */
    }
 
	@Override
	public void onDrawFrame( GL10 unused ) {
        // Get the current time
        long now = System.currentTimeMillis();
 
        // Ensures the time hasn't become discombobulated 
        if (mLastTime > now) return;
 
        // Get the amount of time the last frame took.
        long elapsed = now - mLastTime;
 
        // Update the objects
 
        
        // Render the objects
        Render( viewProjectionMatrix );
 
        // Save the current time so the next frame can calculate time.
        mLastTime = now;	
	}
    private void Render( float[] m ) {
    	 
        // clear Screen and Depth Buffer, we have set the clear color as black.
        GLES20.glClear( GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT );
 
        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES20.glGetAttribLocation( solidColorShaderProgram , "vPosition" );
 
        // Enable generic vertex attribute array
        GLES20.glEnableVertexAttribArray( mPositionHandle );
 
        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, 3,
                                     GLES20.GL_FLOAT, false,
                                     0, vertexBuffer);
 
        // Get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation( solidColorShaderProgram , "uMVPMatrix" );
 
        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);
 
        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
 
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
 
    }
    
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
 
        // We need to know the current width and height.
        mScreenWidth = width;
        mScreenHeight = height;
 
        // Redo the Viewport, making it fullscreen.
        GLES20.glViewport(0, 0, (int)mScreenWidth, (int)mScreenHeight);
 
        // Clear our matrices
        for(int i=0;i<16;i++)
        {
            projectionMatrix[i] = 0.0f;
            viewMatrix[i] = 0.0f;
            viewProjectionMatrix[i] = 0.0f;
        }
 
        // Setup our screen width and height for normal sprite translation.
        Matrix.orthoM( projectionMatrix , 0 , 0f , mScreenWidth , 0.0f , mScreenHeight , 0 , 50 );
 
        // Set the camera position (View matrix)
        Matrix.setLookAtM( viewMatrix, 0 , 0f , 0f , 1f , 0f , 0f , 0f , 0f , 1.0f , 0.0f );
 
        // Calculate the projection and view transformation
        Matrix.multiplyMM( viewProjectionMatrix , 0 , projectionMatrix , 0 , viewMatrix , 0 );
 
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
 
        // Create the triangle
        SetupTriangle();
 
        // Set the clear color to black
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1);
 
        // Create the shaders
        int vertexShader = loadShader( GLES20.GL_VERTEX_SHADER, solidColorVertexShader );
        int fragmentShader = loadShader( GLES20.GL_FRAGMENT_SHADER, solidColorFragmentShader );
 
        solidColorShaderProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader( solidColorShaderProgram, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader( solidColorShaderProgram, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram( solidColorShaderProgram);                  // creates OpenGL ES program executables
 
        // Set our shader programm
        GLES20.glUseProgram( solidColorShaderProgram );
    } 
    
    public void SetupTriangle()
    {
        // We have create the vertices of our view.
        vertices = new float[]
                   {10.0f, 200f, 0.0f,
                    10.0f, 100f, 0.0f,
                    100f, 100f, 0.0f,
                   };
 
        indices = new short[] {0, 1, 2}; // loop in the android official tutorial opengles why different order.
 
        // The vertex buffer.
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
 
        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(indices);
        drawListBuffer.position(0);
 
    }
    public static int loadShader(int type, String shaderCode){
    	 
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
 
        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
 
        // return the shader
        return shader;
    }
}
