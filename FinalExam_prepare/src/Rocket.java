import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rocket extends JFrame {
    // 내부 클래스로 로켓을 나타내는 RocketThread 정의
    class RocketThread extends Thread {
        private JLabel label;
        private int x, y;

        // 생성자: x, y 좌표를 받아 로켓을 초기화
        public RocketThread(int x, int y) {
            this.x = x;
            this.y = y;
            label = new JLabel();
            label.setIcon(new ImageIcon("bubble.jpg"));
            label.setBounds(x, y, 50, 100);  // 로켓의 크기와 위치 설정
            add(label);  // 프레임에 로켓을 추가
        }

        // run 메서드: 로켓의 움직임을 처리
        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                y -= 5;  // y 좌표를 5씩 감소시켜 로켓을 위로 이동
                label.setLocation(x, y);  // 로켓 위치 업데이트

                try {
                    Thread.sleep(30);  // 30 밀리초 동안 쉬기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // RocketGame 생성자
    public Rocket() {
        setTitle("Rocket Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // 레이아웃 매니저를 null로 설정하여 절대 위치 지정

        // 마우스 클릭 이벤트 처리
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                addRocket(evt.getX(), evt.getY());
            }
        });
    }

    // 로켓을 추가하는 메서드
    private void addRocket(int x, int y) {
        RocketThread rocketThread = new RocketThread(x, y);
        rocketThread.start();  // 로켓 스레드 시작
    }

    // main 메서드: 프로그램 시작점
    public static void main(String[] args) {
        // SwingUtilities.invokeLater를 사용하지 않고 직접 GUI 생성
        Rocket rocketGame = new Rocket();
        rocketGame.setVisible(true);
    }
}
