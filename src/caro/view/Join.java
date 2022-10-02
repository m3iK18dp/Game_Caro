package caro.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import caro.socket.Client;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;

	public Join() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 301);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Join.class.getResource("/caro/icon/join.png")));

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Join Now");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().equals(""))
						new Client("localhost", Integer.parseInt(textField.getText()));
					else
						new Client(textField_1.getText(), Integer.parseInt(textField.getText()));
					dispose();
				} catch (NumberFormatException e1) {
					new ShowMess("Nhap chu so vao o ID Room");
				} catch (UnknownHostException e1) {
					new ShowMess("Không tìm thấy Server Host");
				} catch (IOException e1) {
					new ShowMess("Không tìm thấy phòng");
				}
			}
		});
		btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Start();
			}
		});
		btnNewButton_1.setForeground(Color.MAGENTA);
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));

		lblNewLabel_1 = new JLabel("ID ROOM");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));

		lblNewLabel_2 = new JLabel("SERVER HOST");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));

		textField_1 = new JTextField("");
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textField_1.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 129,
														GroupLayout.PREFERRED_SIZE)
												.addGap(142))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 171,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(27)
												.addComponent(btnNewButton_1).addGap(27))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(140).addComponent(lblNewLabel,
								GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)))
				.addGap(492)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(17)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
										.addGap(24))
								.addComponent(btnNewButton_1)))
						.addGroup(Alignment.TRAILING,
								gl_contentPane
										.createSequentialGroup().addGap(61).addComponent(btnNewButton,
												GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addGap(24)))));
		contentPane.setLayout(gl_contentPane);
	}
}
