package caro.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import caro.view.GameCaro;
import caro.view.ShowMess;
import caro.view.Start;

public class Message {
	private PrintWriter out;
	private BufferedReader reader;
	private GameCaro gameCaro;

	public Message(Socket socket) throws IOException {
		out = new PrintWriter(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		receive();
	}

	public void getGame(GameCaro game) {
		this.gameCaro = game;
	}

	private void receive() {
		new Thread(() -> {
			while (true) {
				try {
					String line = reader.readLine();
					if (line != null) {
						if (line.equals("1")) {
							int result = JOptionPane.showConfirmDialog(null,
									"Đối phương muốn chơi ván mới. Bạn có đồng ý?", "Đồng ý", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (result == JOptionPane.YES_OPTION) {
								gameCaro.dispose();
								gameCaro = new GameCaro((++gameCaro.ngChoi) % 2, Message.this);
								send("3");
							} else if (result == JOptionPane.NO_OPTION) {
								send("4");
							}
						} else if (line.equals("2")) {
							new ShowMess("Đối phương đã thoát game");
							gameCaro.dispose();
							new Start();
						} else if (line.equals("3")) {
							gameCaro.dispose();
							gameCaro = new GameCaro((++gameCaro.ngChoi) % 2, Message.this);
						} else if (line.equals("4")) {
							new ShowMess("Đối phương không đồng ý chơi ván mới");
						} else {
							gameCaro.getLock(false);
							String point[] = line.split(" ");
							gameCaro.addPoint(Integer.parseInt(point[0]), Integer.parseInt(point[1]), 1);
						}
					}
				} catch (Exception e) {
				}
			}
		}).start();
	}

	public void send(String msg) {
		out.println(msg);
		out.flush();
	}
}
