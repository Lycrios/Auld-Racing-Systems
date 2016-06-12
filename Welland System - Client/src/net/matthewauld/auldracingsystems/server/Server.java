package net.matthewauld.auldracingsystems.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread implements Runnable {
	public static final int					PORT		= 18188;
	private ArrayList<ClientConnections>	connections	= new ArrayList<ClientConnections>();
	private ServerSocket					serverSocket;

	public Server() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Port already in use.");
		}
		this.start();
	}

	protected void removeConnection(ClientConnections cc) {
		connections.remove(cc);
	}

	@Override
	public void run() {
		setName("LocalServer Socket");
		while (serverSocket.isBound()) {
			try {
				Socket s = serverSocket.accept();
				connections.add(new ClientConnections(this, s));
				System.out.println("Total Connections: " + connections.size());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
