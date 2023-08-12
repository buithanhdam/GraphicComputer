package Robotto;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import javax.swing.JButton;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;

public class robot extends GLCanvas {
    private static final int iWidth = 16;
    private static final int iHeight = 16;
    private static final int iDepth = 16;

    private byte image [][][][] =
    		   new byte [iDepth][iHeight][iWidth][3];
    private int texName [] = new int [1];

    private static int shoulder = 0, elbow = 0 , upperBody1 = 0,
    		upperBody2 = 0, leftHand = 0 , rightHand=0,
    		leftFoot = 0,rightFoot= 0 , upperBody3 =0;
    int count = 5;
    boolean check = true;
    /*  Create a 16x16x16x3 array with different color values in
     *  each array element [r, g, b].  Values range from 0 to 255.
     */
    private void makeImage () {
    	int s, t, r;

	for (s = 0; s < 16; s++) {
	    for (t = 0; t < 16; t++) {
		for (r = 0; r < 16; r++) {
		    image [r][s][t][0] = (byte)(s * 17);
		    image [r][s][t][1] = (byte)(t * 17);
		    image [r][s][t][2] = (byte)(r * 17);
		}
	    }
	}
    }


    private void myinit () {
    	myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
	myGL.glShadeModel (GL.GL_FLAT);
	makeImage ();
//	myGL.glPixelStorei (GL.GL_UNPACK_ALIGNMENT, 1);
//
//	myGL.glGenTextures (1, texName);
//	myGL.glBindTexture (GL.GL_TEXTURE_3D, texName [0]);
//
//	myGL.glTexParameterf (GL.GL_TEXTURE_3D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
//	myGL.glTexParameterf (GL.GL_TEXTURE_3D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
//	myGL.glTexParameterf (GL.GL_TEXTURE_3D, GL.GL_TEXTURE_WRAP_R, GL.GL_CLAMP);
//
//	myGL.glTexParameterf (GL.GL_TEXTURE_3D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
//	myGL.glTexParameterf (GL.GL_TEXTURE_3D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
//	myGL.glTexImage3D (GL.GL_TEXTURE_3D, 0, GL.GL_RGB, iWidth, iHeight,
//	    iDepth, 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, image);
//	myGL.glEnable (GL.GL_TEXTURE_3D);
    }

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT);
	myGL.glPushMatrix ();
		myGL.glTranslatef (0.0f, 0.0f, 0.0f);
	    myGL.glRotatef ((float)upperBody1, 1.0f, 0.0f, 0.0f);
	    myGL.glRotatef ((float)upperBody2, 0.0f, 1.0f, 0.0f);
	    myGL.glRotatef ((float)upperBody3, 0.0f, 0.0f, 1.0f);
		//upper body
		myGL.glPushMatrix ();
		
		    myGL.glTranslatef (0.0f, 0.0f, 0.0f);
//		    myGL.glRotatef ((float)upperBody1, 1.0f, 0.0f, 0.0f);
//		    myGL.glRotatef ((float)upperBody2, 0.0f, 1.0f, 0.0f);
		    myGL.glPushMatrix ();

			myGL.glScalef (2.0f, 2.5f, 1.0f);
			myUT.glutWireCube (1.0);
		    myGL.glPopMatrix ();
		    
		    
		  //head robot
		    myGL.glPushMatrix ();
		    
			    myGL.glTranslatef (0.0f, 1.25f, 0.0f);
//			    myGL.glRotatef ((float)leftHand, 1.0f, 0.0f, 0.0f);
			    myGL.glTranslatef (0.0f, 0.5f, 0.0f);
			    
			    myGL.glPushMatrix ();
					myGL.glScalef (1.5f, 1.0f, 1.0f);
					myUT.glutWireCube (1.0);
			    myGL.glPopMatrix ();
			    
		    myGL.glPopMatrix ();
		    //end head robot
		    
		    //leftHand robot
		    myGL.glPushMatrix ();
		    
			    myGL.glTranslatef (-1.0f, 1.0f, 0.0f);
			    myGL.glRotatef ((float)leftHand, 1.0f, 0.0f, 0.0f);
			    myGL.glTranslatef (-0.4f, -1.0f, 0.0f);
			    
			    myGL.glPushMatrix ();
					myGL.glScalef (0.8f, 2.5f, 1.0f);
					myUT.glutWireCube (1.0);
			    myGL.glPopMatrix ();
			    
		    myGL.glPopMatrix ();
		    //end left hand
		    //right Hand robot
		    myGL.glPushMatrix ();
		    
			    myGL.glTranslatef (1.0f, 1.0f, 0.0f);
			    myGL.glRotatef ((float)rightHand, 1.0f, 0.0f, 0.0f);
			    myGL.glTranslatef (0.4f, -1.0f, 0.0f);
			    
			    myGL.glPushMatrix ();
					myGL.glScalef (0.8f, 2.5f, 1.0f);
					myUT.glutWireCube (1.0);
			    myGL.glPopMatrix ();
			    
		    myGL.glPopMatrix ();
		    //end right  hand
	    
	    myGL.glPopMatrix ();
	    //end upper body
	    
	    
	    //bottom body
	    myGL.glPushMatrix();
		    //left foot
		    myGL.glPushMatrix();
			    myGL.glTranslatef (-0.5f, -1.25f, 0.0f);
			    myGL.glRotatef ((float)leftFoot, 1.0f, 0.0f, 0.0f);
			    myGL.glTranslatef (0.0f, -1.25f, 0.0f);
				    myGL.glPushMatrix ();
					myGL.glScalef (0.8f, 2.5f, 1.0f);
					myUT.glutWireCube (1.0);
					myGL.glPopMatrix ();
		    myGL.glPopMatrix();
		    //end left foot
		    //right foot
		    myGL.glPushMatrix();
			    myGL.glTranslatef (0.5f, -1.25f, 0.0f);
			    myGL.glRotatef ((float)rightFoot, 1.0f, 0.0f, 0.0f);
			    myGL.glTranslatef (0.0f, -1.25f, 0.0f);
				    myGL.glPushMatrix ();
					myGL.glScalef (0.8f, 2.5f, 1.0f);
					myUT.glutWireCube (1.0);
					myGL.glPopMatrix ();
		    myGL.glPopMatrix();
		    //end right foot
		    
	    myGL.glPopMatrix();
	    
	    //end bottom
	    
	myGL.glPopMatrix ();
	myGL.glFlush ();
    }

    public void myReshape (int w, int h) {
	myGL.glViewport (0, 0, w, h);
	myGL.glMatrixMode (GL.GL_PROJECTION);
	myGL.glLoadIdentity ();
	myGLU.gluPerspective (80.0, (double)w/(double)h, 1.0, 40.0);
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
	 case 'm':
	    	upperBody3 = (upperBody3 + 5) % 360;
		myUT.glutPostRedisplay ();
		break;
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
    	if(check) {
    		
    		if (button == GLUT.GLUT_LEFT_BUTTON) {
    			if (state == GLUT.GLUT_DOWN) {
//    	    myUT.glutGet)
//    		myUT.glutIdleFunc ("spinDisplay");
    				myUT.glutTimerFunc(20, "runDisplay", 1);
    				check = false;
    			}
    		} 
    	}
//    	else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
//    	    if (state == GLUT.GLUT_DOWN) {
//    		myUT.glutIdleFunc (null);
//    	    }
//    	}
        }
    public void runAction() {
    	if(check) {
    		
    		myUT.glutTimerFunc(20, "runDisplay", 1);
    		check = false;
    		
    		
    	}
//    	else {
//    		System.out.println("stop");
//    		stopDisplay();
//    		check = true;
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
	robot mainCanvas = new robot ();
	mainCanvas.init();
	//tao panel button
	Panel panel = new Panel();
	Button button = new Button("run");
	button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mainCanvas.runAction();
			
		}
	});
	
	panel.add(button);
	//init robot
	
	
	mainFrame.add (mainCanvas);
	mainFrame.add(panel, BorderLayout.EAST);
	mainFrame.setVisible (true);
    }
   

}