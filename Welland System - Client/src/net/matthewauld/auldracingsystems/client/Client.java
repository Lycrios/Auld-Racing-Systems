/**
 * @author Matthew Donald Auld
 *         File: Client.java
 *         Started on Jun 7, 2016 at 12:55:01 AM
 *         Copyright 2016 (c) to Matthew Auld.
 *         ALL RIGHTS RESERVED
 */
package net.matthewauld.auldracingsystems.client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import net.matthewauld.auldracingsystems.client.controls.UIHandler;
import net.matthewauld.auldracingsystems.client.controls.UIProgressBar;

public class Client extends Canvas implements Runnable, UIHandler {
	private static final long	serialVersionUID	= 6041503378120923470L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Client();
	}

	private boolean	isRunning	= true;

	private JFrame	loadingWindow;

	private Socket	socket;
	private String	status		= "Initializing... Please Wait...";

	public Client() {
		loadingWindow = new JFrame("Initializing \"Auld Racing Systems\"");
		loadingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingWindow.setResizable(false);
		loadingWindow.setSize(500, 100);
		loadingWindow.setLocationRelativeTo(null);
		loadingWindow.add(this, 0);

		loadingWindow.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Closing Application");
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		loadingWindow.setVisible(true);
		createBufferStrategy(2);
		final Client c = this;
		new Thread() {
			@Override
			public void run() {
				setName("Graphics Thread");
				c.run();
			}
		}.start();

		NetworkScanner ns = new NetworkScanner(this);
		ns.start();
		initControls();
	}

	private void initControls() {
		UIProgressBar progress = new UIProgressBar();
		progress.setWidth(400);
		progress.setLocation((getWidth() / 2) - (progress.getWidth() / 2), (getHeight() / 2));
		progress.setProgress(100f);
		addControl(progress);
	}

	private void render(Graphics2D g) {
		g.setColor(loadingWindow.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.drawString(status, (getWidth() / 2) - (g.getFontMetrics().stringWidth(status) / 2), (getHeight() / 6) + 12);

		renderControls(g);
	}

	@Override
	public void run() {
		setName("Graphics Thread");
		BufferStrategy bs = getBufferStrategy();

		while (isRunning) {
			render((Graphics2D) bs.getDrawGraphics());
			bs.show();
			try {
				Thread.sleep(32);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
