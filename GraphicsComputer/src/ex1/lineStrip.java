package ex1;
import java.awt.Color;
import java.awt.Frame;
import java.util.Random;

import jgl.GL;
import jgl.GLCanvas;
public class lineStrip extends GLCanvas{
	double R = 0.25;
	double X = 0.5;
	double Y = 0.5;
	int a = 10;
	Random random = new Random();
	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT); // clear buffer ( color buffer) or (depth buffer)


			myGL.glBegin(GL.GL_LINE_STRIP);
			for (double i = 0; i < a*2; i+=(Math.PI/a)) {
				myGL.glColor3d(1.0f, 0.0f, 0.0f);
				myGL.glVertex3d(X+R*Math.cos(i),Y+ R*Math.sin(i),0.0f);
				if(a/2<i && i<(a/2)+1) {
					Y = Y + 0.015;
				}
				if(a+1<i && i<a+2) {
					Y = Y + 0.015;
				}
				if(a*1.5+2 < i && i < (a*1.5) +3) {
					Y=Y+0.015;
				}
				
			}
		
			myGL.glEnd();
			myGL.glFlush();
			
			
			
		
		
		
		
	}
	public void init() {
		myGL.glClearColor(0.0f,0.0f, 0.0f, 0.0f);
		
		// matrixMode Flat or Projection
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(0.0, 1.0, 0.0, 1.0, 0.0, 1.0);
		
		
		
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
	myUT.glutInitWindowSize(500, 500);
	myUT.glutInitWindowPosition(0, 0);
	myUT.glutCreateWindow(this);
	init();
	myUT.glutDisplayFunc("display");
	myUT.glutKeyboardFunc("keyboard");
	myUT.glutMainLoop();
}
public static void main(String[] args) {
	Frame frame = new Frame();
	frame.setSize(521,518);
	lineStrip mainline = new lineStrip();
	mainline.myInit();
	frame.add(mainline);
	frame.setVisible(true);
	
	
}

}
