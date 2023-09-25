import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CarSimulation extends JFrame {
    private int carX = 50; 
    private final int carY = 100;  
    private final int carWidth = 100;  
    private final int carHeight = 50;  
    private final int moveDistance = 10;  
    private String imagePath = "C:\\car\\jeep.png"; 

    public CarSimulation() {
        setTitle("자동차 시뮬레이션");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton moveLeftButton = new JButton("왼쪽으로 이동");
        JButton moveRightButton = new JButton("오른쪽으로 이동");

        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCarLeft();
            }
        });

        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCarRight();
            }
        });

        panel.add(moveLeftButton);
        panel.add(moveRightButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void moveCarLeft() {
        carX -= moveDistance;
        repaint();
    }

    private void moveCarRight() {
        carX += moveDistance;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

       
        try {
            Image carImage = javax.imageio.ImageIO.read(new File(imagePath));
            g.drawImage(carImage, carX, carY, carWidth, carHeight, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CarSimulation();
            }
        });
    }
}
