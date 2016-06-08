package net.matthewauld.auldracingsystems.server;

import java.net.Socket;

public class ClientConnections extends Thread {

	public ClientConnections(Socket s) {
		System.out.println("Connection: " + s.getInetAddress().getHostName());
	}

}
