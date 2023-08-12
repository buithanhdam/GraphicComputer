package polygon;

import java.awt.Color;
import java.awt.Frame;
import java.io.IOException;
import java.lang.System;

import jgl.GL;
import jgl.GLCanvas;


public class triangle extends GLCanvas{
	public void display () {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		myGL.glColor3f(1.0f, 1.0f, 1.0f);
		myGL.glBegin(GL.GL_TRIANGLE_STRIP);
		myGL.glColor3f(0.0f, 1.0f, 1.0f);
		myGL.glVertex3d (0.0f, 0.0f, 0.0f);
		myGL.glColor3f(0.0f, 1.0f, 1.0f);
	    myGL.glVertex3d (0.0f, 0.0f, 0.5f);
	    myGL.glColor3f(0.0f, 1.0f, 1.0f);
	    myGL.glVertex3d (0.75f, 0.0f, 0.5f);

	    myGL.glEnd();
		myGL.glFlush();
		
		
		
	}
	public void keyboard(char key , int x , int y) {
		switch (key) {
		case 27: 
			System.exit(0);
			default:
			break;
		}
		
	}
	public void myInit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f,0.0f);
//		myGL.glShadeModel(GL.GL_FLAT);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		
		
	}
	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myInit();
		myUT.glutDisplayFunc("display");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
		
	}
	public static void main(String[] args) throws IOException {
		Frame frame = new Frame();
		frame.setSize(510,521);
		triangle tri = new triangle();
		tri.init();
		frame.add(tri);
		frame.setVisible(true);
		
	}

}
