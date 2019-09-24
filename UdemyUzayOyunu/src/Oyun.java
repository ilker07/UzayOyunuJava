import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
	
	private int x;
	private int y;
	public Ates(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}

public class Oyun extends JPanel implements KeyListener,ActionListener {


	Timer timer=new Timer(5,this);
	
	private int gecen_sure=0;
	private int harcanan_ates=0;
	private BufferedImage resim;
	private ArrayList< Ates> atesler=new ArrayList<Ates>();
	
	private int atesdirY=1;
	private int topX=0;
	private int topdirX=2;
	private int uzayGemisiX=0;
	private int dirUzayX=20;
	public boolean vurulduMu()
	{
		for(Ates ates:atesler)
		{
			if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX, 0, 20, 20)))
			{
				return true;
			}
		}
		return false;
	}
	public Oyun() {
		super();
		
		try {
			resim=ImageIO.read(new FileImageInputStream(new File("uzay.jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBackground(Color.black);
		
		//System.out.println(resim.getWidth());500
		
	
		
		timer.start();
		
		
	}

	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		gecen_sure +=5;
		g.setColor(Color.red);
		g.fillOval(topX, 0, 20, 20);
		
		
		g.drawImage(resim,uzayGemisiX,510,resim.getWidth()/3,resim.getHeight()/3,this);
		
		
	
		
		
		g.setColor(Color.blue);
		
		
		for(Ates ates:atesler)
		{
			g.fillRect(ates.getX(), ates.getY(), 10, 20);
		}

		for(Ates ates:atesler)
		{
			if(ates.getY()<0)
				atesler.remove(ates);
		}
		if(vurulduMu())
		{
			timer.stop();
			//JOptionPane.showMessageDialog(null, "Harcanan Ateþ :"+harcanan_ates+"Geçen Süre: "+gecen_sure/1000.0);
			JOptionPane.showMessageDialog(null, "Bitti.");
			
			System.exit(0);
		}
	
		
		
		
	}
	

	@Override
	public void repaint() {
		super.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
       int c=e.getKeyCode();
		
		if(c==KeyEvent.VK_LEFT)
		{
			if(uzayGemisiX<=0)
				uzayGemisiX=0;
			else
				uzayGemisiX -=dirUzayX;
		}
		else if(c==KeyEvent.VK_RIGHT)
		{
			if(uzayGemisiX>=720)
				uzayGemisiX=720;
			else
				uzayGemisiX +=dirUzayX;
		}
		
		else if(c==KeyEvent.VK_CONTROL)
		{
			atesler.add(new Ates(uzayGemisiX+30,510));
			harcanan_ates++;
			
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		topX+=topdirX;
		if(topX>=780)
		{
			topdirX =-topdirX;
		}
		if(topX<=0)
		{
			topdirX=-topdirX;
		}
		for(Ates ates:atesler)
		{
			ates.setY(ates.getY()-atesdirY);
			
		}
		
		repaint();//Bu üstteki iþlemlerin olabilmesi için.
		
	}
	
	
	
	
	
	

}
