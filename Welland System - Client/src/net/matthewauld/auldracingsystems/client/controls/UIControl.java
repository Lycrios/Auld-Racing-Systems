/**
 * @author Matthew Donald Auld
 *         File: UIControl.java
 *         Started on Jun 7, 2016 at 1:31:05 AM
 *         Copyright 2016 (c) to Matthew Auld.
 *         ALL RIGHTS RESERVED
 */
package net.matthewauld.auldracingsystems.client.controls;

import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class UIControl {
	protected int x = 0, y = 0, width = 0, height = 0;

	public abstract void render(Graphics2D g);

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// GETTERS

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}
}
