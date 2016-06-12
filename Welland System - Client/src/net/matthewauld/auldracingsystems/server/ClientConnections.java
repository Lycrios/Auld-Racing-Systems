package net.matthewauld.auldracingsystems.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnections extends Thread {

	private Server	server;
	private Socket	socket;

	public ClientConnections(Server server, Socket s) {
		this.server = server;
		System.out.println("Connection: " + s.getInetAddress().getHostName());
		this.socket = s;
		try {
			in = new InputStreamReader(socket.getInputStream());
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void removeConnection() {
		server.removeConnection(this);
	}

	@Override
	public void run() {
		setName("Client: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
		while (socket.isConnected()) {
			System.out.println("Socket Alive");
		}
		removeConnection();
	}
}
