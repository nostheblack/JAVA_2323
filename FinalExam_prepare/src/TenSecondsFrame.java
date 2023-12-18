import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TenSecondsFrame extends JFrame {
    private JPanel panel;
    private JButton startButton;

    public TenSecondsFrame() {
        panel = new JPanel();
        startButton = new JButton("Start");
        panel.setBackground(Color.YELLOW);
        panel.add(startButton);

        add(panel);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);

                // 백그라운드 스레드 시작
                BackgroundThread backgroundThread = new BackgroundThread();
                backgroundThread.start();
            }
        });
    }

    // 백그라운드 스레드 클래스
    class BackgroundThread extends Thread {
        @Override
        public void run() {
            try {
                // 시작 버튼 누른 후 10초 동안 파란색으로 설정
                panel.setBackground(Color.BLUE);
                
                Thread.sleep(10000);

                // 10초 후에 노란색으로 설정하고 시작 버튼 활성화
                panel.setBackground(Color.YELLOW);
                startButton.setText("종료");
                startButton.setEnabled(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
    	TenSecondsFrame example = new TenSecondsFrame();
        example.setVisible(true);
    }
}
