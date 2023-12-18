import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class bubblegame extends JFrame {
	class Mythread extends Thread{
		private JLabel label;
		private int x,y;
		public Mythread(String fname, int x,int y) {
			this.x=x;
			this.y=y;
			label = new JLabel();
			label.setIcon(new ImageIcon(fname));
			label.setBounds(x,y,100,100);
			add(label);
			 
			
		}
		public void run() {
			for (int i=0 ;i<200; i++) {
				y += 100;
				repaint();
			}
			try {
				
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		public bubblegame() {
			setTitle("Cargame");
			setSize(600,200);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(null);
			(new Mythread("bubble.jpg",100,0)).start();
			(new Mythread("bubble.jpg",100,50)).start();
			(new Mythread("bubble.jpg",100,100)).start();
	
			
			
		}
		
		
		
	
	public static void main(String[] args) {
		Cargame t = new Cargame(); 

	}

}