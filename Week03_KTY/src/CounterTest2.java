import java.awt.event.*;
import java.math.BigInteger;
import javax.swing.*;

class CounterTest2 extends JFrame {
    JButton button;
    JTextField t1;
    JTextField t2;

    public CounterTest2() {
        setTitle("마일을 킬로미터로 변환");
        setSize(400, 150);
        JPanel p1 = new JPanel();
        JLabel label = new JLabel("거리를 마일 단위로 입력하시오");

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        button = new JButton("변환");
        button.addActionListener(new MyListener());

        p1.add(label);
        p1.add(t1);
        p1.add(button);
        p1.add(t2);
        this.add(p1);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CounterTest2());
    }

    class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                try {
                    double t = Double.parseDouble(t1.getText());
                    double k = t * 1.609344;
                    t2.setText("킬로미터는 " + k + "킬로미터입니다.");
                } catch (NumberFormatException ex) {
                    t2.setText("올바른 숫자를 입력하세요.");
                }
            }
        }
    }
}

