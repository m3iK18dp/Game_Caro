package caro.appmain;

import java.awt.EventQueue;

import caro.view.Start;

public class AppMain {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
