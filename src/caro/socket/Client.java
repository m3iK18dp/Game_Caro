package caro.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import caro.view.GameCaro;

public class Client {
	Message msg;
	GameCaro gameCaro = new GameCaro();
	Socket socketOfClient = null;

	public Client(String serverHost, int port) throws UnknownHostException, IOException {
		socketOfClient = new Socket(serverHost, port);
		msg = new Message(socketOfClient);
		gameCaro = new GameCaro(1, msg);
		msg.getGame(gameCaro);
	}

}