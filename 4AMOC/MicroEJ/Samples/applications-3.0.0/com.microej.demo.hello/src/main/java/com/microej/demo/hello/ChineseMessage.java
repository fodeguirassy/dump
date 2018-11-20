/*
 * Java
 *
 * Copyright 2008-2015 IS2T. All rights reserved.
 * For demonstration purpose only.
 * IS2T PROPRIETARY. Use is subject to license terms.
 */
package com.microej.demo.hello;

import ej.microui.display.Font;

public class ChineseMessage implements HelloMessage {

	private final String message = "\u60a8\u597d";
	private final Font font = Font.getFont(Font.HAN, 20, Font.STYLE_PLAIN);

	@Override
	public Font getFont() {
		return this.font;
	}

	@Override
	public String getHelloString() {
		return this.message;
	}
}
