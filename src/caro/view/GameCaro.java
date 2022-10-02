package caro.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import caro.socket.Message;

public class GameCaro extends JFrame implements ActionListener {
	public GameCaro() {

	}

	public GameCaro(int value, Message msg) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameCaro.class.getResource("/caro/icon/logo.png")));
		setType(Type.POPUP);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(GameCaro.class.getResource("/caro/icon/logo.png")));
		this.msg = msg;
		ngChoi = value;
		b = new JButton[soDong + 1][soCot + 1];
		banCo = new char[soDong + 1][soCot + 1];
		setBounds(new Rectangle(5, 5, 5, 5));
		setTitle("GAME CARO");
		setIgnoreRepaint(true);
		cn = this.getContentPane();
		pn = new JPanel();
		pn.setLayout(new GridLayout(soDong, soCot));
		pn.setBackground(Color.BLACK);
		for (int i = 0; i <= soDong; i++)
			for (int j = 0; j <= soCot; j++) {
				b[i][j] = new JButton(" ");
				b[i][j].setActionCommand(i + " " + j);
				b[i][j].setBackground(Color.white);
				b[i][j].addActionListener(this);
				b[i][j].setFont(new Font("Times New Roman", Font.BOLD, 18));
				if (i != 0 && j != 0)
					pn.add(b[i][j]);
			}
		lb = new JLabel(ngChoi == 0 ? "Bạn đánh trước" : "Đối thủ đánh trước");
		lb.setForeground(new Color(255, 0, 0));
		lb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pn2 = new JPanel();
		pn2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pn2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pn2.setBackground(Color.WHITE);
		cn.add(pn);
		cn.add(pn2, "North");
		btnNewButton = new JButton("New Game");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msg.send("1");
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.setBackground(Color.RED);
		btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setForeground(Color.MAGENTA);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Start();
				msg.send("2");
				dispose();
			}
		});
		GroupLayout gl_pn2 = new GroupLayout(pn2);
		gl_pn2.setHorizontalGroup(gl_pn2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pn2.createSequentialGroup().addGap(400).addComponent(lb).addGap(150)
						.addComponent(btnNewButton).addGap(550).addComponent(btnNewButton_1).addContainerGap()));
		gl_pn2.setVerticalGroup(gl_pn2.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pn2.createSequentialGroup().addGap(5).addGroup(gl_pn2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton).addComponent(btnNewButton_1).addComponent(lb))));
		pn2.setLayout(gl_pn2);
		this.setVisible(true);
		this.setSize(1500, 1080);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		lock = ngChoi == 0 ? false : true;
	}

	public static boolean checkWin(int x, int y, char co) {
		// Kiem tra hang ngang
		for (int i = y - 4 >= 1 ? y - 4 : 1, dem = 0; i <= (y + 4 <= soCot ? y + 4 : soCot); i++)
			if (banCo[x][i] == co) {
				dem++;
				if (dem >= 5)
					return true;
			} else
				dem = 0;
		// Kiem tra hang doc
		for (int i = x - 4 >= 1 ? x - 4 : 1, dem = 0; i <= (x + 4 <= soDong ? x + 4 : soDong); i++)
			if (banCo[i][y] == co) {
				dem++;
				if (dem >= 5)
					return true;
			} else
				dem = 0;
		// Kiem tra hang song song voi duong cheo chinh
		int stX = x - 4, stY = y - 4;
		while (stX < 1 || stY < 1) {
			stX++;
			stY++;
		}
		int edX = x + 4, edY = y + 4;
		while (edX > soDong || edY > soCot) {
			edX--;
			edY--;
		}
		for (int i = stX, j = stY, dem = 0; i <= edX; i++, j++)
			if (banCo[i][j] == co) {
				dem++;
				if (dem >= 5)
					return true;
			} else
				dem = 0;
		// Kiem tra hang song song voi duong cheo phu
		stX = x + 4;
		stY = y - 4;
		while (stX > soDong || stY < 1) {
			stX--;
			stY++;
		}
		edX = x - 4;
		edY = y + 4;
		while (edX < 1 || edY > soCot) {
			edX++;
			edY--;
		}
		for (int i = stX, j = stY, dem = 0; i >= edX; i--, j++)
			if (banCo[i][j] == co) {
				dem++;
				if (dem >= 5)
					return true;
			} else
				dem = 0;
		return false;
	}

	public void addPoint(int x, int y, int value) {
		if (banCo[x][y] == '\u0000') {
			b[x][y].setEnabled(true);
			b[x][y].setForeground(cl[(ngChoi + value) % 2]);
			b[x][y].setText("" + quanCo[(ngChoi + value) % 2]);
			banCo[x][y] = quanCo[(ngChoi + value) % 2];
			count++;
			if (checkWin(x, y, quanCo[(ngChoi + value) % 2])) {
				lb.setBackground(Color.MAGENTA);
				if (value == 0)
					lb.setText("Bạn là người chiến thắng");
				else
					lb.setText("Bạn là người thua cuộc");
				lock = true;
			} else if (count == soDong * soCot) {
				lb.setBackground(Color.MAGENTA);
				lb.setText("HÒA");
				lock = true;
			} else {
				if ((++value) % 2 == 0)
					lb.setText("Lượt của bạn");
				else
					lb.setText("Lượt của đối thủ");
			}
		}
	}

	public void getLock(boolean bl) {
		this.lock = bl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!lock) {
			String s = e.getActionCommand();
			String[] k = s.split(" ");
			int x = Integer.parseInt(k[0]);
			int y = Integer.parseInt(k[1]);
			if (banCo[x][y] == '\u0000') {
				new Thread(() -> msg.send(s)).start();
				lock = true;
				addPoint(x, y, 0);
			}
		}
	}

	int count = 0;
	Message msg;
	boolean lock = false;
	private static final int soDong = 20;
	private static final int soCot = 30;
	private static final char[] quanCo = { 'X', 'O' };
	private static final Color[] cl = { Color.RED, Color.BLUE };
	private static JLabel lb;
	private static JButton b[][];
	private static char[][] banCo;
	public static int ngChoi;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Container cn;
	private JPanel pn, pn2;
}
