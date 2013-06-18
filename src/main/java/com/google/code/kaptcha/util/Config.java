package com.google.code.kaptcha.util;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultBackground;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.impl.DefaultNoise;
import com.google.code.kaptcha.impl.WaterRipple;
import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.text.WordRenderer;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import com.google.code.kaptcha.text.impl.DefaultWordRenderer;

/**
 * {@link Config} retrieves configuration values from properties file and
 * specifies default values when no value is specified.
 */
public class Config implements Configuration
{
	/** */
	private Properties properties;

	/** */
	private ConfigHelper helper = new ConfigHelper();

	private Boolean drawBorder;
	private Color borderColor;
	private Integer borderThickness;
	private char[] charSet;
	private Integer charLength;
	private Integer fontSize;
	private Color fontColor;
	private Integer charSpace;
	private Color noiseColor;
	private Color colorFrom;
	private Color colorTo;
	private Integer width;
	private Integer height;

	public Config() {
		this.properties = new Properties();
	}	
	/** */
	public Config(Properties properties)
	{
		this.properties = properties;
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#isBorderDrawn()
	 */
	@Override
	public boolean isBorderDrawn()
	{
		String paramName = Constants.KAPTCHA_BORDER;
		String paramValue = this.properties.getProperty(paramName);
		return this.drawBorder != null ? this.drawBorder : this.helper.getBoolean(paramName, paramValue, true);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getBorderColor()
	 */
	@Override
	public Color getBorderColor()
	{
		String paramName = Constants.KAPTCHA_BORDER_COLOR;
		String paramValue = this.properties.getProperty(paramName);
		return this.borderColor != null ? this.borderColor : this.helper.getColor(paramName, paramValue, Color.BLACK);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getBorderThickness()
	 */
	@Override
	public int getBorderThickness()
	{
		String paramName = Constants.KAPTCHA_BORDER_THICKNESS;
		String paramValue = this.properties.getProperty(paramName);
		return this.borderThickness != null ? this.borderThickness : this.helper.getPositiveInt(paramName, paramValue, 1);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getProducerImpl()
	 */
	@Override
	public Producer getProducerImpl()
	{
		String paramName = Constants.KAPTCHA_PRODUCER_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		Producer producer = (Producer) this.helper.getClassInstance(paramName, paramValue, new DefaultKaptcha(), this);
		return producer;
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getTextProducerImpl()
	 */
	@Override
	public TextProducer getTextProducerImpl()
	{
		String paramName = Constants.KAPTCHA_TEXTPRODUCER_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		TextProducer textProducer = (TextProducer) this.helper.getClassInstance(paramName, paramValue,
				new DefaultTextCreator(), this);
		return textProducer;
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getTextProducerCharString()
	 */
	@Override
	public char[] getTextProducerCharString()
	{
		String paramName = Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING;
		String paramValue = this.properties.getProperty(paramName);
		return this.charSet != null ? this.charSet : this.helper.getChars(paramName, paramValue, "abcde2345678gfynmnpwx".toCharArray());
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getTextProducerCharLength()
	 */
	@Override
	public int getTextProducerCharLength()
	{
		String paramName = Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH;
		String paramValue = this.properties.getProperty(paramName);
		return this.charLength != null ? this.charLength : this.helper.getPositiveInt(paramName, paramValue, 5);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getTextProducerFonts(int)
	 */
	@Override
	public Font[] getTextProducerFonts(int fontSize)
	{
		String paramName = Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getFonts(paramName, paramValue, fontSize, new Font[]{
				new Font("Arial", Font.BOLD, fontSize), new Font("Courier", Font.BOLD, fontSize)
		});
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getTextProducerFontSize()
	 */
	@Override
	public int getTextProducerFontSize()
	{
		String paramName = Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE;
		String paramValue = this.properties.getProperty(paramName);
		return this.fontSize != null ? this.fontSize : this.helper.getPositiveInt(paramName, paramValue, 40);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getTextProducerFontColor()
	 */
	@Override
	public Color getTextProducerFontColor()
	{
		String paramName = Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR;
		String paramValue = this.properties.getProperty(paramName);
		return this.fontColor != null ? this.fontColor : this.helper.getColor(paramName, paramValue, Color.BLACK);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getTextProducerCharSpace()
	 */
	@Override
	public int getTextProducerCharSpace()
    {
		String paramName = Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE;
		String paramValue = properties.getProperty(paramName);
		return this.charSpace != null ? this.charSpace : this.helper.getPositiveInt(paramName, paramValue, 2);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getNoiseImpl()
	 */
	@Override
	public NoiseProducer getNoiseImpl()
	{
		String paramName = Constants.KAPTCHA_NOISE_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		NoiseProducer noiseProducer = (NoiseProducer) this.helper.getClassInstance(paramName, paramValue,
				new DefaultNoise(), this);
		return noiseProducer;
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getNoiseColor()
	 */
	@Override
	public Color getNoiseColor()
	{
		String paramName = Constants.KAPTCHA_NOISE_COLOR;
		String paramValue = this.properties.getProperty(paramName);
		return this.noiseColor != null ? this.noiseColor : this.helper.getColor(paramName, paramValue, Color.BLACK);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getObscurificatorImpl()
	 */
	@Override
	public GimpyEngine getObscurificatorImpl()
	{
		String paramName = Constants.KAPTCHA_OBSCURIFICATOR_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		GimpyEngine gimpyEngine = (GimpyEngine) this.helper.getClassInstance(paramName, paramValue, new WaterRipple(), this);
		return gimpyEngine;
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getWordRendererImpl()
	 */
	@Override
	public WordRenderer getWordRendererImpl()
	{
		String paramName = Constants.KAPTCHA_WORDRENDERER_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		WordRenderer wordRenderer = (WordRenderer) this.helper.getClassInstance(paramName, paramValue,
				new DefaultWordRenderer(), this);
		return wordRenderer;
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getBackgroundImpl()
	 */
	@Override
	public BackgroundProducer getBackgroundImpl()
	{
		String paramName = Constants.KAPTCHA_BACKGROUND_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		BackgroundProducer backgroundProducer = (BackgroundProducer) this.helper.getClassInstance(paramName, paramValue,
				new DefaultBackground(), this);
		return backgroundProducer;
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getBackgroundColorFrom()
	 */
	@Override
	public Color getBackgroundColorFrom()
	{
		String paramName = Constants.KAPTCHA_BACKGROUND_CLR_FROM;
		String paramValue = this.properties.getProperty(paramName);
		return this.colorFrom != null ? this.colorFrom : this.helper.getColor(paramName, paramValue, Color.LIGHT_GRAY);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getBackgroundColorTo()
	 */
	@Override
	public Color getBackgroundColorTo()
	{
		String paramName = Constants.KAPTCHA_BACKGROUND_CLR_TO;
		String paramValue = this.properties.getProperty(paramName);
		return this.colorTo != null ? this.colorTo : this.helper.getColor(paramName, paramValue, Color.WHITE);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getWidth()
	 */
	@Override
	public int getWidth()
	{
		String paramName = Constants.KAPTCHA_IMAGE_WIDTH;
		String paramValue = this.properties.getProperty(paramName);
		return this.width != null ? this.width : this.helper.getPositiveInt(paramName, paramValue, 200);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getHeight()
	 */
	@Override
	public int getHeight()
	{
		String paramName = Constants.KAPTCHA_IMAGE_HEIGHT;
		String paramValue = this.properties.getProperty(paramName);
		return this.height != null ? this.height : this.helper.getPositiveInt(paramName, paramValue, 50);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getSessionKey()
	 */
	@Override
	public String getSessionKey()
	{
		return this.properties.getProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, Constants.KAPTCHA_SESSION_KEY);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getSessionDate()
	 */
	@Override
	public String getSessionDate()
	{
		return this.properties.getProperty(Constants.KAPTCHA_SESSION_CONFIG_DATE, Constants.KAPTCHA_SESSION_DATE);
	}

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.util.Configuration#getProperties()
	 */
	@Override
	public Properties getProperties()
	{
		return this.properties;
	}
	
	public Config withBorder(Boolean border) {
		this.drawBorder = border;
		return this;
	}
	
	public Config withBorderColor(Color color) {
		this.borderColor = color;
		return this;
	}
	
	public Config withBorderThickness(Integer borderThickness) {
		this.borderThickness = borderThickness;
		return this;
	}

	public Config withCharSet(char[] charSet) {
		this.charSet = charSet;
		return this;
	}
	
	public Config withCharLength(Integer charLength) {
		this.charLength = charLength;
		return this;
	}
	
	public Config withFontSize(Integer fontSize) {
		this.fontSize = fontSize;
		return this;
	}

	public Config withFontColor(Color fontColor) {
		this.fontColor = fontColor;
		return this;
	}
	
	public Config withCharSpace(Integer charSpace) {
		this.charSpace = charSpace;
		return this;
	}

	public Config withNoiseColor(Color noiseColor) {
		this.noiseColor = noiseColor;
		return this;
	}
	
	public Config withColorFrom(Color from) {
		this.colorFrom = from;
		return this;
	}

	public Config withColorTo(Color to) {
		this.colorTo = to;
		return this;
	}

	public Config withWidth(Integer width) {
		this.width = width;
		return this;
	}
	
	public Config withHeight(Integer height) {
		this.height = height;
		return this;
	}
	
}
