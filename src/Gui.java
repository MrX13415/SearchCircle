import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SearchCircle;
import javax.swing.SearchCircle.Anchor;
import javax.swing.SearchCircle.STYLE;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Gui implements ActionListener, KeyListener, ChangeListener{
	
	SearchCircle searchCircle = null;
	
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
	
	JComboBox direction;
	JComboBox style;		
	JComboBox anchor;

	JButton buttonColor;
	JButton barColor;
	JButton backgroundColor;
	JButton move;
	
	JPanel mainPanel;
	JFrame window;
	
	JFrame parent;
	JColorChooser cc;
	
	public Gui(SearchCircle searchCircle, JFrame parent) {
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

		direction = new JComboBox();
		direction.addActionListener(this);
		direction.addItem(directionitem1);
		direction.addItem(directionitem2);		
		if (searchCircle.getDirection() == SearchCircle.BAR_DIRECTION_RIGHT) direction.setSelectedItem(directionitem2);
		
		style = new JComboBox();
		style.addActionListener(this);
		STYLE stylevalue = searchCircle.getStyle();
		for (SearchCircle.STYLE currentStyle : SearchCircle.STYLE.values()) {
			style.addItem(currentStyle.toString());
		}
		style.setSelectedItem(stylevalue.toString());
		
		anchor = new JComboBox();
		anchor.addActionListener(this);
		
		Anchor posvalue = searchCircle.getAnchor();
		for (SearchCircle.Anchor currentPos : SearchCircle.Anchor.values()) {
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
				if (direction.getSelectedItem().equals(directionitem1)) searchCircle.setDirection(SearchCircle.BAR_DIRECTION_LEFT);
				if (direction.getSelectedItem().equals(directionitem2)) searchCircle.setDirection(SearchCircle.BAR_DIRECTION_RIGHT);			
			}
			
			if (source.equals(style)) searchCircle.setStyle(STYLE.valueOf(style.getSelectedItem().toString()));		
			if (source.equals(anchor)) searchCircle.setAnchor(Anchor.valueOf(anchor.getSelectedItem().toString()));		

			if (source.equals(buttonColor)) changeColor(buttonColor, new SearchCircle.ImageModifier(searchCircle.getButtonImage()).getAverageColor());
			if (source.equals(barColor)) changeColor(barColor, new SearchCircle.ImageModifier(searchCircle.getBarImage()).getAverageColor());
			if (source.equals(backgroundColor)) changeColor(backgroundColor, new SearchCircle.ImageModifier(searchCircle.getBarBackgroundImage()).getAverageColor());
			if (source.equals(move)) SCTest.automove = !SCTest.automove;
			
		
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
		final JColorChooser newColor = new JColorChooser(color);
	
		ColorSelectionModel model = newColor.getSelectionModel();
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {

			if (newColor != null){
				if (source.equals(buttonColor)) searchCircle.setButtonColor(newColor.getColor());
				if (source.equals(barColor)) searchCircle.setBarColor(newColor.getColor());
				if (source.equals(backgroundColor)) searchCircle.setBackgroundColor(newColor.getColor());
			}
	      }
	    };
	    model.addChangeListener(changeListener);
	    
	    JFrame frame = new JFrame("JColorChooser Popup");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    frame.add(newColor, BorderLayout.CENTER);

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
