package net.matthewauld.auldracingsystems.client.controls;

import java.awt.Graphics2D;
import java.util.ArrayList;

public interface UIHandler {
	public ArrayList<UIControl> controls = new ArrayList<UIControl>();

	public default void addControl(UIControl c) {
		controls.add(c);
	}

	public default void renderControls(Graphics2D g) {
		for (UIControl c : controls) {
			c.render(g);
		}
	}
}
