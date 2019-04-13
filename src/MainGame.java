import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGame extends JFrame {
	
	public MainGame(String title){
		setTitle(title);
		
	}
	
	public static void main (String[] args) {
		MainGame frame = new MainGame("Ping Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		MainPanel mp = new MainPanel();
		panel.add(mp);
		Container c = frame.getContentPane();
		c.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
