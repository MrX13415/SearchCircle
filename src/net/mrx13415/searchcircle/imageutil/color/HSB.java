package net.mrx13415.searchcircle.imageutil.color;

import java.awt.Color;

public class HSB {

	private float hue = 0x00;
	private float saturation = 0x00;
	private float brightness = 0x00;

	public HSB() {
	}
	
	public HSB(Color color) {
		this(getHSBfromColor(color));		
	}
	
	public HSB(HSB hsb) {
		this.hue = hsb.getHue();
		this.saturation = hsb.getSaturation();
		this.brightness = hsb.getBrightness();
	}

	public HSB(float hue, float saturation, float brightness) {
		setHSB(hue, saturation, brightness);
	}

	public HSB(float[] hsbValues) {
		setHSB(hsbValues);
	}
	
	/**
	 * returns the HSB value from the given Color
	 * 
	 * @param The
	 *            color
	 * @return A instance of SearchCircle.ImageModifier.HSB
	 */
	public static HSB getHSBfromColor(Color color) {
		return new HSB((Color.RGBtoHSB(color.getRed(), color.getGreen(),
				color.getBlue(), null)));
	}
	
	public Color getColor() {
		return new Color(Color.HSBtoRGB(hue, saturation, brightness));
	}

	public float[] getHSB() {
		float[] hsb = { hue, saturation, brightness };
		return hsb;
	}

	public float getHue() {
		return hue;
	}

	public float getSaturation() {
		return saturation;
	}

	public float getBrightness() {
		return brightness;
	}

	public void setHSB(float hue, float saturation, float brightness) {
		this.hue = hue;
		this.saturation = saturation;
		this.brightness = brightness;
	}

	public void setHSB(float[] hsbValues) {
		if (hsbValues.length == 3)
			setHSB(hsbValues[0], hsbValues[1], hsbValues[2]);
	}

	public void setHue(float hue) {
		this.hue = hue;
	}

	public void setSaturation(float saturation) {
		this.saturation = saturation;
	}

	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}

	public String toString() {
		return this.getClass().getName() + "[h=" + hue + ";s=" + saturation	+ ";b=" + brightness + "]";
	}
}
