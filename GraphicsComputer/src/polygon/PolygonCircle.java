package polygon;

import java.awt.Frame;

import jgl.GL;
import jgl.GLCanvas;

public class PolygonCircle extends GLCanvas {
	double R = 0.25;
	double X = 0.5;
	double Y = 0.5;
	int a = 10;
	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT); // clear buffer ( color buffer) or (depth buffer)
		myGL.glColor3f (1.0f, 0.0f, 0.0f);  // red
		
		
		myGL.glBegin(GL.GL_POLYGON);
		for (double i=0; i<a; i += (Math.PI*2/a) ){
			myGL.glVertex3d(X+R*Math.cos(i), Y+R*Math.sin(i), 0.0f);
			myGL.glColor3f(1.0f, 0.0f, 0.0f);
		}
		myGL.glEnd(); 
		
		
		myGL.glFlush(); // export to buffer
		
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
	PolygonCircle mainCir = new PolygonCircle();
	mainCir.myInit();
	frame.add(mainCir);
	frame.setVisible(true);
	
	
}
	

}
