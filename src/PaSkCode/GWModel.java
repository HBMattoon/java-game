package PaSkCode;
//henry mattoon
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

public class GWModel{//gameWorld
	PSysModel chars;
	ArrayList <Character>itemType;
	ArrayList <Sprite> sList;
	Image image;
	Image graveStone;
	GWModel(){
		itemType = new ArrayList<Character>();//c=player, b=bot, p=projectile, t=tombstone
		chars = new PSysModel();
		sList = new ArrayList<Sprite>();
	}
	

	void orientBots(){
		int sSize=sList.size();
		for(int i=0; i<sSize; i++){
			if(itemType.get(i)=='b'){
				if(sList.get(i).party.x>sList.get(1).party.x){
					sList.get(i).party.velX = -1;
				}else if(sList.get(i).party.x<=sList.get(1).party.x){
					sList.get(i).party.velX = 1;
				}
				if(sList.get(i).party.y>sList.get(1).party.y){
					sList.get(i).party.velY = -1;
				}else if(sList.get(i).party.y<=sList.get(1).party.y){
					sList.get(i).party.velY = 1;
				}
				addProjectile(image, i);
			}
			
			
		}
	}

	void addBot(int rad,int npx,int npy,int nvx,int nvy,Image sImg){
		sList.add(new Sprite(rad,npx,npy,nvx,nvy,sImg));
		itemType.add('b');

	}

	boolean isOverlap(Particle p1, Particle p2) {
		
		int diffX = Math.abs(p1.x - p2.x);
		int diffY = Math.abs(p1.y - p2.y);
		if (diffX < p1.radius + p2.radius && diffY < p1.radius + p2.radius)
			return true;
		else
			return false;
		
	}

	void checkCollide(){
		//System.out.println("Checking Collide");
		for(int i =0; i<sList.size(); i++){
			for(int j =0; j<sList.size(); j++){
				if(j!=i){
					if(isOverlap(sList.get(i).party, sList.get(j).party)){
						//System.out.println("collision!");
						if(itemType.get(i)=='c'){
							killCharacter(i);
						}
						if(itemType.get(i)=='b'){
							killCharacter(i);
						}
						if(itemType.get(i)=='p'){
							
							removeProj(i);	
						}
						
							
						if(itemType.get(j)=='c'){
							killCharacter(j);
						}
						if(itemType.get(j)=='b'){
							killCharacter(j);
						}
						if(itemType.get(j)=='p'){
							
							removeProj(j);
							
						}
						
					}
				}							
			}		
		}
	}

	void update(int bw,int bh){
		//System.out.println("checking update method");
		for(int i =0; i<sList.size(); i++){
			sList.get(i).party.update(bw,bh);
		}
		//System.out.println("not in update...");
	}	

	void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		int i=1;
		switch(key){
			case KeyEvent.VK_LEFT:
				
				sList.get(i).party.velX=0;
			break;	
			case KeyEvent.VK_RIGHT:
				sList.get(i).party.velX=0;
			break;
			case KeyEvent.VK_UP:
				sList.get(i).party.velY=0;
			break;
			case KeyEvent.VK_DOWN:
				sList.get(i).party.velY=0;
			break;
			case KeyEvent.VK_SPACE:
				if(itemType.get(i)=='c')
				addProjectile(image, 1);
				//killCharacter(0); //testing
			break;
		}
	}

	void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		int i=1;	
		if(itemType.get(i)=='c'){
			switch(key){
				case KeyEvent.VK_LEFT:
					sList.get(i).party.velX=-5;
				break;		
				case KeyEvent.VK_RIGHT:
					sList.get(i).party.velX=5;	
				break;
				case KeyEvent.VK_UP:
					sList.get(i).party.velY=-5;
				break;
				case KeyEvent.VK_DOWN:
					sList.get(i).party.velY=5;
				break;
				case KeyEvent.VK_SPACE:
				break;
			}
		}
	}

	void addPlayer(int rad,int npx,int npy,int nvx,int nvy,Image pImg){
		sList.add(new Sprite(rad,npx,npy,nvx,nvy,pImg));
		itemType.add('c');
	}

	void addProjectileImg(Image img){
		image=img;
	}

	void addGraveStone(Image img){
		graveStone=img;

	}
	void addProjectile(Image img, int source){
		//System.out.println("shooting!!!");
		int hostX = sList.get(source).party.x;
		int hostY = sList.get(source).party.y;
		int hostVX = sList.get(source).party.velX;
		int hostVY = sList.get(source).party.velY;
		int hostRad = sList.get(source).party.radius;
		int offsetX=0;
		int offsetY=0;
		if(hostVX>0){
			offsetX=hostRad;
		}else if(hostVX<0){
			offsetX=-(1+hostRad*2);
		}
		if(hostVY>0){
			offsetY=hostRad;
		}else if(hostVY<0){
			offsetY=-(1+hostRad*2);
		}


		
		int newX=hostX+hostRad*3/4+offsetX;
		int newY=hostY+hostRad*3/4+offsetY;
		if(hostVX>0 || hostVY>0 || hostVX<0|| hostVY<0){
			sList.add(new Sprite(10,newX,newY,hostVX*2,hostVY*2,img));
			itemType.add('p');
		}
		
	}
	void killCharacter(int arrayPos){
		//System.out.println("testing kill character");
		int oldRads = sList.get(arrayPos).party.radius;
		int oldX = sList.get(arrayPos).party.x;
		int oldY = sList.get(arrayPos).party.y;
		sList.set(arrayPos, new Sprite(oldRads,oldX,oldY,0,0, graveStone));
		itemType.set(arrayPos,'t');
		//System.out.println("not in kill character");
	}

	void removeProj(int arrayPos){
		sList.set(arrayPos, new Sprite(0,0,0,0,0,graveStone));
		itemType.set(arrayPos, 'e');
	}


}
