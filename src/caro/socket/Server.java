package caro.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import caro.view.GameCaro;
import caro.view.WaitRoom;

public class Server {
	Message msg;
	GameCaro gameCaro = new GameCaro();
	ServerSocket listener = null;
	Socket socketOfServer = null;
	WaitRoom waitRoom = null;

	public Server() {
		try {
			int port = (int) (Math.random() * 8975) + 1024;
			listener = new ServerSocket(port);
			waitRoom = new WaitRoom(port);
			new Thread(() -> {
				try {
					socketOfServer = listener.accept();
					msg = new Message(socketOfServer);
					waitRoom.dispose();
					gameCaro = new GameCaro(0, msg);
					msg.getGame(gameCaro);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}