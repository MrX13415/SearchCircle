package net.mrx13415.searchcircle.imageutil.color;

public class RGB {

	private short alpha = 0x00;
	private short red = 0x00;
	private short green = 0x00;
	private short blue = 0x00;

	public RGB() {
	}

	public RGB(int rgb) {
		splitRGBValue(rgb);
	}

	private void splitRGBValue(int rgb) {
		alpha = (short) ((rgb >> 24) & 0xFF);
		red = (short) ((rgb >> 16) & 0xFF);
		green = (short) ((rgb >> 8) & 0xFF);
		blue = (short) ((rgb) & 0xFF);
	}

	private int makeRGBValue() {
		return ((alpha) << 24) | (((int) ((red))) << 16)
				| (((int) ((green))) << 8) | ((int) ((blue)));
	}

	public int getRGB() {
		return makeRGBValue();
	}

	public short getAlpha() {
		return alpha;
	}

	public short getRed() {
		return red;
	}

	public short getGreen() {
		return green;
	}

	public short getBlue() {
		return blue;
	}

	public void setRGB(int rgb) {
		splitRGBValue(rgb);
	}

	public void setAlpha(short alpha) {
		this.alpha = alpha;
	}

	public void setRed(int r) {
		this.red = (short) r;
	}

	public void setGreen(int g) {
		this.green = (short) g;
	}

	public void setBlue(int b) {
		this.blue = (short) b;
	}

	public String toString() {
		return "[r=" + red + ";g=" + green + ";b=" + blue + ";a="
				+ alpha + "]";
	}
}