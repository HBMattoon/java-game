package PaSkCode;
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



public class GWView{

	PSysView pView;
	GWView(){
	}
	
	void draw(GWModel model,Graphics grapic){
		
		for(int i=0; i<model.sList.size(); i++){
			grapic.drawImage(model.sList.get(i).getImage(), model.sList.get(i).party.x,model.sList.get(i).party.y, 2*model.sList.get(i).party.radius, 2*model.sList.get(i).party.radius, null);
		}
	}


}
