package net.mrx13415.searchcircle.imageutil;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;

import net.mrx13415.searchcircle.imageutil.color.HSB;
import net.mrx13415.searchcircle.imageutil.color.RGB;


public class ImageModifier {

	public static final float HSB_MAX_VALUE = 1.0f;
	public static final float HSB_MIN_VALUE = 0.0f;

	private BufferedImage image = null;
	private HSB modHSB = new HSB(0, 0, 0);
	private Color averageColor;

	public ImageModifier(Image image) {
		this.image = createNewCompatibleBufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		this.image.getGraphics().drawImage(image, 0, 0, null);
		this.image.getGraphics().dispose();
		
		determineImageColorInformation();
	}

	public ImageModifier(BufferedImage image) {
		this.image = image;
		
		determineImageColorInformation();
	}

	public static BufferedImage createNewCompatibleBufferedImage(int width, int height, int transp)
	{
		BufferedImage image = new BufferedImage(width, height, transp);
		
		// obtain the current system graphical settings
		GraphicsConfiguration gEnv = GraphicsEnvironment.
			getLocalGraphicsEnvironment().getDefaultScreenDevice().
			getDefaultConfiguration();

		
		// if image is already compatible and optimized for current system settings, simply return it
		if (image.getColorModel().equals(gEnv.getColorModel()))
			return image;

		// image is not optimized, so create a new image that is
		BufferedImage newImage = gEnv.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());

		return newImage;
	}

	/**
	 * Applies all changes to the Image.<br/>
	 * <br/>
	 * <b>Warning: </b>This method may take some time to complete.<br/>
	 * <br/>
	 * 
	 * @return The modified image as Buffered Image.
	 */
	public BufferedImage modify() {
		BufferedImage newImage = this.image;

		for (int indexX = 0; indexX < newImage.getWidth(); indexX++) {
			for (int indexY = 0; indexY < newImage.getHeight(); indexY++) {
				// get HSB from each pixel ...
				HSB pixelHSB = getHSBfromPixel(newImage, indexX, indexY);
				HSB newHSB = new HSB(pixelHSB);

				newHSB.setHue(getMinMax(pixelHSB.getHue() + modHSB.getHue()));
				newHSB.setSaturation(getMinMax(pixelHSB.getSaturation() + modHSB.getSaturation()));
				newHSB.setBrightness(getMinMax(pixelHSB.getBrightness() + modHSB.getBrightness()));

				// get RGB from HSB ...
				RGB newrgb = new RGB(Color.HSBtoRGB(newHSB.getHue(),
						newHSB.getSaturation(), newHSB.getBrightness()));

				// restore alpha ...
				newrgb.setAlpha(new RGB(image.getRGB(indexX, indexY))
						.getAlpha());

				// set pixel ...
				newImage.setRGB(indexX, indexY, newrgb.getRGB());
			}
		}

		return newImage;
	}

	/**
	 * Get the average color of the image.
	 * 
	 * @return Average image color.
	 */
	public Color getAverageColor() {
		return averageColor;
	}
	
	private void determineImageColorInformation() {
		BufferedImage newImage = this.image;

		int pixelCounts = newImage.getWidth() * newImage.getHeight();

		int red = 0;
		int green = 0;
		int blue = 0;
		
		for (int indexX = 0; indexX < newImage.getWidth(); indexX++) {
			for (int indexY = 0; indexY < newImage.getHeight(); indexY++) {
				// get RGB from each pixel ...
				RGB rgb = getRGBfromPixel(newImage, indexX, indexY);

				red += rgb.getRed();
				green += rgb.getGreen();
				blue += rgb.getBlue();
			}
		}

		red /= pixelCounts;
		green /= pixelCounts;
		blue /= pixelCounts;

		averageColor = new Color(red, green, blue);
	}

	public static float getMinMax(float value) {
		if (value > HSB_MAX_VALUE) {
			value = HSB_MAX_VALUE;
		}

		if (value < HSB_MIN_VALUE) {
			value = HSB_MIN_VALUE;
		}
		return value;
	}

	/**
	 * returns the HSB value from the given Color
	 * 
	 * @param The
	 *            color
	 * @return A instance of SearchCircle.ImageModifier.HSB
	 */
	public static HSB getHsSBfromColor(Color color) {
		return new HSB(color);
	}

	/**
	 * returns the HSB of a Pixel from a given image.
	 * 
	 * @param the
	 *            image
	 * @param position
	 *            x
	 * @param position
	 *            y
	 * @return A instance of SearchCircle.ImageModifier.HSB
	 */
	public static HSB getHSBfromPixel(BufferedImage img, int x, int y) {
		RGB rgb = new RGB(img.getRGB(x, y));

		float[] hsbValues = Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(),
				rgb.getBlue(), null);
		HSB hsb = new HSB(hsbValues);
		return hsb;
	}

	/**
	 * returns the RGB of a Pixel from a given image.
	 * 
	 * @param the
	 *            image
	 * @param position
	 *            x
	 * @param position
	 *            y
	 * @return A instance of SearchCircle.ImageModifier.RGB
	 */
	public static RGB getRGBfromPixel(BufferedImage img, int x, int y) {
		RGB rgb = new RGB(img.getRGB(x, y));
		return rgb;
	}

	/**
	 * set the HSB of this Image. use <code>modify()</code> method the apply
	 * all changes.
	 * 
	 * @param A instance of <code>SearchCircle.ImageModifier.HSB</code>
	 */
	public void SetHSB(HSB hsb) {
		this.modHSB = hsb;
	}

	public BufferedImage getImage() {
		return image;
	}

	public HSB getHSB() {
		return modHSB;
	}

	/**
	 * Set the hue of the Image. use <code>modify()</code> method the apply
	 * all changes.
	 * 
	 * @param Hue
	 *            (range: 0.0f - 1.0f)
	 */
	public void setHue(float hue) {
		this.modHSB.setHue(hue);
	}
	
	/**
	 * Set the saturation of the Image. use <code>modify()</code> method the
	 * apply all changes.
	 * 
	 * @param saturation 
	 *                   (range: 0.0f - 1.0f)
	 */
	public void setSaturation(float saturation) {
		this.modHSB.setSaturation(saturation);
	}

	/**
	 * Set the brightness of the Image. use <code>modify()</code> method the
	 * apply all changes.
	 * 
	 * @param brightness 
	 *                   (range: 0.0f - 1.0f)
	 */
	public void setBrightness(float brightness) {
		this.modHSB.setBrightness(brightness);
	}

}

