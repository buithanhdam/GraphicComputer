package ex1;

import java.awt.Color;
import java.awt.Frame;
import java.util.Random;

import jgl.GL;
import jgl.GLCanvas;

public class DoThiSinx  extends GLCanvas{

	double a = 0.25;
	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		//ve truc ox
		myGL.glBegin(GL.GL_LINES);
		myGL.glColor3d(0.0f, 1.0f, 0.0f);
		myGL.glVertex3d(-1.0f, 0.0f, 0.0f);
		myGL.glVertex3d(2.0f, 0.0f, 0.0f);
		myGL.glEnd();
		//ve truc oy
		myGL.glBegin(GL.GL_LINES);
		myGL.glColor3d(0.0f, 1.0f, 0.0f);
		myGL.glVertex3d(0.0f, -1.0f, 0.0f);
		myGL.glVertex3d(0.0f, 2.0f, 0.0f);
		myGL.glEnd();
		//ve parabol pt bac 2 SinX
		myGL.glBegin(GL.GL_LINE_STRIP);
		for (double i = 0,j = 0;  i <= Math.PI*2 &&j <= 2; i+= Math.PI/10 , j+=0.0) {
			myGL.glColor3d(1.0f,0.0f,0.0f);
			myGL.glVertex3d(j, Math.sin(i), 0.0f);
			
		}
		myGL.glEnd();
		myGL.glBegin(GL.GL_LINE_STRIP);
		for (double i = 0,j = 0; i >=- Math.PI && j >= -1; i-= Math.PI/10 , j-=0.1) {
			myGL.glColor3d(1.0f,0.0f,0.0f);
			myGL.glVertex3d(j, Math.sin(i), 0.0f);
			
		}
	
		myGL.glEnd();
		myGL.glFlush();
	}
	public void init() {
		myGL.glClearColor(0.0f,0.0f, 0.0f, 0.0f);
		
		// matrixMode Flat or Projection
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(-1.0, 2.0, -1.0, 2.0, 0.0, 1.0);
		
		
		
	}
public void keyboard(char key , int x, int y) {
	switch (key) {
	case 27: 
		System.exit(0);
		
//	case 'w' : if(a<100) a++ ;
//		myUT.glutPostRedisplay();
//		break;
//	case 's' :if(a>0) a-- ;
//		myUT.glutPostRedisplay();
//		break;
		
	default:
		break;
	}
	
}

public void myInit() {
	myUT.glutInitWindowSize(700, 500);
	myUT.glutInitWindowPosition(0, 0);
	myUT.glutCreateWindow(this);
	init();
	myUT.glutDisplayFunc("display");
	myUT.glutKeyboardFunc("keyboard");
	myUT.glutMainLoop();
}
public static void main(String[] args) {
	Frame frame = new Frame();
	frame.setSize(721,721);
	DoThiSinx mainsinx  = new DoThiSinx();
	mainsinx.myInit();
	frame.add(mainsinx);
	frame.setVisible(true);
	
	
}
}
