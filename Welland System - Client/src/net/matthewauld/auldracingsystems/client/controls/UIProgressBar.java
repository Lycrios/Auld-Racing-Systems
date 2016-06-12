/**
 * @author Matthew Donald Auld
 *         File: UIProgressBar.java
 *         Started on Jun 7, 2016 at 1:31:19 AM
 *         Copyright 2016 (c) to Matthew Auld.
 *         ALL RIGHTS RESERVED
 */
package net.matthewauld.auldracingsystems.client.controls;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

import net.matthewauld.auldracingsystems.client.Colors;

public class UIProgressBar extends UIControl {
	private float	progress	= 50;

	public UIProgressBar() {
		x = 0;
		y = 0;
		width = 100;
		height = 25;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Colors.progressBackground);
		g.fillRect(x, y, width, height);

		// Draw Progress
		g.setColor(Colors.progressColor);
		g.fillRect(x, y, (int) ((width / 100) * progress), height);

		// Add Shine
		g.setPaint(new GradientPaint(new Point(x, y + 2), Colors.progressHighlight, new Point(x, height / 3), Colors.clearWhite));
		g.fillRect(x + 2, y + 2, width - 4, height / 3);

		g.setColor(Colors.progressBorder);
		g.drawRect(x, y, width - 1, height - 1);
		g.setColor(new Color(255, 255, 255, 128));
		g.drawRect(x + 1, y + 1, width - 3, height - 3);
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

}
