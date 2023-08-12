
package nopbai;

import java.awt.Frame;
import java.io.IOException;
import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;

public class Bt1d extends GLCanvas{

private double b=40;
float x;
float y;
float angle = 0.0f;

float angleTri = 0.0f;
float triangleX1 =(float)(50.0f*Math.sin(angleTri));
float  triangleY1 = (float)(50.0f*Math.cos(angleTri));
float  triangleZ1 = -50.0f;

float triangleX2 = (float)(50.0f*Math.sin(angleTri+1.0f));
float  triangleY2 =(float)(50.0f*Math.cos(angleTri+1.0f));
float  triangleZ2 = -45.0f;

float triangleX3 = (float)(50.0f*Math.sin(angleTri+3.0f));
float  triangleY3 = (float)(50.0f*Math.cos(angleTri+3.0f));
float  triangleZ3 = -35.0f;



float Z;
int i = 0;



public void display() {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	myGL.glColor3f (1.0f, 1.0f, 1.0f);
	myGL.glLoadIdentity();

	myGL.glRotated(b, 1, 0, 0);
	myGL.glRotated(-30, 0, 1, 0);
	//myGL.glRotated(180, 0, 1, 0);
	
	myGL.glBegin (GL.GL_LINE_STRIP);
	
	 Z = - 50f;
	for(float angle = 0.0f; angle <= (2.0f*Math.PI)*3.0f; angle +=0.1f) {
		x = (float)(50.0f*Math.sin(angle));
		y = (float)(50.0f*Math.cos(angle));
		myGL.glVertex3d(x, y, Z);
		Z+=0.5f;
	}
	
	myGL.glEnd();
	//myGL.glTranslated(triangleX1, triangleY1, triangleZ1);
	
	myGL.glBegin (GL.GL_LINE_LOOP);
	myGL.glColor3f (0.0f, 1.0f, 1.0f);
	myGL.glVertex3d(triangleX1,triangleY1,triangleZ1);
	myGL.glVertex3d(triangleX2,triangleY2,triangleZ2);
	myGL.glVertex3d(triangleX3,triangleY3,triangleZ3);
	myGL.glEnd();
	myGL.glFlush();
}

private void myinit () {
	/* select clearing color */
	myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
	/* initialize viewing values */
	myGL.glMatrixMode (GL.GL_PROJECTION);
	myGL.glLoadIdentity ();	
	myGL.glOrtho (-100.0f, 100.0f, -100.0f, 100.0f, 0.0f, 0.0f);
	myGL.glMatrixMode(GL.GL_MODELVIEW);
	myGL.glLoadIdentity();
	}


	public void init () {
		myUT.glutInitWindowSize (500, 500);
		myUT.glutInitWindowPosition (0, 0);
		myUT.glutCreateWindow (this);
		myinit ();
		myUT.glutDisplayFunc ("display");
		myUT.glutMouseFunc ("mouse");
		myUT.glutKeyboardFunc ("keyboard");
		myUT.glutMainLoop ();		
	    }
	
	 public void keyboard (char key, int x, int y) {
			switch (key) {
			
			    case 27:
				System.exit(0);
			    default:
				break;			   
			}
	 }
	 
	 public void rollingDisplay(int value) {

		  if( angleTri <= (2.0f*Math.PI)*3.0f) { 
			   triangleX1 =(float)(50.0f*Math.sin(angleTri));
			    triangleY1 = (float)(50.0f*Math.cos(angleTri));
			

			  triangleX2 = (float)(50.0f*Math.sin(angleTri+1.0f));
			    triangleY2 =(float)(50.0f*Math.cos(angleTri+1.0f));
			

			triangleX3 = (float)(50.0f*Math.sin(angleTri+3.0f));
			  triangleY3 = (float)(50.0f*Math.cos(angleTri+3.0f));

			  angleTri+=0.1f; 
		  
		  triangleZ1+=0.5f; 
		  triangleZ2+=0.5f; 
		  triangleZ3+=0.5f; 
		  } 
		  else {
			  triangleZ1 = - 50f; 
			  triangleZ2 = - 45f; 
			  triangleZ3 = - 35f; 
			  angleTri = 0.0f;
		  
		  triangleX1 =(float)(50.0f*Math.sin(angleTri));
		    triangleY1 = (float)(50.0f*Math.cos(angleTri));
		
		  triangleX2 = (float)(50.0f*Math.sin(angleTri+1.0f));
		    triangleY2 =(float)(50.0f*Math.cos(angleTri+1.0f));
		    
		triangleX3 = (float)(50.0f*Math.sin(angleTri+3.0f));
		  triangleY3 = (float)(50.0f*Math.cos(angleTri+3.0f));
		 
		  
		  }
			     		
	myUT.glutPostRedisplay ();
	myUT.glutTimerFunc(20, "rollingDisplay", 1);
    }
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
	 static public void main (String args[]) throws IOException {
			Frame mainFrame = new Frame ();
			mainFrame.setSize (508, 527);
			Bt1d mainCanvas = new Bt1d() ;
			mainCanvas.init();
			mainFrame.add (mainCanvas);
			mainFrame.setVisible (true);
		   }
}
