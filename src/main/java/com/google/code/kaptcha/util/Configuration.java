package com.google.code.kaptcha.util;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.text.WordRenderer;

public interface Configuration {

	
	boolean isBorderDrawn();

	
	Color getBorderColor();

	
	int getBorderThickness();

	
	Producer getProducerImpl();

	
	TextProducer getTextProducerImpl();

	
	char[] getTextProducerCharString();

	
	int getTextProducerCharLength();

	
	Font[] getTextProducerFonts(int fontSize);

	
	int getTextProducerFontSize();

	
	Color getTextProducerFontColor();

	
	int getTextProducerCharSpace();

	
	NoiseProducer getNoiseImpl();

	
	Color getNoiseColor();

	
	GimpyEngine getObscurificatorImpl();

	
	WordRenderer getWordRendererImpl();

	
	BackgroundProducer getBackgroundImpl();

	
	Color getBackgroundColorFrom();

	
	Color getBackgroundColorTo();

	
	int getWidth();

	
	int getHeight();

	/**
	 * Allows one to override the key name which is stored in the users
	 * HttpSession. Defaults to Constants.KAPTCHA_SESSION_KEY.
	 */
	String getSessionKey();

	/**
	 * Allows one to override the date name which is stored in the
	 * users HttpSession. Defaults to Constants.KAPTCHA_SESSION_KEY.
	 */
	String getSessionDate();

	/** */
	Properties getProperties();

}