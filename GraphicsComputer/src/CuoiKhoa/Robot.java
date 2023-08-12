package CuoiKhoa;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import javax.imageio.ImageIO;

import jgl.GL;
import jgl.GLCanvas;

public class Robot extends GLCanvas {
	 private static final float ctrlpoints [][][] = {
	    		{	{-2.0f, 0.0f, -2.0f},
					{0.0f,  4.0f, -2.0f},
					{ 2.0f, 4.0f, -2.0f},
					{ 4.0f,  0.0f, -2.0f}},
				
				{{-2.0f, 2.0f, 0.0f},
						{0.0f,  2.0f, 0.0f},
						{ 2.0f, 2.0f, 0.0f},
						{ 3.0f,  2.0f, 0.0f}},
				
				{{-2.0f, 2.0f, 0.0f},
							{0.0f,  0.0f, 0.0f},
							{ 4.0f, 2.0f, 0.0f},
							{ 3.0f,  2.0f, 0.0f}},
				
				{{-2.0f, 0.0f, -2.0f},
								{0.0f,  -2.0f, -2.0f},
								{ 2.0f, -2.0f, -2.0f},
								{ 4.0f,  0.0f, -2.0f}}

				 
	    };

    private static int shoulder = 0, elbow = 0 , upperBody1 = 0,
    		upperBody2 = 0, leftHand = 0 , rightHand=0,
    		leftFoot = 0,rightFoot= 0;

	int angle1 = 0;
	float z = 0.0f;
	  int count = 10;
	    boolean check = true;
	    float zFrame = -20.0f;
	    float zRobot  =0.0f;
	    float countz = 0.0f;
	
 public  void display () {
		float no_mat[]={0.0f,0.0f,0.0f,1.0f};
    	float mat_ambient[]={1.0f,0.0f,0.0f,1.0f};
    	float mat_ambient_color[]={0.8f,0.8f,0.2f,1.0f};
//    	float mat_diffuse[]={0.1f,0.5f,0.8f,1.0f};
    	float mat_diffuse[]={0.0f,0.7f,0.5f,1.0f};
    	float body_diffuse[]={1.0f,0.0f,0.5f,1.0f};
    	float mat_specular[]={1.0f,1.0f,1.0f,1.0f};
    	float no_shininess[]={0.0f};
    	float low_shininess[]={5.0f};
    	float high_shininess[]={100.0f};
//    	float mat_emission[]={0.3f,0.2f,0.2f,0.0f};
    	float mat_emission[]={0.0f,0.5f,0.0f,0.0f};
    	float body_emission[]={0.5f,0.0f,0.0f,0.0f};
    	
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
     
     
   

     //////////////////////////
	
	myGL.glDisable(GL.GL_LIGHT0);
	myGL.glDisable(GL.GL_LIGHTING);
	
	myGL.glDisable(GL.GL_MAP2_VERTEX_3);
	myGL.glDisable(GL.GL_AUTO_NORMAL);
	myGL.glDisable(GL.GL_NORMALIZE);
	/////
	
 myGL.glPushMatrix();

 myGL.glColor3f (1.0f, 1.0f, 1.0f);
 myGL.glEvalMesh2 (GL.GL_FILL, 0, 20, 0, 20);
 
 //top
 
 myGL.glRotatef (angle1, 1.0f, 0.0f, 0.0f);
 myGL.glScaled(2.0,2.0,1.0);
 myGL.glTranslated(0.0f,0.0f,z);
 makeImage5();
 myGL.glPushMatrix();
 myGL.glTranslated(0.0f,0.0f,18.0f);
 myGL.glPushMatrix();
 myGL.glRotated(90,0.0f,1.0f, 0.0f);
 myGL.glBegin(GL.GL_POLYGON);              
 myGL.glTexCoord2f(0, 0);myGL.glVertex3f( 1.0f, 1.0f, -1.0f);
 myGL.glTexCoord2f(1, 0);myGL.glVertex3f(-1.0f, 1.0f, -1.0f);
 myGL.glTexCoord2f(1, 1);myGL.glVertex3f(-1.0f, 1.0f,  1.0f);
 myGL.glTexCoord2f(0, 1);myGL. glVertex3f( 1.0f, 1.0f,  1.0f);
 myGL.glEnd();    
 myGL.glPopMatrix();
 
 makeImage1();
 myGL.glPushMatrix();
 myGL.glRotated(-90,0.0f,1.0f, 0.0f);
 myGL.glBegin(GL.GL_POLYGON);  
 // Bottom face (y = -1.0f)
 myGL.glTexCoord2f(0, 0);myGL.glVertex3f( 1.0f, -1.0f,  1.0f);
 myGL.glTexCoord2f(1, 0);myGL.glVertex3f(-1.0f, -1.0f,  1.0f);
 myGL.glTexCoord2f(1, 1);myGL.glVertex3f(-1.0f, -1.0f, -1.0f);
 myGL.glTexCoord2f(0, 1);myGL.glVertex3f( 1.0f, -1.0f, -1.0f);
 myGL.glEnd();
 myGL.glPopMatrix();
 // Front face  (z = 1.0f)
 /*myGL.glBegin(GL.GL_POLYGON); 
 myGL.glVertex3f( 1.0f,  1.0f, 1.0f);
 myGL.glVertex3f(-1.0f,  1.0f, 1.0f);
 myGL.glVertex3f(-1.0f, -1.0f, 1.0f);
 myGL.glVertex3f( 1.0f, -1.0f, 1.0f);
 myGL.glEnd();*/
 
 // Back face (z = -1.0f)
 makeImage2();
 myGL.glPushMatrix();
 myGL.glRotated(90,0.0f,0.0f, 1.0f);
 myGL.glBegin(GL.GL_POLYGON); 
 myGL.glTexCoord2f(0, 0);myGL.glVertex3f( 1.0f, -1.0f, -1.0f);
 myGL.glTexCoord2f(1, 0);myGL.glVertex3f(-1.0f, -1.0f, -1.0f);
 myGL.glTexCoord2f(1, 1);myGL.glVertex3f(-1.0f,  1.0f, -1.0f);
 myGL.glTexCoord2f(0, 1);myGL.glVertex3f( 1.0f,  1.0f, -1.0f);
 myGL.glEnd();
 myGL.glPopMatrix();
 
 // Left face (x = -1.0f)
 makeImage3();
 myGL.glPushMatrix();
 myGL.glRotated(-90,1.0f,0.0f, 0.0f);
 myGL.glBegin(GL.GL_POLYGON); 
 myGL.glTexCoord2f(0, 0);myGL.glVertex3f(-1.0f,  1.0f,  1.0f);
 myGL.glTexCoord2f(1, 0);myGL.glVertex3f(-1.0f,  1.0f, -1.0f);
 myGL.glTexCoord2f(1, 1);myGL.glVertex3f(-1.0f, -1.0f, -1.0f);
 myGL.glTexCoord2f(0, 1);myGL.glVertex3f(-1.0f, -1.0f,  1.0f);
 myGL.glEnd();
 myGL.glPopMatrix();
 
 // Right face (x = 1.0f)
 makeImage4();
 myGL.glPushMatrix();
 myGL.glRotated(90,1.0f,0.0f, 0.0f);
 myGL.glBegin(GL.GL_POLYGON); 
 myGL.glTexCoord2f(0, 0); myGL.glVertex3f(1.0f,  1.0f, -1.0f);
 myGL.glTexCoord2f(1, 0);myGL.glVertex3f(1.0f,  1.0f,  1.0f);
 myGL.glTexCoord2f(1, 1);myGL.glVertex3f(1.0f, -1.0f,  1.0f);
 myGL.glTexCoord2f(0, 1);myGL.glVertex3f(1.0f, -1.0f, -1.0f);
 myGL.glEnd();  // End of drawing cube
 myGL.glPopMatrix();
 myGL.glPopMatrix();
 myGL.glPopMatrix();
     

     ///////// boat ship
 

     /////////////
 
     myGL.glDisable(GL.GL_TEXTURE_2D);
     myGL.glEnable (GL.GL_LIGHTING);
 	myGL.glEnable (GL.GL_LIGHT0);
     myGL.glTranslatef (0.0f, 0.0f, 0.0f);
   	myGL.glPushMatrix ();
   	myGL.glTranslatef(0.0f, 0.0f, zRobot);

      myGL.glRotatef ((float)upperBody1, 1.0f, 0.0f, 0.0f);
      myGL.glRotatef ((float)upperBody2, 0.0f, 1.0f, 0.0f);
  	//upper body
  	myGL.glPushMatrix ();
  	
  	    myGL.glTranslatef (0.0f, 0.0f, 0.0f);
//  	    myGL.glRotatef ((float)upperBody1, 1.0f, 0.0f, 0.0f);
//  	    myGL.glRotatef ((float)upperBody2, 0.0f, 1.0f, 0.0f);
  	    myGL.glPushMatrix ();

  		myGL.glScalef (2.0f, 2.5f, 1.0f);
  		myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
  	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, body_diffuse);
  	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_ambient);
  	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, high_shininess);
  	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, body_emission);
  	   
  		myUT.glutSolidCube(1);
  	    myGL.glPopMatrix ();
  	    
  	    
  	  //head robot
  	    myGL.glPushMatrix ();
  	    
  		    myGL.glTranslatef (0.0f, 1.25f, 0.0f);
//  		    myGL.glRotatef ((float)leftHand, 1.0f, 0.0f, 0.0f);
  		    myGL.glTranslatef (0.0f, 0.5f, 0.0f);
  		    
  		    myGL.glPushMatrix ();
  				myGL.glScalef (1.5f, 1.0f, 1.0f);
  				myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, low_shininess);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
  			    myGL.glTexCoord3f (1.0f, 0.0f, 0.0f);
  			    myGL.glTexCoord3f (0.0f, 1.0f, 0.0f);
  			    myGL.glTexCoord3f (0.0f, 0.0f, 1.0f);
  			    myGL.glTexCoord3f (1.0f, 0.0f, 1.0f);
  			    
  				myUT.glutSolidCube(1);
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
  				myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, low_shininess);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
  				    myUT.glutSolidCube(1);
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
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, no_shininess);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
  				myUT.glutSolidCube(1);
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
  				myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, no_shininess);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
  				myUT.glutSolidCube(1);
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
  				myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, no_shininess);
  			    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
  				myUT.glutSolidCube(1);
  				myGL.glPopMatrix ();
  	    myGL.glPopMatrix();
  	    //end right foot
  	    
      myGL.glPopMatrix();
      
      //end bottom
     
      
  myGL.glPopMatrix ();
     ////////////
     //////////////
  myGL.glEnable(GL.GL_TEXTURE_2D);
    //////
 

	
     
    
     
	myGL.glFlush ();
    }

    private static final int imageWidth = 256;
    private static final int imageHeight = 256;
    private byte image [][][] = new byte [imageHeight][imageWidth][3];

    
    private void inputImg(File bmpFile) {
    	int i,j ;
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
    
    private void makeImage1() {

	File bmpFile = new File("D:\\GraphicsComputer\\Tutors\\Images\\BedRoom_Bottom.bmp");
	inputImg(bmpFile);
    }
    
    private void makeImage2() {

    	File bmpFile = new File("D:\\GraphicsComputer\\Tutors\\Images\\BedRoom_Center.bmp");
    	inputImg(bmpFile);
        }
    
    private void makeImage3() {

    	File bmpFile = new File("D:\\GraphicsComputer\\Tutors\\Images\\BedRoom_Left.bmp");
    	inputImg(bmpFile);
        }
    
    private void makeImage4() {

    	File bmpFile = new File("D:\\GraphicsComputer\\Tutors\\Images\\BedRoom_Right.bmp");
    	inputImg(bmpFile);
        }
    
    private void makeImage5() {

    	File bmpFile = new File("D:\\GraphicsComputer\\Tutors\\Images\\BedRoom_Top.bmp");
    	inputImg(bmpFile);
        }

    private void myinit () {
 
    	float ambient [] = { 1.0f, 1.0f, 0.0f, 1.0f }; //mau xung quanh
    	float diffuse [] = {1.0f,1.0f,1.0f,1.0f}; /// mau phat sang
    	float specular [] ={ 1.0f, 0.0f, 0.0f, 1.0f };; // mau phan xa
    	float position [] = {0.0f,3.0f,2.0f,0.0f}; 
    	float lmodel_ambient [] = {0.4f,0.4f,0.4f,1.0f}; 
    	float local_view [] = {0.0f};
    	float mat_emission[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    	
    	myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
    	
    	myGL.glDepthFunc (GL.GL_LESS);
    	myGL.glEnable (GL.GL_DEPTH_TEST);
    	myGL.glShadeModel (GL.GL_FLAT);

    	
    	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
    	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse);
    	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, position);
    	myGL.glLightModelfv (GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
    	myGL.glLightModelfv (GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);
    	//////////////////////////////////////////////
    	myGL.glEnable (GL.GL_LIGHTING);
    	myGL.glEnable (GL.GL_LIGHT0);

  	
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
    	myGL.glLoadIdentity();
    	myGL.glTranslatef (0.0f, 0.0f, zFrame);
        
        //myGL.glRotatef ((float)angle, 1.0f, 1.0f, 0.0f);
    }

    public void keyboard (char key, int x, int y) {
	switch (key) {
	 case 'a':
			angle1 = angle1 + 5;
			myUT.glutPostRedisplay ();
			break;
	 case 'z':
			z+= 0.1f;
			myUT.glutPostRedisplay ();
			break;
	 case 'x':
			z-= 0.1f;
			myUT.glutPostRedisplay ();
			break;
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
    public void runDisplay(int value) {
    	if(leftFoot == 50 && rightHand == 50) {
    		count = -10 ;
    	}
    	if(leftFoot == -50 && rightHand == -50) {
    		count = 10 ;
    	}
    	if(leftFoot >= -50 && leftFoot <= 50) {
    		// 1
    		leftFoot += count;
    		rightHand += count;
    		//2
    		rightFoot -= count;
    		leftHand -= count;
    		//3
    		if (zRobot <= 18.0f && countz <= 18.0f) {
				
    			zRobot += 0.2f;
    			countz += 0.2f;
			}else {
				zRobot -= 0.2f;
			}
    		
    		myUT.glutPostRedisplay();
    		myUT.glutTimerFunc(50, "runDisplay", 5);
    	}
    }
    

    public void runAction() {
    	if(check) {
    		
    		myUT.glutTimerFunc(50, "runDisplay", 5);
    		check = false;
    		
    		
    	}
//    	else {
//    		System.out.println("stop");
//    		stopDisplay();
//    		check = true;
//    	}
    }
    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (608, 627);
	Robot mainCanvas = new Robot ();
	mainCanvas.init();
	//	//tao panel button
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