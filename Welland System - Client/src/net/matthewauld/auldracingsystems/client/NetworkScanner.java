/**
 * @author Matthew Donald Auld
 *         File: NetworkScanner.java
 *         Started on Jun 7, 2016 at 1:51:08 AM
 *         Copyright 2016 (c) to Matthew Auld.
 *         ALL RIGHTS RESERVED
 */
package net.matthewauld.auldracingsystems.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import net.matthewauld.auldracingsystems.server.Server;

public class NetworkScanner extends Thread implements Runnable {
	private static int	ThreadCount	= 0;
	private Client		c;

	private boolean		foundServer	= false;

	private String		serverIP	= "";
	private Socket		socket;

	public NetworkScanner(Client client) {
		this.c = client;
	}

	private void becomeHost() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		c.setStatus("Becoming host... Starting up server...");
		new Server();
	}

	private void connectToHost(String serverIP) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		c.setStatus("Connecting to: " + serverIP + "... Please Wait...");
		c.setSocket(this.socket);
	}

	@Override
	public void run() {
		setName("Network Scan");
		this.c.setStatus("Initializing... Please wait...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		this.c.setStatus("Scanning Network For Existing Host...");
		for (int i = 0; i <= 13; i++) {
			final int current = i;
			new Thread() {
				@Override
				public void run() {
					int track = 0 + (current * 20);
					setName("Scanner: " + track + " - " + (track + 20));
					try {
						Thread.sleep(100 * current);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					boolean sF = false;

					for (int t = track; t < (track + 20); t++) {
						if (foundServer) {
							break;
						}
						Socket kkSocket = null;
						try {
							kkSocket = new Socket();
							kkSocket.connect(new InetSocketAddress("192.168.1." + t, Server.PORT), 500);

							if (kkSocket.isConnected()) {
								System.out.println("Server Found");
								serverIP = "192.168.1." + t;
								sF = true;
								foundServer = true;
								socket = kkSocket;
								break;
							}
						} catch (IOException e) {
							// Nothing found
						}
					}
					if (sF) {
						System.out.println("Server IP: " + serverIP);
					}
					ThreadCount++;
				}
			}.start();
		}

		new Thread() {
			@Override
			public void run() {
				setName("Waiting For Network Scan");
				while (ThreadCount < 13) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Finished Network Check.");

				c.setStatus("Network Scan Completed... " + (foundServer ? "Host Found..." : "No Host Found..."));
				if (!foundServer) {
					becomeHost();
				} else {
					connectToHost(serverIP);
				}

			}
		}.start();
	}
}
