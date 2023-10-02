import java.awt.*;

import javax.swing.*;

public class BusinessCard extends JFrame {
		JPanel panel;
		JLabel label1;
		JLabel label2;
		JLabel label3;
	
	
		
		public BusinessCard() {
			setTitle("Business Card");
			panel = new JPanel();
			label1 = new JLabel("치이카와");
			label2 = new JLabel("홈프로텍터");
			label3 = new JLabel("나가노컴퍼니");
			
			
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			ImageIcon icon = new ImageIcon("C:\\kawa\\치이카와.jpg");
			label1.setIcon(icon);
			add(panel);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

		
	


	public static void main(String[] args) {
		new BusinessCard();
		
	}
		

	
}
