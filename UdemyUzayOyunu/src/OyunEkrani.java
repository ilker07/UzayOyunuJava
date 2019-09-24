import java.awt.HeadlessException;

import javax.swing.JFrame;

public class OyunEkrani extends JFrame {

	
	
	public OyunEkrani(String arg0) throws HeadlessException {
		super(arg0);
		
		
		
	}

	public static void main(String[] args) {
		
		
		OyunEkrani ekran =new OyunEkrani("Oyun Ekrani");
		
		ekran.setResizable(false);
		ekran.setFocusable(false);//jpanele odaklansýn diye.
		ekran.setSize(800,600);
		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Oyun oyun=new Oyun();
		oyun.requestFocus();//klavye iþlerini anlamak için.
		oyun.addKeyListener(oyun);//klavye iþlerini anlamak için.
		oyun.setFocusable(true);
		oyun.setFocusTraversalKeysEnabled(false);
		
		ekran.add(oyun);
		
		ekran.setVisible(true);
		
		
	
		
	}

}
