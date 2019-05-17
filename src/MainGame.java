import java.awt.*;
import javax.swing.*;

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
		frame.pack();                          //Racchiude i componenti all'interno della finistra
		frame.setLocationRelativeTo(null);     //Apre la finestra al centro dello schermo
		frame.setVisible(true);

	}

}
