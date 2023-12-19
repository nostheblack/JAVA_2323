
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Cargame2 extends JFrame {

	class MyThread extends Thread{
		private JLabel label;
		private JButton button1,button2;
		private int x,y;
		public MyThread(String fname, int x, int y) {
			this.x=x;
			this.y=y;
			label = new JLabel();
			label.setSize(500,500);
			button1 = new JButton("시작");
			button2 = new JButton("종료");
			
			label.setIcon(new ImageIcon(fname));
			label.setBounds(x,y,100,100);
			label.add(button1);
			label.add(button2);
			add(label);}		
	
		public void run() {
			for (int i =0; i<200;i++) {
				x+= 10*Math.random();
				label.setBounds(x,y,100,100);
				repaint();
				try {
					Thread.sleep(100);
				
				}catch(InterruptedException e) {
					e.printStackTrace();}
			}
		}
	}
	public Cargame2() {
			setTitle("CarRace");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			setLayout(null);
			(new MyThread("bubble.jpg",100,0)).start();
			(new MyThread("bubble.jpg",100,50)).start();
			(new MyThread("bubble.jpg",100,100)).start();
			(new MyThread("bubble.jpg",100,150)).start();
			(new MyThread("bubble.jpg",100,200)).start();
			setVisible(true);

	}
	public static void main(String[] args) {
			Cargame2 t = new Cargame2(); 

	}

}


