package net.mrx13415.searchcircle.tool;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.mrx13415.searchcircle.swing.JSearchCircle;


public class UserInterface implements ActionListener{

	JFrame window = null;
	JPanel panel;
	JButton button;
	JSearchCircle searchBar;
	
	static boolean automove = true;
	
	int index = 0;	
	
	
	public void GUI(){
		
		searchBar = new JSearchCircle();
		searchBar.setOpaque(false);

		button = new JButton();
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(searchBar, BorderLayout.CENTER);
		panel.add(button, BorderLayout.EAST);
		
		//- Images -----------
		
		
		//--------------------
		
		window = new JFrame("SearchCircle 1.9.9 (alpha) - Test App");
		window.setLocationRelativeTo(null);
		window.getContentPane().add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		
		//-- SearchCircle Desing Settings -------------------------------------------------
		
		searchBar.setRotateButton(true);
		searchBar.setBarRotated180(true);
		searchBar.setButtonJutOut(2);
		searchBar.setMinimum(0);
		searchBar.setMaximum(500);
		searchBar.setDirection(JSearchCircle.BAR_DIRECTION_LEFT);
		searchBar.setButtonVisible(true);
		searchBar.setButtonValue(50);
		searchBar.setStartAngle(-30);
		searchBar.setViewAngle(360);
		searchBar.setStyle(JSearchCircle.STYLE.PARTS);		
		searchBar.setAnchor(JSearchCircle.Anchor.CENTER);
		searchBar.addActionListener(this);

//		BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = (Graphics2D) img.getGraphics();
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //enable Antialiasing ...
//		
//		
//		for (int i = 0; i <= 20; i++) {
//			g.setColor(Color.getHSBColor(1.0f, 1.0f, ((float)i / 20f)));
//			g.drawLine(i, 0, i, 20);
//		}
		
//		searchBar.setButtondImage(img);
		
		//modify images color ...
//		SearchCircle.ImageModifier im;
//		im = new SearchCircle.ImageModifier(searchBar.getButtonImage());
//		im.setHue(SearchCircle.ImageModifier.getHSBfromColor(Color.YELLOW).getHue());
//		searchBar.setButtonImage(im.modify());
//		
//		im = new SearchCircle.ImageModifier(searchBar.getBarImage());
//		im.setHue(SearchCircle.ImageModifier.getHSBfromColor(Color.RED).getHue());
//		searchBar.setBarImage(im.modify());
//
//		im = new SearchCircle.ImageModifier(searchBar.getBarBackgroundImage());
//		im.modifyBrightness(-0.4f);
//		im.setSaturation(0.5f);
//		im.setHue(SearchCircle.ImageModifier.getHSBfromColor(Color.BLUE).getHue());
//		searchBar.setBarBackgroundImage(im.modify());

		searchBar.setBarThickness(15);
//		searchBar.setButtonSize(new Dimension(30, 30));
	
		//---------------------------------------------------------------------------------
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				index = (int) searchBar.getMinimum();

				while (window != null) {
					
					if (automove){
						searchBar.setBarValue(index);
						
						if (! searchBar.isMouseChangingButtonValue()) searchBar.setButtonValue(index);
			
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {}
//						System.out.println(index);
						index += 1;
						
						if (index > searchBar.getMaximum()) {
							index = (int) searchBar.getMinimum();
						}
					}
				}
				
			}
		}).start();
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(searchBar)){
			searchBar.setBarValue(index = (int) searchBar.getButtonValue());
		}
		
		if (e.getSource().equals(button)) {
			new EditUserInterface(searchBar, window);
		}
	}
	
//	public void setSimpleStyle() {
//		if (useSimpleStyle) {
//			BufferedImage img = new BufferedImage(9, 20, BufferedImage.TYPE_INT_ARGB);
//			Graphics2D g = (Graphics2D) img.getGraphics();
//			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //enable Antialiasing ...
//			
//			g.setColor(Color.red);
//			g.fillRect(0, 0, img.getWidth(), img.getHeight());
//			searchBar.setBarImage(img);
//			
//			img = new BufferedImage(9 , 20, BufferedImage.TYPE_INT_ARGB);
//			g = (Graphics2D) img.getGraphics();
//			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //enable Antialiasing ...
//			
//			g.setColor(Color.gray);
//			g.fillRect(0, 0, img.getWidth(), img.getHeight());
//			searchBar.setBarBackgroundImage(img);
//			
//			img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
//			g = (Graphics2D) img.getGraphics();
//			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //enable Antialiasing ...
//			
//			g.setColor(Color.black);
//			g.fillOval(0, 0, img.getWidth(), img.getHeight());
//			g.setColor(Color.red);
//			g.fillOval(4, 4, img.getWidth() - (2 * 4), img.getHeight() - (2* 4));
//			searchBar.setButtonImage(img);
//			
//			searchBar.repaintImages();
//		}
//	}
}
