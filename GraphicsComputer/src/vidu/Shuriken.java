package vidu;

import java.awt.Frame;
import java.util.Random;

import ex1.triangle_fan;
import jgl.GL;
import jgl.GLCanvas;

public class Shuriken extends GLCanvas{
	double R = 0.18;
	double X = 0.5;
	double Y = 0.5;
	int a = 10;
	Random random = new Random();
	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT); // clear buffer ( color buffer) or (depth buffer)
		myGL.glPushMatrix();
		myUT.glutWireSphere(0.2, 20, 16);
//		myGL.glRotatef(arg0, arg1, arg2, arg3);
		
		for (double i = 0; i <= 2*Math.PI; i+= Math.PI/2) {
			myGL.glBegin(GL.GL_TRIANGLE_FAN);
			myGL.glColor3d(random.nextDouble(), random.nextDouble(), random.nextDouble());
			myGL.glVertex3d(X+R*2*Math.cos(i), Y+R*2*Math.sin(i), 0.0f);
			for (double j = i - Math.PI/3; j <= i + Math.PI/3; j+= Math.PI/1800) {
				myGL.glVertex3d(X+R*Math.cos(j), X+R*Math.sin(j), 0);
				
			}
			myGL.glEnd();
			myGL.glFlush();
			
			
			
		}
		myGL.glPopMatrix();
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
	triangle_fan mainCir = new triangle_fan();
	mainCir.myInit();
	frame.add(mainCir);
	frame.setVisible(true);
	
	
}
}

