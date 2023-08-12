package vidu;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import javax.imageio.ImageIO;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;

public class test extends GLCanvas {
    private static final int iWidth = 64;
    private static final int iHeight = 64;
    private static final int iDepth = 16;

    private byte image [][][] =
    		   new byte [iHeight][iWidth][3];
    private int texName [] = new int [1];

    private static int shoulder = 0, elbow = 0 , upperBody1 = 0,
    		upperBody2 = 0, leftHand = 0 , rightHand=0,
    		leftFoot = 0,rightFoot= 0;
    int count = 5;
    boolean check = true;
    /*  Create a 16x16x16x3 array with different color values in
     *  each array element [r, g, b].  Values range from 0 to 255.
     */

//    private void makeImage () {
//    	int s, t, r;
//
//	for (s = 0; s < 16; s++) {
//	    for (t = 0; t < 16; t++) {
//		for (r = 0; r < 16; r++) {
//		    image [r][s][t][0] = (byte)(s * 17);
//		    image [r][s][t][1] = (byte)(t * 17);
//		    image [r][s][t][2] = (byte)(r * 17);
//		}
//	    }
//	}
//    }
    private void makeImage () {

        int i, j;

        float ti, tj;

        File bmpFile = new File("C:\\Users\\MyPC\\Downloads\\background-dep-de-ghep-anh-2.bmp");
        	try {
        		BufferedImage image1 = ImageIO.read(bmpFile);
        			for (i = 0; i < iWidth; i++) {
        				for (j = 0; j < iHeight; j++) {
        					Color c = new Color(image1.getRGB(i, j));
        					image [j][i][0] = (byte) (c.getRed());
        					image [j][i][1] = (byte)(c.getGreen());
        					image [j][i][2] = (byte)(c.getBlue());
        					}
        			}

        	} catch (IOException e) {

        		// TODO Auto-generated catch block

        		e.printStackTrace();

        	}

   }


    private void myinit () {
//    	float ambient [] = { 1.0f, 1.0f, 0.0f, 1.0f }; //mau xung quanh
//    	float diffuse [] = {1.0f,1.0f,1.0f,1.0f}; 
//    	float specular [] ={ 1.0f, 0.0f, 0.0f, 1.0f };; // mau phan xa
//    	float position [] = {0.0f,3.0f,2.0f,0.0f}; 
//    	float lmodel_ambient [] = {0.4f,0.4f,0.4f,1.0f};
//    	float local_view [] = {0.0f};
//    	float mat_emission[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    	
    	myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
    	
//    	myGL.glDepthFunc (GL.GL_LESS);
    	myGL.glEnable (GL.GL_DEPTH_TEST);
    	myGL.glShadeModel (GL.GL_FLAT);
    	
//    	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
//    	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse);
//    	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, position);
//    	myGL.glLightModelfv (GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
//    	myGL.glLightModelfv (GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);
//    	//////////////////////////////////////////////
//    	myGL.glEnable (GL.GL_LIGHTING);
//    	myGL.glEnable (GL.GL_LIGHT0);

 
    	// textture 3d
  
    	makeImage ();
    	
    	myGL.glPixelStorei (GL.GL_UNPACK_ALIGNMENT, 1);

    	myGL.glGenTextures (1, texName);
    	myGL.glBindTexture (GL.GL_TEXTURE_2D, texName [0]);


    	myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
    	myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
    	myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
    	myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
    	myGL.glTexImage2D (GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, iWidth,
    		    iHeight, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image);
   

    	
    	myGL.glEnable (GL.GL_TEXTURE_2D);

    }

    public void display () {

    	
    	
    	
    	/////////////////////////
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	

	myGL.glFlush ();
    }

    public void myReshape (int w, int h) {
	myGL.glViewport (0, 0, w, h);
	myGL.glMatrixMode (GL.GL_PROJECTION);
	myGL.glLoadIdentity ();
	if (w <= (h * 2)) {
	    myGL.glOrtho (-3.0f,  3.0f,
			  -2.0f * ((float)h * 2) / (float)w,
			   2.0f * ((float)h * 2) / (float)w,
			 -5.0f, 5.0f);
	} else {
            myGL.glOrtho (-3.0f * (float)w / ((float)h * 2),
            		   3.0f * (float)w / ((float)h * 2),
			  -2.0f,  2.0f,
			 -5.0f, 5.0f);
	}
	myGLU.gluPerspective (50.0, (double)w/(double)h, 1.0, 40.0);
	myGL.glMatrixMode (GL.GL_MODELVIEW);
	myGL.glLoadIdentity ();
	myGL.glTranslatef (0.0f, 0.0f, -5.0f);
    }
    public void runDisplay(int value) {
    	if(leftFoot == 50 && rightHand == 50) {
    		count = -5 ;
    	}
    	if(leftFoot == -50 && rightHand == -50) {
    		count = 5 ;
    	}
    	if(leftFoot >= -50 && leftFoot <= 50) {
    		// 1
    		leftFoot += count;
    		rightHand += count;
    		//2
    		rightFoot -= count;
    		leftHand -= count;
    		//
    		myUT.glutPostRedisplay();
    		myUT.glutTimerFunc(20, "runDisplay", 1);
    	}
    }
    public void stopDisplay() {
    	int lf = leftFoot;
    	int rf = rightFoot;

    	if(leftFoot >= -50 && leftFoot <= 50) {
    		leftFoot = lf;
    		rightHand = lf;
    		
    		rightFoot = rf;
    		leftHand = rf;
    				
    	}
    }

    /* ARGSUSED1 */
    public void keyboard (char key, int x, int y) {
	switch (key) {
	    case 'w':
	    	upperBody1 = (upperBody1 + 5) % 360;
		myUT.glutPostRedisplay ();
		break;
	    case 'W':
	    	upperBody1 = (upperBody1 - 5) % 360;
		myUT.glutPostRedisplay ();
		break;
	    case 's':
	    	upperBody2 = (upperBody2 + 5) % 360;
		myUT.glutPostRedisplay ();
		break;
	    case 'S':
	    	upperBody2 = (upperBody2 - 5) % 360;
		myUT.glutPostRedisplay ();
		break;
		
	    case 'q':
	    	if(leftHand <= 85) {    		
	    		leftHand = (leftHand + 5) % 360;
	    		myUT.glutPostRedisplay ();
	    	}
			break;
		    case 'Q':
		    	if(leftHand >= -85) {
		    		
		    		leftHand = (leftHand - 5) % 360;
		    		myUT.glutPostRedisplay ();
		    	}
			break;
		    case 'e':
		    	if(rightHand <= 85) {    		
		    		rightHand = (rightHand + 5) % 360;
		    		myUT.glutPostRedisplay ();
		    	}
				break;
			    case 'E':
			    	if(rightHand >= -85) {
			    		
			    		rightHand = (rightHand - 5) % 360;
			    		myUT.glutPostRedisplay ();
			    	}
				break;
				
				
			    case 'a':
			    	if(leftFoot <= 85) {    		
			    		leftFoot = (leftFoot + 5) % 360;
			    		myUT.glutPostRedisplay ();
			    	}
					break;
				    case 'A':
				    	if(leftFoot >= -85) {
				    		
				    		leftFoot = (leftFoot - 5) % 360;
				    		myUT.glutPostRedisplay ();
				    	}
					break;
					
				    case 'd':
				    	if(rightFoot <= 85) {    		
				    		rightFoot = (rightFoot + 5) % 360;
				    		myUT.glutPostRedisplay ();
				    	}
						break;
					    case 'D':
					    	if(rightFoot >= -85) {
					    		
					    		rightFoot = (rightFoot - 5) % 360;
					    		myUT.glutPostRedisplay ();
					    	}
						break;		
	    case 27:
		System.exit(0);
	    default:
		break;
	}
    }
    public void mouse (int button, int state, int x, int y) {
//    	if(check) {
//    		
//    		if (button == GLUT.GLUT_LEFT_BUTTON) {
//    			if (state == GLUT.GLUT_DOWN) {
//    	    myUT.glutGet
//    		myUT.glutIdleFunc ("spinDisplay");
//    				myUT.glutTimerFunc(20, "runDisplay", 1);
//    				check = false;
//    			}
//    		} 
//    	}
//    	else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
//    	    if (state == GLUT.GLUT_DOWN) {
//    		myUT.glutIdleFunc (null);
//    	    }
//    	}
        }


    public void init () {
	myUT.glutInitWindowSize (400, 400);
	myUT.glutInitWindowPosition (0, 0);
	myUT.glutCreateWindow (this);
	myinit ();
	myUT.glutDisplayFunc ("display");
	myUT.glutReshapeFunc ("myReshape");
//	myUT.glutMouseFunc ("mouse");
	myUT.glutKeyboardFunc ("keyboard");
	myUT.glutMainLoop ();
    }

   
    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (508, 427);
	mainFrame.setLayout(new BorderLayout());
	//main robot
	test mainCanvas = new test ();
	mainCanvas.init();


	
	
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }
   

}