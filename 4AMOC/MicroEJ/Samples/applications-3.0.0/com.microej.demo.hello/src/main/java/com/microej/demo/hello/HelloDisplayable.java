/*
 * Java
 *
 * Copyright 2008-2017 IS2T. All rights reserved.
 * For demonstration purpose only.
 * IS2T PROPRIETARY. Use is subject to license terms.
 */
package com.microej.demo.hello;

import java.io.IOException;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.EventGenerator;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.wadapps.management.ActivitiesScheduler;

/**
 * Displays MicroEJ image and a list of "Hello" messages.
 */
public class HelloDisplayable extends Displayable implements EventHandler{

	private static final int HOME_PADDING = 10;
	private static final int PADDING_TEXT 					= 5;
	private static final int PADDING_BETWEEN_IMAGE_AND_TEXT = 30;

	private final HelloMessage[] messages;

	private Image microejImage;
	private Image homeImage;

	public HelloDisplayable(HelloMessage[] messages) {
		super(Display.getDefaultDisplay());
		this.messages = messages;
		try {
			microejImage = Image.createImage("/com/microej/demo/hello/images/microej.png");
			homeImage = Image.createImage("/com/microej/demo/hello/images/home.png");
		}
		catch (IOException e) {
			throw new AssertionError(e);
		}
	}

	@Override
	public void paint(GraphicsContext g) {
		// clean
		g.setColor(Colors.WHITE);
		int width = getDisplay().getWidth();
		int height = getDisplay().getHeight();
		g.fillRect(0, 0, width, height);

		// compute vertical center
		int y = height / 2;
		int microejImageHeight = microejImage.getHeight();
		y -= (microejImageHeight + PADDING_BETWEEN_IMAGE_AND_TEXT) / 2;
		for (HelloMessage message : this.messages) {
			y -= (message.getFont().getHeight() + PADDING_TEXT) / 2;
		}

		// draw MicroEJ image
		g.drawImage(microejImage, width / 2, y, GraphicsContext.HCENTER | GraphicsContext.TOP);
		y += microejImageHeight + PADDING_BETWEEN_IMAGE_AND_TEXT;

		// draw each message
		g.setColor(0x262A2C);
		for (HelloMessage message : this.messages) {
			g.setFont(message.getFont());
			g.drawString(message.getHelloString(), width / 2, y, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
			y += message.getFont().getHeight() + PADDING_TEXT;
		}

		// draw Home image
		g.drawImage(homeImage, HOME_PADDING, height - HOME_PADDING, GraphicsContext.BOTTOM | GraphicsContext.LEFT);

	}

	@Override
	public EventHandler getController() {
		return this;
	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Event.POINTER) {
			Pointer p = (Pointer) EventGenerator.get(Event.getGeneratorID(event));
			if (Pointer.isPressed(event)) {
				int height = homeImage.getHeight();
				if (isIncluded(p, HOME_PADDING, Display.getDefaultDisplay().getHeight() - height - HOME_PADDING, homeImage.getWidth(), height)) {
					// here, the user has pressed on the image
					System.out.println("=> EXIT REQUEST");
					ActivitiesScheduler scheduler = ServiceLoaderFactory.getServiceLoader()
							.getService(ActivitiesScheduler.class);
					if (scheduler != null) {
						// An activity scheduler is registered in the system
						// Trigger a come back to the registered launcher
						System.out.println("=> SHOW LAUNCHER");
						scheduler.showLauncher();
					}
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isIncluded(Pointer p, int startX, int startY, int width, int height) {
		int x = p.getX();
		int y = p.getY();
		return (x >= startX && x < width + startX) && (y >= startY && y < height + startY);
	}

}
