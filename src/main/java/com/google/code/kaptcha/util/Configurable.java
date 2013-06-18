package com.google.code.kaptcha.util;


/**
 * This interface determines if a class can be configured by properties handled
 * by config manager.
 */
public abstract class Configurable
{
	private Configuration config = null;

	public Configuration getConfig()
	{
		return this.config;
	}

	public void setConfig(Configuration config)
	{
		this.config = config;
	}
}
