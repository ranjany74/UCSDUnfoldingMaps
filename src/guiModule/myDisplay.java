package guiModule;

import processing.core.*;

public class myDisplay extends PApplet {
	
	PImage img;
	
	public void setup() {
		size(600,600);
		
		String url = "https://picsum.photos/seed/picsum/800/800";
		img = loadImage(url, "png");
		img.resize(0, height);
		image(img,0,0);
//		background(200,200,200);
		
	}
	
	public void draw() {
		
//		fill(255,255,0);
//		ellipse(200,200,390,390);
//		
//		fill(0,0,0);
//		ellipse(120, 130, 50, 70);
//		
//		ellipse(280, 130, 50, 70);
//		
//		arc(200, 280, 75, 75, 0, PI);
		
		int[] color = sunColorSec(second());
		fill(color[0], color[1], color[2]);
		ellipse(width/4, height/5, width/5, height/5 );
	}
	
	public int[] sunColorSec(float seconds) {
		
		int[] rgb = new int[3];
		
		float diffFrom30 = Math.abs(seconds - 30);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
		
	}
}
