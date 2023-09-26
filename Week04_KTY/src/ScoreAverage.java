import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ScoreAverage extends JFrame {
    JButton button1, button2;
    JTextField score, result;
    int totalScore = 0; //총점
    int numberOfScores = 0; //성적입력한 사람의 수

    public ScoreAverage() {
        setSize(600, 200);
        ButtonListener listener = new ButtonListener();

        JPanel panel = new JPanel();//레이블1: 성적
        panel.add(new JLabel("성적 :  "));
        score = new JTextField(20);
        score.addActionListener(listener);
        panel.add(score);

        panel.add(new JLabel("평균 값: ")); // 레이블2: 평균
        result = new JTextField(20);
        result.setEditable(false);//변경 불가
        panel.add(result);

        button1 = new JButton("입력");
        button1.addActionListener(listener);//액션 이벤트를 위한 리스너 생성
        panel.add(button1);

        button2 = new JButton("평균 계산");
        button2.addActionListener(listener);//액션 이벤트를 위한 리스너 생성
        panel.add(button2);

        add(panel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                // "입력" 버튼 클릭 시
                String scoreText = score.getText();
                try {
                    int scoreValue = Integer.parseInt(scoreText);
                    totalScore += scoreValue;
                    numberOfScores++;
                    score.setText(""); // 입력 필드 초기화
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "올바른 숫자를 입력하세요.", "에러", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == button2) {
                // "평균 계산" 버튼 클릭 시
                if (numberOfScores > 0) {
                    double average = (double) totalScore / numberOfScores;
                    result.setText(String.format("%.2f", average)); // 소수점 2자리까지 표시
                } else {
                    JOptionPane.showMessageDialog(null, "성적을 입력하세요.", "에러", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScoreAverage();
            }
        });
    }
}
