package testGC;

import java.awt.Frame;
import java.io.IOException;

public class tester {
	 public static void main(String[] args) throws IOException{
    	 Frame mainFrame = new Frame();
    		mainFrame.setSize(508, 524);
    		hello mainCanvas = new hello();
    		mainCanvas.init();
    		mainFrame.add(mainCanvas);
    		mainFrame.setVisible(true);
    		
	}
}
