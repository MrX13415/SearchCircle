package net.mrx13415.searchcircle.tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.mrx13415.searchcircle.imageutil.ImageModifier;
import net.mrx13415.searchcircle.imageutil.color.HSB;
import net.mrx13415.searchcircle.swing.JSearchCircle;
import net.mrx13415.searchcircle.swing.JSearchCircle.Anchor;
import net.mrx13415.searchcircle.swing.JSearchCircle.STYLE;


public class EditUserInterface implements ActionListener, KeyListener, ChangeListener{
	
	JSearchCircle searchCircle = null;
	
	JCheckBox rotateButton;
	JCheckBox barRotated180;
	JCheckBox buttonVisible;
	
	JLabel buttonJutOutlabel;
	JLabel minimumlabel;
	JLabel maximumlabel;
	JLabel buttonValuelabel;
	JLabel barValuelabel;
	JLabel startAnglelabel;
	JLabel viewAnglelabel;	
	JLabel barThicknesslabel;
	JLabel buttonSizeWlabel;
	JLabel buttonSizeHlabel;
	
	JTextField buttonJutOut;
	JTextField minimum;
	JTextField maximum;
	JTextField barThickness;
	JTextField buttonSizeW;
	JTextField buttonSizeH;
	
	JSlider buttonValue;
	JSlider barValue;
	JSlider startAngle;
	JSlider viewAngle;	
	
	JLabel directionlabel;
	JLabel stylelabel;		
	JLabel anchorlabel;
	
	Object directionitem1 = "Left";
	Object directionitem2 = "Right";
	
	JComboBox<Object> direction;
	JComboBox<String> style;		
	JComboBox<String> anchor;

	JButton buttonColor;
	JButton barColor;
	JButton backgroundColor;
	JButton move;
	
	JPanel mainPanel;
	JFrame window;
	
	JFrame parent;
	JColorChooser cc;
	
	public EditUserInterface(JSearchCircle searchCircle, JFrame parent) {
		this.searchCircle = searchCircle;
		this.parent = parent;
		
		initGui();
	}
	
	public void initGui(){
		
		rotateButton = new JCheckBox("Autorotate button");
		rotateButton.addActionListener(this);
		rotateButton.setSelected(searchCircle.getRotateButton());
		
		barRotated180 = new JCheckBox("Rotated bar-image by 180");
		barRotated180.addActionListener(this);
		barRotated180.setSelected(searchCircle.getBarRotated180());
		
		buttonVisible = new JCheckBox("Show button");
		buttonVisible.addActionListener(this);
		buttonVisible.setSelected(searchCircle.getButtonVisible());
		
		buttonJutOutlabel = new JLabel("Button jutout: ");
		minimumlabel = new JLabel("Min. value: ");
		maximumlabel = new JLabel("Max. value: ");
		buttonValuelabel = new JLabel("Button value: ");
		barValuelabel = new JLabel("Bar value: ");
		startAnglelabel = new JLabel("Start angle: ");
		viewAnglelabel = new JLabel("View angle: ");	
		barThicknesslabel = new JLabel("Bar thickness: ");
		buttonSizeWlabel = new JLabel("Button Width: ");
		buttonSizeHlabel = new JLabel("Button Height: ");
		
		buttonJutOut = new JTextField();
		buttonJutOut.addKeyListener(this);
		buttonJutOut.setText("" + searchCircle.getButtonJutOut());
		
		minimum = new JTextField();
		minimum.addKeyListener(this);
		minimum.setText("" + searchCircle.getMinimum());
		
		maximum = new JTextField();
		maximum.addKeyListener(this);
		maximum.setText("" + searchCircle.getMaximum());
	
		barThickness = new JTextField();
		barThickness.addKeyListener(this);
		barThickness.setText("" + searchCircle.getBarThickness());
				
		buttonSizeW = new JTextField();
		buttonSizeW.addKeyListener(this);
		buttonSizeW.setText("" + searchCircle.getButtonSize().getWidth());
		
		buttonSizeH = new JTextField();
		buttonSizeH.addKeyListener(this);
		buttonSizeH.setText("" + searchCircle.getButtonSize().getHeight());
		
		buttonValue = new JSlider();
		buttonValue.setPaintTicks(true);
		buttonValue.addChangeListener(this);
		buttonValue.setValue((int) searchCircle.getButtonValue());
		buttonValue.setMinimum((int) searchCircle.getMinimum());
		buttonValue.setMaximum((int) searchCircle.getMaximum());
		
		barValue = new JSlider();
		barValue.setPaintTicks(true);
		barValue.addChangeListener(this);
		barValue.setValue((int) searchCircle.getBarValue());
		barValue.setMinimum((int) searchCircle.getMinimum());
		barValue.setMaximum((int) searchCircle.getMaximum());
		
		startAngle = new JSlider();
		startAngle.setPaintLabels(true);
		startAngle.setMajorTickSpacing(90);
		startAngle.setPaintTicks(true);
		startAngle.addChangeListener(this);
		startAngle.setValue((int) searchCircle.getStartAngle());
		startAngle.setMinimum(0);
		startAngle.setMaximum(360);
		
		viewAngle = new JSlider();	
		viewAngle.setPaintLabels(true);
		viewAngle.setMajorTickSpacing(90);
		viewAngle.setPaintTicks(true);
		viewAngle.addChangeListener(this);
		viewAngle.setValue((int) searchCircle.getViewAngle());
		viewAngle.setMinimum(0);
		viewAngle.setMaximum(360);
		
		directionlabel = new JLabel("Bar direction: ");
		stylelabel = new JLabel("Bar style: ");
		anchorlabel = new JLabel("Button anchor: ");

		direction = new JComboBox<Object>();
		direction.addActionListener(this);
		direction.addItem(directionitem1);
		direction.addItem(directionitem2);		
		if (searchCircle.getDirection() == JSearchCircle.BAR_DIRECTION_RIGHT) direction.setSelectedItem(directionitem2);
		
		style = new JComboBox<String>();
		style.addActionListener(this);
		STYLE stylevalue = searchCircle.getStyle();
		for (JSearchCircle.STYLE currentStyle : JSearchCircle.STYLE.values()) {
			style.addItem(currentStyle.toString());
		}
		style.setSelectedItem(stylevalue.toString());
		
		anchor = new JComboBox<String>();
		anchor.addActionListener(this);
		
		Anchor posvalue = searchCircle.getAnchor();
		for (JSearchCircle.Anchor currentPos : JSearchCircle.Anchor.values()) {
			anchor.addItem(currentPos.toString());
		}
		style.setSelectedItem(posvalue.toString());
		
		buttonColor = new JButton("Button color ...");
		buttonColor.addActionListener(this);
		
		barColor = new JButton("Bar color ...");
		barColor.addActionListener(this);
		
		backgroundColor = new JButton("Backgrund color ...");
		backgroundColor.addActionListener(this);
		
		move = new JButton("Start/Stop Auto Move...");	
		move.addActionListener(this);
		
		mainPanel = new JPanel(new GridLayout(9, 4, 10, 10));
		
		mainPanel.add(rotateButton);
		mainPanel.add(barRotated180);
		mainPanel.add(buttonVisible);	
		mainPanel.add(new JPanel());	//just space holder ;)
		
		mainPanel.add(minimumlabel);
		mainPanel.add(minimum);
		mainPanel.add(buttonValuelabel);
		mainPanel.add(buttonValue);
		mainPanel.add(maximumlabel);
		mainPanel.add(maximum);
		mainPanel.add(barValuelabel);
		mainPanel.add(barValue);
		mainPanel.add(startAnglelabel);
		mainPanel.add(startAngle);
		mainPanel.add(viewAnglelabel);	
		mainPanel.add(viewAngle);
		mainPanel.add(barThicknesslabel);
		mainPanel.add(barThickness);
		mainPanel.add(buttonJutOutlabel);
		mainPanel.add(buttonJutOut);
		mainPanel.add(buttonSizeWlabel);
		mainPanel.add(buttonSizeW);
		mainPanel.add(buttonSizeHlabel);
		mainPanel.add(buttonSizeH);		
		
		mainPanel.add(directionlabel);
		mainPanel.add(stylelabel);		
		mainPanel.add(anchorlabel);
		mainPanel.add(new JPanel());	//just space holder ;)
		
		mainPanel.add(direction);
		mainPanel.add(style);		
		mainPanel.add(anchor);
		mainPanel.add(new JPanel());	//just space holder ;)

		mainPanel.add(buttonColor);
		mainPanel.add(barColor);
		mainPanel.add(backgroundColor);
		mainPanel.add(move);
			
		window = new JFrame();
		window.setLocation(parent.getLocation().x + parent.getSize().width, parent.getLocation().y);
		window.setTitle("SearchCircle 1.7 - Test App");
		window.getContentPane().add(mainPanel);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		try {
			if (source.equals(buttonValue)) searchCircle.setButtonValue(Double.valueOf(buttonValue.getValue()));
			if (source.equals(barValue)) searchCircle.setBarValue(Double.valueOf(barValue.getValue()));
			
			if (source.equals(startAngle)) searchCircle.setStartAngle(Double.valueOf(startAngle.getValue()));
			if (source.equals(viewAngle)) searchCircle.setViewAngle(Double.valueOf(viewAngle.getValue()));
			
			if (source.equals(rotateButton)) searchCircle.setRotateButton(rotateButton.isSelected());
			if (source.equals(barRotated180)) searchCircle.setBarRotated180(barRotated180.isSelected());
			if (source.equals(buttonVisible)) searchCircle.setButtonVisible(buttonVisible.isSelected());
			
			if (source.equals(direction)){
				if (direction.getSelectedItem().equals(directionitem1)) searchCircle.setDirection(JSearchCircle.BAR_DIRECTION_LEFT);
				if (direction.getSelectedItem().equals(directionitem2)) searchCircle.setDirection(JSearchCircle.BAR_DIRECTION_RIGHT);			
			}
			
			if (source.equals(style)) searchCircle.setStyle(STYLE.valueOf(style.getSelectedItem().toString()));		
			if (source.equals(anchor)) searchCircle.setAnchor(Anchor.valueOf(anchor.getSelectedItem().toString()));		

			if (source.equals(buttonColor)) changeColor(buttonColor, new ImageModifier(searchCircle.getButtonImage()).getAverageColor());
			if (source.equals(barColor)) changeColor(barColor, new ImageModifier(searchCircle.getBarImage()).getAverageColor());
			if (source.equals(backgroundColor)) changeColor(backgroundColor, new ImageModifier(searchCircle.getBarBackgroundImage()).getAverageColor());
			if (source.equals(move)) UserInterface.automove = !UserInterface.automove;
			
		
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		Object source = e.getSource();
		
		try {
			if (source.equals(buttonJutOut)) searchCircle.setButtonJutOut(Integer.valueOf(buttonJutOut.getText()));
			
			if (source.equals(minimum)){
				double min = Double.valueOf(minimum.getText());
				barValue.setMinimum((int) min);
				buttonValue.setMinimum((int) min);
				searchCircle.setMinimum(min);
			}
			
			if (source.equals(maximum)){
				double max = Double.valueOf(maximum.getText());
				barValue.setMaximum((int) max);
				buttonValue.setMaximum((int) max);
				searchCircle.setMaximum(max);
			}
			
			if (source.equals(barThickness)) searchCircle.setBarThickness(Integer.valueOf(barThickness.getText()));
			if (source.equals(buttonSizeH) || source.equals(buttonSizeW)) searchCircle.setButtonSize( new Dimension(Integer.valueOf(buttonSizeW.getText()), Integer.valueOf(buttonSizeH.getText())));
			
			
		} catch (Exception e2) {
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
	public void changeColor(final Object source, Color color) {

		final JTextField ht = new JTextField("   0.00000");
		final JTextField st = new JTextField("   0.00000");
		final JTextField bt = new JTextField("   0.00000");
		
		final JSlider h = new JSlider(-100000, 100000, 0);
		final JSlider s = new JSlider(-100000, 100000, 0);
		final JSlider b = new JSlider(-100000, 100000, 0);

		ht.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				ht.selectAll();
			}
		});
		
		st.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				st.selectAll();
			}
		});
		
		bt.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				bt.selectAll();
			}
		});
		
		ht.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					float vh = Float.valueOf(ht.getText());
					float vs = Float.valueOf(st.getText());
					float vb = Float.valueOf(bt.getText());
					
					if (vh > 1 || vh < -1) return;
					if (vs > 1 || vs < -1) return;
					if (vb > 1 || vb < -1) return;
					
					h.setValue((int) (vh * 100000));
					s.setValue((int) (vs * 100000));
					b.setValue((int) (vb * 100000));
					
					if (source.equals(buttonColor)) searchCircle.setButtonHSB(new HSB(vh, vs, vb));
					if (source.equals(barColor)) searchCircle.setBarHSB(new HSB(vh, vs, vb));
					if (source.equals(backgroundColor)) searchCircle.setBackgroundHSB(new HSB(vh, vs, vb));
				} catch (Exception e2) {}
			}
		});
		
		st.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					float vh = Float.valueOf(ht.getText());
					float vs = Float.valueOf(st.getText());
					float vb = Float.valueOf(bt.getText());
					
					if (vh > 1 || vh < -1) return;
					if (vs > 1 || vs < -1) return;
					if (vb > 1 || vb < -1) return;
					
					h.setValue((int) (vh * 100000));
					s.setValue((int) (vs * 100000));
					b.setValue((int) (vb * 100000));
					
					if (source.equals(buttonColor)) searchCircle.setButtonHSB(new HSB(vh, vs, vb));
					if (source.equals(barColor)) searchCircle.setBarHSB(new HSB(vh, vs, vb));
					if (source.equals(backgroundColor)) searchCircle.setBackgroundHSB(new HSB(vh, vs, vb));
				} catch (Exception e2) {}
			}
		});

		bt.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					float vh = Float.valueOf(ht.getText());
					float vs = Float.valueOf(st.getText());
					float vb = Float.valueOf(bt.getText());
					
					if (vh > 1 || vh < -1) return;
					if (vs > 1 || vs < -1) return;
					if (vb > 1 || vb < -1) return;
					
					h.setValue((int) (vh * 100000));
					s.setValue((int) (vs * 100000));
					b.setValue((int) (vb * 100000));
					
					if (source.equals(buttonColor)) searchCircle.setButtonHSB(new HSB(vh, vs, vb));
					if (source.equals(barColor)) searchCircle.setBarHSB(new HSB(vh, vs, vb));
					if (source.equals(backgroundColor)) searchCircle.setBackgroundHSB(new HSB(vh, vs, vb));
				} catch (Exception e2) {}
			}
		});

		h.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				float v = ((JSlider)e.getSource()).getValue() / 100000f;
				ht.setText(String.valueOf(v));
			}
		});
		
	
		s.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				float v = ((JSlider)e.getSource()).getValue() / 100000f;
				st.setText(String.valueOf(v));
			}
		});
		
		
		b.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				float v = ((JSlider)e.getSource()).getValue() / 100000f;
				bt.setText(String.valueOf(v));
			}
		});
		
		JLabel t1 = new JLabel("hue: ");
		t1.setPreferredSize(new Dimension(70, 25));
		
		JLabel t2 = new JLabel("saturation: ");
		t2.setPreferredSize(new Dimension(70, 25));
		
		JLabel t3 = new JLabel("brightness: ");
		t3.setPreferredSize(new Dimension(70, 25));
		
		JPanel hp = new JPanel(new BorderLayout());
		hp.add(t1, BorderLayout.WEST);
		hp.add(h);
		hp.add(ht, BorderLayout.EAST);
				
		JPanel sp = new JPanel(new BorderLayout());
		sp.add(t2, BorderLayout.WEST);
		sp.add(s);
		sp.add(st, BorderLayout.EAST);
				
		JPanel bp = new JPanel(new BorderLayout());
		bp.add(t3, BorderLayout.WEST);
		bp.add(b);
		bp.add(bt, BorderLayout.EAST);

	    JFrame frame = new JFrame();
	    
	    frame.setTitle("Change " + (source.equals(buttonColor) ? "button" : 
	    	source.equals(barColor) ? "bar" :
	    		source.equals(backgroundColor) ? "background" : "") + " color");
	    
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    
	    frame.getContentPane().setLayout(new GridLayout(3, 1));
	    frame.getContentPane().add(hp);
	    frame.getContentPane().add(sp);
	    frame.getContentPane().add(bp);

	    frame.pack();
	    frame.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		try {
			if (source.equals(buttonValue)) searchCircle.setButtonValue(Double.valueOf(buttonValue.getValue()));
			if (source.equals(barValue)) searchCircle.setBarValue(Double.valueOf(barValue.getValue()));
			if (source.equals(startAngle)) searchCircle.setStartAngle(Double.valueOf(startAngle.getValue()));
			if (source.equals(viewAngle)) searchCircle.setViewAngle(Double.valueOf(viewAngle.getValue()));
		} catch (Exception e1) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
}
