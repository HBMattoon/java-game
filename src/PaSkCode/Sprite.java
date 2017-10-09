package PaSkCode;
//Henry Mattoon
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Sprite {

	Particle party;

	private Image image;

	Sprite(){
	}

	Sprite(int r, int nx, int ny, int vx, int vy,Image img){
		initSprite(r,nx,ny,vx,vy,img);	
	}

	void initSprite(int r, int nx, int ny, int vx, int vy,Image img){
		image = img;
		party = new Particle(r, nx,ny,vx,vy);
		
	}

	void draw(Graphics2D g){//do i really need this?
		//g.drawImage(image, party.x, party.y, null);
	}
	
	Image getImage(){
		return image;
	}




}
