package nopbai;

	import java.awt.Frame;
	import java.io.IOException;
	import java.lang.String;
	import java.lang.System;
	import java.util.ArrayList;
	import java.util.List;

	import jgl.GL;
	import jgl.GLUT;
	import jgl.GLCanvas;

	// "double" is a reserved word in Java
	public class Bt1b extends GLCanvas {
		float ballX = 0.0f;
		float ballY = 0.0f;
		float ballScale =1;
		float speed = 0.02f;
		float cubeX = 0.0f;
		float cubeY = 0.0f;
		float BallR = 0.2f;
		float X;
	 private int spin = 0;
		 
	    public void display () {
	    	float no_mat[]={0.0f,0.0f,0.0f,1.0f};
	    	float mat_ambient[]={0.7f,0.7f,0.7f,1.0f};
	    	float mat_diffuse[]={0.1f,0.5f,0.8f,1.0f};
	    	float no_shininess[]={0.0f};
	    
	    	
	    	float ambient [] = {0.0f,0.0f,0.0f,1.0f};
	    	float diffuse [] = {1.0f,1.0f,1.0f,1.0f};
	    	float position [] = {0.0f,3.0f,2.0f,0.0f};
	    	float lmodel_ambient [] = {0.4f,0.4f,0.4f,1.0f};
	    	float local_view [] = {0.0f};
	    	
	  
	    		
		myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		myGL.glColor3f(1.0f, 1.0f, 0.0f);
		myGL.glPushMatrix();
		
		myGL.glTranslatef( ballX,ballY, 0.0f);
		myGL.glRotated ((double)spin, 1.0, 0.0, 0.0);
		myGL.glScaled(1, ballScale, 1);
		myGL.glLightfv (GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
		myGL.glLightfv (GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse);
		myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, position);
		myGL.glLightModelfv (GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
		myGL.glLightModelfv (GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);
		
		myGL.glEnable (GL.GL_LIGHTING);
		myGL.glEnable (GL.GL_LIGHT0);	
		myGL.glEnable (GL.GL_DEPTH_TEST);
		
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, no_shininess);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, no_mat);
			myUT.glutSolidSphere(BallR, 20, 20);
		
		myGL.glPopMatrix ();
		myGL.glPushMatrix();
		myGL.glTranslatef(0,-1.0f,0);
		myGL.glRotated (15, 1.0, -1.0, 0.0);
		myGL.glTranslated (cubeX, cubeY, 0.0);
		myGL.glDisable (GL.GL_LIGHTING);
		myGL.glColor3f (0.0f, 1.0f, 1.0f);
		myUT.glutWireCube(1.0);
	
		myGL.glPopMatrix ();
		myGL.glFlush ();
	    }
	      
	    public void rollingDisplay(int value) {
	    	 // 	boolean  b = true;
	    		X+=speed;
	    	  	ballY =-2f*X*X+X*4f;
	    		ballX =X;
	    		spin = (spin + 30) % 360;
	    	  	if((ballY < (cubeY + -0.5f)&& (ballX>=(-0.5+cubeX) && ballX<=(0.5+cubeX)))) {
	    	  		speed*=-1;
	    	  		ballScale = 0.65f;
	    	  		
	    	  	} else {
	    	  	ballScale = 1;
	    	  	}
	    	  	
		myUT.glutPostRedisplay ();
		myUT.glutTimerFunc(20, "rollingDisplay", 1);
	    }
	    
	    private void myinit () {
		myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel (GL.GL_FLAT);
	    }
	   
	    public void myReshape (int w, int h) {
	    myGL.glViewport (0, 0, w, h);
		myGL.glMatrixMode (GL.GL_PROJECTION);
		myGL.glLoadIdentity ();
		myGL.glOrtho (-2.5f, 4.0f, -2.5f, 4.0f, -2.5f,  1.0f);
		myGL.glMatrixMode (GL.GL_MODELVIEW);
		myGL.glLoadIdentity ();
		
	    }

	    /* ARGSUSED2 */
	    public void mouse (int button, int state, int x, int y) {
		if (button == GLUT.GLUT_LEFT_BUTTON) {
		    if (state == GLUT.GLUT_DOWN) { 	
		    	myUT.glutTimerFunc(20, "rollingDisplay", 1);
		    }
		} else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
		    if (state == GLUT.GLUT_DOWN) {
			myUT.glutIdleFunc (null);
		    }
		}
	    }

	    public void keyboard (char key, int x, int y) {
		switch (key) {
		case 'a':
			cubeX-=2.0;
			myUT.glutPostRedisplay ();
		break;
		case 'd':
			cubeX+=2.0;
			myUT.glutPostRedisplay ();
		break;
		    case 27:
			System.exit(0);
		    default:
			break;
		    }
		 
	    }

	    /* 
	     *  Request double buffer display mode.
	     *  Register mouse input callback functions
	     */
	    public void init () {
		myUT.glutInitWindowSize (500, 500);
		myUT.glutInitWindowPosition (0, 0);
		myUT.glutCreateWindow (this);
		myinit ();
		myUT.glutDisplayFunc ("display");
		myUT.glutReshapeFunc ("myReshape");
		myUT.glutMouseFunc ("mouse");
		myUT.glutKeyboardFunc ("keyboard");
		myUT.glutMainLoop ();
	    }

	    static public void main (String args[]) throws IOException {
		Frame mainFrame = new Frame ();
		mainFrame.setSize (508, 527);
		Bt1b mainCanvas = new Bt1b ();
		mainCanvas.init();
		mainFrame.add (mainCanvas);
		mainFrame.setVisible (true);
	    }

	}


