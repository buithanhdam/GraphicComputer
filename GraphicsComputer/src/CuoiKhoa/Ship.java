package CuoiKhoa;

import java.awt.Frame;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;

public class Ship extends GLCanvas {

    private static final float ctrlpoints [][][] = {
    		{	{-2.0f, 0.0f, -2.0f},
				{0.0f,  4.0f, -2.0f},
				{ 2.0f, 4.0f, -2.0f},
				{ 4.0f,  0.0f, -2.0f}},
			
			{{-2.0f, 2.0f, 0.0f},
					{0.0f,  2.0f, 0.0f},
					{ 4.0f, 2.0f, 0.0f},
					{ 3.0f,  2.0f, 0.0f}},
			
			{{-2.0f, 2.0f, 0.0f},
						{0.0f,  0.0f, 0.0f},
						{ 4.0f, 2.0f, 0.0f},
						{ 3.0f,  2.0f, 0.0f}},
			
			{{-2.0f, 0.0f, -2.0f},
							{0.0f,  -2.0f, -2.0f},
							{ 2.0f, -2.0f, -2.0f},
							{ 4.0f,  0.0f, -2.0f}}

			 
    };
    boolean check = true;
    float x = -5.0f;

    private void initlights () {
    	float ambient[] = {0.2f, 0.2f, 0.2f, 1.0f};
    	float position[] = {0.0f, 0.0f, 2.0f, 1.0f};
    	float mat_diffuse[] = {0.6f, 0.6f, 0.6f, 1.0f};
    	float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
            float mat_shininess[] = {50.0f};

        myGL.glEnable (GL.GL_LIGHTING);
        myGL.glEnable (GL.GL_LIGHT0);

        myGL.glLightfv (GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
        myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, position);

        myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
        myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
        myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, mat_shininess);
    }

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	myGL.glTranslatef(0.0f, 0.0f, 0.0f);
	myGL.glPushMatrix ();
	myGL.glTranslatef(x, x, 0.0f);
	myGL.glRotatef (200.0f, 0.0f, 1.0f, 1.0f);
	myGL.glRotatef (170.0f, 1.0f, 0.0f, 0.0f);
	
	myGL.glEvalMesh2 (GL.GL_FILL, 0, 20, 0, 20);
	myGL.glPopMatrix ();
	myGL.glFlush ();
    }

    private void myinit () {
	myGL.glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
	myGL.glEnable (GL.GL_DEPTH_TEST);
	myGL.glMap2f (GL.GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
		      0.0f, 1.0f, 12, 4, ctrlpoints);
	myGL.glEnable (GL.GL_MAP2_VERTEX_3);
	myGL.glEnable (GL.GL_AUTO_NORMAL);
	myGL.glEnable (GL.GL_NORMALIZE);
	myGL.glMapGrid2f (20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
	initlights ();		/* for lighted version only */
    }

    public void myReshape (int w, int h) {
        myGL.glViewport (0, 0, w, h);
        myGL.glMatrixMode (GL.GL_PROJECTION);
        myGL.glLoadIdentity ();
	if (w <= h) {
	    myGL.glOrtho (-8.0f, 8.0f,
	    		  -8.0f *(float)h/(float)w,
			   8.0f *(float)h/(float)w,
			  -8.0f, 8.0f);
	} else {
	    myGL.glOrtho (-8.0f *(float)w/(float)h,
	    		   8.0f *(float)w/(float)h,
			  -8.0f, 8.0f,
			  -8.0f, 8.0f);
	}
        myGL.glMatrixMode (GL.GL_MODELVIEW);
        myGL.glLoadIdentity ();
    }
    public void runDisplay(int value) {
    	
    	if(x <= 6  ) {
    		x += 0.25;
    		myUT.glutPostRedisplay();
    	}
		myUT.glutTimerFunc(500, "runDisplay", 5);
    }
    

    public void mouse (int button, int state, int x, int y) {
    	if(check) {
    		
    		if (button == GLUT.GLUT_LEFT_BUTTON) {
    			if (state == GLUT.GLUT_DOWN) {
//    	    myUT.glutGet
//    		myUT.glutIdleFunc ("spinDisplay");
    				myUT.glutTimerFunc(20, "runDisplay", 1);
    				check = false;
    			}
    		} 
    	}
    	else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
    	    if (state == GLUT.GLUT_DOWN) {
    		myUT.glutIdleFunc (null);
    	    }
    	}
        }

    public void keyboard (char key, int x, int y) {
	switch (key) {
	    case 27:
		System.exit(0);
	    default:
		break;
	}
    }

    public void init () {
	myUT.glutInitWindowSize (500, 500);
	myUT.glutInitWindowPosition (0, 0);
	myUT.glutCreateWindow (this);
	myinit ();
	myUT.glutReshapeFunc ("myReshape");
	myUT.glutDisplayFunc ("display");
	myUT.glutMouseFunc ("mouse");
	myUT.glutKeyboardFunc ("keyboard");
	myUT.glutMainLoop ();
    }

    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (508, 527);
	Ship mainCanvas = new Ship ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}