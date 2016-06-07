package net.matthewauld.auldracingsystems.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread implements Runnable {
	public static final int	PORT	= 18188;

	private ServerSocket	serverSocket;

	public Server() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Port already in use.");
		}
	}

	@Override
	public void run() {
		while (!serverSocket.isClosed()) {

		}
	}
}
