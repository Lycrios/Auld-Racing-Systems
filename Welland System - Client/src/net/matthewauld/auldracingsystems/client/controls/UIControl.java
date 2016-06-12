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
import java.awt.event.MouseEvent;

public abstract class UIControl {
	protected int	x	= 0, y = 0, width = 0, height = 0;

	public int getHeight() {
		return this.height;
	}

	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	/**
	 * <b><i>getWidth</i></b>
	 * <p>
	 * Get the width of the <b>UIControl</b>.
	 *
	 * @return The width of the <b>UIControl</b>
	 */
	public int getWidth() {
		return this.width;
	}

	public boolean isClicked(MouseEvent e) {
		return (e.getX() >= this.x) && (e.getY() >= this.y) && (e.getX() <= (this.x + this.width)) && (e.getY() <= (this.y + this.height));
	}

	public abstract void render(Graphics2D g);

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Set the location of the control by X and Y coordinates.
	 *
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Set the X coordinate.
	 *
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set the Y coordinate.
	 *
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
}
