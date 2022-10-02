package caro.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import caro.socket.Server;

public class Start extends JFrame {

	private JPanel contentPane;

	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 12));
		setForeground(Color.BLACK);
		setTitle("START CARO GAME");
		setType(Type.UTILITY);
		setBounds(100, 100, 674, 441);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Start.class.getResource("/caro/icon/logo.png")));
		JButton btn_taophong = new JButton("Tạo phòng");
		btn_taophong.setBackground(Color.BLUE);
		btn_taophong.setFont(new Font("Arial", Font.BOLD, 16));
		btn_taophong.setForeground(Color.RED);
		btn_taophong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Server();
			}
		});

		JButton btn_thamgiaphong = new JButton("Tham gia");
		btn_thamgiaphong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Join();
			}
		});
		btn_thamgiaphong.setBackground(Color.RED);
		btn_thamgiaphong.setFont(new Font("Arial", Font.BOLD, 16));
		btn_thamgiaphong.setForeground(Color.BLUE);
		JButton btn_thoat = new JButton("Thoát");
		btn_thoat.setBackground(Color.BLACK);
		btn_thoat.setFont(new Font("Arial", Font.BOLD, 16));
		btn_thoat.setForeground(Color.MAGENTA);
		btn_thoat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(526)
						.addComponent(btn_thoat, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE).addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup().addGap(220)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_thamgiaphong, GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE)
								.addComponent(btn_taophong, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
						.addGap(210))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(137)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(135)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE).addGap(11)
						.addComponent(btn_taophong, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE).addGap(18)
						.addComponent(btn_thamgiaphong, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btn_thoat, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}
}
