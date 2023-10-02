import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;

//정보 클래스

class Person{
	String name;
	String tel;
	String address;
	public String getName() {
		return name;
	}
	public void SetName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}
	public void getTel(String tel) {
		this.tel = tel;
	}


	public String getAdress() {
		return address;
	}
	public void setAdress(String Adress) {
		this.address = address;
	}
	
	public Person(String name, String tel, String address) {
		super();
		this.name= name;
		this.tel = tel;
		this.address = address;	
		
	}
	
}
public class InputManagementSystem extends JFrame {
	ArrayList<Person> list = new ArrayList<>();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_;
	
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
