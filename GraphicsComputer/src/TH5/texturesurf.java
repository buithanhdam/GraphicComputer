package TH5;

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

public class texturesurf extends GLCanvas {

    private static final float ctrlpoints [][][] = {
    			{{-1.5f, -1.5f,  4.0f},
			 {-0.5f, -1.5f,  2.0f},
			 { 0.5f, -1.5f, -1.0f},
			 { 1.5f, -1.5f,  2.0f}},
    			{{-1.5f, -0.5f,  1.0f},
			 {-0.5f, -0.5f,  3.0f},
			 { 0.5f, -0.5f,  0.0f},
			 { 1.5f, -0.5f, -1.0f}},
    			{{-1.5f,  0.5f,  4.0f},
			 {-0.5f,  0.5f,  0.0f},
			 { 0.5f,  0.5f,  3.0f},
			 { 1.5f,  0.5f,  4.0f}},
    			{{-1.5f,  1.5f, -2.0f},
			 {-0.5f,  1.5f, -2.0f},
			 { 0.5f,  1.5f,  0.0f},
			 { 1.5f,  1.5f, -1.0f}}};

    private static final float texpts [][][] = {
			{{ 0.0f,  0.0f},
			 { 0.0f,  1.0f}},
			{{ 1.0f,  0.0f},
			 { 1.0f,  1.0f}}};

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
//	myGL.glColor3f (1.0f, 1.0f, 1.0f);

//	myGL.glBegin(GL.GL_POLYGON);
//	myGL.glVertex3f(-63.0f, -63.0f, 0.0f);
//	myGL.glVertex3f(63.0f, -63.0f, 0.0f);
//	myGL.glVertex3f(63.0f, 63.0f, 0.0f);
//	myGL.glVertex3f(-63.0f, 63.0f, 0.0f);
//	myGL.glEnd();
	myGL.glPushMatrix();
	myGL.glEvalMesh2 (GL.GL_FILL, 0, 20, 0, 20);
	myGL.glPopMatrix();
	
	myUT.glutSolidCube(1);
	myGL.glFlush ();
    }

    private static final int imageWidth =64;
    private static final int imageHeight = 64;
    private byte image [][][] = new byte [imageHeight][imageWidth][3];

    private void makeImage () {
        int i, j;
	  File bmpFile = new File("C:\\Users\\MyPC\\Downloads\\test.bmp");
  	try {
  		BufferedImage image1 = ImageIO.read(bmpFile);
  			for (i = 0; i < imageWidth; i++) {
  				for (j = 0; j < imageHeight; j++) {
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
	myGL.glMap2f (GL.GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
		      0.0f, 1.0f, 12, 4, ctrlpoints);
	myGL.glMap2f (GL.GL_MAP2_TEXTURE_COORD_2, 0.0f, 1.0f, 2, 2,
		      0.0f, 1.0f, 4, 2, texpts);
	myGL.glEnable (GL.GL_MAP2_TEXTURE_COORD_2);
	myGL.glEnable (GL.GL_MAP2_VERTEX_3);
	myGL.glMapGrid2f (20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
	makeImage ();
	myGL.glTexEnvf (GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
	myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
        myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
        myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
        myGL.glTexParameterf (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
	myGL.glTexImage2D (GL.GL_TEXTURE_2D, 0, 3, imageWidth, imageHeight,
			   0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, image);
	myGL.glEnable (GL.GL_TEXTURE_2D);
	myGL.glEnable (GL.GL_DEPTH_TEST);
	myGL.glEnable (GL.GL_NORMALIZE);
	myGL.glShadeModel (GL.GL_FLAT);
    }

    public void myReshape (int w, int h) {
        myGL.glViewport (0, 0, w, h);
        myGL.glMatrixMode (GL.GL_PROJECTION);
        myGL.glLoadIdentity ();
	if (w <= h) {
	    myGL.glOrtho (-4.0f, 4.0f,
	    		  -4.0f *(float)h/(float)w,
			   4.0f *(float)h/(float)w,
			  -4.0f, 4.0f);
	} else {
	    myGL.glOrtho (-4.0f *(float)w/(float)h,
	    		   4.0f *(float)w/(float)h,
			  -4.0f, 4.0f,
			  -4.0f, 4.0f);
	}
        myGL.glMatrixMode (GL.GL_MODELVIEW);
        myGL.glLoadIdentity ();
        myGL.glScalef(2.0f, 2.0f, 0.0f);
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
	myUT.glutInitWindowSize (600, 600);
	myUT.glutInitWindowPosition (0, 0);
	myUT.glutCreateWindow (this);
	myinit ();
	myUT.glutReshapeFunc ("myReshape");
	myUT.glutDisplayFunc ("display");
	myUT.glutKeyboardFunc ("keyboard");
	myUT.glutMainLoop ();
    }

    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (608, 627);
	texturesurf mainCanvas = new texturesurf ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}