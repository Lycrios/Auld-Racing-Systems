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
	}
	
	private boolean foundServer = false;
	private String serverIP = "";
	
	@Override
	public void run() {
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

					boolean sF = false;
					int track = 0 + (current * 20);
					setName("Scanner: " + track + " - " + (track + 20));
					for (int t = track; t < (track + 20); t++) {
						// System.out.println("IP:" + t);
						Socket kkSocket = null;
						try {
							kkSocket = new Socket();
							kkSocket.bind(null); // bind socket to random local
													// address, but you might
													// not
													// need to do this

							kkSocket.connect(new InetSocketAddress("192.168.1." + t, Server.PORT), 500);

							if (kkSocket.isConnected()) {
								System.out.println("Server Found");
								serverIP = "192.168.1." + t;
								sF = true;
								foundServer = true;
							}
						} catch (IOException e) {
							// Nothing found
						} finally {
							if (!kkSocket.isClosed()) {
								try {
									kkSocket.close();
								} catch (IOException e) {

								}
							}
						}
					}
					if (sF) {
						System.out.println("Server IP: " + serverIP);
					}
					ThreadCount++;
					if (ThreadCount == 13) {
						System.out.println("Finished Network Check.");

						Socket kkSocket = null;
						try {
							kkSocket = new Socket();
							kkSocket.bind(null); // bind socket to random local
													// address, but you might
													// not
													// need to do this

							kkSocket.connect(new InetSocketAddress("127.0.1.1", Server.PORT), 500);

							if (kkSocket.isConnected()) {
								System.out.println("Server Found");
								serverIP = "127.0.0.1";
								sF = true;
							}
						} catch (IOException e) {
							// Nothing found
						} finally {
							if (!kkSocket.isClosed()) {
								try {
									kkSocket.close();
								} catch (IOException e) {

								}
							}
						}

						c.setStatus("Network Scan Completed... " + (foundServer ? "Host Found..." : "No Host Found..."));
						if (!foundServer) {
							becomeHost();
						} else {
							connectToHost(serverIP);
						}
					}
				}
			}.start();
		}
	}
}
