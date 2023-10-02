import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.*;

public class CarRepair extends JFrame implements ActionListener {
		JLabel label= new JLabel();
		JCheckBox c1 = new JCheckBox("엔진오일교환");
		JCheckBox c2 = new JCheckBox("타이어수리");
		JCheckBox c3 = new JCheckBox("세차");
		JCheckBox c4 = new JCheckBox("에어컨 필터 교환");
	
		
		public CarRepair() {
			add(c1);
			add(c2);
			add(c3);
			add(c4);
			c1.addActionListener(this);
			c2.addActionListener(this);
			c3.addActionListener(this);
			c4.addActionListener(this);
		}
	
		
	
		public void actionPerformed(ActionEvent e) {

			int amount = 0;
			if(c1.isSelected()){
				amount += 20000;}
			if (c2.isSelected()){
				amount += 18000;}
			if(c3.isSelected()){
				amount += 30000;}
			if(c4.isSelected()){
				amount += 27000;}
			label.setText("총 " + amount + "원 입니다"); // 레이블 업데이트
	    }

			
			
		
		
		
	


	public static void main(String[] args) {
		new CarRepair();
		
	}
		

	
}
