/*
 * Java
 *
 * Copyright 2008-2015 IS2T. All rights reserved.
 * For demonstration purpose only.
 * IS2T PROPRIETARY. Use is subject to license terms.
 */
package com.microej.demo.hello;

import ej.microui.display.Font;

public class ArabicMessage implements HelloMessage {

	private final String message =  "\ufee1\ufedb\ufef3\ufedf\ufecb      \ufee1\ufefc\ufeb4\ufedf\ufe8d";
	private final Font font = Font.getFont(Font.ARABIC, 20, Font.STYLE_PLAIN);

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public String getHelloString() {
		return message;
	}
}
