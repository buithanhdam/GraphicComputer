package testGC;

import java.awt.Frame;

import jgl.GL;
import jgl.GLCanvas;

public class square extends GLCanvas{
	
	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glColor3f(1.0f, 1.0f, 1.0f);
		
	}
	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 27: // key 27 = ESC (ASCII)
			System.exit(0);
		    default:
			break;
		}
		
	}

	public void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
		
		
	}
	
	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc(null);
		myUT.glutKeyboardFunc(null);
		myUT.glutMainLoop();
	}
	

	public static void main(String[] args) {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		square mainCanvas = new square();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}
}

